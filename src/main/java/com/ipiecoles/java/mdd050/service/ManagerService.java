package com.ipiecoles.java.mdd050.service;


import com.ipiecoles.java.mdd050.model.Employe;
import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.EmployeRepository;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManagerService {
	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	TechnicienRepository technicienRepository;
	@Autowired
	EmployeRepository employeRepository;

	public Technicien addTechnicienEquipe(long idManager, String matriculeTechnicien) {
		Manager manager = managerRepository.findOneWithEquipeById(idManager);
		Employe employe = employeRepository.findByMatricule(matriculeTechnicien);
		Technicien technicien = technicienRepository.findOne(employe.getId());
		manager.getEquipe().add(technicien);
		managerRepository.save(manager);
		return technicien;
	}

	public void delTechnicienEquipe(long idManager, long idTechnicien) {
		Manager manager = managerRepository.findOneWithEquipeById(idManager);
		Technicien technicien = technicienRepository.findOne(idTechnicien);
		manager.getEquipe().remove(technicien);
		managerRepository.save(manager);
	}
}
