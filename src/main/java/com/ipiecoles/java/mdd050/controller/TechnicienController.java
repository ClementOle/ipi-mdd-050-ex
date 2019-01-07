package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Manager;
import com.ipiecoles.java.mdd050.service.TechnicienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/techniciens")
public class TechnicienController {

	@Autowired
	TechnicienService technicienService;

	@RequestMapping("/{idTechnicien}/manager/{matriculeManager}/add")
	public Manager addManager(@PathVariable(value = "idTechnicien") long idTechnicien, @PathVariable(value = "matriculeManager") String matriculeManger) {
		return technicienService.ajoutManager(idTechnicien, matriculeManger);
	}
}
