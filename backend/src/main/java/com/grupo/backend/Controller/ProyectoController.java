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

import com.grupo.backend.Model.Proyecto;
import com.grupo.backend.Repository.ProyectoRepository;

@Controller
@RequestMapping(path = "/proyecto")
public class ProyectoController {
  @Autowired
  private ProyectoRepository proyectoRepository;

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/list")
  public @ResponseBody Iterable<Proyecto> getProyectos() {
    return this.proyectoRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @PostMapping(path = "/add")
  public @ResponseBody ResponseEntity<String> addProyecto(@RequestBody Proyecto newProyecto) {
    String error = this.validar(newProyecto);

    if (error != null) {
      return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    Proyecto proyecto = new Proyecto();
    proyecto.setTitulo(newProyecto.getTitulo());
    proyecto.setDescripcion(newProyecto.getDescripcion());
    proyecto.setDesde(newProyecto.getDesde());
    proyecto.setHasta(newProyecto.getHasta());
    proyecto.setLink(newProyecto.getLink());
    proyecto.setFoto(newProyecto.getFoto());
    proyecto = proyectoRepository.save(proyecto);
    proyecto = proyectoRepository.getProyectoByDatos(newProyecto.getTitulo(), newProyecto.getDescripcion(),
        newProyecto.getDesde(), newProyecto.getHasta(), newProyecto.getLink(), newProyecto.getFoto());

    return new ResponseEntity<>(String.valueOf(proyecto.getId()), HttpStatus.CREATED);
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping(path = "/delete/{id}")
  public ResponseEntity<HttpStatus> deleteEducacion(@PathVariable("id") int id) {
    try {
      proyectoRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/update/{id}")
  ResponseEntity<String> replaceProyecto(@RequestBody Proyecto newProyecto, @PathVariable int id) {
    String error = this.validar(newProyecto);

    if (error != null) {
      return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    proyectoRepository.findById(id)

        .map(proyecto -> {
          proyecto.setTitulo(newProyecto.getTitulo());
          proyecto.setDescripcion(newProyecto.getDescripcion());
          proyecto.setDesde(newProyecto.getDesde());
          proyecto.setHasta(newProyecto.getHasta());
          proyecto.setLink(newProyecto.getLink());
          proyecto.setFoto(newProyecto.getFoto());

          return new ResponseEntity<String>("", HttpStatus.OK);
        })
        .orElseGet(() -> {
          return new ResponseEntity<String>("Error al actualizar proyectos", HttpStatus.INTERNAL_SERVER_ERROR);
        });

    return new ResponseEntity<String>("", HttpStatus.OK);

  }

  private String validar(Proyecto newProyecto) {
    String mensajeError = null;

    if (newProyecto.getTitulo().length() > 50) {
      return mensajeError = "Título del proyecto excede la longitud permitida";
    }

    if (newProyecto.getTitulo().length() == 0) {
      return mensajeError = "Título no puede estar vacío";
    }

    if (newProyecto.getDescripcion().length() > 250) {
      return mensajeError = "Descripción excede la longitud permitida";
    }

    if (newProyecto.getDescripcion().length() == 0) {
      return mensajeError = "Descripción no puede estar vacía";
    }

    if (newProyecto.getDesde().length() == 0) {
      return mensajeError = "Desde no puede estar vacío";
    }

    if (newProyecto.getHasta().compareTo(newProyecto.getDesde()) < 0) {
      return mensajeError = "Desde tiene que ser anterior a \"hasta\"";
    }

    if (!Funciones.esFecha(newProyecto.getDesde())) {
			return "Fecha inválida. Desde debe estar en el formato AAAA-MM-DD";
		}

    if (!Funciones.esFecha(newProyecto.getHasta())) {
			return "Fecha inválida. Hasta debe estar en el formato AAAA-MM-DD";
		}

    return mensajeError;
  }

}
