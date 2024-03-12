package com.porvenir.prueba.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.porvenir.prueba.dto.FilmDto;
import com.porvenir.prueba.model.filmRecordsModel;
import com.porvenir.prueba.servicesImp.StarWarsServiceImp;

import lombok.extern.slf4j.Slf4j;


/**
 * Prueba creacion se servicio web para Porvenir
 * @author Joan Lemus
 * */
@RestController
@RequestMapping("/api/starwars")
@Slf4j
public class StarWarsController {

	@Autowired
	private StarWarsServiceImp starWarsService;

	/*
	 * Metodo encagado de consuiltar el api se StarWars y almacenar en DDBB dado una respuesta exitosa.
	 * @param id es el id identificador en el registro unico de la base de datos
	 * @return Map<>  Objeto Registrado en DDBB .
	 * */
	@GetMapping("/film/{id}")
	public ResponseEntity<Map<String, ?>> getFilmById(@PathVariable Byte id) {
		Map<String, Object> responseData = new HashMap<>();
		HttpStatus status = null;
		FilmDto response = null;
		try {
			response = starWarsService.getFilm(id);
			responseData.put("Data", response);
			status = HttpStatus.OK;
		} catch (Exception e) {
			log.info(e.getLocalizedMessage());
			responseData.put("mensaje", e.getLocalizedMessage());
			status = HttpStatus.NO_CONTENT; // 204
		}
		return new ResponseEntity<>(responseData, status);
	}
	
	/*
	 * Metodo encagado de Actualizar un filmRecord por id;
	 * @param id es el id identificador en el registro unico de la base de datos
	 * @param data es un objeto de tipo FilmDto que se basa en el modelo de filmrecordsmodel pero que busca 
	 * estandarizar el request del metodo updateFilmRecord. 
	 * @return filmRecordsModel  Objeto Actualizado.
	 * */

	@PutMapping("/filmrecords/{id}")
	public ResponseEntity<Map<String, ?>> updateFilmRecord(@RequestBody FilmDto data, @PathVariable Long id) {
		Map<String, Object> responseData = new HashMap<>();
		HttpStatus status = null;
		filmRecordsModel response = null;
		try {
			response = starWarsService.updateFilmRecord(id, data);
			responseData.put("Data updated", response);
			status = HttpStatus.OK; //
		} catch (Exception e) {
			responseData.put("mensaje", e.getLocalizedMessage());
			status = HttpStatus.NOT_FOUND; //
		}
		return new ResponseEntity<>(responseData, status);

	}
	/*
	 * Metodo encagado de eliminar un filmRecord por id;
	 * @param id es el id identificador en el registro unico de la base de datos
	 * @return Void , se setea mensaje de exito si el registro fue eliminado correctamente.
	 * */
	@DeleteMapping("/filmrecords/{id}")
	public ResponseEntity<Map<String, ?>> deleteFilmRecord(@PathVariable Long id) {
		Map<String, Object> responseData = new HashMap<>();
		HttpStatus status = null;
		try {
			starWarsService.deleteFilmRecord(id);
			responseData.put("mensaje", "FilmRecors " + id + " Eliminado Correctamente!");
			status = HttpStatus.OK; //
		} catch (Exception e) {
			responseData.put("mensaje", e.getLocalizedMessage());
			status = HttpStatus.NOT_FOUND; //
		}
		return new ResponseEntity<>(responseData, status);

	}

}
