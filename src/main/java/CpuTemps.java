import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringJoiner;

public class CpuTemps extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {
        if(event.getMessage().getContentRaw().equalsIgnoreCase("!cpuTemp")){
            NetDataGetter netDataGetter = new NetDataGetter();
            NetDataParams params = new NetDataParams("sensors.coretemp_isa_0000_temperature");
            params.setPoints("1");
            JSONObject data = new JSONObject();
            try {
                data = netDataGetter.getInfo(params);
            } catch (IOException e) {
                event.getChannel().sendMessage("Can't get info from Server");
            }
            HashMap<String, Integer> dataMap = new HashMap<>();
            ArrayList<String> keys = new ArrayList<>();
            Integer counter = 0;
            for (Object lol: data.getJSONArray("labels")) {
                if(lol instanceof String){
                    Double temp = data.getJSONArray("data").getJSONArray(0).getDouble(counter);
                    dataMap.put((String) lol, temp.intValue());
                    keys.add((String) lol);
                }
                counter++;
            }

            StringJoiner response = new StringJoiner("\n");

            response.add("Temperature of Labeouf CPU is:");

            for (String key: keys) {
                if(key == "time"){
                    continue;
                }
                response.add(key + " : " + dataMap.get(key) + " Celsius");
            }

            System.out.println(dataMap.toString());

            event.getChannel().sendMessage(response.toString()).queue();
        }
    }
}
