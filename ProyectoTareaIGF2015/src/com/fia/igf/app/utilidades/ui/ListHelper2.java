package com.fia.igf.app.utilidades.ui;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fia.igf.app.datos.AplicativoDAO;
import com.fia.igf.app.datos.ClaseDAO;
import com.fia.igf.app.datos.InterfaceDAO;
import com.fia.igf.app.datos.MetodoDAO;
import com.fia.igf.app.datos.ParametroDAO;
import com.fia.igf.app.datos.TipoAtributoDAO;
import com.fia.igf.app.datos.TipoClaseDAO;
import com.fia.igf.app.datos.TipoMetodoDAO;
import com.fia.igf.app.dominio.Aplicativo;
import com.fia.igf.app.dominio.Atributo;
import com.fia.igf.app.dominio.Clase;
import com.fia.igf.app.dominio.ClaseInterface;
import com.fia.igf.app.dominio.Interface;
import com.fia.igf.app.dominio.InterfaceImplementa;
import com.fia.igf.app.dominio.Metodo;
import com.fia.igf.app.dominio.Parametro;
import com.fia.igf.app.dominio.TipoAtributo;
import com.fia.igf.app.dominio.TipoClase;
import com.fia.igf.app.dominio.TipoMetodo;

@Transactional
@Service
public class ListHelper2 {
	@Autowired 
	private ClaseDAO claseDao;

	@Autowired 
	private TipoClaseDAO tipoClaseDao;

	@Autowired 
	private AplicativoDAO aplicativoDao;
	
	
	@Autowired 
	private MetodoDAO metodoDao;

	@Autowired 
	private TipoAtributoDAO tipoAtributoDao;

	@Autowired 
	private InterfaceDAO interfaceDao;

	@Autowired 
	private TipoMetodoDAO tipoMetodoDao;

	@Autowired 
	private ParametroDAO parametroDao;

	@Autowired
	public ListHelper2(ClaseDAO claseDao,
			TipoClaseDAO tipoClaseDao,
			AplicativoDAO aplicativoDao,
			MetodoDAO metodoDao,
			TipoAtributoDAO tipoAtributoDao,
			InterfaceDAO interfaceDao,
			TipoMetodoDAO tipoMetodoDao,
			ParametroDAO parametroDao
			){
		this.claseDao=claseDao;
		this.tipoClaseDao=tipoClaseDao;
		this.aplicativoDao=aplicativoDao;
		this.metodoDao=metodoDao;
		this.tipoAtributoDao=tipoAtributoDao;
		this.interfaceDao=interfaceDao;
		this.tipoMetodoDao=tipoMetodoDao;
		this.parametroDao=parametroDao;
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
	
	
	public String generaListaClasesParaAtributo(Atributo atributo,String nameId, String readonly){
		String listHtml="";
		List <Clase> clases = claseDao.obtenerTodos();
		int length = clases.size();
		if(atributo!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Clase claseTmp=clases.get(i);
				if(claseTmp.getcClase()==atributo.getcClase().getcClase()){
						listHtml+="<option value=\""+claseTmp.getcClase()+"\" selected=\"selected\">"+
							claseTmp.getdClase()+"</option>";
				}else{
					listHtml+="<option value=\""+claseTmp.getcClase()+"\">"+
							claseTmp.getdClase()+"</option>";
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
	
	
	public String generaListaMetodosParaAtributo(Atributo atributo,String nameId, String readonly){
		String listHtml="";
		List <Metodo> metodos = metodoDao.obtenerTodos();
		int length = metodos.size();
		if(atributo!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Metodo metodoTmp=metodos.get(i);
				if(metodoTmp.equals(atributo.getcMetodo())){
						listHtml+="<option value=\""+metodoTmp.getcMetodo()+"_"+
								metodoTmp.getcClase()+
								"\" selected=\"selected\">"+
								metodoTmp.getdMetodo()+"</option>";
				}else{
						listHtml+="<option value=\""+metodoTmp.getcMetodo()+"_"+
								metodoTmp.getcClase()+"\">"+
								metodoTmp.getdMetodo()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Metodo metodoTmp=metodos.get(i);
						listHtml+="<option value=\""+metodoTmp.getcMetodo()+"_"+
							metodoTmp.getcClase()+"\">"+
							metodoTmp.getdMetodo()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	public String obtenerNombreClaseDeMetodo(Integer id){
		Clase clase=claseDao.obtenerPorId(Clase.class,id);
		return clase.getdClase();
		
	}
	
	public String listaTipoAtributo(Atributo atributo,String nameId,String readonly){
		String listHtml="";
		List <TipoAtributo> tiposAtributos = tipoAtributoDao.obtenerTodos();
		int length = tiposAtributos.size();
		if(atributo!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				TipoAtributo tipoAtributo=tiposAtributos.get(i);
				if(tipoAtributo.getcTipoAtributo().equalsIgnoreCase(atributo.getcTipoAtributo().getcTipoAtributo())){
					listHtml+="<option value=\""+tipoAtributo.getcTipoAtributo()+"\" selected=\"selected\">"+
							tipoAtributo.getdTipoAtributo()+"</option>";
				}else{
					listHtml+="<option value=\""+tipoAtributo.getcTipoAtributo()+"\">"+
							tipoAtributo.getdTipoAtributo()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				TipoAtributo tipoAtributo=tiposAtributos.get(i);
				listHtml+="<option value=\""+tipoAtributo.getcTipoAtributo()+"\">"+
				tipoAtributo.getdTipoAtributo()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	
	public String generaListaClasesMetodo(Metodo metodo,String nameId, String readonly){
		String listHtml="";
		List <Clase> clases = claseDao.obtenerTodos();
		int length = clases.size();
		if(metodo!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Clase claseTmp=clases.get(i);
				if(claseTmp.getcClase()==metodo.getcClase().getcClase()){
						listHtml+="<option value=\""+claseTmp.getcClase()+"\" selected=\"selected\">"+
							claseTmp.getdClase()+"</option>";
				}else{
					listHtml+="<option value=\""+claseTmp.getcClase()+"\">"+
							claseTmp.getdClase()+"</option>";
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

	public String generaListaTiposMetodoParaMetodo(Metodo metodo,String nameId, String readonly){
		String listHtml="";
		List <TipoMetodo> tiposMetodos = tipoMetodoDao.obtenerTodos();
		int length = tiposMetodos.size();
		if(metodo!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				TipoMetodo tipoMetodo=tiposMetodos.get(i);
				if(tipoMetodo.getcTipoMetodo().equalsIgnoreCase(metodo.getcTipoMetodo().getcTipoMetodo())){
						listHtml+="<option value=\""+tipoMetodo.getcTipoMetodo()+"\" selected=\"selected\">"+
							tipoMetodo.getdTipoMetodo()	+"</option>";
				}else{
					listHtml+="<option value=\""+tipoMetodo.getcTipoMetodo()+"\">"+
							tipoMetodo.getdTipoMetodo()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				TipoMetodo tipoMetodo=tiposMetodos.get(i);
					listHtml+="<option value=\""+tipoMetodo.getcTipoMetodo()+"\">"+
							tipoMetodo.getdTipoMetodo()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	public String generaListaClasesParametro(Parametro parametro,String nameId, String readonly){
		String listHtml="";
		List <Clase> clases = claseDao.obtenerTodos();
		int length = clases.size();
		if(parametro!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Clase claseTmp=clases.get(i);
				if(claseTmp.getcClase()==parametro.getcMetodo().getcClase().getcClase()){
						listHtml+="<option value=\""+claseTmp.getcClase()+"\" selected=\"selected\">"+
							claseTmp.getdClase()+"</option>";
				}else{
					listHtml+="<option value=\""+claseTmp.getcClase()+"\">"+
							claseTmp.getdClase()+"</option>";
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
	
	public String generaListaMetodosParametro(Parametro parametro,String nameId, String readonly){
		String listHtml="";
		List <Metodo> metodos = metodoDao.obtenerTodos();
		int length = metodos.size();
		if(parametro!=null){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Metodo metodo=metodos.get(i);
				if(metodo.getcMetodo()==parametro.getcMetodo().getcMetodo()){
						listHtml+="<option value=\""+metodo.getcMetodo()+"\" selected=\"selected\">"+
							metodo.getdMetodo()+"</option>";
				}else{
					listHtml+="<option value=\""+metodo.getcMetodo()+"\">"+
							metodo.getdMetodo()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Metodo metodo=metodos.get(i);
				listHtml+="<option value=\""+metodo.getcMetodo()+"\">"+
				metodo.getdMetodo()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}	
	
	
	
	public String generarSelectListaInterfaceHijoInterfaceImplementa(InterfaceImplementa interfaceImplementa,String nameId, String readonly){
		String listHtml="";
		List <Interface> interfaces = interfaceDao.obtenerTodos();
		int length = interfaces.size();
		if(interfaceImplementa!=null ){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Interface interfaz=interfaces.get(i);
				if(interfaz.getcInterface()==interfaceImplementa.getInterfaceHijo().getcInterface()){
						listHtml+="<option value=\""+interfaz.getcInterface()+"\" selected=\"selected\">"+
							interfaz.getdInterface()+"</option>";
				}else{
					listHtml+="<option value=\""+interfaz.getcInterface()+"\">"+
							interfaz.getdInterface()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Interface interfaz=interfaces.get(i);
				listHtml+="<option value=\""+interfaz.getcInterface()+"\">"+
				interfaz.getdInterface()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}
	
	
	
	public String generarSelectListaInterfacePadreInterfaceImplementa(InterfaceImplementa interfaceImplementa,String nameId, String readonly){
		String listHtml="";
		List <Interface> interfaces = interfaceDao.obtenerTodos();
		int length = interfaces.size();
		if(interfaceImplementa!=null ){
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Interface interfaz=interfaces.get(i);
				if(interfaz.getcInterface()==interfaceImplementa.getInterfacePadre().getcInterface()){
						listHtml+="<option value=\""+interfaz.getcInterface()+"\" selected=\"selected\">"+
							interfaz.getdInterface()+"</option>";
				}else{
					listHtml+="<option value=\""+interfaz.getcInterface()+"\">"+
							interfaz.getdInterface()+"</option>";
				}
			}
			listHtml+="</select>";
		}else{
			listHtml+="<select id=\""+nameId+"\" name=\""+nameId+"\" class=\"form-control\" "+readonly+">";
			listHtml+="<option value=\"0\">Seleccionar...</option>";
			for(int i=0; i<length ; i++){
				Interface interfaz=interfaces.get(i);
				listHtml+="<option value=\""+interfaz.getcInterface()+"\">"+
				interfaz.getdInterface()+"</option>";
			}
			listHtml+="</select>";
		}
		return listHtml;
	}

}
