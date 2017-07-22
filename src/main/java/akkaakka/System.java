package akkaakka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class System {  
	  
	  public static final Logger log = LoggerFactory.getLogger(System.class);  
	  
	  public static void main(String... args) throws Exception {  
	  
	    final ActorSystem actorSystem = ActorSystem.create("actor-system");  
	  
	    Thread.sleep(5000);  
	  
	    final ActorRef actorRef = actorSystem.actorOf(Props.create(SimpleActor. class), "simple-actor");  
	  
	    actorRef.tell( new Command("CMD 1" ), null);  
	    actorRef.tell( new Command("CMD 2" ), null);  
	    actorRef.tell( new Command("CMD 3" ), null);  
	    actorRef.tell( new Command("CMD 4" ), null);  
	    actorRef.tell( new Command("CMD 5" ), null);  
	  
	    Thread.sleep(5000);  
	  
	    log.debug( "Actor System Shutdown Starting..." );  
	  
	    actorSystem.shutdown();  
	  }  
	}  
