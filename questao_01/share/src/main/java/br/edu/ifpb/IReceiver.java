package br.edu.ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IReceiver extends Remote {
	void delivery(Message msg) throws RemoteException;
	MessageResult result(String id) throws RemoteException;
}
