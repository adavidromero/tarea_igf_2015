package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.ClaseDAO;
import com.fia.igf.app.datos.ClaseInterfaceDAO;
import com.fia.igf.app.datos.InterfaceDAO;
import com.fia.igf.app.datos.TipoClaseDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Clase;
import com.fia.igf.app.dominio.ClaseInterface;
import com.fia.igf.app.dominio.Interface;
import com.fia.igf.app.dominio.TipoClase;

@Transactional
@Service
public class CtrlClaseInterface {

	@Autowired 
	private ClaseInterfaceDAO claseInterfaceDao;
	
	@Autowired 
	private ClaseDAO ClaseDao;

	@Autowired 
	private InterfaceDAO interfaceDao;

	public CtrlClaseInterface(ClaseInterfaceDAO claseInterfaceDao, ClaseDAO ClaseDao, InterfaceDAO interfaceDao) {
		this.claseInterfaceDao = claseInterfaceDao;
		this.ClaseDao = ClaseDao;
		this.interfaceDao = interfaceDao;
	}
	
	public boolean crearClaseInterface(Integer cClaseInterface,String cClase, String cInterface){

	    	System.out.println("llega a crearClaseInterface");
			Interface cinterface= (Interface) interfaceDao.obtenerPorId(Interface.class, Integer.parseInt(cInterface));
			Clase clase = (Clase) ClaseDao.obtenerPorId(Clase.class, Integer.parseInt(cClase));
					
			ClaseInterface claseinterface = new ClaseInterface(cClaseInterface,clase,cinterface);
			claseInterfaceDao.guardaActualiza(claseinterface);
			return true ;
		}
	
	public boolean borraClaseInterface(Integer id){
		if(claseInterfaceDao.obtenerPorId(ClaseInterface.class, id)!=null){
			ClaseInterface claseInterface = claseInterfaceDao.obtenerPorId(ClaseInterface.class, id);
			claseInterfaceDao.eliminar(claseInterface);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<ClaseInterface> obtenerTodosClaseInterfaces(){
		return claseInterfaceDao.obtenerTodos();
	}

	public ClaseInterface obtenerPorId(Integer id){
		return claseInterfaceDao.obtenerPorId(ClaseInterface.class, id);
	}
	
	public boolean actualizar(ClaseInterface claseInterface){
		claseInterfaceDao.guardaActualiza(claseInterface);
		return false;
	}

}
