package com.grupo.backend.Controller;

import com.grupo.backend.Funciones;
import com.grupo.backend.Model.Proyecto;
import com.grupo.backend.Repository.ProyectoRepository;

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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.http.HttpHeaders;

@Controller
@RequestMapping(path = "/proyecto")
public class ProyectoController extends ControllerGenerico {
  @Autowired
  private ProyectoRepository proyectoRepository;

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/list")
  public @ResponseBody Iterable<Proyecto> getProyectos() {
    return this.proyectoRepository.findAll();
  }

  @CrossOrigin(origins = "*")
  @PostMapping(path = "/add")
  public @ResponseBody ResponseEntity<String> addProyecto(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Proyecto newProyecto) {
    if (!this.tokenValido(token)) {
			return new ResponseEntity<String>("Sin acceso", HttpStatus.UNAUTHORIZED);
		}

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
  public ResponseEntity<HttpStatus> deleteEducacion(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("id") int id) {
    if (!this.tokenValido(token)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

    try {
      proyectoRepository.deleteById(id);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/update/{id}")
  ResponseEntity<String> replaceProyecto(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Proyecto newProyecto, @PathVariable int id) {
    if (!this.tokenValido(token)) {
			return new ResponseEntity<String>("Sin acceso", HttpStatus.UNAUTHORIZED);
		}

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
          proyectoRepository.save(proyecto);

          return new ResponseEntity<String>("", HttpStatus.OK);
        })
        .orElseGet(() -> {
          return new ResponseEntity<String>("Error al actualizar proyectos", HttpStatus.INTERNAL_SERVER_ERROR);
        });

    return new ResponseEntity<String>("", HttpStatus.OK);
  }

  private String validar(Proyecto newProyecto) {
    if (newProyecto.getTitulo().length() > 50) {
      return "Título del proyecto excede la longitud permitida";
    }

    if (newProyecto.getTitulo().length() == 0) {
      return "Título no puede estar vacío";
    }

    if (newProyecto.getDescripcion().length() > 250) {
      return "Descripción excede la longitud permitida";
    }

    if (newProyecto.getDescripcion().length() == 0) {
      return "Descripción no puede estar vacía";
    }

    if (newProyecto.getDesde().length() == 0) {
      return "Desde no puede estar vacío";
    }

    if (newProyecto.getHasta().compareTo(newProyecto.getDesde()) < 0) {
      return "Desde tiene que ser anterior a \"hasta\"";
    }

    if (!Funciones.esFecha(newProyecto.getDesde())) {
			return "Fecha inválida. Desde debe estar en el formato AAAA-MM-DD";
		}

    if (!Funciones.esFecha(newProyecto.getHasta())) {
			return "Fecha inválida. Hasta debe estar en el formato AAAA-MM-DD";
		}

    return null;
  }
}
