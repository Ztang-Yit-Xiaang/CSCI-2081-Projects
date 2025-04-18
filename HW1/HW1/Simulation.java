public class Simulation {
    public static void main(String[] args) {
        Particle particle = new Particle(0.0, 1.0, 0.05);
        particle.setVelocity(1.0, 0.0);
        System.out.println(particle.getY());
        
        Obstacle obstacle = new Obstacle(-0.01, 0.6, 0.1);

        for(int i = 0; i<1000; i++){
            particle.handleCollision(obstacle);
   
            particle.update(0.01);
            System.out.println(particle.getX() + "," +particle.getY());

        }
    }
}
