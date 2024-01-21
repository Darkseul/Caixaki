package br.com.dao.implementation;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import org.springframework.dao.DataAccessException;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.implementacao.crud.ImplementacaoCrud;
import br.com.project.model.classes.Entidade;
import br.com.repository.interfaces.RepositoryEntidade;

public class DaoEntidade extends ImplementacaoCrud<Entidade> implements RepositoryEntidade{

	private static final long serialVersionUID = 1L;
	private HibernateUtil hibernate = new HibernateUtil();

	@Override
	public Date getUltimoAcessoEntidadeLogada(String name) throws Exception{
		
		String sql = "select ent_ultimoacesso from entidade where ent_inativo is false and ent_login = ?";
		
		@SuppressWarnings("static-access")
		PreparedStatement preparedStatement = hibernate.getConnection().prepareStatement(sql);
		preparedStatement.setString(1, name);
		
		ResultSet resultSet = preparedStatement.executeQuery();
		Date result = null;
		
		if(resultSet.next()) {
			
			result = resultSet.getDate("ent_ultimoacesso");
		}
		
		return result;
	}

	@Override
	public void updateUltimoAcessoUser(String login) throws DataAccessException, Exception {

		String sql = "update entidade set ent_ultimoacesso = current_timestamp where ent_inativo is false and ent_login = '" + login + "'";
		
		executeUpdateSqlDinamic(sql);
	}		

	@Override
	public boolean existeUsuario(String ent_login) throws DataAccessException, Exception {

		StringBuilder builder = new StringBuilder();
		builder.append("select count(1) >= 1 from entidade where ent_login = '").append(ent_login).append("'");
		return super.getJdbcTemplate().queryForObject(builder.toString(), Boolean.class);
	}

}
