package com.grupo.backend;

import org.mindrot.jbcrypt.BCrypt;

public class GeneradorClave {
	public static void main(String[] args) {
        String salt = BCrypt.gensalt(13);
		String clave = BCrypt.hashpw("flopita", salt);
        System.out.println(clave);

        boolean resultado = BCrypt.checkpw("flopita", clave);
        System.out.println(resultado ? "ok" : "error");
	}    
}
