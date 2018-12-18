package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/employes")
public class EmployeController {

	@Autowired
	private EmployeService employeService;

	@RequestMapping("/count")
	public long count() {
		return employeService.countEmployes();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public Employe findInfo(@PathVariable(value = "id") long id) {
		if (employeService.findById(id) == null) {
			throw new EntityNotFoundException("L'employé d'identifiant : " + id + " n'a pas été trouvé.");
		}
		return employeService.findById(id);
	}

	@RequestMapping(params = "matricule")
	public Employe findMatricule(@RequestParam(value = "matricule", defaultValue = "null") String matricule) {
		System.out.println(matricule);
		return employeService.matriculeEmploye(matricule);
	}

	@RequestMapping("")
	public Page<Employe> test(@RequestParam("page") int page, @RequestParam("size") int size, @RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection) {
		return employeService.pagingEmploye(page, size, sortProperty, sortDirection);
	}

}
