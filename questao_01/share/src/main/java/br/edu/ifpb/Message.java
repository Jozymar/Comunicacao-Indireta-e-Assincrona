package br.edu.ifpb;

import java.io.Serializable;

public class Message implements Serializable {
	private String id;
	private String text;
	
	public Message() {}
	
	public Message(String id, String text){
		this.id = id;
		this.text = text;
	}
	
	public String getId() {
		return id;
	}
	
	public String getText() {
		return text;
	}
	
	private static final long serialVersionUID = 2749862336593551561L;
	
}
