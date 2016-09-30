/*    Project 3 by Harry Grubman
      This project is an exploration of movement on a generated world.
*/
import damkjer.ocd.*;
Camera camera;
float velocity, wallHeight;
PImage sky;
PImage wall;


void setup()
{
  fullScreen(P3D);
  camera = new Camera(this, 10, -2, 10, 1, 1000);
  camera.aim(0, -2, 0);
  wall = loadImage("wall.jpg");
  // sky.resize(10,10);
}
void draw()
{
  background(0);
  worldGen(500, 500);
  wallHeight = 100;
  lights();
  camera.feed();
  camera.dolly(velocity);
  fill(#ffffff);
  textSize(5);
  text(velocity, 10, 0);
  if(keyPressed == true)
  {
    if (key == 's')
    {
      if (velocity < 0)
      {
      velocity += 0.05;
      }
    }
    else if (key == 'w')
    {
      if (velocity > -0.06)
      {
      velocity -= 0.5;
      }
    }
    else if (key == 'd')
    {
      camera.pan(0.05);
    }
    else if (key == 'a')
    {
      camera.pan(-0.05);
    }
  }
}
void worldGen(int worldWid, int worldDep)
{
  beginShape(QUADS);
      fill(#ffffff);
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



      vertex(0,1,worldDep);       // Near Side of Wall
      vertex(0,-(wallHeight + 1),worldDep);
      vertex(worldWid,-(wallHeight + 1), worldDep);
      vertex(worldWid,1,worldDep);

      fill(#99ccff);
      vertex(0,-(wallHeight + 1),0,                0,0);  // Sky of World
      vertex(worldWid,-(wallHeight + 1),0,         0,10);
      vertex(worldWid,-(wallHeight + 1),worldDep,  10,10);
      vertex(0,-(wallHeight + 1),worldDep,         10,0);

  endShape();

  beginShape(QUADS);
    textureMode(NORMAL);
    texture(wall);
    vertex(0,1,0,       0,0);              // Left Side of Wall
    vertex(0,-(wallHeight + 1),0, 0,1000);
    vertex(0,-(wallHeight + 1),worldDep,400,1000);
    vertex(0,1,worldDep,400,0);
  endShape();

}
