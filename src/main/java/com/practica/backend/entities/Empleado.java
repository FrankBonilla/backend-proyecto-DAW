package com.practica.backend.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.util.Objects;


@Entity
@Table(name="EMPLEADOS")
public class Empleado {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id_empleado")
	private int id_empleado;
	@Column(name="TX_NIF",nullable= false,length =9)
	private String NIF;
	@Column(name="TX_NOMBRE",nullable= false,length= 30)
	private String nombre;
	@Column(name="TX_APELLIDO1",nullable= false,length= 40)
	private String apellido1;
	@Column(name="TX_APELLIDO2",nullable= false,length= 40)
	private String apellido2;
	@Column(name="F_NACIMIENTO",nullable= false)
	private Date nacimiento;
	@Column(name="N_TELEFONO1",nullable=false,length= 12)
	private String telefono1;
	@Column(name="N_TELEFONO2",nullable= false,length= 12)
    private String telefono2;
	@Column(name="TX_EMAIL",nullable= false,length= 40)
    private String email;
	@Column(name="F_ALTA",nullable= false)
    private Date f_alta;
	@Column(name="F_BAJA")
    private Date f_baja;
	@Column(name="CX_EDOCIVIL",nullable= false, length= 1)
    private String edoCivil;
	@Column(name="B_SERMILITAR",nullable= false, length= 1)
    private String serMilitar;
	
	/**Constructor por defecto **/
    public Empleado() {
    	
    }
	/**Constructor con parámetros **/
	public Empleado(String nIF, String nombre, String apellido1, String apellido2, Date nacimiento, String telefono1,
					String telefono2, String email, Date f_alta, String edoCivil, String serMilitar) {
		super();
		this.NIF = nIF;
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.nacimiento = nacimiento;
		this.telefono1 = telefono1;
		this.telefono2 = telefono2;
		this.email = email;
		this.f_alta = f_alta;
		this.edoCivil = edoCivil;
		this.serMilitar = serMilitar;
	}
	
	/**Getters and Setters **/
	public int getId_empleado() {
		return id_empleado;
	}
	public void setId_empleado(int id_empleado) {
		this.id_empleado = id_empleado;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getNIF() {
		return NIF;
	}
	public void setNIF(String nIF) {
		NIF = nIF;
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}
	public Date getNacimiento() {
		return nacimiento;
	}
	public void setNacimiento(Date nacimiento) {
		this.nacimiento = nacimiento;
	}
	public String getTelefono1() {
		return telefono1;
	}
	public void setTelefono1(String telefono1) {
		this.telefono1 = telefono1;
	}
	public String getTelefono2() {
		return telefono2;
	}
	public void setTelefono2(String telefono2) {
		this.telefono2 = telefono2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getF_alta() {
		return f_alta;
	}
	public void setF_alta(Date f_alta) {
		this.f_alta = f_alta;
	}
	public Date getF_baja() {
		return f_baja;
	}
	public void setF_baja(Date f_baja) {
		this.f_baja = f_baja;
	}
	public String getEdoCivil() {
		return edoCivil;
	}
	public void setEdoCivil(String edoCivil) {
		this.edoCivil = edoCivil;
	}
	public String getSerMilitar() {
		return serMilitar;
	}
	public void setSerMilitar(String serMilitar) {
		this.serMilitar = serMilitar;
	}

	/**Equals and HashCode **/
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Empleado empleado = (Empleado) o;
		return id_empleado == empleado.id_empleado && Objects.equals(NIF, empleado.NIF) && Objects.equals(nombre, empleado.nombre) && Objects.equals(apellido1, empleado.apellido1) && Objects.equals(apellido2, empleado.apellido2) && Objects.equals(nacimiento, empleado.nacimiento) && Objects.equals(telefono1, empleado.telefono1) && Objects.equals(telefono2, empleado.telefono2) && Objects.equals(email, empleado.email) && Objects.equals(f_alta, empleado.f_alta) && Objects.equals(f_baja, empleado.f_baja) && Objects.equals(edoCivil, empleado.edoCivil) && Objects.equals(serMilitar, empleado.serMilitar);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id_empleado, NIF, nombre, apellido1, apellido2, nacimiento, telefono1, telefono2, email, f_alta, f_baja, edoCivil, serMilitar);
	}
	/**toString **/
	@Override
	public String toString() {
		return "Empleado{" +
				"id_empleado=" + id_empleado +
				", NIF='" + NIF + '\'' +
				", nombre='" + nombre + '\'' +
				", apellido1='" + apellido1 + '\'' +
				", apellido2='" + apellido2 + '\'' +
				", nacimiento=" + nacimiento +
				", telefono1='" + telefono1 + '\'' +
				", telefono2='" + telefono2 + '\'' +
				", email='" + email + '\'' +
				", f_alta=" + f_alta +
				", f_baja=" + f_baja +
				", edoCivil='" + edoCivil + '\'' +
				", serMilitar='" + serMilitar + '\'' +
				'}';
	}
}
