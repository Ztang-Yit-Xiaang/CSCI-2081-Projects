ParticleAPI api = new ParticleAPI();

void setup() {
  size(500, 500);
  ellipseMode(RADIUS);
}

void draw() {
  background(100, 100, 100);
  
  fill(255,0,0);
  circle(50, 50, 25);
}

void mousePressed() {
  System.out.println("" + 1.0*mouseX/width + "," + 1.0*mouseY/height);
  
  api.addMouseClick(1.0*mouseX/width, 1.0*mouseY/height);
  
}
