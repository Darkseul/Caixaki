package br.com.project.report.util;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils implements Serializable{

	private static final long serialVersionUID = 1L;

	/**
	 * Retorna a data atual para usar no reportUtil
	 * @return
	 */
	public static String getDateAtualReportName() {
		
		DateFormat df = new SimpleDateFormat("ddMMyyyy");
		
		return df.format(Calendar.getInstance().getTime());
	}
	
	public static String formatDateSql(Date data) {
		
		StringBuilder retorno = new StringBuilder();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		
		retorno.append("'").append(df.format(data)).append("'");
		
		return retorno.toString();
	}
}
