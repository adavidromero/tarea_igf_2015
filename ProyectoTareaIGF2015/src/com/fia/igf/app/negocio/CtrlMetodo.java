package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.ClaseDAO;
import com.fia.igf.app.datos.MetodoDAO;
import com.fia.igf.app.datos.TipoMetodoDAO;
import com.fia.igf.app.dominio.*;


@Transactional
@Service
public class CtrlMetodo {
	@Autowired 
	private MetodoDAO metodoDAO;

	@Autowired 
	private TipoMetodoDAO tipoMetodoDao;

	@Autowired 
	private ClaseDAO claseDao;

	@Autowired
	public CtrlMetodo() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CtrlMetodo(
			MetodoDAO metodoDAO,
			ClaseDAO claseDao,
			TipoMetodoDAO tipoMetodoDao
			) {
		// TODO Auto-generated constructor stub
		this.metodoDAO = metodoDAO;
		this.claseDao = claseDao;
		this.tipoMetodoDao = tipoMetodoDao;
	}

	public boolean crearMetodo(
			String cClase,
			String cMetodo,
			String dMetodo,
			String dTipoRetorno,
			String cUsuario,
			Date fIngreso,
			String bActivo,
			String nParametros,
			String cTipoMetodo
			){
		if(metodoDAO.obtenerPorIdCompuesto(cClase, cMetodo)==null){
			Clase c=claseDao.obtenerPorId(Clase.class, Integer.parseInt(cClase));
			Metodo metodo = new Metodo(c,Integer.parseInt(cMetodo));
			System.out.println("IMPRIME LA CLASE:"+cClase);
			metodo.setcClase(c);
			metodo.setdMetodo(dMetodo);
			System.out.println("IMPRIME tipo retorno:"+dTipoRetorno);
			metodo.setdTipoRetorno(dTipoRetorno);
			metodo.setcUsuario(cUsuario);
			metodo.setfIngreso(fIngreso);
			metodo.setbActivo(Integer.parseInt(bActivo));
			metodo.setnParametros(Integer.parseInt(nParametros));
			metodo.setcTipoMetodo(tipoMetodoDao.obtenerPorId(TipoMetodo.class, cTipoMetodo));
			metodoDAO.guardaActualiza(metodo);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	
	public boolean editarMetodo(
			String cClase,
			String cMetodo,
			String dMetodo,
			String dTipoRetorno,
			String cUsuario,
			Date fIngreso,
			String bActivo,
			String nParametros,
			String cTipoMetodo
			){
		if(metodoDAO.obtenerPorIdCompuesto(cClase, cMetodo)!=null){
			Metodo metodo= metodoDAO.obtenerPorIdCompuesto(cClase, cMetodo);
			Clase c=claseDao.obtenerPorId(Clase.class, Integer.parseInt(cClase));
			System.out.println("IMPRIME tipo retorno:"+dTipoRetorno);
			metodo.setdTipoRetorno(dTipoRetorno);
			metodo.setcUsuario(cUsuario);
			metodo.setfIngreso(fIngreso);
			metodo.setbActivo(Integer.parseInt(bActivo));
			metodo.setnParametros(Integer.parseInt(nParametros));
			metodo.setcTipoMetodo(tipoMetodoDao.obtenerPorId(TipoMetodo.class, cTipoMetodo));
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
	
	public boolean borraMetodoIdCompuesta(String cClase, String cMetodo){
		if(metodoDAO.obtenerPorIdCompuesto(cClase, cMetodo)!=null){
			Metodo metodo = metodoDAO.obtenerPorIdCompuesto(cClase, cMetodo);
			metodoDAO.eliminar(metodo);
			return true;
		}else{
			return false;
		}
		
	}
	
	public List<Metodo> obtenerTodosMetodos(){
		return metodoDAO.obtenerTodos();
	}

	public Metodo obtenerPorId(String id){
		return metodoDAO.obtenerPorId(Metodo.class, id);
	}

	public Metodo obtenerPorIdCompuesto(String cClase, String cMetodo){
		return metodoDAO.obtenerPorIdCompuesto(cClase,cMetodo);
	}
	
	public boolean actualizar(Metodo metodo){
		metodoDAO.guardaActualiza(metodo);
		return false;
	}
	
}
