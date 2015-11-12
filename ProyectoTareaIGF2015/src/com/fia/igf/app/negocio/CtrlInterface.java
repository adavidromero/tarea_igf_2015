package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.InterfaceDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Interface;

@Transactional
@Service
public class CtrlInterface {
	@Autowired 
	private InterfaceDAO interfaceDao;

	@Autowired
	public CtrlInterface(InterfaceDAO interfaceDao){
		this.interfaceDao=interfaceDao;
	}

	public boolean crearInterface(Integer id, String descripcion,String usuario, Date fechaIngreso){
		if(interfaceDao.obtenerPorId(Interface.class, id)==null){
				Interface interfaz = new Interface(id,descripcion,usuario,fechaIngreso);
				interfaceDao.guardaActualiza(interfaz);
				return true ;
		}
		else{
				return false ;
		}
	}
	
	public boolean borraInterface(Integer id){
		if(interfaceDao.obtenerPorId(Interface.class, id)!=null){
			Interface interfaz = interfaceDao.obtenerPorId(Interface.class, id);
			interfaceDao.eliminar(interfaz);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<Interface> obtenerTodosInterfaces(){
		return interfaceDao.obtenerTodos();
	}

	public Interface obtenerPorId(Integer id){
		return interfaceDao.obtenerPorId(Interface.class, id);
	}
	
	public boolean actualizar(Interface interfaz){
		interfaceDao.guardaActualiza(interfaz);
		return false;
	}

}
