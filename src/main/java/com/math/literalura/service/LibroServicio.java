package com.math.literalura.service;

import com.math.literalura.models.Libro;
import com.math.literalura.repository.LibroRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServicio {

    /*@Autowired
    private LibroRepositorio libroRepositorio;

    public List<Libro> listarLibros(){
        return libroRepositorio.findAll;
    }

    public List<Libro> listarLibrosPorIdioma(String idiomas){
        return libroRepositorio.findByIdioma(idiomas);
    }

    public Libro crearLibro(Libro libro){
        return libroRepositorio.save(libro);
    }

    public Optional<Libro> obtenerLibroPorId(Long id){
        return libroRepository.findById(id);
    }

    public Optional<Libro> obtenerLibroPorTitulo(String titulo){
        return libroRepositorio.findByTituloIgnoreCase(titulo);
    }*/
}
