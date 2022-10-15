package com.practica.backend.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping(path="api/")
public class EmpleadoController {

	private static final Logger LOGGER = LogManager.getLogger(EmpleadoController.class);
	//inyectamos el servicio
	@Autowired
	EmpleadoService empleadoService;
	HttpStatus  httpStatus;

	/**
	 * Este método devuelve una lista con todos los empleados
	 * o en su defecto los errores que se puedan producir en la consulta
	 * **/
	@GetMapping(path="empleados/lista")
	public ResponseEntity<?> mostarTodos(){
		LOGGER.info("Consultando todos empleados");
		List<Empleado> result;
		Map<String, Object> response = new HashMap<>();

		try{
			result = empleadoService.listar();

		}catch (DataAccessException e){
			LOGGER.error("Error al realizar la consulta a la base de datos");
			response.put("mensaje","Error al realizar la consulta a la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,httpStatus.NOT_FOUND);
		}

		if(result == null) {
			LOGGER.warn("La base de datos no tiene empleados registrados");
			response.put("mensaje","La base de datos no tiene empleados registrados");
			return new ResponseEntity<>(response,httpStatus.NOT_FOUND);
		}

		LOGGER.info("Consulta de empleados finalizada con {} resultados",result.size());
		return new ResponseEntity<>(result,httpStatus.OK);
	}

	/**
	 * Este método devuelve una lista con todos los empleados que están activos
	 * o en su defecto los errores que se puedan producir en la consulta
	 * **/
	@GetMapping(path="empleados/activos")
	public ResponseEntity<?> showAct(){
		LOGGER.info("Consultando todos empleados de alta");
		List<Empleado> result;
		Map<String, Object> response = new HashMap<>();

		try{
			result = empleadoService.mostarActivos();

		}catch (DataAccessException e){
			LOGGER.error("Error al realizar la consulta a la base de datos");
			response.put("mensaje","Error al realizar la consulta a la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(response,httpStatus.NOT_FOUND);
		}

		if(result == null) {
			LOGGER.warn("La base de datos no tiene empleados activos");
			response.put("mensaje","La base de datos no tiene empleados activos");
			return new ResponseEntity<>(response,httpStatus.NOT_FOUND);
		}

		LOGGER.info("Consulta de empleados finalizada con {} resultados",result.size());
		return new ResponseEntity<>(result, httpStatus.OK);
	}
	
	@GetMapping(path="empleados/inactivos")
	public ResponseEntity<List<Empleado>> showUnsuscribe(){
		LOGGER.info("Consultando todos empleados de baja");
		List<Empleado> result = null;

		try{
			result = empleadoService.showUnsuscribe();

		}catch(DataAccessException e){
			LOGGER.error("error: "+e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<>(result,httpStatus.NO_CONTENT);
		}

		if(result == null){
			LOGGER.warn("La base de datos no tiene empleados de baja");
			return new ResponseEntity<>(result,httpStatus.NO_CONTENT);
		}

		LOGGER.info("Consulta de empleados finalizada con {} resultados",result.size());
		return new ResponseEntity<>(result,httpStatus.OK);
	}
	
	@PostMapping(path="empleados/agregar")
	@ResponseBody
	public String guardar(@RequestBody Empleado empleado) {
		
		empleadoService.save(empleado);
		return "Se ha agregado el empleado: "+empleado.getNombre();
	}
	
	@DeleteMapping(path="/borrar/{id}")
	public void borrar(@PathVariable(name="id") int id) {
		empleadoService.delete(id);
	}
	
	@PostMapping(path="/status")
	@ResponseBody
	public List<?> status(@RequestBody Map<String, String> json) {
		int idPro = Integer.parseInt(json.get("id_proyecto"));
		return empleadoService.showStatus(idPro);
		
		
	}
	//metodo para verificar si el proyecto tiene empleados asignados antes de darle de baja
	@PostMapping(path="/verificar")
	@ResponseBody
	public List<?> bajaEmp(@RequestBody Map<String, String> json){
		int idPro = Integer.parseInt(json.get("id_proyecto"));
		return empleadoService.searchEmployeesProject(idPro);
		
		
	}
	
	@PostMapping(path="empleados/update")
	@ResponseBody
	public void updateEmployee(@RequestBody Empleado empleado) {
		
		empleadoService.updateEmployee(empleado);
	}
	
	//método para dar de baja directamente
	@PostMapping(path="empleados/baja/{id}")
	public void baja(@PathVariable(name="id") int id) {
			empleadoService.darBaja(id);
		}
	
	//método para volver a dar de alta
	@PostMapping(path="empleados/volver-alta")
	@ResponseBody
	public void volver(@RequestBody Map<String, String> json){
		
		int idEmp = Integer.parseInt(json.get("id_empleado"));
		
		empleadoService.volver(idEmp);
		
		
	}
	
}