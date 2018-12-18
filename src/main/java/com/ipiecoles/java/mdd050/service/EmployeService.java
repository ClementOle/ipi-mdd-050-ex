package com.ipiecoles.java.mdd050.service;

import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class EmployeService {

	@Autowired
	EmployeRepository employeRepository;


	public Long countEmployes() {
		return employeRepository.count();
	}

	public Employe findById(long id) {
		return employeRepository.findOne(id);
	}

	public Employe matriculeEmploye(String matricule) {
		return employeRepository.findByMatricule(matricule);
	}

	public Page<Employe> pagingEmploye(int page, int size, String sortProperty, String sortDirection) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
		return employeRepository.findAll(pageRequest);
	}

	public Employe sauvegardeEmploye(Employe employe) {
		return employeRepository.save(employe);
	}

	public Employe modifierEmploye(long id, Employe employe) {
		return employeRepository.save(employe);
	}

	public boolean supprEmploye(long id) {
		employeRepository.delete(id);
		return true;
	}


}
