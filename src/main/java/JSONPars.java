import org.json.JSONObject;

import java.io.*;

public class JSONPars {

    public JSONObject parsInputStream(InputStream stream) throws IOException {
        BufferedReader streamreader = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
        StringBuilder builder = new StringBuilder();

        String inpStr;
        while((inpStr = streamreader.readLine()) != null)
            builder.append(inpStr);

        JSONObject resp = new JSONObject(builder.toString());

        return resp;
    }

}
