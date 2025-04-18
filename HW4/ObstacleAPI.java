import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
public class ObstacleAPI {
    public void addMouseClick(double x, double y) {
        try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
                MediaType mediaType = MediaType.parse("application/json");
                RequestBody body = RequestBody.create(mediaType, "    {\r\n        \"id\": 1,\r\n        \"radius\": 0.2,\r\n        \"x_val\": 1.0,\r\n        \"y_val\": 0.5\r\n    }");
                Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/obstacle")
                .method("POST", body)
                .addHeader("Content-Type", "application/json")
                .build();
                Response response = client.newCall(request).execute();
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
    public void removeMouseClick(double x, double y, int id) {
        try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/obstacle/" + id)
                .method("DELETE", body)
                .build();
                Response response = client.newCall(request).execute();
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
    public void getObstacles() {
        try {
                OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
                MediaType mediaType = MediaType.parse("text/plain");
                RequestBody body = RequestBody.create(mediaType, "");
                Request request = new Request.Builder()
                .url("http://127.0.0.1:5000/obstacles")
                .method("GET", body)
                .build();
                Response response = client.newCall(request).execute();
        }
        catch(Exception ex) {
          System.out.println("There was an error.");
        }
    }
}
