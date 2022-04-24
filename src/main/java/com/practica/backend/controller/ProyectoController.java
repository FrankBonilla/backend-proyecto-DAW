package com.practica.backend.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.practica.backend.entities.Empleado;
import com.practica.backend.entities.Proyecto;
import com.practica.backend.service.EmpleadoService;
import com.practica.backend.service.ProyectoService;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@RequestMapping(path="proyectos")
public class ProyectoController {
	//inyectamos el servicio
	@Autowired
	ProyectoService service;
	@Autowired
	EmpleadoService serviceEmp;
	
	@GetMapping(path="/lista")
	public List<Proyecto> showAll(){
		
		return service.listar();
	}
	
	//listar solo dados de alta
	@GetMapping(path="/activos")
	public List<Proyecto> showAct(){
		
		return service.mostrarActivos();
	}
	
	//mostrar solo dados de baja
	@GetMapping(path="/inactivos")
	public List<Proyecto> showInac(){
		
		return service.mostrarInactivos();
	}
	
	@PostMapping(path="/guardar")
	@ResponseBody
	public String saveProject(@RequestBody Proyecto proyecto) {
		
		service.save(proyecto);
		return "se guardó el proyecto "+proyecto.getDescripcion();
		
	}
	
	@DeleteMapping(path="/borrar/{id}")
	public void borrar(@PathVariable(name="id", required=true) int id) {
		service.delete(id);
	}
	
	//Asiganmos los empleados al proyecto 
	@PostMapping(path="/save-employee")
	@ResponseBody
	public void addEmp(@RequestBody Map<String, String> json) {
		int idPro = Integer.parseInt(json.get("id_proyecto"));
		int idEmp = Integer.parseInt(json.get("id_empleado"));
		
		Proyecto proyecto = service.listarId(idPro);
		Empleado empleado = serviceEmp.listarId(idEmp);
		
		proyecto.addEmployee(empleado);
		service.save(proyecto);
		
	}
	//Eliminamos el empleado del proyecto
	@PostMapping(path="/remove-employee")
	@ResponseBody
	public void removeEmp(@RequestBody Map<String, String> json) {
		int idPro = Integer.parseInt(json.get("id_proyecto"));
		int idEmp = Integer.parseInt(json.get("id_empleado"));
		
		Proyecto proyecto = service.listarId(idPro);
		Empleado empleado = serviceEmp.listarId(idEmp);
		
		proyecto.removeEmployee(empleado);
		service.save(proyecto);
		
	}
	
	//Verificamos los proyectos asigandos un empleado
	@PostMapping(path="/verificar")
	@ResponseBody
	public List<?> searchProjectsOfEmple(@RequestBody Map<String, String> json){
		
		int idEmp = Integer.parseInt(json.get("id_empleado"));
		
		return service.searchProjectsOfEmple(idEmp);
	}
	
	//metodo para dar de baja al proyecto
	@PostMapping(path="/baja/{id}")
	public void baja(@PathVariable(name="id",required= true) int id) {
		service.darBaja(id);
	}
	
	@PostMapping(path="/update")
	@ResponseBody
	public void updateProject(@RequestBody Proyecto proyecto) {
		service.updateProject(proyecto);
	}
	
}