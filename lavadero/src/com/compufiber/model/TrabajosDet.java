package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;


//@Tab(properties="producto.codprod,producto.descripcion,comisionista,cantidad,precio,texento,tgravado,tiva,tgeneral")
//@View(members="producto,cantidad,stock;precio,tipoiva;comisionista;Totales [texento,tgravado,tiva,tgeneral]")
@Entity
@Table(name="LV_TRABAJOSDET")
public class TrabajosDet extends SuperClaseFeliz {

	@ManyToOne
	@JoinColumn(name="TRABAJOSCAB_ID")
	private TrabajosCab cabecero ;				

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="PRECIOS_ID")
	private Precios precio ;


	@Column(name="FCHHORAINICIO",nullable=false)
	private Date FechoraInicio = new java.util.Date();
	@Column(name="FCHHORAFIN",nullable=false)	
	private Date FechoraFin = new java.util.Date();

	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="EMPLEADOS_ID")
	private Empleados comisionista ;		
	
	public TrabajosCab getCabecero() {
		return cabecero;
	}

	public void setCabecero(TrabajosCab cabecero) {
		this.cabecero = cabecero;
	}

	public Precios getPrecio() {
		return precio;
	}

	public void setPrecio(Precios precio) {
		this.precio = precio;
	}
	

	public Date getFechoraInicio() {
		return FechoraInicio;
	}

	public void setFechoraInicio(Date fechoraInicio) {
		FechoraInicio = fechoraInicio;
	}

	public Date getFechoraFin() {
		return FechoraFin;
	}

	public void setFechoraFin(Date fechoraFin) {
		FechoraFin = fechoraFin;
	}

	public Empleados getComisionista() {
		return comisionista;
	}

	public void setComisionista(Empleados comisionista) {
		this.comisionista = comisionista;
	}

	@PreUpdate
	public void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
	//		this.precio = this.getPrecioFeliz() ;
	//		this.mizTotales();
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	//		this.getCabecero().calcularTotales();			
	}	
	@PrePersist
	private void antesDeGrabar() {
	//	this.precio = this.getPrecioFeliz() ;
	//	this.mizTotales();
	//	this.getCabecero().getDetalles().add(this);		// para pasar los totales al cabecero
	//	this.getCabecero().calcularTotales();
	}
}
