package br.com.srv.implementation;
import java.io.Serializable;

import br.com.dao.implementation.DaoLogin;
import br.com.repository.interfaces.RepositoryLogin;
import br.com.srv.interfaces.SrvLogin;

public class SrvLoginImpl implements SrvLogin, Serializable{

	private static final long serialVersionUID = 1L;

	private RepositoryLogin RepositoryLogin = new DaoLogin();
	
	@Override
	public boolean autenticado(String login, String senha) throws Exception {
		return RepositoryLogin.autentico(login, senha);
	}

}
