package br.edu.ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISender extends Remote{
	void sendMessage(Message dto) throws RemoteException;
	MessageResult getMessage(String id) throws RemoteException;
}
