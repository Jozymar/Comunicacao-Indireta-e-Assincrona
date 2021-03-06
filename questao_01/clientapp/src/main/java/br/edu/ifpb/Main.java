package br.edu.ifpb;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Main {
    //Variáveis criadas para comparar o tempo de execução com RMI e com o GRPC
	private static volatile Integer count_message = 0;
	private static long tempoInicial;
	private static long tempoFinal;
	private static long tempoTotal;
	
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
				//Conta as mensagens
				count_message ++;
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

		//Armazena o tempo inicial de execução
		tempoInicial = System.currentTimeMillis();
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

		//Verifica se todas as mensagens ja foram enviadas e recebidas
		while (true) {
			if (count_message == 100) {
				break;
			}
		}
		//Chama o método para imprimir o tempo de execução
		imprimirTempoDeExecucao();
		
	}

	//Método que imprime o tempo de execução em segundos
	private static void imprimirTempoDeExecucao() {
		tempoFinal = System.currentTimeMillis();
		tempoTotal = tempoFinal - tempoInicial;
		System.out.println("A execução durou: " + tempoTotal/1000 + " segundos.");
	}
	
}
