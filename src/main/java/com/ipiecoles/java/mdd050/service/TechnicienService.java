package com.ipiecoles.java.mdd050.service;


import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.repository.ManagerRepository;
import com.ipiecoles.java.mdd050.repository.TechnicienRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TechnicienService {

	@Autowired
	ManagerRepository managerRepository;
	@Autowired
	TechnicienRepository technicienRepository;

	public Technicien ajoutManager(long idTechnicien, String matriculeManager) {
		Manager manager = managerRepository.findByMatricule(matriculeManager);
		Technicien technicien = technicienRepository.findOne(idTechnicien);

		technicien.setManager(manager);
		return technicienRepository.save(technicien);
	}
}
