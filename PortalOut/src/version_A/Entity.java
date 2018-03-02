package version_A;

import javax.websocket.Session;

public class Entity {
// blocks, ball, portalB, portalO
  @SuppressWarnings("unused")
  final private Update U;
  private int length;
  private int height;
  private double radius;
  private int health;
  final private Session SES;
  public int x = 0;
  public int y = 0;
  private String color;
  private String type = "";
  
  Entity(String type,Session ses){
    this.type = type;
    SES = ses;
    U = null;
  }
  Entity(String type,int x,int y,Update update){
    this.x = x;
    this.y = y;
    this.type = type;
    SES = null;
    U = update;
  }
  public void update(){
    if(U!=null) U.update(this);
  }
  public void send(String mes){
    if(SES!=null) send(mes,0);
  }
  public void send(String mes,int count){
    
    try{
      SES.getAsyncRemote().sendText(mes);
    }catch(IllegalStateException e){
      if(count>5){
        e.printStackTrace();
        System.exit(1); // fail
      }
      try{
        Thread.sleep(5);
        send(mes,count+1);
      }catch(InterruptedException t){
        t.printStackTrace();
      }
    }
  }
  public int getX(){
    return x;
  }
  public void setX(int x){
    this.x = x;
  }
  public int getY(){
    return y;
  }
  public void setY(int y){
    this.y = y;
  }
  public String getColor(){
    return color;
  }
  public Entity setColor(String color) {
    this.color = color;
    return this;
  }
  public int getLength(){
    return length;
  }
  public Entity setLength(int length) {
    this.length = length;
    return this;
  }
  public int getHeight(){
    return height;
  }
  public Entity setHeight(int height) {
    this.height = height;
    return this;
  }
  public double getRadius(){
    return radius;
  }
  public Entity setRadius(double radius) {
    this.radius = radius;
    return this;
  }
  public int getHealth(){
    return health;
  }
  public Entity setHealth(int health) {
   this.health = health;
    return this;
  }
  public void reduceHealth(){
    this.health--;
  }
  public String toString(){
    return "{\"x\":"+x+",\"y\":"+y+",\"color\":\""+color+"\",\"type\":\""+type+"\"}";
  }
  public String getType() {
    return type;
  }
}
