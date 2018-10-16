import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Breaker implements Runnable {
    private final String name;
    private final String command;
    private DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");

    public Breaker(String name) {
        this.name = name;
        this.command = PassBreakRunner.command;
    }

    @Override
    public void run() {
        PassGen passGen = PassGen.getInstance();

        while (PassBreakRunner.locked) {
            String pass = passGen.getNextPass();

            ProcessBuilder builder = new ProcessBuilder("cmd.exe", command + pass + "\"");
            builder.redirectErrorStream(true);

            try (BufferedReader r = new BufferedReader(new InputStreamReader(builder.start().getInputStream()))) {
                StringBuilder sb = new StringBuilder();

                while (true) {
                    String line = r.readLine();

                    if (line == null) {
                        break;
                    }

                    sb.append(line);
                }

                String output = sb.toString();

                if (output.contains("Wrong password")) {
                    System.out.println(df.format(new Date()) + name + "Wrong password " + pass);
                }
                else if (output.contains("Everything is Ok")) {
                    PassBreakRunner.locked = false;
                    System.out.println(df.format(new Date()) + name + "Correct password is " + pass);
                }
                else {
                    System.out.println(df.format(new Date()) + name + "Password " + pass + " produced error:");
                    System.out.println(df.format(new Date()) + name + output);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
