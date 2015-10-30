package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_clase")
public class TipoClase  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic(optional = false)
    @Column(name = "c_tipo_clase")
	private String cTipoClase = new String();
	@Basic(optional = false)
    @Column(name = "d_tipo_clase")
	private String dTipoClase = new String();
	private GregorianCalendar fIngreso = new GregorianCalendar();
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "TipoClase", fetch = FetchType.EAGER)
    private List<Clase> Clase;
	
	public TipoClase() {
		// Constructor utilizado por hibernate
	}
	public String getcTipoClase() {
		return cTipoClase;
	}
	public void setcTipoClase(String cTipoClase) {
		this.cTipoClase = cTipoClase;
	}
	public String getdTipoClase() {
		return dTipoClase;
	}
	public void setdTipoClase(String dTipoClase) {
		this.dTipoClase = dTipoClase;
	}
	public GregorianCalendar getfIngreso() {
		return fIngreso;
	}
	public void setfIngreso(GregorianCalendar fIngreso) {
		this.fIngreso = fIngreso;
	}
	
}
