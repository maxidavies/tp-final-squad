package ar.edu.unlam.tallerweb1.controladores;

import ar.edu.unlam.tallerweb1.modelo.Calendario;
import ar.edu.unlam.tallerweb1.servicios.ServicioCalendario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ControladorCalendario {
    private ServicioCalendario servicioCalendario;

    @Autowired
    public ControladorCalendario(ServicioCalendario servicioCalendario){
        this.servicioCalendario=servicioCalendario;
    }

    @RequestMapping(path = "/", method = RequestMethod.GET)
    public ModelAndView inicio() {
        return new ModelAndView("redirect:/home");
    }

    @RequestMapping("/home")
    public ModelAndView irAHome() {
        return new ModelAndView("home");
    }

    @RequestMapping(path = "/calendarios", method = RequestMethod.GET)
    public ModelAndView verTodosLosCalendarios() {
        ModelMap model=new ModelMap();
        String titulo="Todos";
        ArrayList<Calendario> calendarios=servicioCalendario.obtenerCalendarios();
        model.put("calendarios",calendarios);
        model.put("titulo", titulo);
        return new ModelAndView("calendarios", model);
    }

    @RequestMapping(path = "/calendarios", method = RequestMethod.POST)
    public ModelAndView recibirUnaProfesion(@RequestParam(required = false) String profesion) throws Exception {
        try {
            ModelMap model=new ModelMap();
            String titulo = profesion;
            Calendario unSoloCalendario=servicioCalendario.obtenerUnCalendarioEspecifico(profesion);
            ArrayList<Calendario> calendarios=servicioCalendario.obtenerCalendarios();
            model.put("calendario", unSoloCalendario);
            model.put("calendarios",calendarios);
            model.put("titulo", titulo);
            return new ModelAndView("calendarios", model);
        } catch (Exception e) {
            return new ModelAndView("error");
        }
    }
}
