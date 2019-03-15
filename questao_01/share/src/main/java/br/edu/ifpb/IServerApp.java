package br.edu.ifpb;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IServerApp extends Remote{
	MessageResult print(Message msg) throws RemoteException;
}
