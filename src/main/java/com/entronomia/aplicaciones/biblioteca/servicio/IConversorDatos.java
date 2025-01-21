package com.entronomia.aplicaciones.biblioteca.servicio;

public interface IConversorDatos {
    <T> T obtenerDatos(String json, Class<T> nombreClase );
}
