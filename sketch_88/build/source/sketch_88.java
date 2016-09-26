import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import damkjer.ocd.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class sketch_88 extends PApplet {

/*    Project 3 by Harry Grubman
      This project is an exploration of movement on a generated world.
*/

Camera camera;
float velocity;

public void setup()
{
  
  camera = new Camera(this, 10, -2, 10);
  camera.aim(0, -2, 0);
}
public void draw()
{
  background(0);
  worldGen(100, 100);
  lights();
  camera.feed();
  camera.dolly(velocity);
  fill(0xffffffff);
  textSize(10);
  text(velocity, 10, 0);
  if(keyPressed == true)
  {
    if (key == 's')
    {
      if (velocity < 0)
      {
      velocity += 0.05f;
      }
    }
    else if (key == 'w')
    {
      if (velocity > -0.06f)
      {
      velocity -= 0.1f;
      }
    }
    else if (key == 'd')
    {
      camera.pan(0.05f);
    }
    else if (key == 'a')
    {
      camera.pan(-0.05f);
    }
  }
}
public void worldGen(int worldWid, int worldDep) //  Generates a world of specified dimensions
{
  for (int xPosition = 0; xPosition < worldWid; xPosition++)
  {
    for (int zPosition = 0; zPosition < worldDep; zPosition++)
    {
      pushMatrix();
      translate(xPosition, 0, zPosition);
      box(1, 1, 1);
      popMatrix();
    }
  }
}
  public void settings() {  fullScreen(P3D); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "sketch_88" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
