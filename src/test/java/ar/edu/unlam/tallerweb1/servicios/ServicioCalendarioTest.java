package ar.edu.unlam.tallerweb1.servicios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import ar.edu.unlam.tallerweb1.repositorios.RepositorioCalendario;
import org.junit.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;

public class ServicioCalendarioTest {
    private static final String PROFESION = "Cardiologia";
    private RepositorioCalendario repositorioCalendario=mock(RepositorioCalendario.class);
    private ServicioCalendario servicioCalendario=new ServicioCalendarioImpl(repositorioCalendario);
    private Calendario calendario =new Calendario();

    @Test
    public void obtenerTodosLosCalendarios(){
        givenSolicitoTodosLosCalendarios();
        ArrayList<Calendario> calendarios= whenHagoLaSolicitud();
        thenDeberiaTenerTodosLosCalendarios(calendarios);
    }

    @Test
    public void obtenerUnCalendarioEspecifico(){
        givenSolicitoUnCalendarioEspecifico();
        Calendario calendarioTest=whenHagoLaSolicitudDeUnSoloCalendario();
        thenDeberiaCorresponderAlCalendarioElegido(calendarioTest);
    }

    @Test(expected = Exception.class)
    public void errorDeConexionALaBaseDeDatos(){
        givenOcurreUnProblemaDeConexionALaBD();
        Calendario calendarioTest=whenHagoLaSolicitudDeUnSoloCalendario();
        thenApareceriaLaExcepcion(calendarioTest);
    }

    private void thenApareceriaLaExcepcion(Calendario calendarioTest) {
        verify(repositorioCalendario, never()).unCalendarioEspecifico(PROFESION);
    }

    private void givenOcurreUnProblemaDeConexionALaBD() {
        when(repositorioCalendario.unCalendarioEspecifico(PROFESION)).thenThrow(Exception.class);
    }

    private void givenSolicitoUnCalendarioEspecifico() {
        calendario.setProfesion(PROFESION);
        when(repositorioCalendario.unCalendarioEspecifico(PROFESION)).thenReturn(calendario);
    }

    private Calendario whenHagoLaSolicitudDeUnSoloCalendario() {
        return repositorioCalendario.unCalendarioEspecifico(PROFESION);
    }

    private void thenDeberiaCorresponderAlCalendarioElegido(Calendario calendarioTest) {
        assertThat(calendarioTest).isEqualTo(calendario);
    }

    private void givenSolicitoTodosLosCalendarios() {
        calendario.setProfesion(PROFESION);
        when(repositorioCalendario.todosLosCalendarios()).thenReturn(new ArrayList<>());
    }

    private ArrayList<Calendario> whenHagoLaSolicitud() {
        return servicioCalendario.obtenerCalendarios();
    }

    private void thenDeberiaTenerTodosLosCalendarios(ArrayList<Calendario> calendarios) {
        assertThat(calendarios.contains(calendario));
    }
}
