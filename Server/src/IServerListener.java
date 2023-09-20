import de.ralleytn.simple.json.JSONObject;

import java.util.Map;

public interface IServerListener {
    JSONObject handleRequest(Map<String, String> queryParams);
}
