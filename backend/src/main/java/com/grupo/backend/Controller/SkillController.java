package com.grupo.backend.Controller;

import com.grupo.backend.Model.Skill;
import com.grupo.backend.Repository.SkillRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;

@Controller
@RequestMapping(path="/skill")
public class SkillController {
	@Autowired
	private SkillRepository skillRepository;

	@CrossOrigin(origins = "*")
	@GetMapping(path="/list")
	public @ResponseBody Iterable<Skill> getSkills() {
		return this.skillRepository.findAll();
	}

    @CrossOrigin(origins = "*")
    @PostMapping(path="/add")
    public @ResponseBody ResponseEntity<String> addSkill (@RequestBody Skill newSkill) {
        Skill skill = new Skill();
        skill.setNombre(newSkill.getNombre());
        skill.setNivel(newSkill.getNivel());
        skill = skillRepository.save(skill);
        skill = skillRepository.getSkillByNombre(newSkill.getNombre()); // Para obtener el ID del registro recien insertado
        
        return new ResponseEntity<>(String.valueOf(skill.getId()), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path="/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSkill(@PathVariable("id") int id) {
    try {
        skillRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

    @CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    ResponseEntity<String> updateSkill(@RequestBody Skill newSkill, @PathVariable int id) {
        skillRepository.findById(id)
        .map(skill -> {
            skill.setNombre(newSkill.getNombre());
            skill.setNivel(newSkill.getNivel());
             
            return new ResponseEntity<String>("", HttpStatus.OK);
        })
        .orElseGet(() -> {
            return new ResponseEntity<String>("Error al actualizar skill", HttpStatus.INTERNAL_SERVER_ERROR);
        });

        return new ResponseEntity<String>("", HttpStatus.OK);
    }
}
