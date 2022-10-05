package com.grupo.backend.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo.backend.Model.Educacion;

public interface EducacionRepository extends CrudRepository<Educacion, Integer> {
    @Query("SELECT s FROM Educacion as s WHERE titulo = ?1 AND descripcion = ?2 AND desde = ?3 AND hasta = ?4")
    Educacion getEducacionByDatos(String titulo, String descripcion, String desde, String hasta);

}