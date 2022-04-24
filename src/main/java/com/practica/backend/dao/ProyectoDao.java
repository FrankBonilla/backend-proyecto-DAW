package com.practica.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.practica.backend.entitie.Proyecto;

@Repository
public interface ProyectoDao extends JpaRepository<Proyecto, Integer> {
	
	//creamos una consulta donde devolverá los empleados que no esten de baja
		@Query(value="SELECT l FROM Proyecto l WHERE l.f_baja = null")
		List<Proyecto> findByF_alta();
		
	//creamos otro para buscar los metodos que estan de baja
		@Query(value="SELECT l FROM Proyecto l WHERE l.f_baja != null")
		List<Proyecto> findByF_baja();
		
	//definimos un método para saber que proyectos tiene asginado un empleado segun su id
		@Query(value="SELECT text_descripcion FROM pr_proyectos a "
				+ "JOIN pr_empleados_proyecto b ON a.id_proyecto=b.id_proyecto "
				+ "WHERE b.id_empleado = :idEmp", nativeQuery=true)
		List<?> listProjects(@Param("idEmp") int id);
}
	
