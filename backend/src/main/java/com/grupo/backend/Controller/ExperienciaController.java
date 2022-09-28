package com.grupo.backend.Controller;

import com.grupo.backend.Funciones;
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
import org.springframework.web.bind.annotation.ResponseBody;

import com.grupo.backend.Model.Experiencia;
import com.grupo.backend.Repository.ExperienciaRepository;

@Controller
@RequestMapping(path = "/experiencia")
public class ExperienciaController {
    @Autowired
    private ExperienciaRepository experienciaRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Experiencia> getExperiencias() {
        return this.experienciaRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<String> addExperiencia(@RequestBody Experiencia newExperiencia) {
        String error = this.validar(newExperiencia);

        if (error != null) {
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Experiencia experiencia = new Experiencia();
        experiencia.setEmpresa(newExperiencia.getEmpresa());
        experiencia.setPuesto(newExperiencia.getPuesto());
        experiencia.setDescripcion(newExperiencia.getDescripcion());
        experiencia.setDesde(newExperiencia.getDesde());
        experiencia.setHasta(newExperiencia.getHasta());
        experiencia = experienciaRepository.save(experiencia);
        experiencia = experienciaRepository.getExperienciaByRegistro(newExperiencia.getEmpresa(),
                newExperiencia.getPuesto(), newExperiencia.getDesde(), newExperiencia.getHasta(),
                newExperiencia.getDescripcion());

        return new ResponseEntity<>(String.valueOf(experiencia.getId()), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteExperiencia(@PathVariable("id") int id) {
        try {
            experienciaRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    ResponseEntity<String> replaceExperiencia(@RequestBody Experiencia newExperiencia, @PathVariable int id) {
        String error = this.validar(newExperiencia);

        if (error != null) {
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        experienciaRepository.findById(id)
                .map(experiencia -> {
                    experiencia.setEmpresa(newExperiencia.getEmpresa());
                    experiencia.setPuesto(newExperiencia.getPuesto());
                    experiencia.setDescripcion(newExperiencia.getDescripcion());
                    experiencia.setDesde(newExperiencia.getDesde());
                    experiencia.setHasta(newExperiencia.getHasta());

                    return new ResponseEntity<String>("", HttpStatus.OK);
                })
                .orElseGet(() -> {
                    return new ResponseEntity<String>("Error al actualizar experiencia",
                            HttpStatus.INTERNAL_SERVER_ERROR);

                });

        return new ResponseEntity<String>("", HttpStatus.OK);
    }

    private String validar(Experiencia newExperiencia) {
        if (newExperiencia.getEmpresa().length() > 100) {
            return "Empresa excede la longitud permitida";
        }

        if (newExperiencia.getEmpresa().length() == 0) {
            return "Empresa no puede estar vacío";
        }

        if (newExperiencia.getPuesto().length() > 50) {
            return "Puesto laboral excede la longitud permitida";
        }

        if (newExperiencia.getPuesto().length() == 0) {
            return "Puesto laboral no puede estar vacío";
        }

        if (newExperiencia.getDescripcion().length() > 250) {
            return "Descripción excede la longitud permitida";
        }

        if (newExperiencia.getDescripcion().length() == 0) {
            return "Descripción no puede estar vacía";
        }

        if (!Funciones.esNumero(newExperiencia.getDesde())) {
            return "Desde tiene que ser un año";
        }

        if (newExperiencia.getDesde().length() == 0) {
            return "Desde no puede estar vacío";
        }

        if (newExperiencia.getHasta().compareTo(newExperiencia.getDesde()) < 0) { 
            return "Desde tiene que ser anterior a \"hasta\"";
        }

        if (!Funciones.esNumero(newExperiencia.getHasta())) {
            return "Hasta tiene que ser un año";
        }

        if (newExperiencia.getHasta().length() == 0) {
            return "Hasta no puede estar vacío";
        }

        return null;
    }
}
