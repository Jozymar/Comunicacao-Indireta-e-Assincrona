package br.edu.ifpb;

import java.io.Serializable;

@SuppressWarnings("serial")
public class MessageResult implements Serializable {
	private String id;
	private String hash;
	
	public MessageResult(String id, String hash) {
		this.hash = hash;
		this.id = id;
	}
	
	public MessageResult() {}
	
	public String getId() {
		return id;
	}
	
	public String getHash() {
		return hash;
	}
}
