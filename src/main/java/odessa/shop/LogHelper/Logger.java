package odessa.shop.LogHelper;

import odessa.shop.UI.UserInterface;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Logger {
    private static Logger instance;

    private BufferedWriter writer = null;
    private File logFile = null;

    public static Logger get() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    public Logger() {
        try {
            String timeLog = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
            logFile = new File(timeLog + ".logg");
            System.out.println(logFile.getCanonicalPath());
            writer = new BufferedWriter(new FileWriter(logFile));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void setWriter(String text) {
        try {
            writer.write(text);
            writer.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeWriter() {
        try {
            UserInterface.labelLogFile = logFile.getCanonicalPath();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getLogPath(){
        try {
            return logFile.getCanonicalPath();
        } catch (IOException e) {
            return "";
        }
    }
}
