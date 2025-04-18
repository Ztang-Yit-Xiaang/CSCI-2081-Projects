import java.util.Random;
Particle p = new Particle(0.0,1, 0.01);
Obstacle obs = new Obstacle(0.0, 0.5,0.2);
Simulation simulation = new Simulation();
Random rand = new Random();

double dt = 0.01;
int amount = 50;
ObstacleAPI api = new ObstacleAPI();
void setup(){
  size(500, 500);
  for(int i = 0; i < simulation.getNumParticles();i++){
    simulation.getParticles()[i] = new Particle(rand.nextFloat(1), 1, 0.01);
}
  simulation.addObstacle(obs);
  api.getObstacles
  simulation.addObstacle()
  ellipseMode(RADIUS);
    
}
void draw(){
  background(100, 100, 100);
  p.handleCollision(obs);
  p.update(0.01);
  //System.out.println(width+","+height);
  //circle((float)p.getX()*width, (float)(height-p.getY()*height), (float)p.getRadius()*width);
  //circle((float)obs.getX()*width, (float)(height-obs.getY()*height), (float)obs.getRadius()*width);
if (keyPressed) {
    if (key == '+'){
         simulation.addParticles(amount);
     
    }else if(key == '-'){
         simulation.removeParticles(amount);
    }
}
    
 if (simulation.getNumParticles() > 0) {
    for (int i = 0; i < simulation.getNumParticles(); i++) {
      // Check collision for each obstacle
      ObstacleNode currentNode = simulation.getObstacles();
      while (currentNode != null) {
        Obstacle obstacle = currentNode.getObstacle();
        simulation.getParticles()[i].handleCollision(obstacle);
        currentNode = currentNode.getNext();
      }
          // Update the particle's position and draw it
        simulation.getParticles()[i].update(dt);
        circle((float) simulation.getParticles()[i].getX() * width, 
               (float) (height - simulation.getParticles()[i].getY() * height), 
               (float) simulation.getParticles()[i].getRadius() * width);
    }
}
 // Draw obstacles
 ObstacleNode currentNode = simulation.getObstacles();
  while (currentNode != null) {
    Obstacle obstacle = currentNode.getObstacle();
    circle((float) obstacle.getX() * width,
           (float) (height-obstacle.getY() * height),
           (float) obstacle.getRadius() * width);
    currentNode = currentNode.getNext();
  }
}
void mouseClicked() {
  System.out.println("clicked");
  if (mouseButton == LEFT) {
    System.out.println("left");
    Obstacle newObstacle = new Obstacle((double) mouseX/width, 1-(double) mouseY/height, 0.2);
    simulation.addObstacle(newObstacle);
    api.addMouseClick(1.0*mouseX/width, 1.0*mouseY/height);

  } else if (mouseButton == RIGHT) {
      ObstacleNode currentNode = simulation.getObstacles();
      int id = 0;
      while (currentNode != null) {
        Obstacle obstacle = currentNode.getObstacle();
        id = id + 1;
        currentNode = currentNode.getNext();
        if (obstacle != null) {
            float distance = dist((float) (obstacle.getX()*width ), (float) ((1- obstacle.getY())*height), mouseX, mouseY);
            System.out.println(mouseX+" "+distance+", "+obstacle.getRadius()* width);
            if (distance <= obstacle.getRadius() * width) {
              System.out.println("Remove");
              api.removeMouseClick(1.0*mouseX/width, 1.0*mouseY/height, id);
              simulation.removeObstacle(obstacle);
              break;
            }
           
        }

  }
  }
}
