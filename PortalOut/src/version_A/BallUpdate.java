
/*TODO:  have collision solve for both portals, do 2 separate checks in update? for each portal, 
adjust X and Y positions if collision occurs, increase vx and vy as well...
*/

package version_A;

public class BallUpdate extends Update{ 
  public float vx = 0;
  public float vy = 0;
  public Entity[] portals;

  BallUpdate(int worldX,int worldY,int vx,int vy,Entity[] portals){
    super(worldX, worldY);
    this.vx = vx;
    this.vy = vy;
    this.portals = portals;
  }
  
  public void update(Entity e){
    int x = e.getX();
    int y = e.getY();
    
    if(y>worldY-15){              // TODO: fix ground balls, should have vx applied to vy
      if(portals[1]!=null){
        if(collision(x,y,portals[0])){ // blue
          switch(portals[1].y){ 
           case 595: x=(portals[1].x); y=(portals[1].y); vy+=vx+4; /*vx*=-1;*/ break; // vx flip , vy speed
          }
          switch(portals[1].x){
           case 5: x=(portals[1].x); y=(portals[1].y); vx=vx+vy; break; // breaks without vx
           case 595: x=(portals[1].x); y=(portals[1].y); vx=vy+vx; break;
          }
        }
        else if(collision(x,y,portals[1])){ // orange
          switch(portals[0].y){
           case 595: x=(portals[0].x); y=(portals[0].y); vy+=vx+4; /*vx*=-1;*/ break; // bottom
          }
          switch(portals[0].x){
           case 5: x=(portals[0].x); y=(portals[0].y); vx=vy+vx; break; // also breaks without vx
           case 595: x=(portals[0].x); y=(portals[0].y); vx=vy+vx; break;
          }
        }
      }
      vy*=-.9; // default reduce bounce 'gravity'
    }
    else vy++;
    if(x>worldX-15||x<15){
      /*
      if(portals[1]!=null){
        if(collision(x,y,portals[0])){
          switch(portals[1].x){
           case 5: x=portals[1].x; y=portals[1].y; vx=vy*=-1; break;
           case 595: x=portals[1].x; y=portals[1].y; vx=vy; break;
          }
          switch(portals[1].y){
           case 595: x=portals[1].x; y=portals[1].y; vx*=-1; vy+=4; break;
          }
        }
        else if(collision(x,y,portals[1])){
          switch(portals[0].x){
           case 5: x=portals[0].x; y=portals[0].y; vx=vy*=-1; break;
           case 595: x=portals[0].x; y=portals[0].y; vx=vy; break;
          }
          switch(portals[1].y){
           case 595: x=portals[0].x; y=portals[0].y; vx*=-1; vy+=4; break;
          }
        }
      }
      */
      vx*=-1; // default
    }
    
    //if(y<15) vy*=-1;
    
    /*
    if(y > worldY-20){   
      if(portals[1]!=null){ // has both portals
        if(collision(x,y,portals[0])){// blue
          if(portals[1].y==595){
            x=(portals[1].x);
            y=(portals[1].y);
          }
          else if(portals[1].x==5){   // left
            x=(portals[1].x+20); 
            y=(portals[1].y);
            vx*= -vy;
            vy*=-1;
          }
          else if(portals[1].x==595){ // right
            x=(portals[1].x-20); 
            y=(portals[1].y);
            vx*=-vy;
            vy*=-1;
          }
        }
        else if(collision(x,y,portals[1])){// orange
          if(portals[0].y==595){
            x=(portals[0].x);
            y=(portals[0].y);
          }
          else if(portals[0].x==5){   // left
            x=(portals[0].x+20); 
            y=(portals[0].y);
            vx*= -vy;
            vy*=-1;
          }
          else if(portals[0].x==595){ // right
            x=(portals[0].x-20); 
            y=(portals[0].y);
            vx*=-vy;
            vy*=-1;
          }
        }
        
        else vx*=-.9; // bottom collision w/ portals
      }
      else vx*= -.9; // bottom collision w/o portals
    }
    else if(x > worldX-20||x < 20){   
      if(portals[1]!=null){           // has both portals
        if(collision(x,y,portals[0])){// blue
          if(portals[1].x==5){        // left
            x=(portals[1].x+20); 
            y=(portals[1].y);
            vx= vy;
            vy*=-1;
          }
          else if(portals[1].x==595){ // right
            x=(portals[1].x-20); 
            y=(portals[1].y);
            vx=vy;
            vy*=-1;
          }
          else if(portals[1].y==595){ // bottom
            x=(portals[1].x-20); 
            y=(portals[1].y);
            vx=vy;
            vy*=-1;
          }
        }
        else if(collision(x,y,portals[1])){// orange
          if(portals[0].x==5){
            x=(portals[0].x+20); 
            y=(portals[0].y);
            vx= vy;
            vy*=-1;
          }
          else if(portals[0].x==595){
            x=(portals[0].x-20); 
            y=(portals[0].y);
            vx= vy;
            vy*=-1;
          }
          else if(portals[1].y==595){ // bottom
            x=(portals[1].x-20); 
            y=(portals[1].y);
            vx=vy;
            vy*=-1;
          }
        }
         else vx*=-.5;
      }
      else vx*= -.5;
    }
 
    if(y > worldY-15){
      vy*= -1; // TODO: change back to .85
    }
    else vy++;
    */
    /*
    if(y < worldY+15){ // top
      vy*= -1; 
    }  
    */
    e.setX(x+Math.round(vx));
    e.setY(y+Math.round(vy));
    
    // if ball hits portal(point) +- length/width{ reposition ball to other portal } 
  }
  
  private boolean collision(int x,int y,Entity p){  
    if(p.x==5){ // left
      if(Math.abs(x-p.x)<20){ // portal height
        if(Math.abs(y-p.y)<20){
          return true;
        }
      }   
    }
    else if(p.x==595){ // right 
      if(Math.abs(x-p.x)<20){ // portal height
        if(Math.abs(y-p.y)<20){
          return true;
        }
      }
    }
    else if(p.y==595){ // bottom
      if(Math.abs(y-p.y)<20){ // portal height 
        if(Math.abs(x-p.x)<20){
          return true;
        }
      }
    }
    return false;  // fall through
  }
}


