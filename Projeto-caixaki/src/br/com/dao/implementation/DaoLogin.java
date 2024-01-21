package br.com.dao.implementation;


import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.repository.interfaces.RepositoryLogin;

/**
 * Verifica a existência de um usuário
 * @author willi
 * @return boolean
 */
@Repository
public class DaoLogin extends HibernateUtil implements
		RepositoryLogin {

	private static final long serialVersionUID = 1L;

	@Override
	public boolean autentico(String login, String senha) throws Exception {
		
		
		// Seta o sql para JDBC
		String sql = "select count(1) as autentica from entidade where ent_login = ? and ent_senha = ? ";
		PreparedStatement preparedStatement = getConnection().prepareStatement(sql);
		preparedStatement.setString(1, login);
		preparedStatement.setString(2, senha);
		
		// Executa e retorna os valores do SQL
		ResultSet resultSet = preparedStatement.executeQuery();
		int result = 0;
		
		if(resultSet.next()) {
		    result = resultSet.getInt("autentica");
		}
		preparedStatement.close();
		
		boolean existe = false;
		
		if(result > 0) {
			existe = true;
		}
		return existe;
	}

}