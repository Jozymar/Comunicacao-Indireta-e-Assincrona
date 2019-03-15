package br.edu.ifpb;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.edu.ifpb.repositories.MessageRepository;
import br.edu.ifpb.repositories.MessageResultRepository;

@SuppressWarnings("serial")
public class SenderImpl extends UnicastRemoteObject implements ISender {
	private final MessageRepository repository;
	private final MessageResultRepository resultRepository;
	
	public SenderImpl(MessageRepository rep, MessageResultRepository resultRep) throws RemoteException{
		this.repository = rep;
		this.resultRepository = resultRep;
	}

	@Override
	public void sendMessage(Message dto) throws RemoteException{
		//armazenar temporariamente a mensagem
		repository.add(dto);
	}
	
	@Override
	public MessageResult getMessage(String id) throws RemoteException {
		//recuperar a mensagem no repositório
		MessageResult result = resultRepository.get(id);
		if (result != null) resultRepository.remove(result);
		return result;
	}

}
