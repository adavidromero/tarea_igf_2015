package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.dominio.Aplicativo;

@Transactional
@Service
public class CtrlAplicativo {
	@Autowired 
	private AplicativoDAO aplicativoDao;
	@Autowired
	public CtrlAplicativo(AplicativoDAO aplicativoDao){
		this.aplicativoDao=aplicativoDao;
	}

	public boolean crearAplicativo(String id, String descripcion, Date fechaIngreso){
		if(aplicativoDao.obtenerPorId(Aplicativo.class, id)==null){
				Aplicativo aplicativo = new Aplicativo(id,descripcion,fechaIngreso);
				aplicativoDao.guardaActualiza(aplicativo);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraAplicativo(String id){
		if(aplicativoDao.obtenerPorId(Aplicativo.class, id)!=null){
			Aplicativo aplicativo = aplicativoDao.obtenerPorId(Aplicativo.class, id);
			aplicativoDao.eliminar(aplicativo);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Aplicativo> obtenerTodosAplicativos(){
		return aplicativoDao.obtenerTodos();
	}

	public Aplicativo obtenerPorId(String id){
		return aplicativoDao.obtenerPorId(Aplicativo.class, id);
	}
	
	public boolean actualizar(Aplicativo aplicativo){
		aplicativoDao.guardaActualiza(aplicativo);
		return false;
	}

}
