package br.com.srv.interfaces;

import java.io.Serializable;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public interface SrvEntidade extends Serializable{

	Date getUltimoAcessoEntidadeLogada(String name) throws Exception;
	void updateUltimoAcessoUser(String login) throws DataAccessException, Exception;
	boolean existeUsuario(String ent_login) throws DataAccessException, Exception;
}
