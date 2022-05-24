package com.practica.backend.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.practica.backend.entities.Empleado;

@Repository
public interface EmpleadoRepository extends JpaRepository <Empleado,Integer> {
	//creamos una consulta donde devolve´ra los empleados que no esten de baja
	@Query(value="SELECT l FROM Empleado l WHERE l.f_baja IS NULL")
	List<Empleado> getActivesEmployees();
	
	//hacemos una query nativa para listar a los empleados que nos devolverá una columna con true
	//si estan asiganos al proyecto o de lo contrario false segun el id del proyecto
	
	@Query(value="SELECT l.id_empleado, l.tx_nombre, l.tx_apellido1, l.tx_apellido2, "
			+ "CASE WHEN l.id_empleado IN (SELECT id_empleado FROM pr_empleados_proyecto WHERE id_proyecto = :idPro) THEN 'true' "
			+ "WHEN l.id_empleado  NOT IN (SELECT id_empleado FROM pr_empleados_proyecto WHERE id_proyecto = :idPro) THEN 'false' END AS asignado "
			+ "FROM EMPLEADOS l WHERE l.f_baja IS NULL", nativeQuery = true)
	List<?> showStatus(@Param("idPro") int id);
	
	//definimos los empleados asignados al proyecto segun id
	@Query(value="select tx_nombre from EMPLEADOS a join "
			+ "pr_empleados_proyecto b on a.id_empleado=b.id_empleado where b.id_proyecto = :idPro",nativeQuery=true)
	List<?> searchEmployeesInProject(@Param("idPro") int id);
	
	//creamos una consulta donde devolverá los empleados que  esten de baja
	@Query(value="SELECT l FROM Empleado l WHERE l.f_baja != null")
	List<Empleado> findByF_baja();
	
	//método para volver a dar el alta a un empleado dado de baja, añadimos dos anotaciones para poder ejecutar este tipo de query 
	@Modifying
	@Transactional
	@Query(value="UPDATE EMPLEADOS SET f_baja= null WHERE id_empleado = :idEmp", nativeQuery=true)
	void volverAlta(@Param("idEmp") int id);
	
	
}