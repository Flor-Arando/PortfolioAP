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

@Controller
public class PersonaController {
	@Autowired
	private PersonaRepository personaRepository;

//	@CrossOrigin(origins = "*") // o http://localhost:4200
//	@GetMapping(path="/persona-frontend") //si anda
//	public @ResponseBody /*Optional<*/Persona/*>*/ getPersonaX(/*@RequestParam(value = "name", defaultValue = "World") String name*/) {
//		/*Optional<*/Persona/*>*/ yo = this.personaRepository.findById(1).orElse(new Persona()); // esto lo provee springboot (o hibernate)
//		yo.setClave("");
//		yo.setUsuario("");
//		return yo;
//	}

    @CrossOrigin(origins = "*")
	@GetMapping(path="/persona")
	public @ResponseBody Persona getPersona() {
		return this.personaRepository.findById(1).get();
	}

	@CrossOrigin(origins = "*")
	@PutMapping(path="/persona/update")
	ResponseEntity<String> actualizarPersona(@RequestBody Persona newPersona) {
		// TODO: utilizar constantes para el mensaje 
		if (newPersona.getNombre().length() > 16) {
            return new ResponseEntity<>("Nombre excede la longitud permitida", HttpStatus.INTERNAL_SERVER_ERROR);
        }
		if (!Funciones.tieneSoloLetras(newPersona.getNombre())) {
			return new ResponseEntity<>("Nombre solo puede contener letras", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newPersona.getNombre().length() == 0 ) {
			return new ResponseEntity<>("Nombre no puede estar vacío", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newPersona.getApellido().length() > 6) {
            return new ResponseEntity<>("Apellido excede la longitud permitida", HttpStatus.INTERNAL_SERVER_ERROR);
        }

		if (!Funciones.tieneSoloLetras(newPersona.getApellido())) {
			return new ResponseEntity<>("Apellido solo puede contener letras", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newPersona.getTitulo().length() > 50) {
            return new ResponseEntity<>("Título excede la longitud permitida", HttpStatus.INTERNAL_SERVER_ERROR);
        }

		if (!Funciones.tieneSoloLetras(newPersona.getTitulo())) {
			return new ResponseEntity<>("Título solo puede contener letras", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newPersona.getTitulo().length() == 0 ) {
			return new ResponseEntity<>("Título no puede estar vacío", HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if (!Funciones.esFecha(newPersona.getNacimiento())) {
			return new ResponseEntity<>("Fecha inválida. Debe estar en el formato AAAA-MM-DD", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newPersona.getDescripcion().length() > 250) {
            return new ResponseEntity<>("Descripción excede la longitud permitida", HttpStatus.INTERNAL_SERVER_ERROR);
        } 

		if (newPersona.getDescripcion().length() == 0 ) {
			return new ResponseEntity<>("Descripción no puede estar vacía", HttpStatus.INTERNAL_SERVER_ERROR);
		}

		if (newPersona.getDireccion().length() > 40) {
            return new ResponseEntity<>("Dirección excede la longitud permitida", HttpStatus.INTERNAL_SERVER_ERROR);
        }

		if (newPersona.getDireccion().length() == 0 ) {
			return new ResponseEntity<>("Dirección no puede estar vacía", HttpStatus.INTERNAL_SERVER_ERROR);
		} //validar correo y nacimiento

		if (!Funciones.esEmail(newPersona.getCorreo())) {
			return new ResponseEntity<>("e-mail inválido", HttpStatus.INTERNAL_SERVER_ERROR);
		} //validar correo y nacimiento


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
				
			personaRepository.save(persona);

			return new ResponseEntity<>("", HttpStatus.OK);
		})
		.orElseGet(() -> {
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		});

		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
