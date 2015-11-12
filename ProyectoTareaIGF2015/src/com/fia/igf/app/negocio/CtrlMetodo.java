package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fia.igf.app.datos.MetodoDAO;
import com.fia.igf.app.dominio.*;


@Transactional
@Service
public class CtrlMetodo {
	@Autowired 
	private MetodoDAO metodoDAO;

	@Autowired
	public CtrlMetodo() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CtrlMetodo(MetodoDAO metodoDAO) {
		// TODO Auto-generated constructor stub
		this.metodoDAO = metodoDAO;
	}

	public boolean crearMetodo(Integer cMetodo, Clase cClase, TipoMetodo ctipoMetodo, String dMetodo, String dtipoRetorno, String usuario, Date fechaIngreso, Integer activo, Integer parametros ){
		if(metodoDAO.obtenerPorId(Metodo.class, cMetodo.toString())==null){
			Metodo metodo = new Metodo();
			metodoDAO.guardaActualiza(metodo);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	
	public boolean borraMetodo(String id){
		if(metodoDAO.obtenerPorId(Metodo.class, id)!=null){
			Metodo metodo = metodoDAO.obtenerPorId(Metodo.class, id);
			metodoDAO.eliminar(metodo);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Metodo> obtenerTodosMetodos(){
		return metodoDAO.obtenerTodos();
	}

	public Metodo obtenerPorId(String id){
		return metodoDAO.obtenerPorId(Metodo.class, id);
	}
	
	public boolean actualizar(Metodo metodo){
		metodoDAO.guardaActualiza(metodo);
		return false;
	}
	
}
