import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

public class ParticleAPI {
  public void addMouseClick(double x, double y) {
      try {
        OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\": " +x + ",\r\n    \"y\": " +y + ",\r\n    \"button\": \"right\"\r\n}");
        Request request = new Request.Builder()
          .url("http://127.0.0.1:5000/mouse_click")
          .method("POST", body)
          .addHeader("Content-Type", "application/json")
          .build();
        Response response = client.newCall(request).execute();
      }
      catch(Exception ex) {
        System.out.println("There was an error.");
      }
  }
}
