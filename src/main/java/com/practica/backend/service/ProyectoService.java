package com.practica.backend.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.practica.backend.dao.ProyectoDao;
import com.practica.backend.entitie.Empleado;
import com.practica.backend.entitie.Proyecto;

@Service
public class ProyectoService {
	
	@Autowired
	private ProyectoDao repo;
	
	public List<Proyecto> listar(){
	
		return repo.findAll();
	}
	
	public Proyecto listarId(int id) {
		
		return repo.getById(id);
	}
	
	public void save(Proyecto proyecto) {
		
		repo.save(proyecto);
	}
	
	public void delete(int id) {
		
		repo.deleteById(id);
	}
	//mostrar los proyexctos que estan dados de alta
	public List<Proyecto> mostrarActivos(){
		
		return repo.findByF_alta();
	}
	//mostrar los proyectos que esten dados de baja
	public List<Proyecto> mostrarInactivos(){
		
		return repo.findByF_baja();
	}
	
	//metodo para dar de baja el pryecto
	public void darBaja(int id) {
		
		Proyecto proyecto = repo.getById(id);
		Date fecha = new Date();
		proyecto.setF_baja(fecha);
		repo.save(proyecto);
		
	}
	
	public void updateProject(Proyecto p) {
		
		Proyecto proyecto = repo.getById(p.getId_proyecto());
		proyecto.setDescripcion(p.getDescripcion());
		proyecto.setF_inicio(p.getF_inicio());
		proyecto.setF_fin(p.getF_fin());
		proyecto.setLugar(p.getLugar());
		proyecto.setObservaciones(p.getObservaciones());
		
		repo.save(proyecto);
	}
	
	//implantacion del método 2 definido en el DAO
	public List<?> searchProjectsOfEmple(int id){
		
		List<?> list = repo.listProjects(id);
		if(list != null || list.size() != 0) {
			
			return list;
			
		}else {
			
			return null;
		}
		
	}

}