package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.service.EmployeService;
import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
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
	public Employe findInfo(@PathVariable(value = "id") long id) throws EntityNotFoundException {
		return employeService.findById(id);
	}

	@RequestMapping(params = "matricule")
	public Employe findMatricule(@RequestParam(value = "matricule", defaultValue = "null") String matricule) throws EntityNotFoundException {
		return employeService.matriculeEmploye(matricule);
	}

	@RequestMapping(value = "", method = RequestMethod.GET)
	public Page<Employe> findAllEmploye(@RequestParam(value = "page") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam("sortProperty") String sortProperty, @RequestParam("sortDirection") String sortDirection) {
		return employeService.pagingEmploye(page, size, sortProperty, sortDirection);
	}

	@RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public Employe saveEmploye(@RequestBody Employe employe) {
		return employeService.sauvegardeEmploye(employe);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Employe modifyEmploye(@PathVariable(value = "id") long id, @RequestBody Employe employe) {
		return employeService.modifierEmploye(id, employe);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteEmploye(@PathVariable(value = "id") long id) throws MySQLIntegrityConstraintViolationException {
		employeService.supprEmploye(id);

	}


}
