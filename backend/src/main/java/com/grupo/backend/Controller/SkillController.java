package com.grupo.backend.Controller;

import com.grupo.backend.Model.Skill;
import com.grupo.backend.Repository.SkillRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;

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

}
