package br.edu.ifpb;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		//log
		System.out.println("Inicializado o serviço de Sender");

		//Inicializar o serviço para client app
		Server server = ServerBuilder
				.forPort(10990)
				.addService((BindableService) new SenderImpl()).build();

		server.start();
		server.awaitTermination();
	}
}
