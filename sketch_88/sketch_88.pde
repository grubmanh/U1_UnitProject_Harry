/*    Project 3 by Harry Grubman
      This project is an exploration of movement on a generated world.
*/
import damkjer.ocd.*;
Camera camera;
float velocity;

void setup()
{
  fullScreen(P3D);
  camera = new Camera(this, 10, -2, 10);
  camera.aim(0, -2, 0);
}
void draw()
{
  background(0);
  worldGen(100, 100);
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
      velocity -= 0.1;
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
void worldGen(int worldWid, int worldDep) //  Generates a world of specified dimensions
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
