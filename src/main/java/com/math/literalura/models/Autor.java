package com.math.literalura.models;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tautores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer nacimiento;
    private Integer fallecimiento;

    // Relación con libros
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Libro> libros = new ArrayList<>(); // Inicialización para evitar NullPointerException

    // Constructor vacío requerido por JPA
    public Autor() {}

    // Constructor personalizado
    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.nacimiento = datosAutor.nacimiento();
        this.fallecimiento = datosAutor.fallecimiento();
    }

    // Getters y Setters
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

    public Integer getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(Integer nacimiento) {
        this.nacimiento = nacimiento;
    }

    public Integer getFallecimiento() {
        return fallecimiento;
    }

    public void setFallecimiento(Integer fallecimiento) {
        this.fallecimiento = fallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        libros.forEach(libro -> libro.setAutor(this)); // Asegura la relación bidireccional
        this.libros = libros;
    }

    // Método para agregar un libro a la lista
    public void agregarLibro(Libro libro) {
        libro.setAutor(this); // Establece la relación con el autor
        this.libros.add(libro);
    }

    // Método toString excluyendo la lista de libros
    @Override
    public String toString() {
        return "Autor{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nacimiento=" + nacimiento +
                ", fallecimiento=" + fallecimiento +
                '}';
    }
}