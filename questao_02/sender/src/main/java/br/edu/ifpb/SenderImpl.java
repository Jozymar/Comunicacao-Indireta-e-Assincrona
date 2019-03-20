package br.edu.ifpb;

import br.edu.ifpb.grpc.Message;
import br.edu.ifpb.grpc.MessageResult;
import br.edu.ifpb.grpc.ReceiverServiceGrpc;
import br.edu.ifpb.grpc.SenderServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

@SuppressWarnings("serial")
public class SenderImpl extends SenderServiceGrpc.SenderServiceImplBase {

	ManagedChannel channel  = ManagedChannelBuilder.forAddress("server", 10991)
			.usePlaintext()
			.build();

	ReceiverServiceGrpc.ReceiverServiceStub stub = ReceiverServiceGrpc.newStub(channel);

	@Override
	public void sendMessage(Message msg, StreamObserver<MessageResult> responseObserver) {

		//Enviando mensagem
		stub.delivery(msg, new StreamObserver<MessageResult>() {

			MessageResult messageResult;

			@Override
			public void onNext(MessageResult message) {
				this.messageResult = message;
			}

			@Override
			public void onError(Throwable throwable) {
				responseObserver.onError(throwable);
				System.out.println("Ocorreu um erro ao enviar a mensagem.");
			}

			@Override
			public void onCompleted() {
				responseObserver.onNext(messageResult);
				responseObserver.onCompleted();
			}
		});
	}

}
