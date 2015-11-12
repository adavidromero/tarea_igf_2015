package com.fia.igf.app.dominio;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="tb_tipo_metodo")
public class TipoMetodo implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "c_tipo_metodo", length=1)
	private String cTipoMetodo = new String();


	@Basic(optional = false)
	@Column(name="d_tipo_metodo", length=30)
	private String dTipoMetodo;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;

    @Basic(optional = true)
	@OneToMany(cascade=CascadeType.ALL, mappedBy="cTipoMetodo")
	private List<Metodo> metodos;
    
	public TipoMetodo(){
	    	
	    }

	// constructor
	public TipoMetodo(String cTipoMetodo, String dTipoMetodo, Date fIngreso){
		this.cTipoMetodo = cTipoMetodo;
		this.dTipoMetodo = dTipoMetodo;
		this.fIngreso = fIngreso;
	}

    

	public String getcTipoMetodo() {
		return cTipoMetodo;
	}

	public void setcTipoMetodo(String cTipoMetodo) {
		this.cTipoMetodo = cTipoMetodo;
	}

	public String getdTipoMetodo() {
		return dTipoMetodo;
	}

	public void setdTipoMetodo(String dTipoMetodo) {
		this.dTipoMetodo = dTipoMetodo;
	}

	public Date getfIngreso() {
		return fIngreso;
	}

	public void setfIngreso(Date fIngreso) {
		this.fIngreso = fIngreso;
	}

	public List<Metodo> getMetodos() {
		return metodos;
	}

	public void setMetodos(List<Metodo> metodos) {
		this.metodos = metodos;
	}
    
}
