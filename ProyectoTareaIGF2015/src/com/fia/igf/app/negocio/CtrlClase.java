package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.ClaseDAO;
import com.fia.igf.app.datos.TipoClaseDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Clase;
import com.fia.igf.app.dominio.TipoClase;

@Transactional
@Service
public class CtrlClase {
	@Autowired 
	private ClaseDAO claseDao;

	@Autowired 
	private TipoClaseDAO tipoClaseDao;

	@Autowired 
	private AplicativoDAO aplicativoDao;

	@Autowired
	public CtrlClase(ClaseDAO claseDao, TipoClaseDAO tipoClaseDao,
			AplicativoDAO aplicativoDao){
		this.claseDao=claseDao;
		this.tipoClaseDao=tipoClaseDao;
		this.aplicativoDao=aplicativoDao;
	}

	public boolean crearClase(Integer cClase,String dClase, String cUsuario,
		Date fIngreso, String cAplicativo, String cClasePadre,
		String cTipoClase){

    	System.out.println("llega a crearClase");
		Aplicativo aplicativo= (Aplicativo)aplicativoDao.
				obtenerPorId(Aplicativo.class, cAplicativo);

		boolean tieneClasePadre=(!cClasePadre.equalsIgnoreCase(""));

		Clase clasePadre = (tieneClasePadre) 
				? claseDao.obtenerPorId(Clase.class, Integer.parseInt(cClasePadre)) 
				: null ; 
		
		TipoClase tipoClase = (TipoClase)tipoClaseDao.obtenerPorId(TipoClase.class, cTipoClase);
				
		Clase clase = new Clase(cClase, dClase,cUsuario,fIngreso,aplicativo,clasePadre,tipoClase);
		claseDao.guardaActualiza(clase);
		Integer cClaseCreada=clase.getcClase();
		System.out.println("Id de clase ingresada: "+cClaseCreada);
		return true ;
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
