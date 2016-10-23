package com.compufiber.model;

import java.util.*;

import javax.persistence.*;

import org.openxava.util.*;

@Entity
@Table(name="LV_LIBROCAJA")
public class LibroCaja extends SuperClaseFeliz {

	
	@PreUpdate
	private void ultimoPaso() {
			Date mifechora = new java.util.Date() ;
			super.setModificadoPor(Users.getCurrent()) ;
			super.setFchUltMod(mifechora)  ;
	}	
}
