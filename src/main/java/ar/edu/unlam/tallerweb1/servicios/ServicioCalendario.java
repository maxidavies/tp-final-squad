package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;

import java.util.ArrayList;

public interface ServicioCalendario {
    ArrayList<Calendario> obtenerCalendarios();

    Calendario obtenerUnCalendarioEspecifico(String profesion) throws Exception;
}
