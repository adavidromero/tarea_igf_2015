package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.MetodoDAO;
import com.fia.igf.app.datos.ParametroDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Metodo;
import com.fia.igf.app.dominio.Parametro;

@Transactional
@Service
public class CtrlParametro {
	@Autowired 
	private ParametroDAO parametroDao;

	@Autowired 
	private MetodoDAO metodoDao;

	@Autowired
	public CtrlParametro(
			ParametroDAO parametroDao,
			MetodoDAO metodoDao
			){
		this.parametroDao=parametroDao;
		this.metodoDao=metodoDao;
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
	
	public boolean crearParametro(
			String cClase,
			String cMetodo,
			String cParametro,
			String dParametro,
			String dTipoParametro,
			String cUsuario,
			Date fechaIngreso
			){
		Metodo metodo = metodoDao.obtenerPorIdCompuesto(cClase, cMetodo);
		Parametro parametro= new Parametro(metodo, Integer.parseInt(cParametro));
		parametro.setdParametro(dParametro);
		parametro.setdTipoParametro(dTipoParametro);
		parametro.setcUsuario(cUsuario);
		parametro.setfIngreso(fechaIngreso);
		parametroDao.guardaActualiza(parametro);
		return true;
	}

	public boolean editarParametro(
			String cClase,
			String cMetodo,
			String cParametro,
			String dParametro,
			String dTipoParametro,
			String cUsuario,
			Date fechaIngreso
			){
		Parametro parametro = parametroDao.obtenerPorIdCompuesto(cClase, cMetodo,cParametro);
		parametro.setdParametro(dParametro);
		parametro.setdTipoParametro(dTipoParametro);
		parametro.setcUsuario(cUsuario);
		parametro.setfIngreso(fechaIngreso);
		parametroDao.guardaActualiza(parametro);
		return true;
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

	public boolean eliminarPorIdCompuesto(String cClase, String cMetodo, String cParametro){
		return parametroDao.eliminar(cClase,cMetodo,cParametro);
	}
	
	public boolean actualizar(Parametro parametro){
		parametroDao.guardaActualiza(parametro);
		return false;
	}

}
