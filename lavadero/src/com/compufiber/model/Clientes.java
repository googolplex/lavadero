package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;
// import com.compufiber.validadores.*;

@Entity
@Table(name="LV_CLIENTES", 
uniqueConstraints={
        @UniqueConstraint(name="cl_no_repetir_cliente", columnNames={"nombre"}),
        @UniqueConstraint(name="cl_no_repetir_ruc", columnNames={"ruc"})
    }
		)
public class Clientes extends SuperClaseFeliz {
	@Required
	@Column(length=20,nullable=false,name="RUC")
	private String ruc ;

	@Required
	@Column(length=100,nullable=false,name="NOMBRE")	
	private String nombre ;

	@Stereotype("PHONE")
	@Column(length=40,nullable=true,name="CELULAR")	
	private String celular ;
	

	@Stereotype("EMAIL")
	@Column(length=100,nullable=true,name="CORREO")	
	private String correo ;
	
	@DescriptionsList(descriptionProperties="direccion, ciudad, barrio")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="DIRECCION_ID")
	private DireccionesClientes direccion ;		

	public String getRuc() {
		return ruc;
	}

	public void setRuc(String ruc) {
		this.ruc = ruc.toUpperCase().trim();
	}



	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre.toUpperCase().trim();
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular.toUpperCase().trim();
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public DireccionesClientes getDireccion() {
		return direccion;
	}

	public void setDireccion(DireccionesClientes direccion) {
		this.direccion = direccion;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}