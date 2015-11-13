package com.fia.igf.app.negocio;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fia.igf.app.datos.*;
//import com.fia.igf.app.dominio.*;
import com.fia.igf.app.dominio.*;

@Transactional
@Service
public class CtrlInterfaceImplementa {
	@Autowired 
	private InterfaceImplementaDAO interImplementaDao;

	@Autowired 
	private InterfaceDAO interfaceDaoHijo;

	@Autowired 
	private InterfaceDAO interfaceDaoPadre;

	public CtrlInterfaceImplementa() {
		// TODO Auto-generated constructor stub
	}
	
	@Autowired
	public CtrlInterfaceImplementa (InterfaceImplementaDAO interfaceImplementaDao){
		this.interImplementaDao=interImplementaDao;
	}

	public boolean crearInterfaceImplementa(String id, String idInterfaceHijo, String idInterfacePadre){
		Integer idPadre = new Integer("0");
		Integer idHijo = new Integer("0");
		Interface hijo, padre;
		
		try {
			//Inicializando id
			 idPadre = Integer.parseInt(idInterfacePadre.trim());
			 idHijo = Integer.parseInt(idInterfaceHijo.trim());
			 
		} catch (Exception e) {
			// TODO: handle exception
			
		}
		
		hijo = interfaceDaoHijo.obtenerPorId(Interface.class, idHijo);
		padre = interfaceDaoPadre.obtenerPorId(Interface.class, idPadre);
		
		
		if( hijo != null && padre != null){
			
				InterfaceImplementa interImplementa = new InterfaceImplementa(id,hijo,padre);
				interImplementaDao.guardaActualiza(interImplementa);
				return true ;
		}
		else{
				return false ;
		}
	}
	

	public boolean borraInterfaceImplementa(Integer id){
		if(interImplementaDao.obtenerPorId(InterfaceImplementa.class, id)!=null){
			InterfaceImplementa interfaceImplementa = interImplementaDao.obtenerPorId(InterfaceImplementa.class, id);
			interImplementaDao.eliminar(interfaceImplementa);
			return true;
		}
		else{
			return false;
		}
	}
	
	public List<InterfaceImplementa> obtenerTodosInterfaceImplementas(){
		return interImplementaDao.obtenerTodos();
	}

	public InterfaceImplementa obtenerPorId(Integer id){
		return interImplementaDao.obtenerPorId(InterfaceImplementa.class, id);
	}
	
	public boolean actualizar(InterfaceImplementa interImplementa){
		interImplementaDao.guardaActualiza(interImplementa);
		return false;
	}

}
