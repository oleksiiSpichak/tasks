package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

/**
 * Created by Zver on 23.11.2017.
 */
public class Node {
    private Node next;
    private Task task;

    public Node(){

    }
    public Node (Task task){
        this.task = task;
        this.next = null;
    }

    public void setTask(Task task){
        this.task = task;
    }

    public Task getTask(){
        return task;
    }

    public void setNext(Node next){
        this.next = next;
    }

    public Node getNext(){
        return next;
    }
}

