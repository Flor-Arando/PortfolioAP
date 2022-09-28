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

import com.grupo.backend.Model.Educacion;
import com.grupo.backend.Repository.EducacionRepository;

@Controller
@RequestMapping(path = "/educacion")
public class EducacionController {
    @Autowired
    private EducacionRepository educacionRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Educacion> getEducaciones() {
        return this.educacionRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<String> addEducacion(@RequestBody Educacion newEducacion) {
        String error = this.validar(newEducacion);

        if (error != null) {
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Educacion educacion = new Educacion();
        educacion.setTitulo(newEducacion.getTitulo());
        educacion.setDescripcion(newEducacion.getDescripcion());
        educacion.setDesde(newEducacion.getDesde());
        educacion.setHasta(newEducacion.getHasta());
        educacion = educacionRepository.save(educacion);
        educacion = educacionRepository.getEducacionByDatos(newEducacion.getTitulo(), newEducacion.getDescripcion(),
                newEducacion.getDesde(), newEducacion.getHasta());

        return new ResponseEntity<>(String.valueOf(educacion.getId()), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteEducacion(@PathVariable("id") int id) {
        try {
            educacionRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    ResponseEntity<String> replaceEducacion(@RequestBody Educacion newEducacion, @PathVariable int id) {
        String error = this.validar(newEducacion);

        if (error != null) {
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        educacionRepository.findById(id)
                .map(educacion -> {
                    educacion.setTitulo(newEducacion.getTitulo());
                    educacion.setDescripcion(newEducacion.getDescripcion());
                    educacion.setDesde(newEducacion.getDesde());
                    educacion.setHasta(newEducacion.getHasta());

                    return new ResponseEntity<String>("", HttpStatus.OK);
                })
                .orElseGet(() -> {
                    return new ResponseEntity<String>("Error al actualizar educación",
                            HttpStatus.INTERNAL_SERVER_ERROR);
                });

        return new ResponseEntity<String>("", HttpStatus.OK);

    }

    private String validar(Educacion newEducacion) {
        String mensajeError = null;

        if (newEducacion.getTitulo().length() > 100) {
            mensajeError = "Título excede la longitud permitida";
        }

        if (newEducacion.getTitulo().length() == 0) {
            mensajeError = "Título no puede estar vacío";
        }

        if (newEducacion.getDescripcion().length() > 250) {
            mensajeError = "Descripción excede la longitud permitida";
        }

        if (newEducacion.getDescripcion().length() == 0) {
            mensajeError = "Descripción no puede estar vacía";
        }

        if (!Funciones.esNumero(newEducacion.getDesde())) {
            mensajeError = "Desde tiene que ser un año";
        }

        if (newEducacion.getDesde().length() == 0) {
            mensajeError = "Desde no puede estar vacío";
        }

        if (newEducacion.getHasta().compareTo(newEducacion.getDesde()) < 0) {
            mensajeError = "Desde tiene que ser anterior a \"hasta\"";
        }

        if (!Funciones.esNumero(newEducacion.getHasta())) {
            mensajeError = "Hasta tiene que ser un año";
        }

        if (newEducacion.getHasta().length() == 0) {
            mensajeError = "Hasta no puede estar vacío";
        }

        return mensajeError;
    }
}
