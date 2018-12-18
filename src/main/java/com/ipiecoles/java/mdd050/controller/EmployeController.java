package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import com.ipiecoles.java.mdd050.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityNotFoundException;

@RestController
public class EmployeController {

	@Autowired
			private EmployeService employeService;

	@RequestMapping("/employes/count")
	public long count() {
		return employeService.countEmployes();
	}

/*	@RequestMapping(value ="employes/{id}")
	public Employe findById(@PathVariable(value="id")long id) {


	}*/
}
