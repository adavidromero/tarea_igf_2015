package com.fia.igf.app.dominio;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable()
public class MetodoPK implements Serializable {
	//default serial version id, required for serializable classes.
    private static final long serialVersionUID = 1L;
    
    @Column(name = "c_metodo")
    private Integer cMetodo;
		
	@ManyToOne 
	@JoinColumn(name = "c_clase", referencedColumnName = "c_clase")
	private Clase cClase;
		
	@ManyToOne 
	@JoinColumn(name = "c_tipo_metodo", referencedColumnName = "c_tipo_metodo")
	private TipoMetodo cTipoMetodo;
	
	//Generando set and get
	public Integer getcMetodo() {
		return cMetodo;
	}

	public void setcMetodo(Integer cMetodo) {
		this.cMetodo = cMetodo;
	}

	public Clase getcClase() {
		return cClase;
	}

	public void setcClase(Clase cClase) {
		this.cClase = cClase;
	}

	public TipoMetodo getcTipoMetodo() {
		return cTipoMetodo;
	}

	public void setcTipoMetodo(TipoMetodo cTipoMetodo) {
		this.cTipoMetodo = cTipoMetodo;
	}	
}
