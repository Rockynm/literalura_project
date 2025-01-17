package com.math.literalura.models;
import jakarta.persistence.*;

import java.util.List;

@Entity //Establece que esta es una entidad
@Table(name = "tlibros")  //Sirve para renombrar el nombre de la tabla
public class Libro {
    @Id    //Establece que este valor será un ID
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //Establece la estrategia al momento de generar una ID, en este caso será determinado por la entidad
    private Long Id;

    @Column(unique = true)  //IMPORTANTE, Establece que los valores título en la columna sean únicos, osea, no se repitan
    private String titulo;
    private Integer numeroDeDescargas;
    private String idiomas;

    @Enumerated(EnumType.STRING)  //Informa que la variable debajo es un enum y el cómo se deben traer, STRING agarra el texto y ORDINAL funciona como un índice
    private Generos genero;

    // @Transient //--- Solo para aclarar, es útil para informar que el valor existe, pero que estará vacío por ahora
    //Constructor
    public Libro(DatosLibro datosLibro){
        this.titulo = datosLibro.titulo();
        this.numeroDeDescargas = datosLibro.numeroDeDescargas();
        List<String> operationIdiomas = datosLibro.idiomas();
        String operatinIdioma = operationIdiomas.get(0);
        this.idiomas = operatinIdioma;

        //Convertidor de Lista a String
        List<String> operation = datosLibro.genero();
        String operatin = operation.get(0);
        this.genero = Generos.fromString(operatin);

    }
    public Libro(){}

    //Conector Habilitado
    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;


    public Autor getAutor() {
        return autor;
    }
    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public Long getId() {
        return Id;
    }
    public void setId(Long id) {
        Id = id;
    }
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public Integer getNumeroDeDescargas() {
        return numeroDeDescargas;
    }
    public void setNumeroDeDescargas(Integer numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }
    public String getIdiomas() {
        return idiomas;
    }
    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }
    public Generos getGenero() {
        return genero;
    }
    public void setGenero(Generos genero) {
        this.genero = genero;
    }



    //To string modificado
    @Override
    public String toString() {
        return "Libro{" +
                "Id=" + Id +
                ", titulo='" + titulo + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas +
                ", idiomas=" + idiomas +
                ", genero=" + genero +
                '}';
    }
}
