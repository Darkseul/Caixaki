package br.com.project.bean.geral;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.web.context.request.FacesRequestAttributes;

@SuppressWarnings("unchecked")
public class ViewScope implements Scope, Serializable{

	private static final long serialVersionUID = 1L;
	
	public static final String VIEW_SCOPE_CALLBACKS = "viewScope.callBacks";

	/**
	 * Retorna o objeto de scopo a partir de um nome no armazenamento do JSF
	 * @param name
	 * @param objectFactory
	 * @return
	 */
	@Override
	public Object get(String name, ObjectFactory<?> objectFactory) {
		
		Object instance = getViewMap().get(name);
		
		if(instance == null) {
			
			instance = objectFactory.getObject();
			getViewMap().put(name, instance);
		}
		
		return instance;
	}

	/**
	 * Para facilitar quem est� na tela de sess�o, com esse metodo � possivel identificar o ID de quem est� na pagina
	 */
	@Override
	public String getConversationId() {
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesRequestAttributes requestAttributes = new FacesRequestAttributes(context);
		
		return requestAttributes.getSessionId() + "-" + context.getViewRoot().getId();
	}

	/**
	 * Encerra o ViewScope quando a p�gina fecha
	 */
	@Override
	public void registerDestructionCallback(String name, Runnable runnable) {
		
		Map<String, Runnable> callbacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
		
		if(callbacks != null) {
			
			callbacks.put(VIEW_SCOPE_CALLBACKS, runnable);
		}
	}

	/**
	 * Remove um objeto do scopo do JSF
	 * @param name
	 * @return
	 */
	@Override
	public Object remove(String name) {
		
		Object instance = getViewMap().remove(name);
		
		if(instance != null) {
			
			Map<String, Runnable> callBacks = (Map<String, Runnable>) getViewMap().get(VIEW_SCOPE_CALLBACKS);
			
			if(callBacks != null) {
				
				callBacks.remove(name);
			}
		}
		return instance;
	}

	/**
	 * Resolve a refer�ncia do objetos no JSF
	 */
	@Override
	public Object resolveContextualObject(String name) {
		
		FacesContext context = FacesContext.getCurrentInstance();
		FacesRequestAttributes requestAttributes = new FacesRequestAttributes(context);
		
		return requestAttributes.resolveReference(name);
	}

	/**
	 * Retorna o componenete raiz que est� associado a esta solicita��o(request)
	 * @return
	 */
	private Map<String, Object> getViewMap(){
		
		return FacesContext.getCurrentInstance() != null ? FacesContext.getCurrentInstance().getViewRoot().getViewMap() : new HashMap<String, Object>();  
	}
}
