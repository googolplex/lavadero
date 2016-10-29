package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.util.*;
@Tab(properties="tipovehiculo.descripcion,marca.descripcion,color.descripcion,chapa,modelo")
@Entity
@Table(name="LV_VEHICULOS",
uniqueConstraints={
        @UniqueConstraint(name="vh_no_repetir_chapa", columnNames={"chapa"})
    })
public class Vehiculos extends SuperClaseFeliz {

	@Required
	@Column(length=20,nullable=false,name="CHAPA")
	private String chapa ;

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="TIPOVEHICULOS_ID")
	private TipoVehiculos tipovehiculo;

	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="MARCAS_ID")
	private Marcas marca;

	@Required
	@Column(length=20,nullable=false,name="MODELO")
	private Long modelo ;
		
	@DescriptionsList(descriptionProperties="descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="COLOR_ID")
	private Colores color;

	@Stereotype("memo")
	@Column(name="COMENTARIO")
	private String comentario;
	
	@Hidden
	@Column(name="rotulo")
	private String rotulo;	
	
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


	public Long getModelo() {
		return modelo;
	}

	public void setModelo(Long modelo) {
		this.modelo = modelo;
	}

	public Colores getColor() {
		return color;
	}

	public void setColor(Colores color) {
		this.color = color;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario.toUpperCase().trim();
	}

	
	
	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
			this.setRotulo(this.getChapa()+"-"+this.getMarca().getDescripcion()+"-"+this.getColor().getDescripcion()+"-"+this.getTipovehiculo().getDescripcion());			
	}	
	@PrePersist
	private void antesDeGrabar() {
		this.setRotulo(this.getChapa()+"-"+this.getMarca().getDescripcion()+"-"+this.getColor().getDescripcion()+"-"+this.getTipovehiculo().getDescripcion());
	}
}
