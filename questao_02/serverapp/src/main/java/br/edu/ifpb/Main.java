package br.edu.ifpb;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		//
		System.out.println("Servidor inicializado");
		//

		//Inicializando o server
		Server server = ServerBuilder
				.forPort(10992)
				.addService((BindableService) new ServerApp()).build();

		server.start();
		server.awaitTermination();
	}
	
}
