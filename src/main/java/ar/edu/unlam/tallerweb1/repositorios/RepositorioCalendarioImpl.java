package ar.edu.unlam.tallerweb1.repositorios;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository("repositorioCalendario")
public class RepositorioCalendarioImpl implements RepositorioCalendario{
    private SessionFactory sessionFactory;

    @Autowired
    public RepositorioCalendarioImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public ArrayList<Calendario> todosLosCalendarios() {
        return (ArrayList<Calendario>) getCurrentSession().createCriteria(Calendario.class).list();
    }

    @Override
    public Calendario unCalendarioEspecifico(String profesion) {
        return (Calendario) getCurrentSession().createCriteria(Calendario.class).add(Restrictions.eq("profesion",profesion)).uniqueResult();
    }

    private Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }
}
