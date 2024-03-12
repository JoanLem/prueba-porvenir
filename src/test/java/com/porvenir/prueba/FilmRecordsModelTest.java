package com.porvenir.prueba;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.porvenir.prueba.model.filmRecordsModel;

public class FilmRecordsModelTest {

    @Test
    void testConstructorAndGetters() {
        // Arrange
        Long id = 1L;
        String title = "Test Title";
        String episodeId = "1";
        String releaseDate = "2023-01-01";

        // Act
        filmRecordsModel filmRecord = new filmRecordsModel(id, title, episodeId, releaseDate);

        // Assert
        assertEquals(id, filmRecord.getId());
        assertEquals(title, filmRecord.getTitle());
        assertEquals(episodeId, filmRecord.getEpisode_id());
        assertEquals(releaseDate, filmRecord.getRelease_date());
    }

    @Test
    void testSetters() {
        // Arrange
        filmRecordsModel filmRecord = new filmRecordsModel();
        Long id = 1L;
        String title = "Test Title";
        String episodeId = "1";
        String releaseDate = "2023-01-01";

        // Act
        filmRecord.setId(id);
        filmRecord.setTitle(title);
        filmRecord.setEpisode_id(episodeId);
        filmRecord.setRelease_date(releaseDate);

        // Assert
        assertEquals(id, filmRecord.getId());
        assertEquals(title, filmRecord.getTitle());
        assertEquals(episodeId, filmRecord.getEpisode_id());
        assertEquals(releaseDate, filmRecord.getRelease_date());
    }
    
    void testHashCode() {
        // Arrange
        Long id = 1L;
        String title = "Test Title";
        String episodeId = "1";
        String releaseDate = "2023-01-01";

        filmRecordsModel filmRecord1 = new filmRecordsModel(id, title, episodeId, releaseDate);
        filmRecordsModel filmRecord2 = new filmRecordsModel(id, title, episodeId, releaseDate);

        // Act
        int hashCode1 = filmRecord1.hashCode();
        int hashCode2 = filmRecord2.hashCode();

        // Assert
        assertEquals(hashCode1, hashCode2);
    }
    
    @Test
    void testEquals_SameInstance() {
        // Arrange
        filmRecordsModel filmRecord = new filmRecordsModel(1L, "Test Title", "1", "2023-01-01");

        // Act & Assert
        assertTrue(filmRecord.equals(filmRecord));
    }

    @Test
    void testEquals_Null() {
        // Arrange
        filmRecordsModel filmRecord = new filmRecordsModel(1L, "Test Title", "1", "2023-01-01");

        // Act & Assert
        assertFalse(filmRecord.equals(null));
    }

    @Test
    void testEquals_DifferentClass() {
        // Arrange
        filmRecordsModel filmRecord = new filmRecordsModel(1L, "Test Title", "1", "2023-01-01");

        // Act & Assert
        assertFalse(filmRecord.equals("Not a filmRecordModel"));
    }

    @Test
    void testEquals_SameValues() {
        // Arrange
        filmRecordsModel filmRecord1 = new filmRecordsModel(1L, "Test Title", "1", "2023-01-01");
        filmRecordsModel filmRecord2 = new filmRecordsModel(1L, "Test Title", "1", "2023-01-01");

        // Act & Assert
        assertTrue(filmRecord1.equals(filmRecord2));
        assertTrue(filmRecord2.equals(filmRecord1));
    }

    @Test
    void testEquals_DifferentValues() {
        // Arrange
        filmRecordsModel filmRecord1 = new filmRecordsModel(1L, "Test Title 1", "1", "2023-01-01");
        filmRecordsModel filmRecord2 = new filmRecordsModel(2L, "Test Title 2", "2", "2024-01-01");

        // Act & Assert
        assertFalse(filmRecord1.equals(filmRecord2));
        assertFalse(filmRecord2.equals(filmRecord1));
    }
}
