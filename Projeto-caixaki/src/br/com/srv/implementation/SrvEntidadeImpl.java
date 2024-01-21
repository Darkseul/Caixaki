package br.com.srv.implementation;

import java.util.Date;

import org.springframework.dao.DataAccessException;

import br.com.dao.implementation.DaoEntidade;
import br.com.repository.interfaces.RepositoryEntidade;
import br.com.srv.interfaces.SrvEntidade;

public class SrvEntidadeImpl implements SrvEntidade {

	private static final long serialVersionUID = 1L;
	
	private RepositoryEntidade repositoryEntidade = new DaoEntidade();

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) throws Exception {

		return repositoryEntidade.getUltimoAcessoEntidadeLogada(name);
	}

	@Override
	public void updateUltimoAcessoUser(String login) throws DataAccessException, Exception {

		repositoryEntidade.updateUltimoAcessoUser(login);
	}

	@Override
	public boolean existeUsuario(String ent_login) throws DataAccessException, Exception {

		return repositoryEntidade.existeUsuario(ent_login);
	}

}
