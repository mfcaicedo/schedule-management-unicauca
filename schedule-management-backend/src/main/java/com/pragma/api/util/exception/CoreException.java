package com.pragma.api.util.exception;

import org.springframework.context.i18n.LocaleContextHolder;

import java.util.ResourceBundle;

/**
 * Excepcion personalizada de ejemplo para el arquetipo de los microservicios. Permite controlar definir excepciones propias de la
 * aplicacion.
 * 
 * @author ricardo.ayala@pragma.com.co
 *
 * @version 1.0
 * 
 */
public class CoreException extends RuntimeException {

    /** Serialize */
    private static final long serialVersionUID = 6365652257268547172L;

    /** Mensaje informativo para el usuario */
    private final String userMessage;

    /** Codigo que define el estado de la transaccion */
    private final int status;

    /**
     * The exceptions resource bundle.
     */
    private static final ResourceBundle messages =
            ResourceBundle.getBundle("exceptions", LocaleContextHolder.getLocale());


    CoreException(String key, String arg) {
        super(formatMessage(getMessage(key), arg));
        this.status = 0;
        this.userMessage = "";
    }

    /**
     * Método constructor
     */
    public CoreException(String developerMessage, String userMessage, int status, Throwable e) {
        super(developerMessage, e);
        this.userMessage = userMessage;
        this.status = status;
    }

    /**
     * Método constructor
     */
    public CoreException(String developerMessage, String userMessage, int status) {
        super(developerMessage);
        this.userMessage = userMessage;
        this.status = status;
    }

    /**
     * Método constructor
     */
    public CoreException(String userMessage, int status, Throwable e) {
        super(e);
        this.userMessage = userMessage;
        this.status = status;
    }

    /**
     * @return the userMessage
     */
    public String getUserMessage() {
        return userMessage;
    }

    /**
     * @return the status.
     */
    public int getStatus() {
        return status;
    }

    private static String formatMessage(String message, String arg) {
        return message.replace("{}", arg);
    }

    private static String getMessage(String key) {
        return messages.getString(key);
    }

}
