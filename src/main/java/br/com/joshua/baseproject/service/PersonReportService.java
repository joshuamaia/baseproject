package br.com.joshua.baseproject.service;

public interface PersonReportService {
	
	public byte[] generateReportPdf(String nameReport);
	
	public byte[] generatePersonReportCsv();

}
