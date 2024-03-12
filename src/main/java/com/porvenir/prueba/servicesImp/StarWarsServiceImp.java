package com.porvenir.prueba.servicesImp;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.porvenir.prueba.dto.FilmDto;
import com.porvenir.prueba.model.filmRecordsModel;
import com.porvenir.prueba.repository.FilmRecordRepository;
import com.porvenir.prueba.services.StarWarsService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class StarWarsServiceImp implements StarWarsService {

	@Autowired
	private FilmRecordRepository filmRepository;

	private final RestTemplate restTemplate;

	public StarWarsServiceImp(RestTemplateBuilder restTemplateBuilder) {
		this.restTemplate = restTemplateBuilder.build();
	}

	/*
	 * Metodo encargado del consumo del API de Start wars y 
	 * persiste el registro en DDBB si la respuesta es exitosa. 
	 * 
	 */
	@Override
	public FilmDto getFilm(Byte id) throws Exception {
		String url = "https://swapi.py4e.com/api/films/" + id;
		log.info("Ejecutando GetFilm desde Service");
		ResponseEntity<FilmDto> response = restTemplate.exchange(url, HttpMethod.GET, null, FilmDto.class);
		if (response.getStatusCode().value() != 200) {
			throw new Exception("Error en la solicitud");
		}
			filmRecordsModel record = new filmRecordsModel();
			record.setEpisode_id(response.getBody().getEpisode_id());
			record.setTitle(response.getBody().getTitle());
			record.setRelease_date(response.getBody().getRelease_date());
			filmRepository.save(record);
		log.info("Finalziando ejecucion de GetFilm desde Service");
		return response.getBody();

	}
	/*
	 * Metodo para actualizar registro de la tabla Film_Record
	 * */
	@Override
	public filmRecordsModel updateFilmRecord(Long id, FilmDto data) throws Exception {
		log.info("Ejecutando updateFilmRecord desde Service");
		if(!filmRepository.existsById(id)) {
	        throw new Exception("No se encontró el registro con ID: " + id);
	    }
			filmRecordsModel dtu = new filmRecordsModel(); // dtu = data To Update
			dtu.setId(id);
			dtu.setEpisode_id(data.getEpisode_id());
			dtu.setRelease_date(data.getRelease_date());
			dtu.setTitle(data.getTitle());
			log.info("Finalziando ejecucion updateFilmRecord desde Service");
			return filmRepository.save(dtu);
	}
	
	@Override
	public void deleteFilmRecord(Long id) throws Exception {
		log.info("Ejecutando deleteFilmRecord desde Service");
			if(!filmRepository.existsById(id)) {
				throw new Exception("No se encontró el registro con ID: " + id);
			}
			log.info("Finalziando ejecucion deleteFilmRecord desde Service");
				filmRepository.deleteById(id);					
	}

}
