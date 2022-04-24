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
import com.practica.backend.service.EmpleadoService;

@CrossOrigin(origins = {"http://localhost:8081"})
@RestController
@RequestMapping(path="empleados")
public class EmpleadoController {
	//inyectamos el servicio
	@Autowired
	EmpleadoService service;
	
	@GetMapping(path="/lista")
	public List<Empleado> mostarTodos(){
		
		return service.listar();
	}
	
	@GetMapping(path="/activos")
	public List<Empleado> showAct(){
		
		return service.mostarActivos();
	}
	
	@GetMapping(path="/inactivos")
	public List<Empleado> showUnsuscribe(){
		
		return service.showUnsuscribe();
	}
	
	@PostMapping(path="/agregar")
	@ResponseBody
	public String guardar(@RequestBody Empleado empleado) {
		
		service.save(empleado);
		return "Se ha agregado el empleado: "+empleado.getNombre();
	}
	
	@DeleteMapping(path="/borrar/{id}")
	public void borrar(@PathVariable(name="id", required=true) int id) {
		service.delete(id);
	}
	
	@PostMapping(path="/status")
	@ResponseBody
	public List<?> status(@RequestBody Map<String, String> json) {
		int idPro = Integer.parseInt(json.get("id_proyecto"));
		return service.showStatus(idPro);
		
		
	}
	//metodo para verificar si el proyecto tiene empleados asignados antes de darle de baja
	@PostMapping(path="/verificar")
	@ResponseBody
	public List<?> bajaEmp(@RequestBody Map<String, String> json){
		int idPro = Integer.parseInt(json.get("id_proyecto"));
		return service.searchEmployeesProject(idPro);
		
		
	}
	
	@PostMapping(path="/update")
	@ResponseBody
	public void updateEmployee(@RequestBody Empleado empleado) {
		
		service.updateEmployee(empleado);
	}
	
	//método para dar de baja directamente
	@PostMapping(path="/baja/{id}")
	public void baja(@PathVariable(name="id",required= true) int id) {
			service.darBaja(id);
		}
	
	//método para volver a dar de alta
	@PostMapping(path="/volver-alta")
	@ResponseBody
	public void volver(@RequestBody Map<String, String> json){
		
		int idEmp = Integer.parseInt(json.get("id_empleado"));
		
		service.volver(idEmp);
		
		
	}
	
}