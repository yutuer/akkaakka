package akkaakka.parentChild;

import java.util.UUID;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akkaakka.Command;
import akkaakka.Event;

public class ParentActor extends UntypedActor {  
	  
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);  
 
    private final ActorRef childActor ;  
 
    public ParentActor() {  
          childActor = getContext().actorOf(Props.create(ChildActor. class), "child-actor");  
    }  
 
    @Override  
    public void onReceive(Object msg ) throws Exception {  
 
          log.info( "Received Command: " + msg );  
 
          if (msg instanceof Command) {  
              final String data = ((Command) msg).getData();  
              final Event event = new Event(data, UUID.randomUUID().toString());  
 
              childActor.tell(event , getSelf());  
         } else if (msg .equals("echo" )) {  
              log.info( "ECHO!");  
         }  
    }  
}