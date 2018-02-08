package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

/**
 * Created by Zver on 21.11.2017.
 */
public abstract class TaskList implements Iterable<Task>, Cloneable{


    abstract void add(Task task);

    abstract boolean remove(Task task);

    abstract int size();

    abstract Task getTask(int index);

    public abstract int hashCode();

    public abstract boolean equals(Object obj);

    public String toString() {
        String a = "TaskListType  [ ";
        for (int i = 1; i<size()+1; i ++){
            a = a + "Task-" + i + "; ";
        }
        a = a + "]";
        return a;
    }
    @Override
    public TaskList clone()throws CloneNotSupportedException{
        try {
            TaskList copy =(TaskList)super.clone();
            return copy;
        }
        catch (CloneNotSupportedException e){
            throw new AssertionError("");
        }
    }

}


