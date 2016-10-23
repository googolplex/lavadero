package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;

@Entity
@Table(name="LV_VEHICULOS")
public class Vehiculos extends SuperClaseFeliz {

	@Required
//	@PropertyValidator(value=ValidadorTipoIva.class,message="TipoIva duplicado",onlyOnCreate=true)
	@Column(length=7,nullable=false,name="CHAPA",unique=true)
	private String chapa ;

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TIPOVEHICULOS_ID")
	private TipoVehiculos tipovehiculo;

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="MARCAS_ID")
	private Marcas marca;
	
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="MODELOS_ID")	
	private Modelos modelo;

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="COLOR_ID")
	private Colores color;

	@Column(length=200,nullable=true,name="COMENTARIO")
	private String comentario;
	
	public String getChapa() {
		return chapa;
	}

	public void setChapa(String chapa) {
		this.chapa = chapa.toUpperCase().trim();
	}


	public TipoVehiculos getTipovehiculo() {
		return tipovehiculo;
	}

	public void setTipovehiculo(TipoVehiculos tipovehiculo) {
		this.tipovehiculo = tipovehiculo;
	}

	public Marcas getMarca() {
		return marca;
	}

	public void setMarca(Marcas marca) {
		this.marca = marca;
	}

	public Modelos getModelo() {
		return modelo;
	}

	public void setModelo(Modelos modelo) {
		this.modelo = modelo;
	}

	public Colores getColor() {
		return color;
	}

	public void setColor(Colores color) {
		this.color = color;
	}

	public String getComentario() {
		return comentario.toUpperCase().trim();
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}
