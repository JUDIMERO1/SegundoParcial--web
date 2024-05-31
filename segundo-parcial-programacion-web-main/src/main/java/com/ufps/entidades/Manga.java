package com.ufps.entidades;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Manga {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private Date fechaLanzamiento;

    @Column(nullable = false)
    private int temporadas;
    
    @JsonIgnore
    @ManyToMany
    @JoinTable
    	
    	

    @ManyToOne
    @JoinColumn(name = "paisId", nullable = false)
    private Pais pais;

    @ManyToOne
    @JoinColumn(name = "tipoId", nullable = false)
    private Tipo tipo;

    @Column(nullable = false)
    private boolean anime;

    @Column(nullable = false)
    private boolean juego;

    @Column(nullable = false)
    private boolean pelicula;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(Date fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public int getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(int temporadas) {
		this.temporadas = temporadas;
	}

	public Pais getPais() {
		return pais;
	}

	public void setPais(Pais pais) {
		this.pais = pais;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public boolean isAnime() {
		return anime;
	}

	public void setAnime(boolean anime) {
		this.anime = anime;
	}

	public boolean isJuego() {
		return juego;
	}

	public void setJuego(boolean juego) {
		this.juego = juego;
	}

	public boolean isPelicula() {
		return pelicula;
	}

	public void setPelicula(boolean pelicula) {
		this.pelicula = pelicula;
	}

    // Getters and Setters
    
}
