package com.grupo.backend.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo.backend.Model.Proyecto;

public interface ProyectoRepository extends CrudRepository<Proyecto, Integer> {
    @Query("SELECT s FROM Proyecto as s WHERE nombre = ?1")
    Proyecto getProyectoByNombre(String nombre);
}
