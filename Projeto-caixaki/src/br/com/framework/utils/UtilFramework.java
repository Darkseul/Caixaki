package br.com.framework.utils;

import java.io.Serializable;

import org.springframework.stereotype.Component;

/**
 * Classe respons�vel por retornar o ID do usu�rio na sess�o
 * @author willi
 *
 */

@Component
public class UtilFramework implements Serializable{

	private static final long serialVersionUID = 1L;

	private static ThreadLocal<Long> threadLocal = new ThreadLocal<Long>();
	
	public synchronized static ThreadLocal<Long> getThreadLocal(){
		
		return threadLocal;
	}
}
