package com.grupo.backend.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import com.grupo.backend.Model.Proyecto;

public interface ProyectoRepository extends CrudRepository<Proyecto, Integer> {
    @Query("SELECT s FROM Proyecto as s WHERE titulo = ?1 AND descripcion = ?2 AND desde = ?3 AND hasta = ?4 AND link = ?5 AND foto = ?6")
    Proyecto getProyectoByDatos(String titulo, String descripcion, String desde, String hasta, String link, String foto);
}
