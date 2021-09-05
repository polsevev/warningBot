package Poller;

import NetData.NetDataGetter;
import NetData.NetDataParams;
import net.dv8tion.jda.api.JDA;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class TempPoller implements Runnable{

    JDA jda;

    public TempPoller(JDA jda){
        this.jda = jda;
    }
    @Override
    public void run() {
        NetDataGetter netDataGetter = new NetDataGetter();
        NetDataParams params = new NetDataParams("sensors.coretemp_isa_0000_temperature");
        params.setPoints("50");

        JSONObject data = new JSONObject();
        try{
            data = netDataGetter.getInfo(params);
        }catch(IOException e){
            System.out.println("Failed to get data with poller");
        }
        System.out.println(data.toString());
        boolean stopLooping = false;
        for (Object dat: data.getJSONArray("data")) {
            for (Object l: (JSONArray) dat) {
                if(l instanceof Double){
                    if((Double) l >= 65){
                        jda.retrieveUserById("102050136762372096").complete().openPrivateChannel().queue(channel -> {
                            channel.sendMessage("Warning, CPU temp over threshold").queue();
                        });
                        stopLooping = true;
                    }
                }
                if(stopLooping)
                    break;
            }
            if(stopLooping)
                break;
        }

    }
}
