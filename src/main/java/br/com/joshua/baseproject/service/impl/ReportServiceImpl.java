package br.com.joshua.baseproject.service.impl;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.joshua.baseproject.domain.Person;
import br.com.joshua.baseproject.repository.PersonRepository;
import br.com.joshua.baseproject.service.ReportService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private PersonRepository repository;
	
	@Autowired
	DataSource dataSource;

	@Override
	public byte[] generateReportPdf(String nameReport) {
		try {
			String fileReport = String.format("/report/src/%s.jasper" ,nameReport);
			JasperReport compile = (JasperReport) JRLoader
					.loadObject(this.getClass().getResourceAsStream(fileReport));
			try (Connection connection = dataSource.getConnection()) {
				Map<String, Object> parameters = new LinkedHashMap<>();
				JasperPrint jasperPrint = JasperFillManager.fillReport(compile, parameters, connection);
				return JasperExportManager.exportReportToPdf(jasperPrint);
			} catch (SQLException sqle) {
				throw new RuntimeException("Report SQL Error", sqle);
			}
		} catch (JRException jrpe) {
			throw new RuntimeException("Report Error", jrpe);
		}
	}

	@Override
	public byte[] generatePersonReportCsv() {
		StringBuilder str = new StringBuilder();		
		List<Person> persons = repository.findAll();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		str.append("Name,E-mail,BirthDate");
		
		for (Person person : persons) {
			str.append(System.lineSeparator());
			
			str.append(person.getName()).append(",").append(person.getEmail()).append(",").append(person.getBirthDate().format(formatter));
		}
		
		Charset charset = StandardCharsets.UTF_8;
		return str.toString().getBytes(charset);
	}

}
