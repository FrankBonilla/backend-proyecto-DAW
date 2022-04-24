package com.practica.backend.service;



import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backend.repositories.EmpleadoRepository;
import com.practica.backend.entities.Empleado;

@Service
public class EmpleadoService {
	//inyectamos el repositorio
		@Autowired
		private EmpleadoRepository repo;

		public List<Empleado> listar() {
			
			return repo.findAll();
		}

		public void save(Empleado empleado) {
			
			repo.save(empleado);
		}

		public void delete(int id) {
			
			repo.deleteById(id);
		}

		public Empleado listarId(int id) {
			
			return repo.getById(id);
		}

		public void updateEmployee(Empleado a) {
			Empleado empleado = listarId(a.getId_empleado());
			empleado.setNIF(a.getNIF());
			empleado.setNombre(a.getNombre());
			empleado.setApellido1(a.getApellido1());
			empleado.setApellido2(a.getApellido2());
			empleado.setNacimiento(a.getNacimiento());
			empleado.setTelefono1(a.getTelefono1());
			empleado.setTelefono2(a.getTelefono2());
			empleado.setEmail(a.getEmail());
			empleado.setF_alta(a.getF_alta());
			empleado.setEdoCivil(a.getEdoCivil());
			empleado.setSerMilitar(a.getSerMilitar());
			repo.save(empleado);
			
		}
		
		
		public void darBaja(int id) {
			
			Empleado empleado = repo.getById(id);
			LocalDate fecha = LocalDate.now();
			empleado.setF_baja(Date.valueOf(fecha));
			repo.save(empleado);
			
		}
		//método 1 que define la consulta creada en el repositorio
		public List<Empleado> mostarActivos(){
			
			return repo.findByF_alta();
		}
		
		//metodo 2 que define la consulta de empleados y muestra su asignación
		public List<?>  showStatus(int id){
			return repo.showStatus(id);
		}
		//metodo 3 creado en el repositorio
		public List<?> searchEmployeesProject(int id) {
			List<?> asignedProjects = repo.searchEmployeesInProject(id);
			if(asignedProjects != null || asignedProjects.size() != 0) {
					
				return asignedProjects;
			}
			return null;
		}
		//método 4 que busca a los empleados de baja
		public List<Empleado> showUnsuscribe(){
			
			return repo.findByF_baja();
		}
		
		//metodo 5 para volver a dar de alta
		public void volver(int id) {
			//obtenemos el empleado y cambiamos la fecha de alta a la actual
			Empleado empleado = repo.getById(id);
			LocalDate fecha = LocalDate.now();
			empleado.setF_alta(Date.valueOf(fecha));
			//establecemos null en su fecha de baja para que aparezca de lata nuevamente
			repo.volverAlta(id);
			//guardamos el empleado con la nueva fecha de alta
			repo.save(empleado);
		}
}
