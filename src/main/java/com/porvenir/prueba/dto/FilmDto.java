package com.porvenir.prueba.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FilmDto {
	private String title;
	private String episode_id;
	private String release_date;
}
