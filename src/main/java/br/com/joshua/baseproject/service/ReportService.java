package br.com.joshua.baseproject.service;

public interface ReportService {
	
	public byte[] generateReportPdf(String nameReport);
	
	public byte[] generatePersonReportCsv();

}
