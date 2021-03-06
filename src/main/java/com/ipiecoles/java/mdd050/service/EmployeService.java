package com.ipiecoles.java.mdd050.service;

import com.ipiecoles.java.mdd050.exception.ConflictException;
import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class EmployeService {

	@Autowired
	EmployeRepository employeRepository;

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

	public Page<Employe> pagingEmploye(Integer page, int size, String sortProperty, String sortDirection) {
		if (page < 0) {
			throw new IllegalArgumentException("Le numéro de page ne peut être négatif");
		} else if (page > countEmployes() / size) {
			throw new IllegalArgumentException("Le numéro de page est trop grand");
		} else if (size < 0) {
			throw new IllegalArgumentException("La taille ne peut être négative");
		} else if (size >= countEmployes()) {
			throw new IllegalArgumentException("La taille ne peut être supérieur au nombre d'employé");
		}

		PageRequest pageRequest = new PageRequest(page, size, Sort.Direction.fromString(sortDirection), sortProperty);
		return employeRepository.findAll(pageRequest);
	}

	public Employe sauvegardeEmploye(Employe employe) throws ConflictException {
		if (employeRepository.findByMatricule(employe.getMatricule()) != null) {
			throw new ConflictException("Erreur, le matricule " + employe.getMatricule() + " existe déjà");
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
