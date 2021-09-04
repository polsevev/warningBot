import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.*;
import org.json.JSONObject;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        JDA jda = JDABuilder.createDefault("ODI1MDQ2MDc1MDgwNTcyOTg5.YF4OCA.esBROVI49As0dpzvyx8CQ6eGdRM").build();
        jda.awaitReady();
        jda.addEventListener(new CpuTemps());
        User user =  jda.retrieveUserById("102050136762372096").complete();
        //sendMessage(user);

        Guild guild = jda.getGuildById("825046718046928936");


        NetDataGetter netDataGetter = new NetDataGetter();

        NetDataParams params = new NetDataParams("system.cpu");
        params.setPoints("1");

        JSONObject cpuInfo = netDataGetter.getInfo(params);

    }
}
