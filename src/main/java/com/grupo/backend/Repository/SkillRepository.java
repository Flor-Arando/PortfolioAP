package com.grupo.backend.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo.backend.Model.Skill;

public interface SkillRepository extends CrudRepository<Skill, Integer> {
    @Query("SELECT s FROM Skill as s WHERE nombre = ?1")
    Skill getSkillByNombre(String nombre);
}