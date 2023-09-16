import org.json.simple.JSONObject;

import java.util.Map;

public interface ServerListener {

    public JSONObject handleRequest(Map<String, String> queryParams);
}
