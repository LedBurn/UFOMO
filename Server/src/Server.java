import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import jdk.nashorn.api.scripting.JSObject;
import org.json.simple.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class Server {

    private final ServerListener listener;

    public Server(ServerListener listener) {
        this.listener = listener;
    }

    public Map<String, String> lastMessage;
    public void startListening() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
            server.createContext("/", new MyHttpHandler(this.listener));
            server.start();
            System.out.println(" Server started on port 8001");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    private static class MyHttpHandler implements HttpHandler {

        private final ServerListener listener;

        public MyHttpHandler(ServerListener listener) {
            this.listener = listener;
        }
        @Override
        public void handle(HttpExchange httpExchange) throws IOException {
            Map<String, String> query = this.splitQuery(httpExchange.getRequestURI());
            JSONObject response = this.listener.handleRequest(query);

            httpExchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            httpExchange.getResponseHeaders().add("Content-Type", String.format("application/json; charset=%s", StandardCharsets.UTF_8));
            final byte[] rawResponseBody = response.toJSONString().getBytes();
            httpExchange.sendResponseHeaders(200, rawResponseBody.length);
            httpExchange.getResponseBody().write(rawResponseBody);
        }

        private Map<String, String> splitQuery(URI url) throws UnsupportedEncodingException {
            Map<String, String> query_pairs = new LinkedHashMap<>();
            String query = url.getQuery();
            if (query == null) return query_pairs;
            String[] pairs = query.split("&");
            for (String pair : pairs) {
                int idx = pair.indexOf("=");
                query_pairs.put(URLDecoder.decode(pair.substring(0, idx), "UTF-8"), URLDecoder.decode(pair.substring(idx + 1), "UTF-8"));
            }
            return query_pairs;
        }
    }
}
