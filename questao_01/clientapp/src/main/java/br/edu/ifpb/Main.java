package br.edu.ifpb;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
	
	private static void sendAndResultMessage(String id, String text, ISender sender) throws RemoteException, InterruptedException{
		//
		sender.sendMessage(new Message(id, text));
		//recuperar uma resposta
		while(true){
			//
			Thread.sleep(2000);
			//
			System.out.println("Verificando se há resposta.");
			MessageResult result = sender.getMessage(id);
			if (result == null) {
				continue;
			}
			else {
				System.out.println("Recebido resultado para mensagem " + id + ": " + result.getHash());
				break;
			}
		}
	}

	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {
		//log
		System.out.println("Acionado o clientapp");
		//recuperação do Sender
		Registry registry = LocateRegistry.getRegistry(10990);
		ISender sender = (ISender) registry.lookup("Sender");
		//
		String id = "askjdlkasjd";
		String text = "Hello World!";
		//
		for (int i = 0; i < 100; i++){
			//
			final String ix = id + "#" + i;
			final String mx = text + "#" + i;
			//
			Thread t = new Thread(){
				public void run() {
					try {
						sendAndResultMessage(ix, mx, sender);
					} 
					catch (RemoteException | InterruptedException e) {
						e.printStackTrace();
					}
				};
			};
			t.start();
		}
		
	}
	
}
