package ch.guggisberg.stefan.groupfitness.exceptions;


import javax.ejb.ApplicationException;

@ApplicationException(rollback = true, inherited = true)
public class GroupfitnessException extends Exception {

}

