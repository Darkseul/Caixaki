package br.com.project.util.all;

import org.springframework.stereotype.Component;

@Component
public abstract class BeanViewAbstract implements ActionViewPadrao{

	private static final long serialVersionUID = 1L;

	@Override
	public void limparLista() throws Exception {
		
	}

	@Override
	public String save() throws Exception {
		return null;
	}

	@Override
	public void saveNotReturn() throws Exception {
		
	}

	@Override
	public void saveEdit() throws Exception {
		
	}

	@Override
	public void excluir() throws Exception {
		
	}

	@Override
	public String ativar() throws Exception {
		return null;
	}

	@Override
	public String novo() throws Exception {
		return null;
	}

	@Override
	public String editar() throws Exception {
		return null;
	}

	@Override
	public void setarVariaveisNulas() throws Exception {
		
	}

	@Override
	public void consultarEntidade() throws Exception {
		
	}

	@Override
	public void statusOperation(EstatusPersistencia e) throws Exception {
		
		Mensagens.responseOperation(e);
	}

	@Override
	public String redirecionarNewEntidade() throws Exception {
		return null;
	}

	@Override
	public String redirecionarFindEntidade() throws Exception {
		return null;
	}

	@Override
	public void addMsg(String msg) throws Exception {
		
		Mensagens.msg(msg);
	}
	
	protected void sucesso() throws Exception{
		
		statusOperation(EstatusPersistencia.SUCESSO);
	}
	
	protected void erro() throws Exception{
		
		statusOperation(EstatusPersistencia.ERRO);
	}
}
