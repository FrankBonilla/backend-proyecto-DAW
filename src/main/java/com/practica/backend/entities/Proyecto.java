package com.practica.backend.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;



@Entity
@Table(name="PR_PROYECTOS")
public class Proyecto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id_proyecto;
	@Column(name="TEXT_DESCRIPCION",nullable= false,length= 125)
	private String descripcion;
	@Column(name="F_INICIO",nullable= false)
	private Date f_inicio;
	@Column(name="F_FIN",nullable= true)
	private Date f_fin;
	@Column(name="F_BAJA",nullable= true)
	private Date f_baja;
	@Column(name="TX_LUGAR",nullable= true,length= 30)
	private String lugar;
	@Column(name="TEXT_OBSERVACIONES",nullable= true,length= 300)
	private String observaciones;
	
	@ManyToMany(cascade= {
			CascadeType.PERSIST,
			CascadeType.MERGE
	})
	@JoinTable(name="pr_empleados_proyecto",
			joinColumns=@JoinColumn(name="id_proyecto"),
			inverseJoinColumns= @JoinColumn(name="id_empleado"))
	private List<Empleado> empleados = new ArrayList<>();
    
	
	/**Constructor por defecto **/
	public Proyecto() {
		
	}
	/**Constructor con parámetros **/
	public Proyecto(String descripcion, Date f_inicio) {
		super();
		this.descripcion = descripcion;
		this.f_inicio = f_inicio;
	}

	/**Getters and Setters **/
	public int getId_proyecto() {
		return id_proyecto;
	}

	public void setId_proyecto(int id_proyecto) {
		this.id_proyecto = id_proyecto;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getF_inicio() {
		return f_inicio;
	}

	public void setF_inicio(Date f_inicio) {
		this.f_inicio = f_inicio;
	}

	public Date getF_fin() {
		return f_fin;
	}

	public void setF_fin(Date f_fin) {
		this.f_fin = f_fin;
	}

	public Date getF_baja() {
		return f_baja;
	}

	public void setF_baja(Date f_baja) {
		this.f_baja = f_baja;
	}

	public String getLugar() {
		return lugar;
	}

	public void setLugar(String lugar) {
		this.lugar = lugar;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	//metodos de la lista
	public List<Empleado> getEmployees(){
		return empleados;
	}
	
	public void setEmployees(List<Empleado> empleados) {
		this.empleados = empleados;
	}
	
	public void addEmployee(Empleado empleado) {
		empleados.add(empleado);
	}
	
	public void removeEmployee(Empleado empleado) {
		empleados.remove(empleado);;
	}
	/**Equals and HashCode **/
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Proyecto proyecto = (Proyecto) o;
		return id_proyecto == proyecto.id_proyecto && Objects.equals(descripcion, proyecto.descripcion) && Objects.equals(f_inicio, proyecto.f_inicio) && Objects.equals(f_fin, proyecto.f_fin) && Objects.equals(f_baja, proyecto.f_baja) && Objects.equals(lugar, proyecto.lugar) && Objects.equals(observaciones, proyecto.observaciones) && Objects.equals(empleados, proyecto.empleados);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_proyecto, descripcion, f_inicio, f_fin, f_baja, lugar, observaciones, empleados);
	}
	/**toString **/
	@Override
	public String toString() {
		return "Proyecto{" +
				"id_proyecto=" + id_proyecto +
				", descripcion='" + descripcion + '\'' +
				", f_inicio=" + f_inicio +
				", f_fin=" + f_fin +
				", f_baja=" + f_baja +
				", lugar='" + lugar + '\'' +
				", observaciones='" + observaciones + '\'' +
				", empleados=" + empleados +
				'}';
	}
}
