
package version_A;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/serverA")
public class Server {
  //private final int worldX = 600;
  //private final int worldY = 600;
  static Stage stage = null;
 // private Portal portal = new Portal();
 
// static Map<String, Session> sessions = new ConcurrentHashMap<String, Session>();
 @OnOpen public void onOpen(Session ses){
   //sessions.put(ses.getId(), ses);
   stage = Stage.getStage();
   stage.addPlayer(ses); 
   ses.getAsyncRemote().sendText("{\"id\":\""+ses.getId()+"\",\"worldX\":"+stage.worldX+",\"worldY\":"+stage.worldY+"}"); 
   // id: id , worldX: worldX , worldY: worldY
 }
 
 @OnMessage public void onMessage(String mes, Session ses){ //throws IOException{
   String[] mp = mes.split(",");
   //String[] bp = mes.split(",");
   int mouseX = Integer.parseInt(mp[0]);
   int mouseY = Integer.parseInt(mp[1]);
   //if(mouseX>=575) stage.createPortal(stage.worldX,stage.worldY,mouseX,mouseY);
   if(mouseY>560&&mouseX>=15){
     mouseY = 595;
    
     stage.createPortal(stage.worldX,stage.worldY,mouseX,mouseY);
   }
   if(mouseX<15&&mouseY>300&&mouseY<=565){
     mouseX = 5;
     stage.createPortal(stage.worldX,stage.worldY,mouseX,mouseY);
   }
   if(mouseX>575&&mouseY>300&&mouseY<=565){
     mouseX = 595;
     stage.createPortal(stage.worldX,stage.worldY,mouseX,mouseY);
   }
   // TODO: collision 
     
     
   
   
 // ses.getAsyncRemote().sendText("{"+stage.worldX+","+stage.worldY+"}");
 }
 

@OnClose public void onClose(Session ses){
   /*
   sessions.remove(ses.getId());
   broadcast(ses.getUserProperties().get("name").toString()+" logged out","SERVER");
 */
 }
 /*
 public void broadcast(String mes, String owner){
   for(Session s : sessions.values()){
     s.getAsyncRemote().sendText(owner+":"+mes);
   }
 }
 */
}
