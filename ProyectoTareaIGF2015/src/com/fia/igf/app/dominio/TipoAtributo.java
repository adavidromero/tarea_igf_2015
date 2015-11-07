package com.fia.igf.app.dominio;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="tb_tipo_atributo")
public class TipoAtributo {
	private static final long serialVersionUID = 1L;

	@Id
	@Basic(optional = false)
    @Column(name = "c_tipo_atributo", length=1)
	private String cTipoAtributo = new String();

	@Basic(optional = false)
    @Column(name = "d_tipo_atributo", length=50)
	private String dTipoAtributo;

    @Basic(optional = false)
    @Column(name = "f_ingreso")
    @Temporal(TemporalType.DATE)
    private Date fIngreso;
    
    public TipoAtributo(){
    	
    }

	public String getcTipoAtributo() {
		return cTipoAtributo;
	}

	public void setcTipoAtributo(String cTipoAtributo) {
		this.cTipoAtributo = cTipoAtributo;
	}

	public String getdTipoAtributo() {
		return dTipoAtributo;
	}

	public void setdTipoAtributo(String dTipoAtributo) {
		this.dTipoAtributo = dTipoAtributo;
	}

	public Date getfIngreso() {
		return fIngreso;
	}

	public void setfIngreso(Date fIngreso) {
		this.fIngreso = fIngreso;
	}

}