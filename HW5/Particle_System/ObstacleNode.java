public class ObstacleNode {
    private Obstacle obs;
    private ObstacleNode nextNode;
    public ObstacleNode(ObstacleNode obstacleNode){
        if (obstacleNode != null) {
            this.obs = obstacleNode.getObstacle();
            this.nextNode = obstacleNode.getNext();
        }
    }
    public void setObstacle(Obstacle obstacle){
        this.obs = obstacle;
    }
    public Obstacle getObstacle(){
        return obs;

    }
    public ObstacleNode getNext(){
        return this.nextNode;
    }
    public void setNext(ObstacleNode next){
        this.nextNode = next;
    }
}
