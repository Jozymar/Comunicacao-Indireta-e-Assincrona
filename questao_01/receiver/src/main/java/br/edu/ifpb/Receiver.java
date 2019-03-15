package br.edu.ifpb;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

@SuppressWarnings("serial")
public class Receiver extends UnicastRemoteObject implements IReceiver {
	private final ResponseMessageRepository repository;

	protected Receiver(ResponseMessageRepository repository) throws RemoteException {
		this.repository = repository;
	}

	@Override
	public void delivery(Message msg) throws RemoteException {
		//
		System.out.println("Recebendo uma mensagem e tentando encaminhar para o server.");
		//
		Registry registry =  LocateRegistry.getRegistry(10992);
		try {
			IServerApp serverApp = (IServerApp) registry.lookup("ServerApp");
			MessageResult result = serverApp.print(msg);
			repository.add(result);
		}
		catch(NotBoundException | AccessException e){
			throw new RuntimeException("Foi mal!!");
		}
		
	}

	@Override
	public MessageResult result(String id) throws RemoteException {
		return repository.get(id);
	}

	
}
