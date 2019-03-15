package br.edu.ifpb;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {

	public static void main(String[] args) throws RemoteException, AlreadyBoundException {
		//
		System.out.println("Servidor inicializado");
		//
		Registry registry = LocateRegistry.createRegistry(10992);
		registry.bind("ServerApp", new ServerApp());
	}
	
}
