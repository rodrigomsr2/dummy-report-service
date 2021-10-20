package com.dummy.infrastructure.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.dummy.core.builder.DummyReportBuilder;
import com.dummy.domain.model.DummyReport;
import com.dummy.domain.repository.DummyRepository;

@Component
public class DummyRepositoryImpl implements DummyRepository {

	@Override
	public List<DummyReport> findDummies(Long amount) {
		List<DummyReport> dummies = new ArrayList<>(); 
		
		for (int i = 0; i < amount; i++) {
			dummies.add(DummyReportBuilder.newDummyReport().build());
		}
		
		return dummies;
	}
	
	
}
