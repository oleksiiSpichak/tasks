package ua.sumdu.j2se.AleksiiSpichak.tasks.Controller;

import ua.sumdu.j2se.AleksiiSpichak.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.AleksiiSpichak.tasks.model.Task;
import ua.sumdu.j2se.AleksiiSpichak.tasks.model.TaskIO;
import ua.sumdu.j2se.AleksiiSpichak.tasks.view.TaskManagerView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.Date;
import java.util.Timer;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * Created by o.spichak on 10.08.2017.
 */
public class TaskManagerController {
    static File file = new File("Kalendar.txt");

    static final String path = "resources/log4j.properties";
    private static final Logger logger = Logger.getLogger(TaskManagerController.class);

    static BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
    static ArrayTaskList tasksNew = new ArrayTaskList();

    public static void runNen() throws IOException, ParseException, ArrayIndexOutOfBoundsException
    {
        PropertyConfigurator.configure(path);

        TaskManagerView viewer = new TaskManagerView();
        if (file.length() > 0) {
            TaskIO.readText(tasksNew, file);
          // runSignal();
        } else {
            file.createNewFile();
        }

        while (true) {
            logger.debug(" we are here!!");
            viewer.menu(tasksNew);
            int input = viewer.readInt("enter number");
            switch (input) {
                case 1:
                    String name = viewer.readName(viewer.addNameTask());
                    Date date = viewer.getDate(viewer.addDateTask());
                    viewer.question();
                    int inp = viewer.readInt(" ");
                        if (inp == 9) {
                            viewer.addIntervalTask();
                            int inter = viewer.readInt("");
                            Date endDate = viewer.getDate(viewer.addEndTask());

                            Task task = new Task(name, date, endDate, inter);

                            tasksNew.add(task);
                        } else {
                            Task task = new Task(name, date);
                            tasksNew.add(task);
                        }
                    break;
                case 2:
                    if (tasksNew.size() >0) {
                        viewer.listTask(tasksNew);
                        viewer.nomer();
                        int i = viewer.readInt("");
                        if(tasksNew.size() > i ){
                            Task task3 = tasksNew.getTask(i);
                            viewer.edit();
                            int input1 = viewer.readInt("");
                            switch (input1) {
                                case 1:
                                    String name1 = viewer.readName(viewer.addNameTask());
                                    task3.setTitle(name1);
                                    break;
                                case 2:
                                    Date c = viewer.getDate(viewer.addDateTask());
                                    task3.setTime(c);
                                    break;
                                case 3:
                                    int u = viewer.readInt("");
                                    task3.setInterval(u);
                                    break;
                                case 4:
                                    Date d = viewer.getDate(viewer.addEndTask());
                                    task3.setTime(d);
                                    break;
                                case 5:
                                    viewer.exit();
                                    in.readLine();
                                    break;
                            }
                        } else {
                            viewer.noTask();
                        }
                    }
                    break;

                // metod udaleniya zadaci
                case 3:
                    boolean l = false;
                    if (tasksNew.size() != 0) {
                        viewer.listTask(tasksNew);
                        int s = viewer.readInt("");
                        for (int i = 0; i < tasksNew.size(); ++i) {
                            if (i == s)
                                l = true;
                            tasksNew.remove(tasksNew.getTask(i));
                        }
                    }
                    if (l) {
                        viewer.delite();
                    } else {
                        viewer.noTask();
                    }
                    break;

                /* sostoyanie zadachi*/
                case 4:
                    viewer.statusTask(tasksNew);
                    break;
                //listName zadach
                case 5:
                    viewer.calendar(tasksNew, viewer.addDateTask());
                    break;
                case 6:
                    TaskIO.writeText(tasksNew, file);
                    System.exit(0);
                    break;
            }
        }
    }


    public static void  runSignal() throws IOException, ParseException {
        Timer time = new Timer();
        TaskIO.readText(tasksNew, file);
        for(Task task: tasksNew){
            if(task.isRepeated()){
                time.schedule(task, task.getStartTime(), task.getRepeatInterval()*1000);
            }
            else {
                time.schedule(task, task.getTime());
            }
        }
    }

}