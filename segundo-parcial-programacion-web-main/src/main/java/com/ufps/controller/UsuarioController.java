package com.ufps.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufps.entidades.Manga;

import co.edu.ufps.entities.Usuario;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MangaRepository mangaRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @GetMapping("/{username}/favoritos")
    public ResponseEntity<List<Manga>> getFavoritos(@PathVariable String username) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        List<Manga> favoritos = favoritoRepository.findAll().stream()
                .filter(favorito -> favorito.getUsuario().getId().equals(usuario.getId()))
                .map(Favorito::getManga)
                .collect(Collectors.toList());
        return ResponseEntity.ok().body(favoritos);
    }

    @PostMapping("/{username}/favoritos")
    public ResponseEntity<Favorito> addFavorito(@PathVariable String username, @RequestBody Favorito favorito) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null || !mangaRepository.existsById(favorito.getId().getMangaId())) {
            return ResponseEntity.badRequest().build();
        }
        favorito.setUsuario(usuario);
        Favorito newFavorito = favoritoRepository.save(favorito);
        return ResponseEntity.ok().body(newFavorito);
    }

    @DeleteMapping("/{username}/favoritos/{mangaId}")
    public ResponseEntity<Void> deleteFavorito(@PathVariable String username, @PathVariable Long mangaId) {
        Usuario usuario = usuarioRepository.findByUsername(username);
        if (usuario == null) {
            return ResponseEntity.notFound().build();
        }
        Favorito favorito = favoritoRepository.findById(new FavoritoId(usuario.getId(), mangaId)).orElse(null);
        if (favorito == null) {
            return ResponseEntity.notFound().build();
        }
        favoritoRepository.delete(favorito);
        return ResponseEntity.noContent().build();
    }
}
	      


	    
