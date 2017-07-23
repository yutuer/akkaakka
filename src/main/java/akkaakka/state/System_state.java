package akkaakka.state;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akkaakka.Command;
import akkaakka.Event;

public class System_state {
	public static final Logger log = LoggerFactory.getLogger(System.class);  
	  
	  public static void main(String... args) throws Exception {  
	  
	    final ActorSystem actorSystem = ActorSystem.create("actor-server");  
	  
	    final ActorRef handler = actorSystem.actorOf(Props.create(EventHandler. class));  
	    // 订阅  
	    actorSystem.eventStream().subscribe(handler , Event.class);  
	  
	    Thread.sleep(2000);  
	  
	    final ActorRef actorRef = actorSystem.actorOf(Props.create(BaseProcessor. class), "eventsourcing-processor" );  
	  
	    actorRef.tell( new Command("CMD 1" ), null);  
	    actorRef.tell( new Command("CMD 2" ), null);  
	    actorRef.tell( new Command("CMD 3" ), null);  
	    actorRef.tell( "snapshot", null );//发送保存快照命令  
	    actorRef.tell( new Command("CMD 4" ), null);  
	    actorRef.tell( new Command("CMD 5" ), null);  
	    actorRef.tell( "printstate", null );  
	  
	    Thread.sleep(5000);  
	  
	    log.debug( "Actor System Shutdown Starting..." );  
	  
	    actorSystem.shutdown();  
	  }  
}
