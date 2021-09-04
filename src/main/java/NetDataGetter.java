import org.json.JSONObject;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.HashMap;

public class NetDataGetter {
    private String host = "http://192.168.1.100:19999/api/v1/data";
    private String charset = "UTF-8";


    public JSONObject getInfo(NetDataParams params) throws IOException {
        HashMap<String, String> data = new HashMap<>();

        URL url = new URL(host);
        String charset = "UTF-8";

        String query = params.getQuery();
        System.out.println(query);


        URLConnection con = new URL(url + "?" + query).openConnection();
        con.setRequestProperty("Accept-Charset", charset);

        InputStream response = con.getInputStream();

        JSONObject respJ = new JSONPars().parsInputStream(response);

        System.out.println(respJ);

        return respJ;
    }


}
