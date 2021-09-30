package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;

import java.util.ArrayList;

public interface RepositorioCalendario {
    ArrayList<Calendario> todosLosCalendarios();

    Calendario unCalendarioEspecifico(String profesion);
}
