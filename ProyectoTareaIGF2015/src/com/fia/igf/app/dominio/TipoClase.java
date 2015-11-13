package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tb_tipo_clase")
public class TipoClase  implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "c_tipo_clase")
	private String cTipoClase;

	@Basic(optional = false)
    @Column(name = "d_tipo_clase")
	private String dTipoClase;

	@Basic(optional = false)
	@Column(name = "f_ingreso")
	private Date fIngreso;

    @Basic(optional = true)
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "cTipoClase")
    private List<Clase> clases = new ArrayList();
    
	public List<Clase> getClases() {
		return clases;
	}
	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	public TipoClase() {
		// Constructor utilizado por hibernate
	}
	public TipoClase(String cTipoClase, String descripcion, Date fechaIngreso) {
		this.cTipoClase=cTipoClase;
		this.dTipoClase=descripcion;
		this.fIngreso=fechaIngreso;
		// TODO Auto-generated constructor stub
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
	public Date getfIngreso() {
		return fIngreso;
	}
	public void setfIngreso(Date fIngreso) {
		this.fIngreso = fIngreso;
	}
	
}
