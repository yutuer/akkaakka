package akkaakka.base;

import java.util.UUID;

import akka.actor.UntypedActor;
import akka.event.Logging;
import akka.event.LoggingAdapter;
import akkaakka.Command;
import akkaakka.Event;

public class SimpleActor extends UntypedActor {  
	  
    LoggingAdapter log = Logging.getLogger(getContext().system(), this);  
 
    public SimpleActor() {  
          log.info( "SimpleActor constructor");  
    }  
 
    @Override  
    public void onReceive(Object msg) throws Exception {  
 
          log.info( "Received Command: " + msg ); 
          log.info("sender:" + getSender());
          if (msg instanceof Command) {  
              final String data = ((Command) msg).getData();  
              final Event event = new Event(data, UUID.randomUUID().toString());  
              // emmit an event somewhere...  
 
         } else if (msg .equals("echo" )) {  
              log.info( "ECHO!");  
         }  
    }  
} 