package com.porvenir.prueba;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.porvenir.prueba.dto.FilmDto;

public class FilmDtoTest {

    @Test
    void testFilmDtoConstructor() {
        // Arrange
        String title = "A New Hope";
        String episodeId = "4";
        String releaseDate = "1977-05-25";
        
        // Act
        FilmDto filmDto = new FilmDto(title, episodeId, releaseDate);
        
        // Assert
        assertEquals(title, filmDto.getTitle());
        assertEquals(episodeId, filmDto.getEpisode_id());
        assertEquals(releaseDate, filmDto.getRelease_date());
    }
    
    @Test
    void testFilmDtoSettersAndGetters() {
        // Arrange
        FilmDto filmDto = new FilmDto();
        String title = "The Empire Strikes Back";
        String episodeId = "5";
        String releaseDate = "1980-05-21";
        
        // Act
        filmDto.setTitle(title);
        filmDto.setEpisode_id(episodeId);
        filmDto.setRelease_date(releaseDate);
        
        // Assert
        assertEquals(title, filmDto.getTitle());
        assertEquals(episodeId, filmDto.getEpisode_id());
        assertEquals(releaseDate, filmDto.getRelease_date());
    }
}
