package com.entronomia.aplicaciones.biblioteca.dto;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosBiblioteca(
        @JsonAlias("count") Integer cantidadDeLibros,
        @JsonAlias("results") List<DatosLibro> libros
) {
}
