package com.pragma.api.util;

import java.util.List;

import com.pragma.api.domain.ReportDTO;
import com.pragma.api.domain.Response;

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


	private static boolean isReportListEmpty(List<ReportDTO> list){
		if(list.size() < 1){
			return true;
		}
		return false;
	}

	public static Response<List<ReportDTO>> setResponse(List<ReportDTO> list){
		Response<List<ReportDTO>> response = new Response<>();
        if(!isReportListEmpty(list))
		{
			response.setStatus(200);
			response.setUserMessage("Data Finded successfully");
			response.setDeveloperMessage("Data Finded successfully");
			response.setMoreInfo("localhost:8081/api/report(toDO)");
			response.setErrorCode("");
			response.setData(list);
		}else{
			//asignar el status adecuado y enviar mensaje de error "Reporte vacio"  
			response.setStatus(500);
			response.setUserMessage("Data Not Found");
			response.setDeveloperMessage("Data Not Found");
			response.setMoreInfo("localhost:8081/api/report(toDO)");
			response.setErrorCode(" No data found");
			//response.setData(list);

		}
		return response;
	}

}
