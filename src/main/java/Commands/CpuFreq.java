package Commands;

import NetData.NetDataGetter;
import NetData.NetDataParams;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.StringJoiner;


public class CpuFreq extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if(event.getAuthor().getId() != "102050136762372096"){
            event.getChannel().sendMessage("Permission denied u hackerman");
        }
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!cpufreq")){
            NetDataGetter netDataGetter = new NetDataGetter();
            NetDataParams params = new NetDataParams("cpu.cpufreq");
            params.setPoints("1");
            JSONObject data = new JSONObject();
            try {
                data = netDataGetter.getInfo(params);
            } catch (IOException e) {
                event.getChannel().sendMessage("Can't get info from Server");
            }

            System.out.println(data.toString());


            HashMap<String, Integer> dataMap = new HashMap<>();
            ArrayList<String> keys = new ArrayList<>();
            Integer counter = 0;
            for (Object lol: data.getJSONArray("labels")) {
                if(lol instanceof String){
                    if(((String) lol).contains("time")){
                        counter++;
                        continue;
                    }
                    Double freq = data.getJSONArray("data").getJSONArray(0).getDouble(counter);
                    dataMap.put((String) lol, freq.intValue());
                    keys.add((String) lol);
                }
                counter++;
            }

            StringJoiner response = new StringJoiner("\n");

            response.add("Frequency  of Labeouf CPU is:");

            for (String key: keys) {
                response.add(key + " : " + dataMap.get(key) + " Mhz");
            }

            System.out.println(dataMap.toString());

            event.getChannel().sendMessage(response.toString()).queue();




        }
    }
}
