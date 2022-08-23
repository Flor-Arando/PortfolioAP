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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupo.backend.Model.Educacion;
import com.grupo.backend.Repository.EducacionRepository;

@Controller
@RequestMapping(path="/educacion")
public class EducacionController {
    @Autowired
    private EducacionRepository educacionRepository;


    @CrossOrigin(origins = "*")
    @GetMapping(path="/list")
    public @ResponseBody Iterable<Educacion> getEducaciones() {
        return this.educacionRepository.findAll();
    }

  
    @PostMapping(path="/add")
   public @ResponseBody String addeducacion (@RequestParam String titulo, @RequestParam String descripcion, @RequestParam String desde, @RequestParam String hasta) {
    //@ResponseBody means the returned String is the response, not a view name
    //@RequestParam means it is a parameter from the GET or POST request


        Educacion educacion = new Educacion();
        educacion.setTitulo(titulo);
        educacion.setDescripcion(descripcion);
		educacion.setDesde(desde);
        educacion.setHasta(hasta);
        educacionRepository.save(educacion);


        return "Saved";
    } 


    //@DeleteMapping(path="/delete/{id}")
    //public @ResponseBody String deleteEducacion (@RequestParam String titulo, @RequestParam String descripcion, @RequestParam String periodo) {

    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEducacion(@PathVariable("id") int id) {
    try {
        educacionRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

    @PutMapping("/update/{id}")
    Educacion replaceEducacion(@RequestBody Educacion newEducacion, @PathVariable int id) {
    //public @ResponseBody  String replaceEducacion(@PathVariable Long id) {  
        return educacionRepository.findById(id)
            .map(educacion -> {
                educacion.setTitulo(newEducacion.getTitulo());
                educacion.setDescripcion(newEducacion.getDescripcion());
                educacion.setDesde(newEducacion.getDesde());
                educacion.setHasta(newEducacion.getHasta());

                return educacionRepository.save(educacion);
                })
                .orElseGet(() -> {
                    newEducacion.setId(id);
                        return
                        educacionRepository.save(newEducacion);
                
            });

            
    }



}


