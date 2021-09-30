package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalendario;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ControladorCalendarioTest {
    private ModelAndView mav;
    private final ServicioCalendario servicioCalendario=mock(ServicioCalendario.class);
    private final ControladorCalendario controladorCalendario=new ControladorCalendario(servicioCalendario);
    private final String PROFESION="Cardiologia";
    private final Calendario CALENDARIO =new Calendario();

    @Test
    public void verPaginaDeInicio(){
        mav= whenIngresoAlInicio();
        thenVeoLaPaginaHome(mav);
    }

    @Test
    public void verTodosLosCalendarios(){
        givenSolicitoLosCalendarios();
        mav=whenIngresoACalendarios();
        thenVeoTodosLosCalendarios(mav);
    }

    private void givenSolicitoLosCalendarios() {
        CALENDARIO.setProfesion(PROFESION);
        ArrayList<Calendario> listaCalendarios=new ArrayList<>();
        listaCalendarios.add(CALENDARIO);
        when(servicioCalendario.obtenerCalendarios()).thenReturn(listaCalendarios);
    }

    @Test
    public void recibirUnaProfesion() throws Exception{
        mav=whenRecibeUnaProfesion(PROFESION);
        thenVerificarQueSeRecibe(mav);
    }

    @Test
    public void verUnCalendarioDeUnaProfesionEspecifica() throws Exception{
        givenSolicitoUnSoloCalendario();
        mav= whenRecibeUnaProfesion(PROFESION);
        thenSeMuestraElCalendarioEspecifico(mav);
    }

    @Test
    public void errorDeConexionALaBD() throws Exception{
        givenOcurreUnErrorEnLaBD();
        mav=whenRecibeUnaProfesion(PROFESION);
        thenOcurreElErrorEnLaBD(mav);
    }

    private void thenOcurreElErrorEnLaBD(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("error");
    }

    private void givenOcurreUnErrorEnLaBD() throws Exception {
        when(servicioCalendario.obtenerUnCalendarioEspecifico(PROFESION)).thenThrow(Exception.class);
    }

    private void givenSolicitoUnSoloCalendario() throws Exception{
        CALENDARIO.setProfesion(PROFESION);
        when(servicioCalendario.obtenerUnCalendarioEspecifico(PROFESION)).thenReturn(CALENDARIO);
    }

    private ModelAndView whenIngresoAlInicio() {
        return controladorCalendario.irAHome();
    }

    private void thenVeoLaPaginaHome(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("home");
    }

    private ModelAndView whenIngresoACalendarios() {
        return controladorCalendario.verTodosLosCalendarios();
    }

    private void thenVeoTodosLosCalendarios(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("calendarios");
        CALENDARIO.setProfesion(PROFESION);
        ArrayList<Calendario> profesiones= (ArrayList<Calendario>) mav.getModel().get("calendarios");
        assertThat(profesiones).contains(CALENDARIO);
    }

    private void thenVerificarQueSeRecibe(ModelAndView mav) {
        assertThat(mav.getModel().get("titulo")).isEqualTo(PROFESION);
    }

    private ModelAndView whenRecibeUnaProfesion(String profesion) throws Exception {
        return controladorCalendario.recibirUnaProfesion(profesion);
    }

    private void thenSeMuestraElCalendarioEspecifico(ModelAndView mav) {
        assertThat(mav.getViewName()).isEqualTo("calendarios");
        assertThat(mav.getModel().get("titulo")).isEqualTo(PROFESION);
        assertThat(mav.getModel().get("calendario")).isEqualTo(CALENDARIO);
    }
}
