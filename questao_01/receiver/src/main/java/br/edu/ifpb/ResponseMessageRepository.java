package br.edu.ifpb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ResponseMessageRepository {
	private volatile List<MessageResult> list = new ArrayList<>();
	
	public void add(MessageResult msg) {
		System.out.println("Adicionando uma mensagem de resposta: " + msg.getId());
		list.add(msg);	
	}
	
	public void remove(MessageResult msg){
		System.out.println("Removendo uma mensagem de resposta: " + msg.getId());
		for (int i = 0; i < list.size(); i++) {
			MessageResult message = list.get(i);
			if (msg.getId().equals(message.getId())) {
				list.remove(i); 
				break;
			}
		}
	}

	public List<MessageResult> list() {
		return Collections.unmodifiableList(list);
	}
	
	public MessageResult get(String id){
		for (MessageResult messageResult : list) {
			if (messageResult.getId().equals(id)){
				return messageResult;
			}
		}
		return null;
	}
}
