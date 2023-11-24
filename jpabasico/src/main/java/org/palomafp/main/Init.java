package org.palomafp.main;

import jakarta.persistence.Persistence;

public class Init {

	public static void main(String[] args) {
	
		// Crea el schema
		Persistence.generateSchema("unidad-persistencia", null);
	}

}
