package com.entronomia.aplicaciones.biblioteca.modelo;

import com.entronomia.aplicaciones.biblioteca.dto.DatosAutor;
import com.entronomia.aplicaciones.biblioteca.dto.DatosLibro;
import jakarta.persistence.*;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer numeroID;
    @Column(unique = true)
    private String titulo;
    private String autor;
    private Integer annoNacimiento;
    private Integer annoDefuncion;
    private Integer descargas;
    private String lenguaje;

    public Libro() {}

    public Libro(DatosLibro l, DatosAutor a) {
        this.numeroID = l.id();
        this.titulo = l.titulo();
        this.autor = a.nombre();
        this.annoNacimiento = Integer.parseInt(a.annoDeNacimiento());
        this.annoDefuncion = Integer.parseInt(a.annoDeDeceso());
        this.descargas = l.descargas();
        this.lenguaje = l.lenguajes().get(0);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroID() {
        return numeroID;
    }

    public void setNumeroID(Integer numeroID) {
        this.numeroID = numeroID;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public Integer getAnnoNacimiento() {
        return annoNacimiento;
    }

    public void setAnnoNacimiento(Integer annoNacimiento) {
        this.annoNacimiento = annoNacimiento;
    }

    public Integer getAnnoDefuncion() {
        return annoDefuncion;
    }

    public void setAnnoDefuncion(Integer annoDefuncion) {
        this.annoDefuncion = annoDefuncion;
    }

    public Integer getDescargas() {
        return descargas;
    }

    public void setDescargas(Integer descargas) {
        this.descargas = descargas;
    }

    public String getLenguaje() {
        return lenguaje;
    }

    public void setLenguaje(String lenguaje) {
        this.lenguaje = lenguaje;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "numeroID=" + numeroID +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", annoNacimiento=" + annoNacimiento +
                ", annoDefuncion=" + annoDefuncion +
                ", descargas=" + descargas +
                ", lenguaje='" + lenguaje + '\'' +
                '}';
    }
}


