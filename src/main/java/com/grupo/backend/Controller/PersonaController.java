package com.grupo.backend.Controller;

import com.grupo.backend.Model.Persona;
import com.grupo.backend.Repository.PersonaRepository;
import com.grupo.backend.Funciones;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
public class PersonaController extends ControllerGenerico {
	@Autowired
	private PersonaRepository personaRepository;

	@CrossOrigin(origins = "*")
	@GetMapping(path = "/persona")
	public @ResponseBody Persona getPersona() {
		return this.personaRepository.findById(1).get();
	}

	@CrossOrigin(origins = "*")
	@PutMapping(path = "/persona/update")
	ResponseEntity<String> actualizarPersona(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Persona newPersona) {
		if (!this.tokenValido(token)) {
			return new ResponseEntity<String>("Sin acceso", HttpStatus.UNAUTHORIZED);
		}

		String error = this.validar(newPersona);

		if (error != null) {
			return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		personaRepository.findById(1)
				.map(persona -> {
					persona.setNombre(newPersona.getNombre());
					persona.setApellido(newPersona.getApellido());
					persona.setTitulo(newPersona.getTitulo());
					persona.setDescripcion(newPersona.getDescripcion());
					persona.setCorreo(newPersona.getCorreo());
					persona.setDireccion(newPersona.getDireccion());
					persona.setNacimiento(newPersona.getNacimiento());
					persona.setFotoPerfil(newPersona.getFotoPerfil());
					persona.setBanner(newPersona.getBanner());
					persona.setOrdenNacimiento(newPersona.getOrdenNacimiento());
					persona.setOrdenDireccion(newPersona.getOrdenDireccion());
					persona.setOrdenCorreo(newPersona.getOrdenCorreo());
					personaRepository.save(persona);

					return new ResponseEntity<>("", HttpStatus.OK);
				})
				.orElseGet(() -> {
					return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
				});

		return new ResponseEntity<>("", HttpStatus.OK);
	}

	private String validar(Persona newPersona) {
		if (newPersona.getNombre().length() > 16) {
			return "Nombre excede la longitud permitida";
		}

		if (!Funciones.tieneSoloLetras(newPersona.getNombre())) {
			return "Nombre solo puede contener letras";
		}

		if (newPersona.getNombre().length() == 0) {
			return "Nombre no puede estar vacío";
		}

		if (newPersona.getApellido().length() > 6) {
			return "Apellido excede la longitud permitida";
		}

		if (!Funciones.tieneSoloLetras(newPersona.getApellido())) {
			return "Apellido solo puede contener letras";
		}

		if (newPersona.getApellido().length() == 0) {
			return "Apellido no puede estar vacío";
		}

		if (newPersona.getTitulo().length() > 50) {
			return "Título excede la longitud permitida";
		}

		if (!Funciones.tieneSoloLetras(newPersona.getTitulo())) {
			return "Título solo puede contener letras";
		}

		if (newPersona.getTitulo().length() == 0) {
			return "Título no puede estar vacío";
		}

		if (!Funciones.esFecha(newPersona.getNacimiento())) {
			return "Fecha inválida. Debe estar en el formato AAAA-MM-DD";
		}

		if (newPersona.getDescripcion().length() > 250) {
			return "Descripción excede la longitud permitida";
		}

		if (newPersona.getDescripcion().length() == 0) {
			return "Descripción no puede estar vacía";
		}

		if (newPersona.getDireccion().length() > 40) {
			return "Dirección excede la longitud permitida";
		}

		if (newPersona.getDireccion().length() == 0) {
			return "Dirección no puede estar vacía";
		}

		if (!Funciones.esEmail(newPersona.getCorreo())) {
			return "E-mail inválido";
		}

		if (newPersona.getCorreo().length() == 0) {
			return "E-mail no puede estar vacío";
		}

		return null;
	}
}
