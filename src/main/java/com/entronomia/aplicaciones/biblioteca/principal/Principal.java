package com.entronomia.aplicaciones.biblioteca.principal;

import com.entronomia.aplicaciones.biblioteca.dto.DatosAutor;
import com.entronomia.aplicaciones.biblioteca.dto.DatosBiblioteca;
import com.entronomia.aplicaciones.biblioteca.dto.DatosLibro;
import com.entronomia.aplicaciones.biblioteca.modelo.Libro;
import com.entronomia.aplicaciones.biblioteca.repositorio.LibroRepository;
import com.entronomia.aplicaciones.biblioteca.servicio.ConsumoAPI;
import com.entronomia.aplicaciones.biblioteca.servicio.ConversorDatos;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Principal {
    private Scanner entrada = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConversorDatos conversorDatos = new ConversorDatos();
    private String json;
    private DatosLibro datosLibro = null;
    private DatosAutor datosAutor = null;
    private DatosBiblioteca datosBiblioteca = null;
    private final String URL_BASE = "https://gutendex.com/books/";
    List<Libro> libros = new ArrayList<>();
    private LibroRepository libroRepository;
    public Principal() {
    }

    public Principal(LibroRepository libroRepository) {
        this.libroRepository = libroRepository;
    }

    public void mostrarMenu() {
        Integer opcion = -1;
        String menu = """
        **********Menú**********
        1. Buscar libro por título
        2. Listar libros registrados
        3. Listar autores registrados
        4. Listar autores vivos en una determinada fecha
        5. Listar libros por idioma
        0. Salir
        *************************
        """;
        while(opcion != 0) {
            System.out.println(menu);
            System.out.println("Elija una opción por favor: " );
            opcion = Integer.parseInt(entrada.nextLine());
            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    listarLibrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosEnUnaDeterminadaFecha();
                    break;
                case 5:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Proceso terminado");
                    break;
                default:
                    System.out.println("Error en la entrada elegida");
            }
        }
    }

    public void buscarLibroPorTitulo() {
        System.out.println("Escriba el título del libro, cuyos datos está buscando");
        String tituloLibro = entrada.nextLine();
        String url = URL_BASE + "?search=" + tituloLibro.replace(" ","+");
        json = consumoAPI.obtenerDatos(url);
        datosBiblioteca = conversorDatos.obtenerDatos(json, DatosBiblioteca.class);
        List<DatosLibro> datosLibros = datosBiblioteca.libros();
        datosLibro = datosLibros.get(0);
        datosAutor = datosLibro.autores().get(0);
        Libro libro = new Libro(datosLibro, datosAutor);
        System.out.println(libro);
        libroRepository.save(libro);
    }

    public void listarLibrosRegistrados() {
        libros = libroRepository.findAll();
        libros.forEach(System.out::println);
        libros.stream().map(Libro::getTitulo).forEach(System.out::println);
    }

    public void listarAutoresRegistrados() {
        libros = libroRepository.findAll();
        libros.stream().map(Libro::getAutor).forEach(System.out::println);
    }

    public void listarAutoresVivosEnUnaDeterminadaFecha() {
        System.out.println("¿Cuál es el año que desea consultar?: " );
        Integer anno = Integer.parseInt(entrada.nextLine());
        libros = libroRepository.findAll();
        libros.stream()
                .filter(l -> l.getAnnoNacimiento() < anno && l.getAnnoDefuncion() > anno)
                .map(Libro::getAutor)
                .forEach(System.out::println);
    }

    public void listarLibrosPorIdioma() {
        String menu = """
        *************************
        1. ingles(en)
        2. español(es)
        3. frances(fr)
        4. aleman(de)
        5. portugues(pt)
        6. otro idioma
        *************************
        """;
        System.out.println("¿Cuál es el idioma de su elección?");
        System.out.println(menu);
        System.out.println("Elija el número del idioma por favor: " );
        Integer numeroIdioma = Integer.parseInt(entrada.nextLine());
        libros = libroRepository.findAll();
        switch (numeroIdioma) {
            case 1:
                libros.stream().filter(l -> l.getLenguaje().equals("en")).forEach(System.out::println);
                break;
            case 2:
                libros.stream().filter(l -> l.getLenguaje().equals("es")).forEach(System.out::println);
                break;
            case 3:
                libros.stream().filter(l -> l.getLenguaje().equals("fr")).forEach(System.out::println);
                break;
            case 4:
                libros.stream().filter(l -> l.getLenguaje().equals("de")).forEach(System.out::println);;
                break;
            case 5:
                libros.stream().filter(l -> l.getLenguaje().equals("pt")).forEach(System.out::println);;
                break;
            case 6:
                System.out.println("No hay libros disponibles en otros idiomas");
                break;
            default:
                System.out.println("Error en la entrada elegida");
        }
    }
}