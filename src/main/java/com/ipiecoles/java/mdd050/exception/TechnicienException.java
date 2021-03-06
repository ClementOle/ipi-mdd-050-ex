package com.ipiecoles.java.mdd050.exception;

import com.ipiecoles.java.mdd050.model.Technicien;

public class TechnicienException extends Exception {

	public static final String GRADE = "Le grade doit être compris entre 1 et 5 : ";
	/**
	 *
	 */
	private static final long serialVersionUID = -46465298479125228L;

	public TechnicienException(String message, Technicien technicien, Object valeurIncorrecte) {
		super(message + valeurIncorrecte + ", technicien : " + technicien.toString());
		System.out.println(this.getMessage());
	}
}

