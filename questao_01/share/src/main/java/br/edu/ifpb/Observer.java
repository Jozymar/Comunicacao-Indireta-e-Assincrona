package br.edu.ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Observer extends Remote{
	void notify(MessageResult result) throws RemoteException;
}
