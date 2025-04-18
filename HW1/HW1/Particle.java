public class Particle{
    private double positionX;
    private double positionY;
    private double radius;
    private double[] velocityVec = new double[2];
    private double gravity; //gravity acceleration g = 9.8 m/s^2
    //private double bouncingLoss;
    public void printCoordinate(double[] array){
        System.out.print("(");
        for(int i = 0; i < array.length; i++){
            
            System.out.printf("%.4f", array[i]);
            if(i < array.length - 1){
                System.out.print( ", ");
            }
        }
        System.out.println(")");
    }
    public Particle(double xPos, double yPos, double r){
        gravity = -9.8;
        if(yPos <= r){
            System.out.println("The y position of particle should be greater than its radius.");
            System.exit(0);// we do not want to put the particle underground.
        }else {
            this.radius = r;
            this.positionX = xPos;
            this.positionY = yPos;
            
        }
    }
    public void setRadius(double r){
        if(r >= 0){
        this.radius = r;
        System.out.println("You have successfully set the radius!");
        }
        else {
        System.out.println("The value of Radius cannot be negative!!! Please type another r value!");
        }
    }
    public void setGravity(double g){
        this.gravity = g;
        System.out.println("You have successfully set the gravity!");
    }
    public void setPositionX(double px){
        this.positionX = px;
        //System.out.println("You have successfully set the x position!");
    }
    public void setPositionY(double py){
        this.positionY = py;
        //System.out.println("You have successfully set the y position!");
    }
    
    
    public void setPosition(double px, double py){
        this.positionX = px;
        this.positionY = py;
        System.out.println("You have successfully set the Position!");
    }
    public void setVelocity(double vx, double vy){
        this.velocityVec[0] = vx;
        this.velocityVec[1] = vy;
        System.out.println("You have successfully set the velocity!");
    }

    public double getX(){
        return this.positionX;
    }
    public double getY(){
        return this.positionY;
    }
    public double getRadius(){
        return this.radius;
    }
    public double[] getVelocity(){
        return this.velocityVec;
    }

    public double getGravity(){
        return this.gravity;
    }

    public void update(double dt){
        //velocity = velocity + acceleration * dt
        velocityVec[1] = velocityVec[1] + gravity * dt;
        //position = position + velocity * dt
        positionX = positionX + velocityVec[0] * dt;
        positionY = positionY + velocityVec[1] * dt;

        if(positionY <= radius){
            positionY = radius;
            velocityVec[1] = -1 * velocityVec[1]*0.8;
        }
    }
    public void handleCollision(Obstacle obstacle){
        
        double[] normal = new double[2];
        if(obstacle.checkCollision(this)){
            normal = obstacle.collide(this);
        
            //bounce (reflect)
            //if the velocity is too small to bounce (|v| < 0.2)
            if(Vector2DMath.magnitude(velocityVec[0], velocityVec[1]) < 0.2){
            
                velocityVec[0] = normal[0] * 0.5;
                velocityVec[1] = normal[1] * 0.5;
            }else{
            double[] reflect = Vector2DMath.reflect(normal, velocityVec[0], velocityVec[1]);
            velocityVec[0] = reflect[0] * 0.75;
            velocityVec[1] = reflect[1] * 0.75;
        }
        }
    }

}