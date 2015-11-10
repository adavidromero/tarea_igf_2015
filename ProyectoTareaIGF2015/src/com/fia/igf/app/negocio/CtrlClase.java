package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.ClaseDAO;
import com.fia.igf.app.dominio.Clase;

@Transactional
@Service
public class CtrlClase {
	@Autowired 
	private ClaseDAO claseDao;
	@Autowired
	public CtrlClase(ClaseDAO claseDao){
		this.claseDao=claseDao;
	}

	public boolean crearClase(Integer id, String descripcion, Date fechaIngreso){
		if(claseDao.obtenerPorId(Clase.class, id)==null){
				Clase clase = new Clase();

				claseDao.guardaActualiza(clase);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraClase(Integer id){
		if(claseDao.obtenerPorId(Clase.class, id)!=null){
			Clase clase = claseDao.obtenerPorId(Clase.class, id);
			claseDao.eliminar(clase);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Clase> obtenerTodosClases(){
		return claseDao.obtenerTodos();
	}

	public Clase obtenerPorId(Integer id){
		return claseDao.obtenerPorId(Clase.class, id);
	}
	
	public boolean actualizar(Clase clase){
		claseDao.guardaActualiza(clase);
		return false;
	}

}
