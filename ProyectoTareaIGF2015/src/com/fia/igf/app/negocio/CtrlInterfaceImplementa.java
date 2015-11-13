package com.fia.igf.app.negocio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.fia.igf.app.datos.*;

@Transactional
@Service
public class CtrlInterfaceImplementa {
	@Autowired 
	private InterfaceImplementaDAO interImplementaDao;

}
