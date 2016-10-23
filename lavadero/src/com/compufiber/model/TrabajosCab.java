package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.annotations.*;
import org.openxava.jpa.*;
import org.openxava.util.*;

//@Tab(properties="ventasNro,fechaventas,sucursal.nombre,empleado.nombre,estadoventa.descripcion,yexento,ygravado,yiva,ytotal")
//@View(members="ventasNro,fechaventas;sucursal,cliente;empleado,formadepago,estadoventa ; detalles; wexento,wgravado,wiva,wtotal;nrotjch")
@Entity
@Table(name="LV_TRABAJOSCAB")
public class TrabajosCab extends SuperClaseFeliz {
	@ReadOnly	
	@Column(length=19,nullable=false,name="ventasnro",unique=true)
	private Long trabajoNro = 0L; 

	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="CLIENTES_ID")
	private Clientes cliente ;		
	
	@DescriptionsList(descriptionProperties="chapa,color,marca")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="VEHICULOS_ID")
	private Vehiculos vehiculo ;		

	@DescriptionsList(descriptionProperties="nombre")
	@ManyToOne(fetch=FetchType.LAZY,optional=false)	
	@JoinColumn(name="EMPLEADOS_ID")
	private Empleados empleado ;		
	
	@Column(length=200,nullable=true,name="COMENTARIO")
	private String comentario;	

	@ListProperties("producto.codprod,producto.descripcion,tipoiva.descripcion, comisionista.nombre,cantidad,precio,texento,tgravado,tiva,tgeneral")
	@OneToMany(mappedBy="cabecero",cascade=CascadeType.ALL)
	private Collection<TrabajosDet> detalles = new ArrayList<TrabajosDet>() ;	
	
	
	private Long siguienteventasNro() {
		Date ahora = new Date() ;
		Calendar micalendario = Calendar.getInstance() ;
		Integer anholargo  = 0 ;	
		Integer meslargo  = 0 ;
		Long ventasfeliz = 0L;
		Long ultimoNumero = 0L;
		Long cual = 0L ;
		
		Query w = XPersistence.getManager().createQuery("select count(*) as numerazo from VentasCab i where year(i.fechaventas) = :anhofeliz and month(i.fechaventas) = :mesfeliz");
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
	
	
	
	public Long getVentasNro() {
		return trabajoNro;
	}

	public void setVentasNro(Long ventasNro) {
		this.trabajoNro = ventasNro;
	}

	public Long getTrabajoNro() {
		return trabajoNro;
	}

	public void setTrabajoNro(Long trabajoNro) {
		this.trabajoNro = trabajoNro;
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
			// this.calcularTotales();
	}	
	
	@PrePersist
	private void antesDeGrabar() {
		this.setVentasNro(this.siguienteventasNro()) ;
		// this.calcularTotales();
	}	
}
