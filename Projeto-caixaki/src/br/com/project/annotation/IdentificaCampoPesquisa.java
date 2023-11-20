package br.com.project.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public abstract @interface IdentificaCampoPesquisa {

	/**
	 * Descri��o do campo para a tela
	 * @return
	 */
	String descricaoCampo();
	
	/**
	 * Objeto do campo
	 * @return
	 */
	String campoConsulta();
	
	/**
	 * Posi��o que ir� aparecer no combo
	 * @return
	 */
	int principal() default 10000;
}
