package br.com.project.listener;

import java.io.Serializable;

import org.hibernate.envers.RevisionListener;
import org.springframework.stereotype.Service;

import br.com.framework.utils.UtilFramework;
import br.com.project.model.classes.Entidade;
import br.com.project.model.classes.InformacaoRevisao;

/**
 * Classe responsável por retornar uma classe de entidade para a classe de auditable
 * @author willi
 *
 */
@Service
public class CustomListener implements RevisionListener, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public void newRevision(Object revisionEntidade) {

		InformacaoRevisao informacaoRevisao = (InformacaoRevisao) revisionEntidade;
		Long codUser = UtilFramework.getThreadLocal().get();
		Entidade entidade = new Entidade();
		
		if(codUser != null && codUser != 0L) {
			
			entidade.setEnt_codigo(codUser);
			informacaoRevisao.setEntidade(entidade);
		}
	}

}
