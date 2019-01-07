package com.ipiecoles.java.mdd050.service;


import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ManagerService {
	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	TechnicienRepository technicienRepository;

	public Technicien addTechnicienEquipe(long idManager, String matriculeTechnicien) {
		Manager manager = managerRepository.findOneWithEquipeById(idManager);
		Technicien technicien = technicienRepository.findByMatricule(matriculeTechnicien);

		if (manager == null) {
			throw new EntityNotFoundException("Il n'existe aucun manager avec cette id");
		} else if (technicien == null) {
			throw new EntityNotFoundException("Il n'existe aucun technicien possédant ce matricule");
		}
		manager.ajoutTechnicienEquipe(technicien);
		managerRepository.save(manager);
		return technicien;
	}

	public void delTechnicienEquipe(long idManager, long idTechnicien) {
		Manager manager = managerRepository.findOneWithEquipeById(idManager);
		Technicien technicien = technicienRepository.findOne(idTechnicien);

		if (manager == null) {
			throw new EntityNotFoundException("Il n'existe aucun manager avec cette id");
		} else if (technicien == null) {
			throw new EntityNotFoundException("Il n'existe aucun technicien possédant cette id");
		}
		manager.getEquipe().remove(technicien);
		managerRepository.save(manager);
	}
}
