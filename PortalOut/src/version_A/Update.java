package version_A;

public abstract class Update {
  protected int worldX;
  protected int worldY;
  
  Update(int worldX,int worldY){
    this.worldX = worldX;
    this.worldY = worldY;  
    
  }
  public abstract void update(Entity e);
}
