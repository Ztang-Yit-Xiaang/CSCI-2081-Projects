import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
public class ObstacleAPI {
    public void addMouseClick(double x, double y) {
        try {
              OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
              MediaType mediaType = MediaType.parse("application/json");
              RequestBody body = RequestBody.create(mediaType, "    {\r\n \"radius\": " + 0.2 + ",\r\n \"x_val\": "+ x +",\r\n \"y_val\": " + y + "\r\n}");
              Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/obstacle")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
              Response response = client.newCall(request).execute();
              if (response.isSuccessful()) {
                System.out.println("Obstacle added successfully.");
              } else {
                 System.out.println("Failed to add obstacle. Code: " + response.code());
              }
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
    public void removeMouseClick(double x, double y) {
        try {
              OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
              MediaType mediaType = MediaType.parse("text/plain");
              RequestBody body = RequestBody.create(mediaType, "");
              Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/obstacle/" + x + "/" + y)
                .method("DELETE", null)
                .build();
              Response response = client.newCall(request).execute();
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
    public void getObstacle(Simulation simulation) {
        try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/obstacles")
                .method("GET", null)
                .build();
                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                  String jsonResponse = response.body().string();
                  ObjectMapper mapper = new ObjectMapper();
                  JsonNode obstaclesArray = mapper.readTree(jsonResponse);  
                  
                  for (JsonNode obstacleNode : obstaclesArray) {
                      double x = obstacleNode.get("x_val").asDouble();
                      double y = obstacleNode.get("y_val").asDouble();
                      double radius = obstacleNode.get("radius").asDouble();
  
                      // Create an obstacle object
                      Obstacle obstacle = new Obstacle(x, y, radius);
                      // Add the obstacle to the linked list
                      simulation.addObstacle(obstacle);
                  }
                } else {
                    System.out.println("Failed to fetch obstacles. Code: " + response.code());
                    
                }
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
}