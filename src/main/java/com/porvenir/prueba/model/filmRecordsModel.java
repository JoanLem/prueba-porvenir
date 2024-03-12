package com.porvenir.prueba.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "filmRecords")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class filmRecordsModel implements Serializable {

	/**
	* 
	*/
	private static final long serialVersionUID = 1L;
	/**
	 */

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String title;
	
	@Column
	private String episode_id;
	
	@Column
	private String release_date;

}
