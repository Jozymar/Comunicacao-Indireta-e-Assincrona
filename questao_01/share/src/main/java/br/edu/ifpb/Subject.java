package br.edu.ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Subject extends Remote{
	void registry(Observer obj) throws RemoteException;
}
