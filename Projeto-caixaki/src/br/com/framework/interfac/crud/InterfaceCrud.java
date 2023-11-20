package br.com.framework.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
/**
 * Metódos para realização de manobras no banco de dados descrito no arquivo 'context.xml'
 * @author willi
 *
 * @param <T>
 */
public interface InterfaceCrud<T> extends Serializable{

	/**
	 * Salva os dados de qualquer objeto
	 * @param obj
	 * @throws Exception
	 */
	void save(T obj) throws Exception;
	
	/**
	 * Salva os dados de qualquer objeto
	 * @param obj
	 * @throws Exception
	 */
	void persist(T obj) throws Exception;
	
	/**
	 * Salva ou atualiza qualquer objeto
	 * @param obj
	 * @throws Exception
	 */
	void saveOrUpdate(T obj) throws Exception;
	
	/**
	 * Atualiza qualquer objeto
	 * @param obj
	 * @throws Exception
	 */
	void update(T obj) throws Exception;
	
	/**
	 * Deleta qualquer objeto
	 * @param obj
	 * @throws Exception
	 */
	void delete(T obj) throws Exception;
	
	/**
	 * Salva ou atualiza e retorna o objeto em estado persistente
	 * @param obj
	 * @return
	 * @throws Exception
	 */
	T merge(T obj) throws Exception;
	
	/**
	 * Carrega a lista de dados de determinada classe
	 * @param objs
	 * @return
	 * @throws Exception
	 */
	List<T> findList(Class<T> objs) throws Exception;
	
	/**
	 * Procura um objeto a partir da classe e id
	 * @param entidade
	 * @param id
	 * @return
	 * @throws Exception
	 */
	Object findById(Class<T> entidade, Long id) throws Exception;
	
	/**
	 * Procura um objeto a partir da classe e id
	 * @param entidade
	 * @param id
	 * @return
	 * @throws Exception
	 */
	T findPorId(Class<T> entidade, Long id) throws Exception;
	
	/**
	 * Procura uma lista a partir de uma determinada query HQL
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<T> findListByQueryDinamic(String query) throws Exception;
	
	/**
	 * Realiza um update no banco a partir de uma determinada query HQL
	 * @param query
	 * @throws Exception
	 */
	void executeUpdateByQueryDinamic(String query) throws Exception;
	
	/**
	 * Realiza um update a partir de uma determinada query SQL
	 * @param query
	 * @throws Exception
	 */
	void executeUpdateSqlDinamic(String query) throws Exception;
	
	/**
	 * Faz com que o Hibernate limpe sua sessão, impedindo acúmulo de cache
	 * @throws Exception
	 */
	void clearSession() throws Exception;
	
	/**
	 * Retira um objeto da sessão do Hibernate
	 * @param objs
	 * @throws Exception
	 */
	void evict(Object objs) throws Exception;
	
	/**
	 * Pega a sessão do Hibernate
	 * @return
	 * @throws Exception
	 */
	Session getSession() throws Exception;
	
	/**
	 * Retorna uma lista qualquer a partir de uma query SQL
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<?> getListSqlDinamic(String query) throws Exception;
	
	/**
	 * Retona um array de objetos a partir de uma query
	 * @param query
	 * @return
	 * @throws Exception
	 */
	List<Object[]> getListSqlDinamicArray(String query) throws Exception;
	
	/**
	 * Trabalha com a conexão do banco de dados do spring
	 * @return
	 * @throws Exception
	 */
	JdbcTemplate getJdbcTemplate() throws Exception;
	
	/**
	 * Trabalha com a conexão do banco de dados do spring
	 * @return
	 * @throws Exception
	 */
	SimpleJdbcTemplate getSimpleJdbcTemplate() throws Exception;
	
	/**
	 * Trabalha com a conexão do banco de dados do spring
	 * @return
	 * @throws Exception
	 */
	SimpleJdbcInsert getSimpleJdbcInsert() throws Exception;
	
	/**
	 * Retorna a quantidade de resgistros em uma tabela do banco de dados
	 * @param table
	 * @return
	 * @throws Exception
	 */
	Long totalRegistros(String table) throws Exception;
	
	/**
	 * Realiza uma montagem dinâmica para o banco de dados a partir de uma query
	 * @param query
	 * @return
	 * @throws Exception
	 */
	Query obterQuery(String query) throws Exception;
	
	/**
	 * Carrega uma certa quantidade da lista-----------
	 * Exemplo: carregar somente os 10 primeiros dados em uma tabela de 1000 resultados
	 * @param query
	 * @param inicialRegistro
	 * @param maxResultado
	 * @return
	 * @throws Exception
	 */
	List<?> findListByQueryDinamic(String query, int inicialRegistro, int maxResultado) throws Exception;
}
