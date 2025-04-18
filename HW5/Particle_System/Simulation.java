import java.util.Random;
public class Simulation {
    Random rand = new Random();
    private int numParticles = 20;
    private ObstacleNode head = null;
    private Particle[] particleList = new Particle[numParticles];
    public Particle[] getParticles(){
        return particleList;
    }
    public int getNumParticles(){
        return this.numParticles;
    }
    public void addParticles(int amount){

        if (numParticles == 0) {
            numParticles = amount; // Add the new particles directly
            particleList = new Particle[numParticles];
            for (int i = 0; i < numParticles; i++) {
                particleList[i] = new Particle(rand.nextFloat(1), 1, 0.01); // Initialize new particles
            }
            return; // Exit early
        }
   
        Particle[] particletemp;
        while (particleList.length < numParticles + amount) {
            particletemp= new Particle[particleList.length * 2];
            for (int i = 0; i < particleList.length; i++) {
                particletemp[i] = particleList[i];
            }
            particleList = particletemp;

        }
        numParticles += amount; // Update the count of particles
        for(int i = 0; i < amount; i++){
             this.getParticles()[Math.max(this.getNumParticles()- amount,0) + i] = new Particle(rand.nextFloat(1), 1, 0.01);
         }
    }

    public void removeParticles(int amount){
       if(amount <= numParticles) {
           for (int i = 0; i < amount; i++) {
               particleList[numParticles - 1 - i] = null;
           }
           numParticles -= amount;
       } else{
           numParticles = 0;// No particles left
           //particleList = new Particle[0];// Initialize as an empty array
       }

        this.numParticles -= amount;
    }

    public ObstacleNode getObstacles(){
        return head;
    }
    public ObstacleNode getTail(){
        if(head == null) {
            return head;
        } else{
            ObstacleNode currentNode = head;
            while(currentNode != null) {
                currentNode = currentNode.getNext();
            }
            return currentNode;
        }
  }
    public void addObstacle(Obstacle obstacle){
      ObstacleNode newNode = new ObstacleNode(null);
      newNode.setObstacle(obstacle);  
      if(head == null) {
            head = newNode;
        } else {
            ObstacleNode currentNode = head;
            while(currentNode.getNext() != null) {
                currentNode = currentNode.getNext();
            }
            //ObstacleNode newNode = new ObstacleNode(currentNode);
            //newNode.setObstacle(obstacle);
            currentNode.setNext(newNode);
        }
    }

    public void removeObstacle(Obstacle obstacle){
        if(head == null) {
            System.out.println("No obstacle found");
            return;
        }
        else{
            ObstacleNode currentNode = head;
            ObstacleNode prevNode = null;
            System.out.println("CurrentNode Obstacle: " + currentNode.getObstacle());
            System.out.println("Target Obstacle: " + obstacle);
            while(!currentNode.getObstacle().equals(obstacle)) {
               prevNode = currentNode;
               System.out.println("previousNode:"+ prevNode);
               currentNode = currentNode.getNext();
            }
            System.out.println(currentNode);
            System.out.println(prevNode);
                if (prevNode != null) {
                      prevNode.setNext(currentNode.getNext());
                } else {
                      head = currentNode.getNext();  
    }

            



        }
    }
}
