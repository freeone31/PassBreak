import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PassBreakRunner {
    public static volatile boolean locked = true;
    public static String command = "";

    public static void main(String[] args) {
        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss ");

        if (args.length == 0) {
            System.out.println(df.format(new Date()) + "Pass archive name as input parameter");
            return;
        }

        command = "/c 7z e " + args[0] + ".7z -o" + args[0] + " \"-p";

        System.out.println(df.format(new Date()) + "Started");

        Thread thread00 = new Thread(new Breaker("thread_00 "));
        Thread thread01 = new Thread(new Breaker("thread_01 "));
        Thread thread02 = new Thread(new Breaker("thread_02 "));
        Thread thread03 = new Thread(new Breaker("thread_03 "));
        Thread thread04 = new Thread(new Breaker("thread_04 "));
        Thread thread05 = new Thread(new Breaker("thread_05 "));
        Thread thread06 = new Thread(new Breaker("thread_06 "));
        Thread thread07 = new Thread(new Breaker("thread_07 "));
        Thread thread08 = new Thread(new Breaker("thread_08 "));
        Thread thread09 = new Thread(new Breaker("thread_09 "));
        Thread thread10 = new Thread(new Breaker("thread_10 "));
        Thread thread11 = new Thread(new Breaker("thread_11 "));
        Thread thread12 = new Thread(new Breaker("thread_12 "));
        Thread thread13 = new Thread(new Breaker("thread_13 "));
        Thread thread14 = new Thread(new Breaker("thread_14 "));
        Thread thread15 = new Thread(new Breaker("thread_15 "));
        Thread thread16 = new Thread(new Breaker("thread_16 "));
        Thread thread17 = new Thread(new Breaker("thread_17 "));
        Thread thread18 = new Thread(new Breaker("thread_18 "));
        Thread thread19 = new Thread(new Breaker("thread_19 "));

        thread00.start();
        thread01.start();
        thread02.start();
        thread03.start();
        thread04.start();
        thread05.start();
        thread06.start();
        thread07.start();
        thread08.start();
        thread09.start();
        thread10.start();
        thread11.start();
        thread12.start();
        thread13.start();
        thread14.start();
        thread15.start();
        thread16.start();
        thread17.start();
        thread18.start();
        thread19.start();

        System.out.println(df.format(new Date()) + "Done");
    }
}
