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

    @PostMapping(path="/add")
    public @ResponseBody String addSkill (@RequestParam String nombre, @RequestParam int nivel) {
    // @ResponseBody means the returned String is the response, not a view name
    // @RequestParam means it is a parameter from the GET or POST request

        Skill skill = new Skill();
        skill.setNombre(nombre);
        skill.setNivel(nivel);
        skillRepository.save(skill);

        return "Saved";
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

  @PutMapping("/update/{id}")
  Skill replaceSkill(@RequestBody Skill newSkill, @PathVariable int id) {
  //public @ResponseBody  String replaceEducacion(@PathVariable Long id) {  
      return skillRepository.findById(id)
          .map(skill -> {
              skill.setNombre(newSkill.getNombre());
              skill.setNivel(newSkill.getNivel());
             
              return skillRepository.save(skill);
              })
              .orElseGet(() -> {
                  newSkill.setId(id);
                      return
                      skillRepository.save(newSkill);
              
          });

          
  }


}
