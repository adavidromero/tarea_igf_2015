package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fia.igf.app.datos.TipoMetodoDAO;

import com.fia.igf.app.dominio.TipoMetodo;


public class CtrlTipoMetodo {
	@Autowired
	private TipoMetodoDAO tipoMetodoDAO;
	@Autowired
	public CtrlTipoMetodo(TipoMetodoDAO tipoMetodoDAO) {
		// TODO Auto-generated constructor stub		
		this.tipoMetodoDAO = tipoMetodoDAO;
	}


	public boolean crearTipoMetodo(String id, String descripcion, Date fechaIngreso){
		if(tipoMetodoDAO.obtenerPorId(TipoMetodo.class, id)==null){
				TipoMetodo tipoMetodo = new TipoMetodo(id,descripcion,fechaIngreso);
				tipoMetodoDAO.guardaActualiza(tipoMetodo);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraTipoMetodo(String id){
		if(tipoMetodoDAO.obtenerPorId(TipoMetodo.class, id)!=null){
			try{
				TipoMetodo tipoMetodo = tipoMetodoDAO.obtenerPorId(TipoMetodo.class, id);
				tipoMetodoDAO.eliminar(tipoMetodo);
			}catch(Exception e){
				return false;
			}
		}
		else{
			return false;
		}
		return true;
	}

	public List<TipoMetodo> obtenerTodosTipoMetodos(){
		return tipoMetodoDAO.obtenerTodos();
	}

	public TipoMetodo obtenerPorId(String id){
		return tipoMetodoDAO.obtenerPorId(TipoMetodo.class, id);
	}
	
	public boolean actualizar(TipoMetodo tipoMetodo){
		tipoMetodoDAO.guardaActualiza(tipoMetodo);
		return false;
	}
}
