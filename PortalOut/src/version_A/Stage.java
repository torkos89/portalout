package version_A;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.Session;

public class Stage implements Runnable{
  private static Stage stage= null;
  private static Thread thread = null;
  final Map<Integer, Entity> ENTITIES = new ConcurrentHashMap<>();
  final Map<Integer, Entity> PLAYERS = new ConcurrentHashMap<>();
  final Entity[] portals = new Entity[2];
  
  final int worldX = 600;
  final int worldY = 600;
  public int stageId = 0;
  private int id = 0;
  private int portalCount = 0;
  private Map<Integer,int[]> layout = new HashMap<>(); // TODO: move bricks to server, brick update / collision
  
  public Entity addPlayer(Session ses){
    Entity session = new Entity("player",ses);
    PLAYERS.put(PLAYERS.size(),session);
    return session;
  }
  public static Stage getStage(){
    if(stage==null){
      stage = new Stage();
      thread = new Thread(stage);
      thread.start();  // stage.run()
    }
    return stage;
  }
  
  public Entity createBall(){
    return new Entity("ball",worldX/2,worldY/2,new BallUpdate(worldX,worldY,2,10,portals)); // type, x, y, update(worldX,worldY,vx,vy)
  }
  public void createPortal(int worldX, int worldY, int mouseX, int mouseY){
    portals[portalCount++%2] = new Entity("portal-"+(portalCount%2==0?"orange":"blue"),mouseX,mouseY,new PortalUpdate(worldX,worldY));
  }
  public void removePortal(){
    ENTITIES.remove("portal");
  }
  
  public Entity createBrick(int x, int y){
    return new Entity("brick",400,400,new BrickUpdate(worldX,worldY,400,400)).setColor("lightgreen").setLength(25).setHeight(10);
  }
  
  @Override
  public void run(){
    try{
      ENTITIES.put(id++,createBall());
      while(true){
        for(Entity e: ENTITIES.values()) e.update();
        String bricks = "";
        String balls = "";
        for(Entity e: ENTITIES.values()){  
          switch(e.getType()){
          case"brick":  bricks += "," + e.toString(); break;
          case"ball": balls += "," + e.toString(); break;
         // case"portal": portal += "," + e.toString(); break;
          }
         
        }
        String state = "{\"ball\":"+(balls.length()>0?balls.substring(1):"")
            +",\"portals\":["+(portals[0]==null?"\"\"":portals[0].toString())+","+(portals[1]==null?"\"\"":portals[1].toString())+"]}";
        for(Entity p: PLAYERS.values()){
          p.send(state);
        }
        Thread.sleep(50);
      }
    }
    catch(InterruptedException e){
     e.printStackTrace();
    }
  }
 // CVS.toBlob(function(blob){let i = document.createElement("img"), url = URL.createObjectURL(blob); i.src=url; document.body.appendChild(i)})
}
