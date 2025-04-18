public class Obstacle {
    private double obsX;
    private double obsY;
    private double obsRadius;
    private double[] obsPos = new double[2];

    public Obstacle(double x, double y, double r){
        this.obsRadius = r;
        this.obsX = x;
        this.obsY = y;
    }
    public void setRadius(double r){
        if(r >= 0){
        this.obsRadius = r;
        System.out.println("You have successfully set the radius of obstacle!");
        }
        else {
        System.out.println("The value of Radius cannot be negative!!! Please type another r value!");
        }
    }
    public void setPositionX(double px){
        this.obsX = px;
        System.out.println("You have successfully set the x position of obstacle!");
    }
    public void setPositionY(double py){
        this.obsY = py;
        System.out.println("You have successfully set the y position of obstacle!");
    }
    public void setPosition(double px, double py){
        this.obsX = px;
        this.obsY = py;
        this.obsPos[0] = this.obsX;
        this.obsPos[1] = this.obsY;
        System.out.println("You have successfully set the Position of obstacle!");
    }
    public double getX(){
        return this.obsX;
    }
    public double getY(){
        return this.obsY;
    }
    public double getRadius(){
        return this.obsRadius;
    }

    public boolean checkCollision(Particle p){
        double distance = Vector2DMath.magnitude(obsX - p.getX(), obsY - p.getY());
        return distance <= obsRadius +p.getRadius();
        }
    
    public double[] collide(Particle p){
        double[] normal = Vector2DMath.normal(p.getX() - this.obsX, p.getY() - this.obsY);
        double distance = obsRadius + p.getRadius();
        p.setPositionX(obsX + normal[0] * distance);
        p.setPositionY(obsY + normal[1] * distance);
        return normal;
    }
//    @Override
//public boolean equals(Object obj) {
//    if (this == obj) return true;
//    if (obj == null || getClass() != obj.getClass()) return false;
//    Obstacle obstacle = (Obstacle) obj;
//    return this.obsRadius == obstacle.obsRadius && this.obsX == obstacle.obsX && this.obsY == obstacle.obsY;
//}
}
