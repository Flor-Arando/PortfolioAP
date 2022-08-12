package com.grupo.backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupo.backend.Model.Experiencia;
import com.grupo.backend.Repository.ExperienciaRepository;

@Controller
    @RequestMapping(path="/experiencia")
    public class ExperienciaController {
        @Autowired
        private ExperienciaRepository experienciaRepository;
    
    
        @CrossOrigin(origins = "*")
        @GetMapping(path="/list")
        public @ResponseBody Iterable<Experiencia> getExperiencias() {
            return this.experienciaRepository.findAll();
        }
    
    
        @PostMapping(path="/add")
        public @ResponseBody String addexperiencia (@RequestParam String empresa, @RequestParam String puesto, @RequestParam String periodo, @RequestParam String descripcion) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
    
    
            Experiencia experiencia = new Experiencia();
            experiencia.setEmpresa(empresa);
            experiencia.setPuesto(puesto);  
            experiencia.setPeriodo(periodo);
            experiencia.setDescripcion(descripcion);
            experienciaRepository.save(experiencia);
    
    
            return "Saved";
        }

        @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExperiencia(@PathVariable("id") int id) {
    try {
        experienciaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }


}
