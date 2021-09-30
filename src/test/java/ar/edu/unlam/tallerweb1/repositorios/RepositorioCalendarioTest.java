package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.SpringTest;
import ar.edu.unlam.tallerweb1.modelo.Calendario;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RepositorioCalendarioTest extends SpringTest {
    @Autowired
    private RepositorioCalendario repositorioCalendario;
    private Calendario cardiologia=new Calendario();
    private Calendario odontologia=new Calendario();

    @Rollback
    @Transactional
    @Test
    public void solicitoTodosLosCalendarios(){
        giverListaDeCalendarios();
        List<Calendario> calendariosTest=whenSolicitoLosCalendarios();
        thenDeberiaTenerTodosLosCalendarios(calendariosTest);
    }

    @Rollback
    @Transactional
    @Test
    public void solicitoUnCalendarioEspecifico(){
        giverListaDeCalendarios();
        String profesion="Cardiologia";
        Calendario calendario=whenSolicitoUnsoloCalendario(profesion);
        thenDeberiaRetornarElCalendarioSolicitado(calendario, profesion);
    }

    private void thenDeberiaRetornarElCalendarioSolicitado(Calendario calendario, String profesion) {
        assertThat(calendario.getProfesion()).isEqualTo(profesion);
    }

    private Calendario whenSolicitoUnsoloCalendario(String profesion) {
        return repositorioCalendario.unCalendarioEspecifico(profesion);
    }

    private void giverListaDeCalendarios() {
        cardiologia.setProfesion("Cardiologia");
        odontologia.setProfesion("Odontologia");
        session().save(cardiologia);
        session().save(odontologia);
    }

    private List<Calendario> whenSolicitoLosCalendarios() {
        return repositorioCalendario.todosLosCalendarios();
    }

    private void thenDeberiaTenerTodosLosCalendarios(List<Calendario> calendariosTest) {
        assertThat(calendariosTest).contains(cardiologia);
        assertThat(calendariosTest).contains(odontologia);
    }
}
