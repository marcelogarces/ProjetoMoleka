package br.com.moleka.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

public class RelatorioUtil {

	/**
	 * Método para chamar relatório usando primefaces.
	 * 
	 * <p:commandButton value="Gerar relatorio" ajax="false"
	 * icon="ui-icon-arrowthichk-s">
	 * <p:fileDownload value="#{seuBean.gerarRelatorioTeste()}">
	 * </p:fileDownload>
	 * </p:commandButton>
	 * 
	 * @param localRelatorio
	 * @param parametros
	 * @param lista
	 * @param nomeArquivoSaida
	 * @return StreamedContent
	 * @throws MalformedURLException
	 * @throws JRException
	 * @throws IOException
	 * @throws Exception
	 */
	public static StreamedContent gerarRelatorioFileDownload(
			String localRelatorio, Map<String, Object> parametros,
			Collection<?> lista, String nomeArquivoSaida)
			throws MalformedURLException, JRException, IOException, Exception {

		InputStream relatorio = null;
		ByteArrayOutputStream relat = new ByteArrayOutputStream();
		ExternalContext externalContext = FacesContext.getCurrentInstance()
				.getExternalContext();
		ServletContext contextS = (ServletContext) externalContext.getContext();
		String arquivo = contextS.getRealPath(localRelatorio);

		JasperPrint print = JasperFillManager.fillReport(new FileInputStream(
				new File(arquivo)), parametros, new JRBeanCollectionDataSource(
				lista));
		JRExporter exporter = new JRPdfExporter();
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, relat);
		exporter.exportReport();
		relatorio = new ByteArrayInputStream(relat.toByteArray());

		return new DefaultStreamedContent(relatorio, "application/pdf",
				nomeArquivoSaida + ".pdf");

	}

	/**
	 * Método para gerar relatorio em pdf.
	 * 
	 * @param localRelatorio
	 * @param parametros
	 * @param lista
	 * @throws IOException
	 * @throws JRException
	 */
	public static void gerarRelatorio(String localRelatorio,Map<String, Object> parametros, Collection<?> lista) throws IOException, JRException {

		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		ServletContext contextS = (ServletContext) externalContext.getContext();
		ServletOutputStream servletOutputStream = null;
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context.getExternalContext().getResponse();
		String arquivo = contextS.getRealPath(localRelatorio);
			servletOutputStream = response.getOutputStream();
			
			// criando o datasource com os dados criados
	        JRDataSource ds = new JRBeanCollectionDataSource(lista);

			JasperRunManager.runReportToPdfStream(new FileInputStream(new File(
					arquivo)), response.getOutputStream(), parametros,ds);
			
			

			//response.setHeader("Content-Disposition", "attachment; filename=NotaFiscal.pdf"); 
			response.setHeader("Content-Disposition", "inline; filename=teste.pfd"); 
			response.setContentType("application/pdf");
			servletOutputStream.flush();
			servletOutputStream.close();
			context.renderResponse();
			context.responseComplete();
	}
	
	
	public void gerarRelatorio2(){
		
		//TODO Este método será estudado.
		
	List<String> lista = new ArrayList<String>();
		
		lista.add("item1");
		lista.add("item2");
		lista.add("item3");
		lista.add("item4");
		lista.add("item5");
		

		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(lista);

		HashMap parameters = new HashMap();
		
		parameters.put("teste1", "chorei");

		try {

		FacesContext facesContext = FacesContext.getCurrentInstance();

		facesContext.responseComplete();

		ServletContext scontext = (ServletContext) facesContext.getExternalContext().getContext();

		JasperPrint jasperPrint = JasperFillManager.fillReport(scontext.getRealPath("/relatorios/report1.jasper"), parameters, ds);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		JRPdfExporter exporter = new JRPdfExporter();

		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);

		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, baos);

		exporter.exportReport();

		byte[] bytes = baos.toByteArray();

		if (bytes != null && bytes.length > 0) {

		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

		response.setContentType("application/pdf");

		response.setHeader("Content-disposition", "inline; filename=\"relatorioPorData.pdf\"");

		response.setContentLength(bytes.length);

		ServletOutputStream outputStream = response.getOutputStream();

		outputStream.write(bytes, 0, bytes.length);

		outputStream.flush();

		outputStream.close();

		}

		} catch (Exception e) {

		e.printStackTrace();

		}


	}

}
