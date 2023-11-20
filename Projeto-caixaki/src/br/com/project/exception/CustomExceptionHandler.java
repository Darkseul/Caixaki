package br.com.project.exception;

import java.util.Iterator;
import java.util.Map;

import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

import org.hibernate.SessionFactory;

import br.com.framework.hibernate.session.HibernateUtil;

/**
 * Classe responsável por tratar de erros dentro do JSF
 * @author willi
 *
 */
@SuppressWarnings("unused")
public class CustomExceptionHandler extends ExceptionHandlerWrapper{

	private ExceptionHandler wrapperd;
	private final FacesContext facesContext = FacesContext.getCurrentInstance();
	private final Map<String, Object> requestMap = facesContext.getExternalContext().getRequestMap();
	private final NavigationHandler navigationHandler = facesContext.getApplication().getNavigationHandler();
	
	public CustomExceptionHandler(ExceptionHandler exceptionHandler) {
	
		this.wrapperd = exceptionHandler;
	}
	
	@Override
	public ExceptionHandler getWrapped() {
		
		return wrapperd;
	}
	
	/**
	 * Manipula as exceções
	 */
	@Override
	public void handle() throws FacesException {

		final Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		
		while(iterator.hasNext()) {
			
			ExceptionQueuedEvent event = iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event.getSource();
			
			// recupera a exceção do contexto
			
			Throwable exception = context.getException();
			
			// Aqui trabalha a exceção
			
			try {
				
				requestMap.put("exceptionMessage", exception.getMessage());
				
				if(exception != null && exception.getMessage() != null 
						&& exception.getMessage().indexOf("ConstraintViolationException") != -1) {
					
					facesContext.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_WARN, "Registro não pode"
							+ " ser removido por estar associado", ""));
					
				}else if(exception != null && exception.getMessage() != null 
						&& exception.getMessage().indexOf("org.hibernate.StaleObjectStateException") != -1) {
					
					facesContext.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Este registro foi"
							+ " atualizado ou excluido por outro usuário", ""));
				}else {
					
					facesContext.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_FATAL, "O sistema se"
							+ " recuperou de um erro inesperado", ""));
					
					facesContext.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "Você pode continuar"
							+ " usando o sistema normalmente", ""));
					
					facesContext.addMessage("msg", new FacesMessage(FacesMessage.SEVERITY_INFO, "O erro foi causado"
							+ " por:\n" + exception.getMessage(), ""));
				}
				
				// executa as mensagens de erro
				facesContext.renderResponse();
				
			}finally {
				SessionFactory sf = HibernateUtil.getSessionFactory();
				
				if(sf.getCurrentSession().getTransaction().isActive()) {
					sf.getCurrentSession().getTransaction().rollback();
				}
				
				exception.printStackTrace();
				
				iterator.remove();
			}
		}
		
		getWrapped().handle();
	}
}
