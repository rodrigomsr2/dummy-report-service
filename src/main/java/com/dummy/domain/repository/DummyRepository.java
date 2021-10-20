package com.dummy.domain.repository;

import java.util.List;

import com.dummy.domain.model.DummyReport;

public interface DummyRepository {

	public List<DummyReport> findDummies(Long amount);
	
}
