package com.porvenir.prueba;
import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.porvenir.prueba.dto.FilmDto;
import com.porvenir.prueba.model.filmRecordsModel;
import com.porvenir.prueba.repository.FilmRecordRepository;
import com.porvenir.prueba.servicesImp.StarWarsServiceImp;

@RunWith(MockitoJUnitRunner.class)
public class StarWarsServiceImpTest {

    @Mock
    private FilmRecordRepository filmRepository;

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private StarWarsServiceImp starWarsService;

    @Before
    public void setUp() {
        // Configurar restTemplate para evitar errores de null pointer
        starWarsService = new StarWarsServiceImp(new RestTemplateBuilder());
    }

    // covertura al metodo GetFilm
    @Test
    public void testGetFilm_Success() throws Exception {
        String url = "https://swapi.py4e.com/api/films/1";
        FilmDto mockFilmDto = new FilmDto();
        mockFilmDto.setEpisode_id("4");
        mockFilmDto.setRelease_date("2024-12-31");
        mockFilmDto.setTitle("A New Hope");
        ResponseEntity<FilmDto> mockResponse = new ResponseEntity<>(mockFilmDto, HttpStatus.OK);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), eq(FilmDto.class))).thenReturn(mockResponse);

        FilmDto result = starWarsService.getFilm((byte) 1);
        
        assertNotNull(result);
        assertEquals("4", result.getEpisode_id());
        assertEquals("2024-12-31", result.getRelease_date());
        assertEquals("A New Hope", result.getTitle());
        verify(filmRepository).save(any(filmRecordsModel.class));
    }

    @Test()
    public void testGetFilm_ErrorResponse() throws Exception {
        String url = "https://swapi.py4e.com/api/films/1";
        ResponseEntity<FilmDto> mockResponse = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        when(restTemplate.exchange(eq(url), eq(HttpMethod.GET), eq(null), eq(FilmDto.class))).thenReturn(mockResponse);

        starWarsService.getFilm((byte) 1);
    }
    
    @Test
    void testGetFilm_Error() {
        // Arrange
        byte id = 1;
        RestTemplate restTemplate = mock(RestTemplate.class);
        ResponseEntity<FilmDto> responseEntity = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        when(restTemplate.exchange(anyString(), eq(HttpMethod.GET), eq(null), eq(FilmDto.class))).thenReturn(responseEntity);
        // Act & Assert
        assertThrows(Exception.class, () -> starWarsService.getFilm(id));
    }
    
    
    // covertura al metodo UpdateFilmRecord
    
    @Test
    void testUpdateFilmRecord_RecordNotFound() {
        // Arrange
        Long id = 1L;
        FilmDto data = new FilmDto("4", "2024-12-31", "Test Film");
        when(filmRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(Exception.class, () -> starWarsService.updateFilmRecord(id, data));
    }
    
    
    
    
    @Test
    public void testUpdateFilmRecord_Success() throws Exception {
        // Arrange
        Long id = 1L;
        FilmDto data = new FilmDto("4", "2024-12-31", "Test Film");
        when(filmRepository.existsById(id)).thenReturn(true);

        // Act
        filmRecordsModel updatedRecord = starWarsService.updateFilmRecord(id, data);

        // Assert
        assertNotNull(updatedRecord);
        assertEquals(id, updatedRecord.getId());
        assertEquals(data.getEpisode_id(), updatedRecord.getEpisode_id());
        assertEquals(data.getRelease_date(), updatedRecord.getRelease_date());
        assertEquals(data.getTitle(), updatedRecord.getTitle());
    }

    // covertura al metodo DeleteFilmRecord   
    @Test
    void testDeleteFilmRecord_Success() throws Exception {
        // Arrange
        long id = 1L;
        filmRecordsModel filmRecord = new filmRecordsModel();
        filmRecord.setId(id);
        when(filmRepository.existsById(id)).thenReturn(true);

        // Act
        starWarsService.deleteFilmRecord(id);

        // Assert
        verify(filmRepository, times(1)).deleteById(id);
    }

    @Test
    void testDeleteFilmRecord_NotFound() {
        // Arrange
        long id = 1L;
        when(filmRepository.existsById(id)).thenReturn(false);

        // Act & Assert
        assertThrows(Exception.class, () -> starWarsService.deleteFilmRecord(id));
    }
    
}
