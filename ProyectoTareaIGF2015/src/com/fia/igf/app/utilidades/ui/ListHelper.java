package com.fia.igf.app.utilidades.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.ClaseDAO;
import com.fia.igf.app.datos.InterfaceDAO;
import com.fia.igf.app.datos.TipoClaseDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Clase;
import com.fia.igf.app.dominio.ClaseInterface;
import com.fia.igf.app.dominio.Interface;
import com.fia.igf.app.dominio.TipoClase;

@Transactional
@Service
public class ListHelper {
	@Autowired 
	private ClaseDAO claseDao;

	@Autowired 
	private TipoClaseDAO tipoClaseDao;

	@Autowired 
	private AplicativoDAO aplicativoDao;
	
	@Autowired 
	private InterfaceDAO interfaceDao;

	@Autowired
	public ListHelper(ClaseDAO claseDao,
			TipoClaseDAO tipoClaseDao,
			AplicativoDAO aplicativoDao
			){
		this.claseDao=claseDao;
		this.tipoClaseDao=tipoClaseDao;
		this.aplicativoDao=aplicativoDao;
	}
	
	
	public String generaListaTipoClase(Clase clase,String nameId,String readonly){
		String listHtml="";
		List <TipoClase> tiposClases = tipoClaseDao.obtenerTodos();
		int length = tiposClases.size();
		if(clase!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				TipoClase tipoClase=tiposClases.get(i);
				if(tipoClase.getcTipoClase().equalsIgnoreCase(clase.getcTipoClase().getcTipoClase())){
					listHtml+="<option value=\""+tipoClase.getcTipoClase()+"\" selected=\"selected\">"+
							tipoClase.getdTipoClase()+"</option>";
				}else{
					listHtml+="<option value=\""+tipoClase.getcTipoClase()+"\">"+
							tipoClase.getdTipoClase()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				TipoClase tipoClase=tiposClases.get(i);
				listHtml+="<option value=\""+tipoClase.getcTipoClase()+"\">"+
				tipoClase.getdTipoClase()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	
	
	/*
	 * Retorna una string de select que contiene los aplicativos y
	 * cual es el aplicativo seleccionado de la clase que tiene por parametro
	 */
	public String generarSelectListaAplicativos(Clase clase,String nameId, String readonly){
		String listHtml="";
		List <Aplicativo> aplicativos = aplicativoDao.obtenerTodos();
		int length = aplicativos.size();
		if(clase!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Aplicativo aplicativo=aplicativos.get(i);
				if(aplicativo.getcAplicativo().equalsIgnoreCase(clase.getcAplicativo().getcAplicativo())){
					listHtml+="<option value=\""+aplicativo.getcAplicativo()+"\" selected=\"selected\">"+
							aplicativo.getdAplicativo()+"</option>";
				}else{
					listHtml+="<option value=\""+aplicativo.getcAplicativo()+"\">"+
							aplicativo.getdAplicativo()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Aplicativo aplicativo=aplicativos.get(i);
				listHtml+="<option value=\""+aplicativo.getcAplicativo()+"\">"+
				aplicativo.getdAplicativo()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	
	
	/*
	 * Retorna una string de select que contiene las clases y la clase que es 
	 * padre de la enviada
	 */
	public String generarSelectListaClasesPadres(Clase clase,String nameId, String readonly){
		String listHtml="";
		List <Clase> clases = claseDao.obtenerTodos();
		boolean tienePadre=false;
		int length = clases.size();
		if(clase!=null ){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			tienePadre=(clase.getClasePadre()!=null);
			for(int i=0; i<length ; i++){
				Clase claseTmp=clases.get(i);
				if(tienePadre && claseTmp.getcClase()==clase.getClasePadre().getcClase()){
					if(claseTmp.getcClase()!=clase.getcClase()){
						listHtml+="<option value=\""+claseTmp.getcClase()+"\" selected=\"selected\">"+
							claseTmp.getdClase()+"</option>";
					}
				}else{
					if(claseTmp.getcClase()!=clase.getcClase()){
					listHtml+="<option value=\""+claseTmp.getcClase()+"\">"+
							claseTmp.getdClase()+"</option>";
					}
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Clase claseTmp=clases.get(i);
				listHtml+="<option value=\""+claseTmp.getcClase()+"\">"+
				claseTmp.getdClase()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	public String generaListaClase(ClaseInterface claseInterface,String nameId, String readonly){
		String listHtml="";
		List <Clase> clases = claseDao.obtenerTodos();
		int length = clases.size();
		if(claseInterface!=null ){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Clase clase =clases.get(i);
				if(clase.getcClase().equalsIgnoreCase(claseInterface.getcClase().getcClase())){
					listHtml+="<option value=\""+clase.getcClase()+"\" selected=\"selected\">"+
							clase.getdClase()+"</option>";
				}else{
					listHtml+="<option value=\""+clase.getcClase()+"\">"+
							clase.getdClase()+"</option>";
				}
			}
			listHtml+="</select>";
			
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Clase clase=clases.get(i);
				listHtml+="<option value=\""+clase.getcClase()+"\">"+
				clase.getdClase()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	public String generaListaInterface(ClaseInterface claseInterface,String nameId, String readonly){
		String listHtml="";
		List <Interface> interfaces = interfaceDao.obtenerTodos();
		int length = interfaces.size();
		if(claseInterface!=null ){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Interface iinterface =interfaces.get(i);
				if(iinterface.getcInterface().equalsIgnoreCase(claseInterface.getcInterface().getcInterface())){
					listHtml+="<option value=\""+iinterface.getcInterface()+"\" selected=\"selected\">"+
							iinterface.getdInterface()+"</option>";
				}else{
					listHtml+="<option value=\""+iinterface.getcInterface()+"\">"+
							iinterface.getdInterface()+"</option>";
				}
			}
			listHtml+="</select>";
			
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Interface iinterface=interfaces.get(i);
				listHtml+="<option value=\""+iinterface.getcInterface()+"\">"+
				iinterface.getdInterface()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}

}
