package com.grupo.backend.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo.backend.Model.Experiencia;

public interface ExperienciaRepository extends CrudRepository<Experiencia, Integer> {
    @Query("SELECT s FROM Experiencia as s WHERE empresa = ?1 AND puesto = ?2 AND desde =?3 AND hasta =?4 AND descripcion = ?5")
    Experiencia getExperienciaByRegistro(String empresa, String puesto, String desde, String hasta, String descripcion);
}
