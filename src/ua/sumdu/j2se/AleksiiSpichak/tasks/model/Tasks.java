package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

import java.util.*;


public class Tasks  {
     public static Iterable<Task> incoming(Iterable<Task> tasks, Date start, Date end) {
        ArrayTaskList incomList = new ArrayTaskList();

        for (Task t :tasks) {
            if (t.isActive() && t.nextTimeAfter(start) != null
                    && t.nextTimeAfter(start).compareTo(end) <= 0) {
                incomList.add(t);
            }
        }
        return incomList;

    }
    public static SortedMap <Date, Set<Task>> calendar (Iterable<Task> tasks, Date start, Date end){
        SortedMap<Date, Set<Task>> time = new TreeMap<Date, Set<Task>>() ;
        for(Task task: tasks){
            Date temp = task.nextTimeAfter(start);
            while ( temp !=null && temp.compareTo(end)<=0 ){
                if(time.containsKey(temp)){
                    time.get(temp).add(task);
                }
                else{
                    Set <Task> first = new HashSet<Task>();
                    first.add(task);
                    time.put(temp, first);
                }
                temp = task.nextTimeAfter(temp);
            }
        }
        return time;
    }
}
