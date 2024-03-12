package com.porvenir.prueba.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.porvenir.prueba.model.filmRecordsModel;

public interface FilmRecordRepository extends JpaRepository<filmRecordsModel, Long> {

}
