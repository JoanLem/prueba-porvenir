package com.porvenir.prueba.services;

import com.porvenir.prueba.dto.FilmDto;
import com.porvenir.prueba.model.filmRecordsModel;

/**
 * @author Joan Lemus
 * */

public interface StarWarsService {

	public FilmDto getFilm(Byte id) throws Exception;

	public filmRecordsModel updateFilmRecord(Long id, FilmDto data) throws Exception;

	public void deleteFilmRecord(Long id) throws Exception;

}
