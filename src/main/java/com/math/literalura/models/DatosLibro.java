package com.math.literalura.models;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("download_count") Integer numeroDeDescargas,
        @JsonAlias("languages") List<String> idiomas,
        @JsonAlias("bookshelves") List<String> genero
        //@JsonAlias("name") String autor
    ){
}
