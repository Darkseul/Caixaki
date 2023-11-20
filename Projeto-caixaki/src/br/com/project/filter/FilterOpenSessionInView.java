package br.com.project.filter;

import java.io.IOException;
import java.io.Serializable;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.web.filter.DelegatingFilterProxy;

import br.com.framework.hibernate.session.HibernateUtil;
import br.com.framework.utils.UtilFramework;
import br.com.project.listener.ContextLoaderListenerCaixakiUtils;
import br.com.project.model.classes.Entidade;

/**
 * Classe responsável por fazer rollbacks e atualizações no banco
 * @author willi
 *
 */
@WebFilter(filterName = "conexaoFilter")
public class FilterOpenSessionInView extends DelegatingFilterProxy implements Serializable{

	private static final long serialVersionUID = 1L;
	private static SessionFactory sf;
	
	/**
	 * Metodo executado quando está sendo iniciado a aplicação, sendo executado somente uma vez
	 */
	@Override
	protected void initFilterBean() throws ServletException {
		sf = HibernateUtil.getSessionFactory();
		super.initFilterBean();
	}
	
	/**
	 * Invocado para toda requisição e toda resposta
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		
		//JDBC spring
		
		BasicDataSource springDataSource = // banco de dados spring
				(BasicDataSource) ContextLoaderListenerCaixakiUtils.getBean("springDataSource");
		
		// transações de banco e status
		
		PlatformTransactionManager transactionManager = new DataSourceTransactionManager(springDataSource);
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		TransactionStatus status = transactionManager.getTransaction(def);
		
		try {
			
			request.setCharacterEncoding("UTF-8");
			
			// Captura o usuário e joga ele para o spring
			
			HttpServletRequest request2 = (HttpServletRequest) request;
			HttpSession sessao = request2.getSession();
			Entidade userLogadoSessao = (Entidade) sessao.getAttribute("userLogadoSessao");
			
			if(userLogadoSessao != null) {
				
				UtilFramework.getThreadLocal().set(userLogadoSessao.getEnt_codigo());
			}
			
			// Começa uma transação
			sf.getCurrentSession().beginTransaction();
			transactionManager.commit(status);
			
			// Dá o commit em todas as operações
			if(sf.getCurrentSession().getTransaction().isActive()) {
				
				sf.getCurrentSession().flush();
				sf.getCurrentSession().getTransaction().commit();
			}
			
			// Fecha a sessão após a execução
			if(sf.getCurrentSession().isOpen()) {
				sf.getCurrentSession().close();
			}
			
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=UTF-8");
			
		}catch(Exception e) {
			
			// Dá rollback e fecha o hibernate
			
			transactionManager.rollback(status);
			e.printStackTrace();
			
			if(sf.getCurrentSession().isOpen()) {
				
				sf.getCurrentSession().getTransaction().rollback();
			}
			
			if(sf.getCurrentSession().isOpen()) {
				
				sf.getCurrentSession().close();
			}
			
		}finally {
			
			// Fecha tudo e limpa as sessões do hibernate
			
			if(sf.getCurrentSession().isOpen()) {
				
				if(sf.getCurrentSession().beginTransaction().isActive()) {
					sf.getCurrentSession().flush();
					sf.getCurrentSession().clear();
				}
				
				if(sf.getCurrentSession().isOpen()) {
					sf.getCurrentSession().close();
				}
			}
		}
		
		// antes de executar a ação (Request)
		chain.doFilter(request, response);
		
		// Após a execução do Request
	}
}
