package br.edu.ifpb;

import br.edu.ifpb.grpc.Message;
import br.edu.ifpb.grpc.ServerAppServiceGrpc;
import io.grpc.stub.StreamObserver;
import br.edu.ifpb.grpc.MessageResult;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@SuppressWarnings("serial")
public class ServerApp extends ServerAppServiceGrpc.ServerAppServiceImplBase {

	@Override
	public void print(Message msg, StreamObserver<MessageResult> responseObserver) {
		//
		MessageDigest msd = null;
		try {
			msd = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			System.out.println("Erro de MD5" + e);
			responseObserver.onError(e);
		}
		//
		byte[] bhash = msd.digest(msg.getText().getBytes());
		BigInteger bi = new BigInteger(bhash);
		//
		MessageResult result = MessageResult.newBuilder().setId(msg.getId()).setHash(bi.toString(16)).build();
		//
		System.out.println(msg.getId() + " " + msg.getText());
		//
		responseObserver.onNext(result);
		responseObserver.onCompleted();

	}

}
