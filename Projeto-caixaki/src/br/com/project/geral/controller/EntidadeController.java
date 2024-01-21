package br.com.project.geral.controller;

import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;

import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;
import br.com.srv.implementation.SrvEntidadeImpl;
import br.com.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade>{

	private static final long serialVersionUID = 1L;
	
	private SrvEntidade srvEntidade = new SrvEntidadeImpl();
	
	public Entidade findUserLogado(String userLogado) throws Exception{
		
		return super.findInuqueByProperty(Entidade.class, userLogado, "ent_login", " and entity.ent_inativo is false");
	}
	
	public Date getUltimoAcessoEntidadeLogada(String login) throws Exception {
		
		return srvEntidade.getUltimoAcessoEntidadeLogada(login);
	}

	public void updateUltimoAcesso(String name) throws DataAccessException, Exception {

		srvEntidade.updateUltimoAcessoUser(name);
	}

}
