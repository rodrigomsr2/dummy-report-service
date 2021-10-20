package com.dummy.core.builder;

import java.math.BigDecimal;
import java.util.Date;

import com.dummy.domain.model.DummyReport;

public class DummyReportBuilder {

	private DummyReport dummyReport;
	
	private DummyReportBuilder() {
		this.dummyReport = new DummyReport();
		this.dummyReport.setMockDate(new Date());
		this.dummyReport.setMockBigDecimal(new BigDecimal(10.50));
		this.dummyReport.setMockString("Dummy");
	}
	
	public static DummyReportBuilder newDummyReport() {
		return new DummyReportBuilder();
	}
	
	public DummyReport build() {
		return this.dummyReport;
	}
	
}
