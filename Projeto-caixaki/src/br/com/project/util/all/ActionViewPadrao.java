package br.com.project.util.all;

import java.io.Serializable;

import javax.annotation.PostConstruct;

/**
 * Classe responsável pela manipulação da interface do usuário
 * @author willi
 *
 */
public interface ActionViewPadrao extends Serializable{

	/**
	 * Limpa o campo de consulta retornado
	 * @throws Exception
	 */
	abstract void limparLista() throws Exception;
	
	/**
	 * Salva algum registro
	 * @return
	 * @throws Exception
	 */
	abstract String save() throws Exception;
	
	/**
	 * Salva algum registro, mas não retorna
	 * @throws Exception
	 */
	abstract void saveNotReturn() throws Exception;
	
	/**
	 * Salva ou edita algum registro
	 * @throws Exception
	 */
	abstract void saveEdit() throws Exception;
	
	/**
	 * Exclui algum registro
	 * @throws Exception
	 */
	abstract void excluir() throws Exception;
	
	/**
	 * Não sei o que faz
	 * @return
	 * @throws Exception
	 */
	abstract String ativar() throws Exception;
	
	/**
	 * Limpa o campo input
	 * @return
	 * @throws Exception
	 */
	@PostConstruct
	abstract String novo() throws Exception;
	
	/**
	 * Edita algum registro
	 * @return
	 * @throws Exception
	 */
	abstract String editar() throws Exception;
	
	/**
	 * Não sei e nunca nem vi
	 * @throws Exception
	 */
	abstract void setarVariaveisNulas() throws Exception;
	
	/**
	 * Procura por alguma entidade
	 * @throws Exception
	 */
	abstract void consultarEntidade() throws Exception;
	
	/**
	 * Alguma coisa relacionada com erros ou informações na tela com PrimeFaces
	 * @param e
	 * @throws Exception
	 */
	abstract void statusOperation(EstatusPersistencia e) throws Exception;
	
	/**
	 * Redireciona o novo usuário
	 * @return
	 * @throws Exception
	 */
	abstract String redirecionarNewEntidade() throws Exception;
	
	/**
	 * Não faço a mínima ideia
	 * @return
	 * @throws Exception
	 */
	abstract String redirecionarFindEntidade() throws Exception;
	
	/**
	 * Adiciona uma mensagem no campo PrimeFaces
	 * @param msg
	 * @throws Exception
	 */
	abstract void addMsg(String msg) throws Exception;
}
