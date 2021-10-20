package com.dummy.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dummy.domain.service.DummyReportService;

@RestController
@RequestMapping("/dummyReport")
public class DummyController {

	@Autowired
	private DummyReportService dummyReportService;

//	@GetMapping(path = "/dummies/{amount}")
//	public ResponseEntity<byte[]> generateDummyReportPdf(@PathVariable Long amount) {
//		byte[] reportAsBytes = this.dummyReportService.generateReportAsPdf(amount);
//
//		HttpHeaders headers = new HttpHeaders();
//		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dummy-report.pdf");
//		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:7400"); 
//
//		return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(reportAsBytes);
//	}
	
	@GetMapping(path = "/dummies/{amount}")
	public ResponseEntity<byte[]> generateDummyReportXlsx(@PathVariable Long amount) {
		byte[] reportAsBytes = this.dummyReportService.generateReportAsXlsx(amount);

		HttpHeaders headers = new HttpHeaders();
		headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=dummy-report");
		headers.add(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "*, Authorization");
		headers.add(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:7400");
		headers.add(HttpHeaders.CONTENT_TYPE, "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");

		return ResponseEntity.ok().headers(headers).body(reportAsBytes);
	}

}
