package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.calculators.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

@Tab(properties="trabajoNro,fechatrabajo,cliente.nombre,vehiculo.chapa,vehiculo.marca.descripcion")
//@View(members="trabajoNro,fechatrabajo;sucursal,cliente;empleado,formadepago,estadoventa ; detalles; wexento,wgravado,wiva,wtotal;nrotjch")
@Entity
@Table(name="LV_TRABAJOSCAB")
public class TrabajosCab extends SuperClaseFeliz {
	@ReadOnly	
	@Column(length=19,nullable=false,name="trabajonro",unique=true)
	private Long trabajoNro = 0L; 
	
	@Required
	@DefaultValueCalculator(CurrentDateCalculator.class)
	@Column(nullable=false,name="FECHATRABAJO")
	private Date fechatrabajo ; 

	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="CLIENTES_ID")
	private Clientes cliente ;		
	
	@DescriptionsList(descriptionProperties="chapa,color.descripcion,marca.descripcion")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="VEHICULOS_ID")
	private Vehiculos vehiculo ;		

	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="EMPLEADOS_ID")
	private Empleados empleado ;		
	
	
	@Stereotype("MEMO")
	@Column(length=500,nullable=true,name="COMENTARIO")
	// @Column(nullable=true,name="COMENTARIO")
	private String comentario;	

	@Stereotype("TIME")
	@Required
	@Column(name="HORAESTIMADAFIN",nullable=false)	
	private String horaEstimadaFin;
	
	@ReadOnly
	@Stereotype("TIME")
	@Column(name="HORACALCULADAFIN")	
	private String horaCalculadaFin;	
	
	@Hidden
	@Column(name="rotulo")
	private String rotulo;	
	
	//@ListProperties("producto.codprod,producto.descripcion,tipoiva.descripcion, comisionista.nombre,cantidad,precio,texento,tgravado,tiva,tgeneral")
	@OneToMany(mappedBy="cabecero",cascade=CascadeType.ALL)
	private Collection<TrabajosDet> detalles = new ArrayList<TrabajosDet>() ;	
	
	public Long getTrabajoNro() {
		return trabajoNro;
	}

	public void setTrabajoNro(Long trabajoNro) {
		this.trabajoNro = trabajoNro;
	}
	
	public Date getFechatrabajo() {
		return fechatrabajo;
	}

	public void setFechatrabajo(Date fechatrabajo) {
		this.fechatrabajo = fechatrabajo;
	}

	public Collection<TrabajosDet> getDetalles() {
		return detalles;
	}

	public void setDetalles(Collection<TrabajosDet> detalles) {
		this.detalles = detalles;
	}

	public String getHoraEstimadaFin() {
		return horaEstimadaFin;
	}
	
	public String getHoraCalculadaFin() {
		return horaCalculadaFin;
	}

	public void setHoraCalculadaFin(String horaCalculadaFin) {
		this.horaCalculadaFin = horaCalculadaFin;
	}

	public void setHoraEstimadaFin(String horaEstimadaFin) {
		this.horaEstimadaFin = horaEstimadaFin;
	}
	

	public String getRotulo() {
		return rotulo;
	}

	public void setRotulo(String rotulo) {
		this.rotulo = rotulo;
	}

	private Long siguienteventasNro() {
		Date ahora = new Date() ;
		Calendar micalendario = Calendar.getInstance() ;
		Integer anholargo  = 0 ;	
		Integer meslargo  = 0 ;
		Long ventasfeliz = 0L;
		Long ultimoNumero = 0L;
		Long cual = 0L ;
		
		Query w = XPersistence.getManager().createQuery("select count(*) as numerazo from TrabajosCab i where year(i.fechatrabajo) = :anhofeliz and month(i.fechatrabajo) = :mesfeliz");
			micalendario.setTime(ahora);		
			anholargo = micalendario.get(Calendar.YEAR) ;
			meslargo  = micalendario.get(Calendar.MONTH) + 1;
			w.setParameter("anhofeliz",anholargo) ;
			w.setParameter("mesfeliz",meslargo) ;		
			ultimoNumero =  (Long) w.getSingleResult();
			cual = ultimoNumero + 1;
			ventasfeliz =  (Long.valueOf(anholargo) * 1000000L) + (Long.valueOf(meslargo) * 10000L) + cual ;	
		
			return ventasfeliz ;		
	}	
	
	public Clientes getCliente() {
		return cliente;
	}

	public void setCliente(Clientes cliente) {
		this.cliente = cliente;
	}

	public Vehiculos getVehiculo() {
		return vehiculo;
	}

	public void setVehiculo(Vehiculos vehiculo) {
		this.vehiculo = vehiculo;
	}

	public Empleados getEmpleado() {
		return empleado;
	}

	public void setEmpleado(Empleados empleado) {
		this.empleado = empleado;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario.toUpperCase().trim();
	}

	@PreUpdate
	public void ultimoPaso() {			
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;			
			this.setRotulo(this.getVehiculo().getRotulo());
	}	
	
	@PrePersist
	private void antesDeGrabar() {
		this.setTrabajoNro(this.siguienteventasNro());
		this.setRotulo(this.getVehiculo().getRotulo());
		// this.calcularTotales();
	}
	
}
