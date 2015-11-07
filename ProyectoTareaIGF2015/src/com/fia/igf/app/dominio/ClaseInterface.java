package com.fia.igf.app.dominio;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "as_clase_interface")
public class ClaseInterface {
	private static final long serialVersionUID = 1L;

	@Id
    @Basic(optional = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "c_clase_interface")
    private Integer cClaseInterface;

	@ManyToOne
	@JoinColumn(name = "c_clase", referencedColumnName = "c_clase")
	private Clase cClase;

	@ManyToOne
	@JoinColumn(name = "c_interface", referencedColumnName = "c_interface")
	private Interface cInterface;

	public ClaseInterface(){
		
	}

	public Integer getcClaseInterface() {
		return cClaseInterface;
	}

	public void setcClaseInterface(Integer cClaseInterface) {
		this.cClaseInterface = cClaseInterface;
	}


	public Clase getcClase() {
		return cClase;
	}

	public void setcClase(Clase cClase) {
		this.cClase = cClase;
	}

	public Interface getcInterface() {
		return cInterface;
	}

	public void setcInterface(Interface cInterface) {
		this.cInterface = cInterface;
	}

}
