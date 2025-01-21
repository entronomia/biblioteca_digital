package com.entronomia.aplicaciones.biblioteca.servicio;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConversorDatos implements IConversorDatos{

    private ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public <T> T obtenerDatos(String json, Class<T> nombreClase) {
        try {
            return objectMapper.readValue(json, nombreClase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
