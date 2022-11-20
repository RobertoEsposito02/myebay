package it.prova.myebay.exception;

public class CreditoNonSufficienteException extends RuntimeException{
	public CreditoNonSufficienteException(String message) {
		super(message);
	}
}
