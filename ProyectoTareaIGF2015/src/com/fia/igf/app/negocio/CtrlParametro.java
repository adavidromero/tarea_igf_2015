package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.ParametroDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Parametro;

@Transactional
@Service
public class CtrlParametro {
	@Autowired 
	private ParametroDAO parametroDao;

	@Autowired
	public CtrlParametro(ParametroDAO parametroDao){
		this.parametroDao=parametroDao;
	}

	public boolean crearParametro(String id, String descripcion, Date fechaIngreso){
		if(parametroDao.obtenerPorId(Parametro.class, Integer.parseInt(id))==null){
				Parametro parametro = new Parametro();
				parametroDao.guardaActualiza(parametro);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraParametro(String id){
		if(parametroDao.obtenerPorId(Parametro.class, Integer.parseInt(id))!=null){
			Parametro parametro = parametroDao.obtenerPorId(Parametro.class, Integer.parseInt(id));
			parametroDao.eliminar(parametro);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Parametro> obtenerTodosParametros(){
		return parametroDao.obtenerTodos();
	}

	public Parametro obtenerPorId(String id){
		return parametroDao.obtenerPorId(Parametro.class, Integer.parseInt(id));
	}
	
	public Parametro obtenerPorIdCompuesto(String cClase, String cMetodo, String cParametro){
		return parametroDao.obtenerPorIdCompuesto(cClase,cMetodo,cParametro);
	}
	
	public boolean actualizar(Parametro parametro){
		parametroDao.guardaActualiza(parametro);
		return false;
	}

}
