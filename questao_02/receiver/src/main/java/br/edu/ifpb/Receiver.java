package br.edu.ifpb;

import br.edu.ifpb.grpc.Message;
import br.edu.ifpb.grpc.ReceiverServiceGrpc;
import br.edu.ifpb.grpc.ServerAppServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;
import br.edu.ifpb.grpc.MessageResult;

@SuppressWarnings("serial")
public class Receiver extends ReceiverServiceGrpc.ReceiverServiceImplBase {
	ManagedChannel channel  = ManagedChannelBuilder.forAddress("server", 10992)
			.usePlaintext()
			.build();

	ServerAppServiceGrpc.ServerAppServiceStub stub = ServerAppServiceGrpc.newStub(channel);

	@Override
	public void delivery(Message msg, StreamObserver<MessageResult> responseObserver) {
		//
		System.out.println("Recebendo uma mensagem e tentando encaminhar para o server.");
		//
		stub.print(msg, new StreamObserver<MessageResult>() {

			MessageResult messageResult;

			@Override
			public void onNext(MessageResult message) {
				this.messageResult = message;
			}

			@Override
			public void onError(Throwable throwable) {
				responseObserver.onError(throwable);
				System.out.println("Ocorreu um erro ao receber a mensagem.");
			}

			@Override
			public void onCompleted() {
				responseObserver.onNext(messageResult);
				responseObserver.onCompleted();

			}
		});
	}

}
