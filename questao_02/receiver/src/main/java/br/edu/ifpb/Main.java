package br.edu.ifpb;

import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		//
		System.out.println("Inicializando o receiver");

		//Inicializando o server
		Server server = ServerBuilder
				.forPort(10991)
				.addService((BindableService) new Receiver()).build();

		server.start();
		server.awaitTermination();
	}
	
}
