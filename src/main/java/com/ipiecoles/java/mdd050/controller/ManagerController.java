package com.ipiecoles.java.mdd050.controller;

import com.ipiecoles.java.mdd050.model.Technicien;
import com.ipiecoles.java.mdd050.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/managers")
public class ManagerController {

	@Autowired
	ManagerService managerService;

	@RequestMapping(value = "/{idManager}/equipe/{matriculeTechnicien}/add", method = RequestMethod.GET)
	public Technicien addTechnicienEquipe(@PathVariable(value = "idManager") long idManager, @PathVariable(value = "matriculeTechnicien") String matriculeTechnicien) {
		return managerService.addTechnicienEquipe(idManager, matriculeTechnicien);
	}

	@RequestMapping(value = "/{idManager}/equipe/{idTechnicien}/remove", method = RequestMethod.GET)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deleteTechnicienEquipe(@PathVariable(value = "idManager") long idManager, @PathVariable(value = "idTechnicien") long idTechnicien) {
		managerService.delTechnicienEquipe(idManager, idTechnicien);
	}
}
