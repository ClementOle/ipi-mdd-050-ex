package com.ipiecoles.java.mdd050.service;

import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeService {

	@Autowired
	EmployeRepository employeRepository;


	public Long countEmployes() {
		return employeRepository.count();
	}
}
