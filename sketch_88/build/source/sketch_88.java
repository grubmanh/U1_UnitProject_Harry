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
Cloud cloud1;
float velocity, wallHeight;
PImage sky;
PImage wall;
int worldSize = 500;


public void setup()
{
  
  camera = new Camera(this, 10, -2, 10, 1, 1000);
  cloud1 = new Cloud();
  camera.aim(0, -2, 0);
  wall = loadImage("wall.jpg");
  // sky.resize(10,10);
}
public void draw()
{
  background(0);
  worldGen(worldSize, worldSize);
  wallHeight = 100;
  lights();
  camera.feed();
  camera.dolly(velocity);
  fill(0xffffffff);
  textSize(5);
  cloud1.start(1);
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
      velocity -= 0.5f;
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
public void worldGen(int worldWid, int worldDep)
{
  beginShape(QUADS);
      fill(255);
      vertex(0,0,0);              // Far Side of Cube
      vertex(0,1,0);
      vertex(worldWid,1, 0);
      vertex(worldWid,0,0);

      vertex(worldWid,0,0);         // Right Side of Cube
      vertex(worldWid,1,0);
      vertex(worldWid,1,worldDep);
      vertex(worldWid,0,worldDep);

      vertex(0,1,0);         // Top Side of Cube
      vertex(0,1,worldDep);
      vertex(worldWid,1,worldDep);
      vertex(worldWid,1,0);

      vertex(0,1,worldDep);    // Front Side of Cube
      vertex(0,0,worldDep);
      vertex(worldWid,0,worldDep);
      vertex(worldWid,1,worldDep);

      vertex(0,0,0);              // Left Side of Cube
      vertex(0,0,worldDep);
      vertex(0,1,worldDep);
      vertex(0,1,0);

      vertex(0,0,0);              // Bottom Side of Cube
      vertex(0,0,worldDep);
      vertex(worldWid,0,worldDep);
      vertex(worldWid,0,0);

      vertex(0,1,0);              // Far Side of Wall
      vertex(0,-(wallHeight + 1),0);
      vertex(worldWid,-(wallHeight + 1),0);
      vertex(worldWid,1,0);

      vertex(worldWid,1,0);       // Right Side of Wall
      vertex(worldWid,-(wallHeight + 1),0);
      vertex(worldWid,-(wallHeight + 1),worldDep);
      vertex(worldWid,1,worldDep);

      vertex(0,1,0);              // Left Side of Wall
      vertex(0,-(wallHeight + 1),0);
      vertex(0,-(wallHeight + 1),worldDep);
      vertex(0,1,worldDep);

      vertex(0,1,worldDep);       // Near Side of Wall
      vertex(0,-(wallHeight + 1),worldDep);
      vertex(worldWid,-(wallHeight + 1), worldDep);
      vertex(worldWid,1,worldDep);

      fill(0xff99ccff);
      vertex(0,-(wallHeight + 1),0,                0,0);  // Sky of World
      vertex(worldWid,-(wallHeight + 1),0,         0,10);
      vertex(worldWid,-(wallHeight + 1),worldDep,  10,10);
      vertex(0,-(wallHeight + 1),worldDep,         10,0);

  endShape();
}

class Cloud
{
  float x_pos = 250.0f;
  float y_pos = -2.0f;
  float z_pos = 250.0f;
  int cloudLength = 100;
  int cloudWidth = 50;
  int cloudHeight = -10;

  public void start(int cloudNumber)
  {
    pushMatrix();
    translate(x_pos, y_pos, z_pos);
    cloudShape();
    popMatrix();
  }

  public void cloudShape()
  {
    beginShape(QUADS);
      fill(255);
      vertex(0,0,0);   // Bottom of Cloud
      vertex(0,0,cloudLength);
      vertex(cloudWidth,0,cloudLength);
      vertex(cloudWidth,0,0);

      vertex(0,cloudHeight,0);   // Top of Cloud
      vertex(0,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,0);

      vertex(0,0,0);   // Left of Cloud
      vertex(0,0,cloudLength);
      vertex(0,cloudHeight,cloudLength);
      vertex(0,cloudHeight,0);

      vertex(cloudWidth,0,0);    // Right of Cloud
      vertex(cloudWidth,0,cloudLength);
      vertex(cloudWidth,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,0);

      vertex(0,0,0);    // Front of Cloud
      vertex(0,cloudHeight,0);
      vertex(cloudWidth,cloudHeight,0);
      vertex(cloudWidth,0,0);

      vertex(0,0,cloudLength);   //Back of Cloud
      vertex(0,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,cloudLength);
      vertex(cloudWidth,0,cloudLength);
    endShape();
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
