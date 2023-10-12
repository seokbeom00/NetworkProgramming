package org.example;

import java.io.*;
import java.util.Date;
import java.util.logging.LoggingPermission;

public class LogFile {
    private Writer out;
    public LogFile(File f) throws IOException {
        FileWriter fw = new FileWriter(f);
        this.out = new BufferedWriter(fw);
    }

    public synchronized void writeEntry(String message) throws IOException {
        Date d = new Date();
        out.write(d.toString());
        out.write('\t');
        out.write(message);
        out.write("\r\n");
        out.flush();
    }

    public static void main(String[] args) throws IOException {
        File f = new File("/Users/seokbeom/backend-intellij/networdprogramming/untitled/src/main/java/org/example/bufferdstream/a.txt");
        LogFile lf = new LogFile(f);
        lf.writeEntry("ki ki ki");
    }
}
