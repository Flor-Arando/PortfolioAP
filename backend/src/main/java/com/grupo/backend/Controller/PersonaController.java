package com.grupo.backend.Controller;

import java.util.Optional;

//import java.time.LocalDate;

import com.grupo.backend.Model.Persona;
import com.grupo.backend.Repository.PersonaRepository;

import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;

// estos son del tutorial
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
///
import org.springframework.web.bind.annotation.CrossOrigin;

//@RestController
@Controller
public class PersonaController {
	@Autowired
	private PersonaRepository personaRepository;

	@CrossOrigin(origins = "*") // o http://localhost:4200
	@GetMapping(path="/persona-frontend")
	public @ResponseBody /*Optional<*/Persona/*>*/ getPersonaX(/*@RequestParam(value = "name", defaultValue = "World") String name*/) {
		/*Optional<*/Persona/*>*/ yo = this.personaRepository.findById(1).orElse(new Persona()); // esto lo provee springboot (o hibernate)
		yo.setClave("");
		yo.setUsuario("");
		return yo;
	}

	@GetMapping(path="/personas")
	public @ResponseBody Iterable<Persona> getPersonas() {
		return this.personaRepository.findAll(); // esto lo provee springboot (o hibernate)
	}

}
