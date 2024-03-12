package com.porvenir.prueba;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.porvenir.prueba.controller.StarWarsController;
import com.porvenir.prueba.dto.FilmDto;
import com.porvenir.prueba.model.filmRecordsModel;
import com.porvenir.prueba.servicesImp.StarWarsServiceImp;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
class StarWarsControllerTest {

	@Mock
	private StarWarsServiceImp starWarsService;

	@InjectMocks
	private StarWarsController starWarsController;

	@Test
	void testGetFilmById_Success() throws Exception {
		// Arrange
		byte id = 1;
		FilmDto filmDto = new FilmDto();
		// filmDto.setEpisode_id(id);
		when(starWarsService.getFilm(id)).thenReturn(filmDto);

		// Act
		ResponseEntity<Map<String, ?>> responseEntity = starWarsController.getFilmById(id);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(filmDto, responseEntity.getBody().get("Data"));
	}

	@Test
	void testGetFilmById_Error() throws Exception {
		// Arrange
		byte id = 1;
		when(starWarsService.getFilm(id)).thenThrow(new Exception("Error"));

		// Act
		ResponseEntity<Map<String, ?>> responseEntity = starWarsController.getFilmById(id);

		// Assert
		assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
		assertEquals("Error", responseEntity.getBody().get("mensaje"));
	}

	@Test
	void testUpdateFilmRecord_Success() throws Exception {
		// Arrange
		long id = 1;
		FilmDto filmDto = new FilmDto();
		// filmDto.setId(id);
		filmDto.setEpisode_id("4");
		filmDto.setRelease_date("2024-12-31");
		filmDto.setTitle("Test Film");

		filmRecordsModel filmRecord = new filmRecordsModel();
		filmRecord.setId(id);
		when(starWarsService.updateFilmRecord(id, filmDto)).thenReturn(filmRecord);

		// Act
		ResponseEntity<Map<String, ?>> responseEntity = starWarsController.updateFilmRecord(filmDto, id);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(filmRecord, responseEntity.getBody().get("Data updated"));
	}

	@Test
	void testUpdateFilmRecord_Error() throws Exception {
		// Arrange
		long id = 1;
		FilmDto filmDto = new FilmDto();
		when(starWarsService.updateFilmRecord(id, filmDto)).thenThrow(new Exception("Error"));

		// Act
		ResponseEntity<Map<String, ?>> responseEntity = starWarsController.updateFilmRecord(filmDto, id);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals("Error", responseEntity.getBody().get("mensaje"));
	}

	@Test
	void testDeleteFilmRecord_Success() throws Exception {
		// Arrange
		long id = 1;
		Map<String, Object> expectedResponse = new HashMap<>();
		expectedResponse.put("mensaje", "FilmRecors " + id + " Eliminado Correctamente!");

		// Act
		ResponseEntity<Map<String, ?>> responseEntity = starWarsController.deleteFilmRecord(id);

		// Assert
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(expectedResponse, responseEntity.getBody());
	}

	@Test
	void testDeleteFilmRecord_Error() throws Exception {
		// Arrange
		long id = 1;
		doThrow(new Exception("Error")).when(starWarsService).deleteFilmRecord(id);
		// Act
		ResponseEntity<Map<String, ?>> responseEntity = starWarsController.deleteFilmRecord(id);

		// Assert
		assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
		assertEquals("Error", responseEntity.getBody().get("mensaje"));
	}
}
