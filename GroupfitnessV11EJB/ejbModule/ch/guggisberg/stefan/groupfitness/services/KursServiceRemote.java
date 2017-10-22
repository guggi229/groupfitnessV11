package ch.guggisberg.stefan.groupfitness.services;

import java.util.List;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.exceptions.KursAlreadyExistsException;
import ch.guggisberg.stefan.groupfitness.exceptions.KursNotFoundException;

public interface KursServiceRemote {

	public Kurs create(Kurs kurs) throws KursAlreadyExistsException;
	public Kurs update(Kurs kurs) throws KursNotFoundException;
	public void remove(Long id) throws KursNotFoundException;
	public Kurs getKurs(Long id) throws KursNotFoundException;
	public List <Kurs> getAllKurs();
	
}
