package version_A;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Input implements ActionListener{
  private int mouseX;
  private int mouseY;
  
  public void setMouse(int x, int y){
    mouseX = x;
    mouseY = y;
  }
  public int getMouseX(){
    return mouseX;
  }
  public int getMouseY(){
    return mouseY;
  }
  //playSpace.addActionListener(Input);
  @Override
  public void actionPerformed(ActionEvent e) {
    
    // TODO action
    
  }
}
