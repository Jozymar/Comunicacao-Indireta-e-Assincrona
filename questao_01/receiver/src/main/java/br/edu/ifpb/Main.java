package br.edu.ifpb;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//
		System.out.println("Inicializando o receiver");
		//
		ResponseMessageRepository repository = new ResponseMessageRepository();
		//
		Registry registry = LocateRegistry.createRegistry(10991);
		registry.bind("Receiver", new Receiver(repository));
	}
	
}
