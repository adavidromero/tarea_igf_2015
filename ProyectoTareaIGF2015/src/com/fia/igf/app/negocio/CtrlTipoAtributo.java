package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.TipoAtributoDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.TipoAtributo;

@Transactional
@Service
public class CtrlTipoAtributo {
	@Autowired 
	private TipoAtributoDAO tipoAtributoDao;

	@Autowired
	public CtrlTipoAtributo(TipoAtributoDAO tipoAtributoDao){
		this.tipoAtributoDao=tipoAtributoDao;
	}

	public boolean crearTipoAtributo(String id, String descripcion, Date fechaIngreso){
		if(tipoAtributoDao.obtenerPorId(TipoAtributo.class, id)==null){
				TipoAtributo tipoAtributo = new TipoAtributo(id,descripcion,fechaIngreso);
				tipoAtributoDao.guardaActualiza(tipoAtributo);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraAplicativo(String id){
		if(tipoAtributoDao.obtenerPorId(TipoAtributo.class, id)!=null){
			TipoAtributo tipoAtributo = tipoAtributoDao.obtenerPorId(TipoAtributo.class, id);
			tipoAtributoDao.eliminar(tipoAtributo);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<TipoAtributo> obtenerTodosTiposAtributos(){
		return tipoAtributoDao.obtenerTodos();
	}

	public TipoAtributo obtenerPorId(String id){
		return tipoAtributoDao.obtenerPorId(TipoAtributo.class, id);
	}
	
	public boolean actualizar(TipoAtributo tipoAtributo){
		tipoAtributoDao.guardaActualiza(tipoAtributo);
		return false;
	}

}
