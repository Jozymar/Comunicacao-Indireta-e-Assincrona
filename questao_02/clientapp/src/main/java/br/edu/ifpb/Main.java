package br.edu.ifpb;

import br.edu.ifpb.grpc.SenderServiceGrpc;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import br.edu.ifpb.grpc.Message;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import br.edu.ifpb.grpc.MessageResult;

public class Main {
    //Variáveis criadas para comparar o tempo de execução com GRPC e com o RMI
	private static volatile Integer count_message = 0;
	private static long tempoInicial;
	private static long tempoFinal;
	private static long tempoTotal;
	
	private static void sendAndResultMessage(String id, String text, SenderServiceGrpc.SenderServiceStub senderServiceStub) {
		//
		Message message = Message.newBuilder()
				.setId(id)
				.setText(text)
				.build();

		senderServiceStub.sendMessage(message, new StreamObserver<MessageResult>() {

			MessageResult messageResult;

			@Override
			public void onNext(MessageResult message) {
				this.messageResult = message;
			}

			@Override
			public void onError(Throwable throwable) {
				System.out.println("Ocorreu um erro durante o envio e recebimento da mensagem.");
			}

			@Override
			public void onCompleted() {
				System.out.println("Recebido resultado para mensagem " + messageResult.getId() + ": " + messageResult.getHash());
				//Conta as mensagens
				count_message ++;
			}
		});

	}

	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {

		//log
		System.out.println("Acionado o clientapp");

		//recuperação do Sender
		ManagedChannel channel  = ManagedChannelBuilder.forAddress("localhost", 10990)
				.usePlaintext()
				.build();

		SenderServiceGrpc.SenderServiceStub stub = SenderServiceGrpc.newStub(channel);

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
			sendAndResultMessage(ix, mx, stub);

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
