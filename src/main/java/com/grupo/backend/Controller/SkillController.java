package com.grupo.backend.Controller;

import com.grupo.backend.Model.Skill;
import com.grupo.backend.Repository.SkillRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.RequestHeader;

@Controller
@RequestMapping(path = "/skill")
public class SkillController extends ControllerGenerico {
    @Autowired
    private SkillRepository skillRepository;

    @CrossOrigin(origins = "*")
    @GetMapping(path = "/list")
    public @ResponseBody Iterable<Skill> getSkills() {
        return this.skillRepository.findAll();
    }

    @CrossOrigin(origins = "*")
    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<String> addSkill(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Skill newSkill) {
        if (!this.tokenValido(token)) {
			return new ResponseEntity<String>("Sin acceso", HttpStatus.UNAUTHORIZED);
		}

        String error = this.validar(newSkill);

        if (error != null) {
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Skill skill = new Skill();
        skill.setNombre(newSkill.getNombre());
        skill.setNivel(newSkill.getNivel());
        skill = skillRepository.save(skill);
        skill = skillRepository.getSkillByNombre(newSkill.getNombre());

        return new ResponseEntity<>(String.valueOf(skill.getId()), HttpStatus.CREATED);
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(path = "/delete/{id}")
    public ResponseEntity<HttpStatus> deleteSkill(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable("id") int id) {
        if (!this.tokenValido(token)) {
			return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}

        try {
            skillRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @PutMapping("/update/{id}")
    ResponseEntity<String> updateSkill(@RequestHeader(HttpHeaders.AUTHORIZATION) String token, @RequestBody Skill newSkill, @PathVariable int id) {
        if (!this.tokenValido(token)) {
			return new ResponseEntity<String>("Sin acceso", HttpStatus.UNAUTHORIZED);
		}

        String error = this.validar(newSkill);

        if (error != null) {
            return new ResponseEntity<String>(error, HttpStatus.INTERNAL_SERVER_ERROR);
        }

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

    private String validar(Skill newSkill) {
        if (newSkill.getNivel() < 1) {
            return "El nivel tiene que ser mayor a 0";
        }
        if (newSkill.getNivel() > 100) {
            return "El nivel tiene que ser menor a 100";
        }

        if (newSkill.getNombre().length() > 30) {
            return "Nombre excede la longitud permitida";
        }

        if (newSkill.getNombre().length() == 0) {
            return "Nombre no puede estar vacío";
        }

        return null;
    }
}
