package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fia.igf.app.datos.MetodoDAO;
import com.fia.igf.app.dominio.Metodo;

@Transactional
@Service
public class CtrlMetodo {
	@Autowired 
	private MetodoDAO metodoDAO;

	@Autowired
	private CtrlMetodo() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CtrlMetodo(MetodoDAO metodoDAO) {
		// TODO Auto-generated constructor stub
		this.metodoDAO = metodoDAO;
	}
	
	public boolean crearMetodo(Integer id, String descripcion, Date fechaIngreso){
		if(metodoDAO.obtenerPorId(Metodo.class, id.toString())==null){
			Metodo metodo = new Metodo(id,descripcion,fechaIngreso);
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
	
	public List<Metodo> obtenerTodosMetodo(){
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
