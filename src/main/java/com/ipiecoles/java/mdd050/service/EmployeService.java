package com.ipiecoles.java.mdd050.service;

import com.ipiecoles.java.mdd050.exception.GlobalExceptionHandler;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@Service
public class EmployeService {

	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	TechnicienRepository technicienRepository;


	public Long countEmployes() {
		return employeRepository.count();
	}

	public Employe findById(long id) throws EntityNotFoundException {
		Employe employe = employeRepository.findOne(id);
		if (employe == null) {
			throw new EntityNotFoundException("L'employé d'identifiant : " + id + " n'a pas été trouvé.");
		}
		return employe;
	}

	public Employe matriculeEmploye(String matricule) throws EntityNotFoundException {
		Employe employe = employeRepository.findByMatricule(matricule);
		if (employe == null) {
			throw new EntityNotFoundException("L'employé d'identifiant : " + matricule + " n'a pas été trouvé.");
		}
		return employe;

	}

	public Page<Employe> pagingEmploye(int page, int size, String sortProperty, String sortDirection) {
		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
		return employeRepository.findAll(pageRequest);
	}

	public Employe sauvegardeEmploye(Employe employe) {
		Employe employe1 = employeRepository.findByMatricule(employe.getMatricule());
		if(employe1 != null){
			throw new EntityExistsException("Un employe existe déja avec ce matricule");
		}
		return employeRepository.save(employe);
	}

	public Employe modifierEmploye(long id, Employe employe) {
		return employeRepository.save(employe);
	}

	public void supprEmploye(long id) {
		employeRepository.delete(id);
	}
}
