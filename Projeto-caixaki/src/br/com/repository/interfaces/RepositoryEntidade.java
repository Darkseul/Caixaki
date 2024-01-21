package br.com.repository.interfaces;

import java.io.Serializable;
import java.util.Date;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface RepositoryEntidade extends Serializable{

	Date getUltimoAcessoEntidadeLogada(String name) throws Exception;
	void updateUltimoAcessoUser(String login) throws DataAccessException, Exception;
	boolean existeUsuario(String ent_login) throws DataAccessException, Exception;
}
