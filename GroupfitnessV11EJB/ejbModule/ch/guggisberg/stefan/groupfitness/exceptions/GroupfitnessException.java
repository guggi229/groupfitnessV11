package ch.guggisberg.stefan.groupfitness.exceptions;


import javax.ejb.ApplicationException;

@ApplicationException(rollback = true, inherited = true)
public class GroupfitnessException extends Exception {

	private static final long serialVersionUID = 5248555275578479903L;

}

