package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;



// @View(members="trabajo.servicio.descripcion,trabajo.tipovehiculo.descripcion")
@Tab(properties="rotulo,subtotal")
@Entity
@Table(name="LV_TRABAJOSDET"
,
uniqueConstraints={ 
	@UniqueConstraint(name="td_no_repetir_detalle", columnNames={"trabajo_id"})		
	}
		)
public class TrabajosDet extends SuperClaseFeliz {

	@ManyToOne
	@JoinColumn(name="TRABAJOSCAB_ID")
	private TrabajosCab cabecero ;				

	@DescriptionsList(descriptionProperties="rotulo")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TRABAJO_ID")
	private Precios trabajo ;
	
	@ReadOnly
	@Stereotype("MONEY")
	@Column(name="subtotal",nullable=true,length=20)
	private double subtotal ;	

	@ReadOnly
	@Column(name="rotulo")
	private String rotulo;

	public TrabajosCab getCabecero() {
		return cabecero;
	}

	public void setCabecero(TrabajosCab cabecero) {
		this.cabecero = cabecero;
	}



	public Precios getTrabajo() {
		return trabajo;
	}



	public void setTrabajo(Precios trabajo) {
		this.trabajo = trabajo;
	}



	public double getSubtotal() {
		return subtotal;
	}



	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}



	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	@PreUpdate
	public void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;	
			this.setRotulo(this.getTrabajo().getRotulo());
			this.setSubtotal(this.getTrabajo().getPrecio());
	}	
	@PrePersist
	private void antesDeGrabar() {
		this.setRotulo(this.getTrabajo().getRotulo());
		this.setSubtotal(this.getTrabajo().getPrecio());

	}
}
