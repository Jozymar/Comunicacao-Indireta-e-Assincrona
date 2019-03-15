package br.edu.ifpb;

import java.math.BigInteger;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class ServerApp extends UnicastRemoteObject implements IServerApp {

	protected ServerApp() throws RemoteException {
		super();
	}

	@Override
	public MessageResult print(Message msg) throws RemoteException {
		//
		MessageDigest msd;
		try {
			msd = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new RemoteException("Erro de MD5", e);
		}
		//
		byte[] bhash = msd.digest(msg.getText().getBytes());
		BigInteger bi = new BigInteger(bhash);
		//
		MessageResult result = new MessageResult(msg.getId(), bi.toString(16));
		//
		System.out.println(msg.getId() + " " + msg.getText());
		//
		return result;

	}

}
