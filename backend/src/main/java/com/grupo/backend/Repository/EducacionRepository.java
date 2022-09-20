package com.grupo.backend.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo.backend.Model.Educacion;

public interface EducacionRepository extends CrudRepository<Educacion, Integer> {
    @Query("SELECT s FROM Educacion as s WHERE nombre = ?1")
    Educacion getEducacionByNombre(String nombre);

}