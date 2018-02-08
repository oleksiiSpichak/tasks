package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

import java.io.*;
import java.util.*;
import java.text.*;

/**
 * class dlja peredachi i hranwnija spiskov zadach;
 */

public class TaskIO implements Serializable{

    private static SimpleDateFormat convert = new SimpleDateFormat( "yyyy-MM-dd;HH:mm:ss:SSS" );

    /**
     * method peredachi spiska zadach cherez OutputStream;
     */
    public static void write(TaskList tasks, OutputStream out) throws IOException {
        if (tasks.equals(null)){
            throw  new NullPointerException("aaaa");
        }
        DataOutputStream first = new DataOutputStream(out);
        try {
            int size = tasks.size();
            first.writeInt(size);
            for (Task task : tasks) {
                String tit = task.getTitle();
                long start = task.getStartTime().getTime();
                long end = task.getEndTime().getTime();
                int rep = task.getRepeatInterval();
                boolean act = task.isActive();
                first.writeUTF(tit);
                first.writeLong(start);
                first.writeLong(end);
                first.writeInt(rep);
                first.writeBoolean(act);
            }
        } finally {
            first.flush();
        }
    }

    /**
     * method zapisi spiska zadach v opredelennij file;
     */
    public static void writeBinary(TaskList tasks, File filename) throws IOException {
        FileOutputStream outfile = new FileOutputStream(filename);
        try {
            write(tasks, outfile);
        } finally {
            outfile.close();
        }
    }

    /**
     * method poluchenija spiska zadach iz potoka Inputstream;
     */
    public static void read(TaskList tasks, InputStream in) throws IOException {
        DataInputStream second = new DataInputStream(in);
        int nSize = second.readInt();
        try {
            for (int i = 0; i < nSize; i++) {
                String newTitle = second.readUTF();
                Date nStart = new Date(second.readLong());
                Date nEnd = new Date(second.readLong());
                int nRep = second.readInt();
                boolean act = second.readBoolean();
                Task nTask = new Task(newTitle, nStart, nEnd, nRep);
                nTask.setActive(act);
                tasks.add(nTask);
            }
        } finally {
            second.close();
        }
    }

    /**
     * method poluchenija spiska zadach v opredelennij file;
     */
    public static void readBinary(TaskList tasks, File filename) throws IOException {
        FileInputStream inFile = new FileInputStream(filename);
        read(tasks, inFile);
    }

    /**
     * method peredachi spiska zadach cherez potok Writer;
     */
    public static void write(TaskList tasks, Writer out) throws IOException {
        BufferedWriter outw = new BufferedWriter(out);

        try {
            for (Task task : tasks) {
                boolean rep;
                if(task.isRepeated()){
                    rep = true;
                Date nStart = task.getStartTime();
                Date nEnd = task.getEndTime();
                Boolean act = task.isActive();

                outw.write(String.valueOf(rep));
                outw.write(" && ");
                outw.write(task.getTitle());
                outw.write(" && ");
                outw.write(convert.format(nStart));
                outw.write(" && ");
                outw.write(convert.format(nEnd));
                outw.write(" && ");
                outw.write(String.valueOf(task.getRepeatInterval()));
                outw.write(" && ");
                outw.write(String.valueOf(act));
                outw.write(" && ");
                outw.append("\n");
                }
                else {

                    Date nStart = task.getStartTime();
                    Boolean act = task.isActive();
                    outw.write(String.valueOf(false));
                    outw.write(" && ");
                    outw.write(task.getTitle());
                    outw.write(" && ");
                    outw.write(convert.format(nStart));
                    outw.write(String.valueOf(act));
                    outw.write(" && ");
                    outw.append("\n");
                }
            }
        } finally {
            outw.close();
        }
    }

    /**
     * method zapisi spiska zadach v opredelennij file;
     */
    public static void writeText(TaskList tasks, File filename) throws IOException {

        FileWriter inFile = new FileWriter(filename);

        try {
            write(tasks, inFile);
        } finally {
            inFile.close();
        }
    }


    /**
     * method chitivanija spiska zadach v opredelennij file;
     */
    public static void read(TaskList tasks, Reader in) throws IOException, ParseException {
        Scanner inps = new Scanner(in).useDelimiter("\\s* && \\s*");

        try {
            while (inps.hasNext()) {
                    String rep = inps.next();
                    Boolean repited = Boolean.valueOf(rep);
                    if(repited == true){
                    String newTitle = inps.next();
                    String start = inps.next();
                    Date nStart = convert.parse(start);
                    String end = inps.next();
                    Date nEnd = convert.parse(end);
                    String nRep = inps.next();
                    int newRep = Integer.parseInt(nRep);

                    Task nTask = new Task(newTitle, nStart, nEnd, newRep);
                    nTask.setActive(Boolean.parseBoolean(inps.next()));
                    tasks.add(nTask);
                    }
                    else {
                        String newTitle = inps.next();
                        String start = inps.next();
                        Date nStart = convert.parse(start);
                        Task nTask = new Task(newTitle,nStart);
                        tasks.add(nTask);
                    }
            }
        } finally {
            inps.close();
        }
    }

    /**
     * method schitivanija spiska zadach v opredelennij file;
     */
    public static void readText(TaskList tasks, File filename) throws IOException, ParseException {
        FileReader inFile = new FileReader(filename);
        try {
            read(tasks, inFile);
        } finally {
            inFile.close();
        }
    }
}