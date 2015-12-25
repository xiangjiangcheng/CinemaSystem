package com.cinema.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeSet;

/**
 * FilmType
 * Created by rayn on 2015/12/24.
 */
@Entity
@Table(name = "film_type")
public class FilmType implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column
	private String name;

	@ManyToMany(cascade = CascadeType.ALL, mappedBy = "types")
	private Set<Film> films = new TreeSet<Film>();

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Film> getFilms() {
		return films;
	}

	public void setFilms(Set<Film> films) {
		this.films = films;
	}
}
