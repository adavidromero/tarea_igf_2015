package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_aplicativo",catalog="modelo_proyecto", schema="")
public class Aplicativo implements Serializable{
	private static final long serialVersionUID=1L;
	
	@Id
	@Column(name="c_aplicativo",nullable = false, length=5)
	private String cAplicativo;
	
	@Column(name="d_aplicativo",nullable = false, length=100)
	private String dAplicativo;

	@Basic(optional = false)
	@Column(name = "f_ingreso")
	private Date fIngreso;


    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cAplicativo")
	private  List<Clase> clases= new ArrayList();

	public Aplicativo(){
		
	}
	
	public Aplicativo(String cAplicativo, String dAplicativo, Date fIngreso) {
		this.cAplicativo=cAplicativo;
		this.dAplicativo=dAplicativo;
		this.fIngreso=fIngreso;
	}

	public String getcAplicativo() {
		return cAplicativo;
	}

	public void setcAplicativo(String cAplicativo) {
		this.cAplicativo = cAplicativo;
	}

	public String getdAplicativo() {
		return dAplicativo;
	}

	public void setdAplicativo(String dAplicativo) {
		this.dAplicativo = dAplicativo;
	}

	public Date getfIngreso() {
		return fIngreso;
	}

	public void setfIngreso(Date fIngreso) {
		this.fIngreso = fIngreso;
	}

	public List<Clase> getClases() {
		return clases;
	}

	public void setClases(List<Clase> clases) {
		this.clases = clases;
	}
	

}
