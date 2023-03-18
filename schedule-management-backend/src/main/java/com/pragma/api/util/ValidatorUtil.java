package com.pragma.api.util;

/**
 * Clase donde se definen metodos utilitarios para validaciones generales
 * 
 * @author ricardo.ayala@pragma.com.co
 *
 * @version 1.0
 */
public class ValidatorUtil {

	/**
	 * Método constructor
	 */
	private ValidatorUtil() {

	}

	/**
	 * Metodo que permite validar si un objeto es nulo
	 * 
	 * @param obj Objeto a comprobar
	 * @return true si es nulo, false en cualquier otro caso
	 */
	public static boolean isObjectNull(Object obj) {
		return obj == null;
	}

	/**
	 * Metodo que permite comprobar si una cadena es nula, vacia o solo contiene un
	 * espacio en blanco
	 * 
	 * @param text Cadena a validar
	 * @return true si la cadena es nula, vacia o solo contiene un espacio, false en
	 *         caso contrario
	 */
	public static boolean isNullOrEmpty(String text) {
		return text == null || text.trim().isEmpty();
	}

}
