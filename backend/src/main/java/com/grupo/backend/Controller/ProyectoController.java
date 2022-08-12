package com.grupo.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupo.backend.Model.Proyecto;
import com.grupo.backend.Repository.ProyectoRepository;

@Controller
@RequestMapping(path="/proyecto")
public class ProyectoController {
    @Autowired
    private ProyectoRepository proyectoRepository;


    @CrossOrigin(origins = "*")
    @GetMapping(path="/list")
    public @ResponseBody Iterable<Proyecto> getProyectos() {
        return this.proyectoRepository.findAll();
    }


    @PostMapping(path="/add")
    public @ResponseBody String addproyecto (@RequestParam String titulo, @RequestParam String descripcion, @RequestParam String periodo, @RequestParam String link) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

        Proyecto proyecto = new Proyecto();
        proyecto.setTitulo(titulo);
        proyecto.setDescripcion(descripcion);
		proyecto.setPeriodo(periodo);
		proyecto.setLink(link);
        proyectoRepository.save(proyecto);


        return "Saved";
    }
}
