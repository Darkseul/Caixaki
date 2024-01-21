package br.com.framework.implementacao.crud;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.interfac.crud.InterfaceCrud;
import br.com.project.model.classes.Entidade;

@Component
@Transactional
@SuppressWarnings(value = { "unused", "unchecked" })
public class ImplementacaoCrud<T> implements InterfaceCrud<T>{

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;
	
	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcClassImpl simpleJdbcClass;
	
	private void validarSessionFactory() {
		
		if(sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}
		
		validarTransaction();
	}
	
	/**
	 * Valida se há transação ativa ou não. Caso não tenha, é iniciada uma transação
	 */
	public void validarTransaction() {
		if(!sessionFactory.getCurrentSession().getTransaction().isActive()) {
			sessionFactory.getCurrentSession().beginTransaction();
		}
	}
	
	/**
	 * Usada para fazer commits de processos ajax
	 */
	private void commitProcessoAjax() {
		
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}
	
	/**
	 * Faz um rollback em processos ajax
	 */
	private void rollbackProcessAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	/**
	 * Executa instantâneamente a instrução dada ao banco de dados
	 */
	public void executeFlushSession() {
		
		sessionFactory.getCurrentSession().flush();
	}

	@Override
	public void save(T obj) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();
	}

	@Override
	public void persist(T obj) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);
		executeFlushSession();
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);;
		executeFlushSession();
	}

	@Override
	public void update(T obj) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();
	}

	@Override
	public void delete(T obj) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();
	}

	@Override
	public T merge(T obj) throws Exception {
		
		validarSessionFactory();
		T retorno = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		
		return retorno;
	}

	@Override
	public List<T> findList(Class<T> objs) throws Exception {
		
		validarSessionFactory();
		
		StringBuilder query = new StringBuilder();
		query.append("select distinc(entity) from ").append(objs.getSimpleName()).append(" entity");
		
		List<T> lista = (List<T>) sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		
		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		
		validarSessionFactory();
		Object retorno = sessionFactory.getCurrentSession().load(entidade, id);
		
		return retorno;
	}

	@Override
	public T findPorId(Class<T> entidade, Long id) throws Exception {
		
		validarSessionFactory();
		T retorno = (T) sessionFactory.getCurrentSession().load(entidade, id);
		
		return retorno;
	}

	@Override
	public List<T> findListByQueryDinamic(String query) throws Exception {
		
		validarSessionFactory();
		List<T> retorno = sessionFactory.getCurrentSession().createQuery(query).list();
		
		return retorno;
	}

	@Override
	public void executeUpdateByQueryDinamic(String query) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().createQuery(query).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void executeUpdateSqlDinamic(String query) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(query).executeUpdate();
		executeFlushSession();
	}

	@Override
	public void clearSession() throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().clear();
		executeFlushSession();
	}

	@Override
	public void evict(Object objs) throws Exception {
		
		validarSessionFactory();
		sessionFactory.getCurrentSession().evict(objs);
		executeFlushSession();
	}

	@Override
	public Session getSession() throws Exception {
		
	    validarSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSqlDinamic(String query) throws Exception {
		
		validarSessionFactory();
		List<?> retorno = sessionFactory.getCurrentSession().createSQLQuery(query).list();
		
		return retorno;
	}
	
	@Override
	public List<Object[]> getListSqlDinamicArray(String query) throws Exception {
		
		validarSessionFactory();
		List<Object[]> retorno = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(query).list();
		
		return retorno;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() throws Exception {
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() throws Exception {
		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() throws Exception {
		return simpleJdbcInsert;
	}
	
	public SimpleJdbcClassImpl getSimpleJdbcClass() {
		return simpleJdbcClass;
	}

	@Override
	public Long totalRegistros(String table) throws Exception {
		
		StringBuilder string = new StringBuilder();
		string.append("select count(1) from ").append(table);

		return jdbcTemplate.queryForLong(string.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		
		validarSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query);
		
		return queryReturn;
	}

	@Override
	public List<?> findListByQueryDinamic(String query, int inicialRegistro, int maxResultado) throws Exception {
		
		validarSessionFactory();
		List<?> retorno = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(inicialRegistro)
				.setMaxResults(maxResultado).list();
		
		return retorno;
	}
	
	public T findUniqueByQueryDinamica(String query) throws Exception{
		validarSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().createQuery(query.toString()).uniqueResult();
		return obj;
	}
	
	public T findInuqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		
		validarSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select entity from ").append(entidade.getSimpleName()).append(" entity where entity.")
		.append(atributo).append(" = '").append(valor).append("' ").append(condicao);
		
		T obj = (T) this.findUniqueByQueryDinamica(query.toString());
		return obj;
	}

	@Override
	public void executeUpdateSqlDinamic(String query, String login) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
