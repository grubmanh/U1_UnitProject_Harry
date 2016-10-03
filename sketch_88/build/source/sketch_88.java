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

/*    Unit 1 Final Project by Harry Grubman
      This project is a combination of all the concepts learned in Unit 1.
      It is randomly generated clouds moving with vectors in a generated world.
*/
   // Import OCD Cam
Camera camera;
Cloud cloud1, cloud2, cloud3, cloud4, cloud5;   //  Creates 5 clouds
float velocity;
int worldSize = 500;    // Square world of length and width worldSize
int wallHeight = 100;   // Walls of height wallHeight

public void setup()
{
  
  camera = new Camera(this, 10, -5, 10, 1, 1000);
  cloud1 = new Cloud();
  cloud2 = new Cloud();
  cloud3 = new Cloud();
  cloud4 = new Cloud();
  cloud5 = new Cloud();
  camera.aim(worldSize, -5, worldSize);
}
public void draw()
{
  background(0);
  worldGen(worldSize, worldSize, wallHeight);   // Generated the square world of worldSize
  lights();
  camera.feed();
  camera.dolly(velocity);
  cloud1.render();    // Rendering and movement of clouds
  cloud1.cloudMovement();
  cloud2.render();
  cloud2.cloudMovement();
  cloud3.render();
  cloud3.cloudMovement();
  cloud4.render();
  cloud4.cloudMovement();
  cloud5.render();
  cloud5.cloudMovement();
  ocdMovement();      // Function for movement of OCD cam
}
public void worldGen(int worldWid, int worldDep, int wallHeight)
{
  beginShape(QUADS);   // Create world with custom shapes
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
      fill(0xff7CAF4C);
      vertex(0,0,0);              // Bottom Side of Cube
      vertex(0,0,worldDep);
      vertex(worldWid,0,worldDep);
      vertex(worldWid,0,0);
      fill(0xffffffff);
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
      vertex(0,-(wallHeight + 1),0);  // Sky of World
      vertex(worldWid,-(wallHeight + 1),0);
      vertex(worldWid,-(wallHeight + 1),worldDep);
      vertex(0,-(wallHeight + 1),worldDep);

  endShape();
}
public void ocdMovement()    // Move OCD cam with keyboard WASD keys
{
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
class Cloud   // Cloud object
{
  PVector location;
  PVector velocity;
  float y_pos = -90.0f;
  int cloudLength = 100;
  int cloudWidth = 50;
  int cloudHeight = -10;
  float cloudXMinSpeed = 0.05f;
  float cloudXMaxSpeed = 0.15f;
  float cloudZMinSpeed = 0.05f;
  float cloudZMaxSpeed = 0.15f;

  Cloud()
  {
    location = new PVector(random(0,worldSize), y_pos, random(0,worldSize));
    velocity = new PVector(random(cloudXMinSpeed,cloudXMaxSpeed),0,random(cloudZMinSpeed,cloudZMaxSpeed));
  }
  public void render()
  {
    pushMatrix();
    translate(location.x, location.y, location.z); // Show cloud at position
    cloudShape();
    popMatrix();
  }
  public void cloudMovement()
  {
    if (location.x < worldSize && location.z < worldSize) // Move cloud at velocity
      location.add(velocity);
    if (location.x > worldSize) // Keep cloud in x axis
      location.add(-(worldSize + cloudWidth),0,0);
    if (location.z > worldSize) // Keep cloud in y axis
      location.add(0,0,-(worldSize + cloudLength));
  }
  public void cloudShape()
  {
    noStroke();
    beginShape(QUADS);  // Custom cloud shape
      fill(0xffffffff);
      vertex(0,0,0);   // Bottom of Cloud
      vertex(0,0,cloudLength);
      vertex(cloudWidth,0,cloudLength);
      vertex(cloudWidth,0,0);
      fill(0xffffffff);
      vertex(0,cloudHeight,0);   // Top of Cloud
      vertex(0,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,0);
      fill(0xffffffff);
      vertex(0,0,0);   // Left of Cloud
      vertex(0,0,cloudLength);
      vertex(0,cloudHeight,cloudLength);
      vertex(0,cloudHeight,0);
      fill(0xffffffff);
      vertex(cloudWidth,0,0);    // Right of Cloud
      vertex(cloudWidth,0,cloudLength);
      vertex(cloudWidth,cloudHeight,cloudLength);
      vertex(cloudWidth,cloudHeight,0);
      fill(0xffffffff);
      vertex(0,0,0);    // Front of Cloud
      vertex(0,cloudHeight,0);
      vertex(cloudWidth,cloudHeight,0);
      vertex(cloudWidth,0,0);
      fill(0xffffffff);
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
