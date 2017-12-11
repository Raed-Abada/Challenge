package tn.challenge;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Abada Raed on 10/12/2017.
 */

public class insertRequest extends StringRequest {
    private static final String INSERT_REQUEST_URL = "https://erotic-ally.000webhostapp.com/insert.php";
    private Map<String, String> params;

    public insertRequest(String name, int number, Response.Listener<String> Listener) {
        super(Method.POST, INSERT_REQUEST_URL, Listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("number", number + "");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

