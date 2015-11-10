package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.TipoClaseDAO;
import com.fia.igf.app.dominio.TipoClase;


@Transactional
@Service
public class CtrlTipoClase {
	@Autowired 
	private TipoClaseDAO tipoClaseDao;
	

	@Autowired
	public CtrlTipoClase(TipoClaseDAO tipoClaseDao){
		this.tipoClaseDao=tipoClaseDao;
	}

	public boolean crearTipoClase(String id,String descripcion,Date fechaIngreso){
		if(tipoClaseDao.obtenerPorId(TipoClase.class, id)==null){
				TipoClase tipoClase = new TipoClase(id,descripcion,fechaIngreso);
				tipoClaseDao.guardaActualiza(tipoClase);
				return true ;
		}
		else{
				return false ;
		}

	}
	
	public boolean borraClase(String id){
		if(tipoClaseDao.obtenerPorId(TipoClase.class, id)!=null){
			TipoClase tipoClase = tipoClaseDao.obtenerPorId(TipoClase.class, id);
			tipoClaseDao.eliminar(tipoClase);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<TipoClase> obtenerTodosClases(){
		return tipoClaseDao.obtenerTodos();
	}

	public TipoClase obtenerPorId(String id){
		return tipoClaseDao.obtenerPorId(TipoClase.class, id);
	}
	
	public boolean actualizar(TipoClase tipoClase){
		tipoClaseDao.guardaActualiza(tipoClase);
		return false;
	}

}