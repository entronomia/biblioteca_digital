package com.entronomia.aplicaciones.biblioteca.repositorio;

import com.entronomia.aplicaciones.biblioteca.modelo.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepository extends JpaRepository<Libro, Long> {
}
