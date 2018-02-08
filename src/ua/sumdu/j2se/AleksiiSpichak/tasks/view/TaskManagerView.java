package ua.sumdu.j2se.AleksiiSpichak.tasks.view;

import ua.sumdu.j2se.AleksiiSpichak.tasks.model.ArrayTaskList;
import ua.sumdu.j2se.AleksiiSpichak.tasks.model.Task;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;


public class TaskManagerView {
    static SimpleDateFormat dat = new SimpleDateFormat("dd.MM.yy HH:mm");

    public static void listTask(ArrayTaskList tasksNew) {
        if (tasksNew.size() < 0) {
            System.out.println("No Task in list");
        }
        else
            System.out.println("__List all active Task__ ");
            for (int i = 0; i < tasksNew.size(); ++i) {
                System.out.println(tasksNew.getTask(i) + " Number #" + i);
            }
    }
    public static void menu(ArrayTaskList tasksNew) {
        listTask(tasksNew);
        System.out.println("Select option number \n"
                + "1 - Create task \n"
                + "2 - edit task \n"
                + "3 - remove task \n"
                + "4 - see status \n"
                + "5 - see calendar \n"
                + "6 - exit \n");

    }

    public static void edit() {
        System.out.println("Select option edit \n" +
                " 1. Name task \n" +
                " 2. Start  \n" +
                " 3. Interval \n" +
                " 4. End \n" +
                " 5. Return");
    }

    public static String addNameTask() {
        return "name Task";
    }

    public static String addDateTask() {
        return "enter the date in the format dd.MM.yy HH:mm";
    }

    public static void addIntervalTask() {
        System.out.println("add time interval");
    }

    public static String addEndTask() {
        return "add end time";
    }

    public static void question() {
        System.out.println("press the number 9 if you want to enter an interval, to exit press any number");
    }

    public static void noTask() {
        System.out.println("no task in list");
    }

    public static void delite() {
        System.out.println("task delite");
    }

    public static void nomer() {
        System.out.println("enter number of task");
    }

    public static void exit() {
        System.out.println("to exit to the main menu, press Enter");
    }

    public static Date getDate(String message){
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        dat.setLenient(false);
        if (sc.hasNextLine())
            try {
                String temp = sc.nextLine();
                return dat.parse(temp);
            } catch (Exception e) {
                System.out.println("you did not enter a date!");
                return getDate(message);
            }return getDate(message);
    }

    public static String readName(String message){
        System.out.println(message);
        Scanner sc = new Scanner(System.in);

        if(sc.hasNextLine())
        {
            return sc.nextLine();
        }else
        {
            System.out.println("Wrong name, please  type correct name");
            return  readName(message);
        }
    }
        public static void statusTask(ArrayTaskList tasksNew) {
             if (tasksNew.size() >= 0) {
                 for (int g = 0; g < tasksNew.size(); g++) {
            System.out.println("Task in Number " + g + " " + tasksNew.getTask(g).toString());
            }System.out.println("End List ");
                 System.out.println("Return of main menu ");
            } else {
                 TaskManagerView.noTask();
             }
        }
    public static void calendar(ArrayTaskList tasksNew, String message) {
        ArrayTaskList newList = new ArrayTaskList();
        System.out.println(message);
        Scanner sc = new Scanner(System.in);
        String x = sc.nextLine();
        try {
            Date a = dat.parse(x);
            System.out.println("enter the end date in the format dd.MM.yy HH:mm");
            Scanner sq = new Scanner(System.in);
            String e = sq.nextLine();
            Date b = dat.parse(e);
            for (Task task : tasksNew) {
                if (task.getTime().compareTo(a) >= 0 && task.getTime().compareTo(b) <= 0) {
                    newList.add(task);
                }
                if (task.getStartTime().compareTo(a) <= 0 && task.getEndTime().compareTo(b) >= 0) {
                    newList.add(task);
                } else {
                    System.out.println("at the time you specified there is no assignment");
                }
            }
        }catch (Exception e) {
            System.out.println("you did not enter a date!");
            calendar(tasksNew, message);
        }
        for (Task task: newList)
            System.out.println("list in a given interval  " + task.toString());
    }
    public static int readInt(String number){

       Scanner scanner = new Scanner(System.in);
        System.out.println(number);
        if (scanner.hasNextInt()){

            return scanner.nextInt();
        }
        else {
            System.out.println("is a not number ");

            return readInt(number);
        }
    }



}

