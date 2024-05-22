import processing.core.PApplet;
import processing.core.PImage;

public class Sketch extends PApplet {

  float[] snowX = new float[42];
  float[] snowY = new float[42];
  float speed = 2;
  int snowDiameter = 10;
  PImage reindeer;
  
  float reindeerX, reindeerY;
  boolean gameOver = false;

  public void settings() {
    size(1200, 1200);

    // Reindeer Image
    reindeer = loadImage("reindeer.png");
    reindeer.resize(64, 64);
  }

  public void setup() {
    background(0);

    // Generate random x and y values for snowflakes
    for (int i = 0; i  < snowX.length; i++) {
      snowX[i] = random(width);
      snowY[i] = random(height);
    }
    
    // Reindeer position
    reindeerX = width / 2;
    reindeerY = height / 2;
  }

  public void draw() {
    if (gameOver) {
      background(255, 0, 0);
      textAlign(CENTER, CENTER);
      textSize(100);
      fill(255);
      text("Game Over", width / 2, height / 2);
      return;
    }

    background(0);

    // Draw snow and other methods
    snow();
    player();
    checkCollision();
  }

  // Draw snowflakes
  public void snow() {
    for (int i = 0; i < snowX.length; i++) {
      circle(snowX[i], snowY[i], snowDiameter);
      snowY[i] += speed;

      // Reset snowflakes
      if (snowY[i] > height) {
        snowY[i] = 0;
      }
    }
  }

  // Key Pressing
  public void keyPressed() {
    if (key == 'w') {
      reindeerY -= 5;
    } else if (key == 'a') {
      reindeerX -= 5;
    } else if (key == 's') {
      reindeerY += 5;
    } else if (key == 'd') {
      reindeerX += 5;
    }
  }

  // Player Elements
  public void player() {
    image(reindeer, reindeerX, reindeerY);
  }

  // Detecting collision between player and snow
  public void checkCollision() {
    for (int i = 0; i < snowX.length; i++) {
      float distance = dist(reindeerX + reindeer.width / 2, reindeerY + reindeer.height / 2, snowX[i], snowY[i]);
      if (distance < snowDiameter / 2 + reindeer.width / 2) {
        gameOver = true;
      }
    }
  }
}