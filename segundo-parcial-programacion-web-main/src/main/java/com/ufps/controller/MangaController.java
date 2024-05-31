package com.ufps.controller;

import com.ufps.entidades.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/mangas")
public class MangaController {

    @Autowired
    private MangaRepository mangaRepository;

    @GetMapping
    public List<Manga> getAllMangas() {
        return mangaRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Manga> getMangaById(@PathVariable Long id) {
        return mangaRepository.findById(id)
                .map(manga -> ResponseEntity.ok().body(manga))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Manga createManga(@RequestBody Manga manga) {
        return mangaRepository.save(manga);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Manga> updateManga(@PathVariable Long id, @RequestBody Manga mangaDetails) {
        return mangaRepository.findById(id)
                .map(manga -> {
                    manga.setNombre(mangaDetails.getNombre());
                    manga.setFechaLanzamiento(mangaDetails.getFechaLanzamiento());
                    manga.setTemporadas(mangaDetails.getTemporadas());
                    manga.setPais(mangaDetails.getPais());
                    manga.setTipo(mangaDetails.getTipo());
                    manga.setAnime(mangaDetails.isAnime());
                    manga.setJuego(mangaDetails.isJuego());
                    manga.setPelicula(mangaDetails.isPelicula());
                    Manga updatedManga = mangaRepository.save(manga);
                    return ResponseEntity.ok().body(updatedManga);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Manga> deleteManga(@PathVariable Long id) {
        return mangaRepository.findById(id)
                .map(manga -> {
                    mangaRepository.delete(manga);
                    return ResponseEntity.ok().body(manga);
                }).orElse(ResponseEntity.notFound().build());
    }
