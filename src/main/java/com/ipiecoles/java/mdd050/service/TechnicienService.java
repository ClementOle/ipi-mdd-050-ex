package com.ipiecoles.java.mdd050.service;


import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class TechnicienService {

	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	TechnicienRepository technicienRepository;

	public Manager ajoutManager(long idTechnicien, String matriculeManager) {
		Manager manager = managerRepository.findByMatricule(matriculeManager);
		Technicien technicien = technicienRepository.findOne(idTechnicien);
		if (manager == null) {
			throw new EntityNotFoundException("Il n'existe aucun manager avec ce matricule");
		} else if (technicien == null) {
			throw new EntityNotFoundException("Il n'existe aucun technicien possédant cette id");
		}
		technicien.setManager(manager);
		technicienRepository.save(technicien);
		return manager;
	}
}
