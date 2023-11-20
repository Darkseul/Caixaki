package br.com.project.report.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.project.util.all.BeanViewAbstract;

public class BeanReportView extends BeanViewAbstract{

	private static final long serialVersionUID = 1L;

	protected StreamedContent arquivoReport;
	protected int tipoRelatorio;
	protected List<?> listaBeanCollectionReport;
	protected HashMap<Object, Object> parametrosRelatorio;
	protected String nomeRelatorioJasper = "default";
	protected String nomeRelatorioSaida = "default";
	
	@Autowired
	private ReportUtil reportUtil;
	
	public StreamedContent getArquivoReport() throws Exception{
		
		return getReportUtil().geraRelatorio(listaBeanCollectionReport,
				getParametrosRelatorio(), getNomeRelatorioJasper(), getNomeRelatorioSaida(), getTipoRelatorio());
	}
	
	@SuppressWarnings("rawtypes")
	public BeanReportView() {
		parametrosRelatorio = new HashMap<Object, Object>();
		listaBeanCollectionReport = new ArrayList();
	}
	
	public int getTipoRelatorio() {
		return tipoRelatorio;
	}

	public void setTipoRelatorio(int tipoRelatorio) {
		this.tipoRelatorio = tipoRelatorio;
	}

	public HashMap<Object, Object> getParametrosRelatorio() {
		return parametrosRelatorio;
	}

	public void setParametrosRelatorio(HashMap<Object, Object> parametrosRelatorio) {
		this.parametrosRelatorio = parametrosRelatorio;
	}

	public ReportUtil getReportUtil() {
		return reportUtil;
	}
	
	public List<?> getListaBeanCollectionReport() {
		return listaBeanCollectionReport;
	}

	public void setListaBeanCollectionReport(List<?> listaBeanCollectionReport) {
		this.listaBeanCollectionReport = listaBeanCollectionReport;
	}

	public String getNomeRelatorioJasper() {
		return nomeRelatorioJasper;
	}

	public void setNomeRelatorioJasper(String nomeRelatorioJasper) {
		
		if(nomeRelatorioJasper == null || nomeRelatorioJasper.isEmpty()) {
			nomeRelatorioJasper = "default";
		}
		
		this.nomeRelatorioJasper = nomeRelatorioJasper;
	}

	public String getNomeRelatorioSaida() {
		return nomeRelatorioSaida;
	}

	public void setNomeRelatorioSaida(String nomeRelatorioSaida) {
		
		if(nomeRelatorioSaida == null || nomeRelatorioSaida.isEmpty()) {
			nomeRelatorioSaida = "default";
		}
		
		this.nomeRelatorioSaida = nomeRelatorioSaida;
	}

	public void setReportUtil(ReportUtil reportUtil) {
		this.reportUtil = reportUtil;
	}
}
