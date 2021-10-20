package com.dummy.infrastructure.service;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dummy.domain.model.DummyReport;
import com.dummy.domain.repository.DummyRepository;
import com.dummy.domain.service.DummyReportService;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

@Service
public class DummyReportServiceImpl implements DummyReportService {

	@Autowired
	private DummyRepository dummyRepostiory;
	
	public byte[] generateReportAsXlsx(Long amount) {
		try {
			// relatório
			InputStream inputStream = this.getClass().getResourceAsStream("/reports/mock.jasper");
			
			// params
			Map<String, Object> params = new HashMap<>();
			params.put("REPORT_LOCALE", new Locale("pt", "BR"));
			
			// dados
			List<DummyReport> reportData = this.dummyRepostiory.findDummies(10l);
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(reportData);
			
			JasperPrint jasperPrint = JasperFillManager.fillReport(inputStream, params, dataSource);
			
			ByteArrayOutputStream output = new ByteArrayOutputStream();
			
			JRXlsxExporter exporter = new JRXlsxExporter();
			exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(output));
			
			SimpleXlsxReportConfiguration configuration = new SimpleXlsxReportConfiguration();
			configuration.setOnePagePerSheet(Boolean.TRUE);
			configuration.setWhitePageBackground(Boolean.FALSE);
			configuration.setRemoveEmptySpaceBetweenRows(Boolean.TRUE);
			
			exporter.setConfiguration(configuration);
			exporter.exportReport();
			
			return output.toByteArray();
		} catch (Exception e) {
			throw new ReportException("Could not issue dummy report. Reason: " + e.getCause());
		}
	}
	
}
