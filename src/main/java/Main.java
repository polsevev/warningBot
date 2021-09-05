import Commands.CpuFreq;
import Commands.CpuTemps;
import Poller.TempPoller;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {
        JDA jda = JDABuilder.createDefault("ODI1MDQ2MDc1MDgwNTcyOTg5.YF4OCA.DmOT0WAAohIbM5t9u_h56sinl8A").build();
        jda.awaitReady();
        jda.addEventListener(new CpuTemps());
        jda.addEventListener(new CpuFreq());
        TempPoller poller = new TempPoller(jda);
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.scheduleAtFixedRate(poller, 0, 15, TimeUnit.MINUTES);
    }

}
