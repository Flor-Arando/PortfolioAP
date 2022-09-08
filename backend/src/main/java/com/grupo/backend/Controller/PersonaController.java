package com.grupo.backend.Controller;

import com.grupo.backend.Model.Persona;
import com.grupo.backend.Repository.PersonaRepository;

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
				
			personaRepository.save(persona);

			return new ResponseEntity<>("", HttpStatus.OK);
		})
		.orElseGet(() -> {
			return new ResponseEntity<>("", HttpStatus.INTERNAL_SERVER_ERROR);
		});

		return new ResponseEntity<>("", HttpStatus.OK);
	}
}
