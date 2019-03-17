package br.edu.ifpb;

import java.util.Timer;

import br.edu.ifpb.repositories.MessageRepository;
import br.edu.ifpb.repositories.MessageResultRepository;
import br.edu.ifpb.repositories.SendedMessageRepository;

public class TaskManager {
	
	public static void runTask(MessageRepository repository, SendedMessageRepository sendedRepository,
                               MessageResultRepository resultRepository){
		Timer timer = new Timer();
		timer.schedule(new SendTask(repository, sendedRepository), 3000, 10000);
		timer.schedule(new GetResultTask(sendedRepository, resultRepository), 2000, 2000);
	}
	
}
