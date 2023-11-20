package br.com.project.listener;

import java.io.Serializable;

import javax.faces.bean.ApplicationScoped;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Essa classe permite pegar componentes do Spring criados por nós
 * @author willi
 *
 */
@ApplicationScoped
public class ContextLoaderListenerCaixakiUtils extends ContextLoaderListener implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Retorna o contexto do spring
	 * @return
	 */
	private static WebApplicationContext getWebContext() {
		
		return WebApplicationContextUtils.getWebApplicationContext(
				getCurrentWebApplicationContext().getServletContext());
	}
	
	/**
	 * Retorna um componente do spring criado por nós
	 * @param idNomeBean
	 * @return
	 */
	public static Object getBean(String idNomeBean) {
		
		return getWebContext().getBean(idNomeBean);
	}
	
	/**
	 * Retorna um componente do spring criado por nós
	 * @param classe
	 * @return
	 */
	public static Object getBeanByClass(Class<?> classe) {
		
		return getWebContext().getBean(classe);
	}
}
