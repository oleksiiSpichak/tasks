package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

import java.io.Serializable;
import java.util.Date;
import java.util.TimerTask;

/**
 * Created by Zver on 08.08.2017.
 */
public class Task extends TimerTask implements Cloneable, Serializable{

    String title;
    Date time;
    Date start;
    Date end;
    int interval;
    boolean active;


    public Task(String title,Date time){
        this.title = title;
        this.time = time;

    }

    public Task(String title,Date start,Date end,int interval){
        this.title = title;
        this.start = start;
        this.end = end;
        this.interval = interval;

    }

    public String getTitle() {return title;}

    public void setTitle(String title){
        if (title.equals(null))
            throw new IllegalArgumentException("Wrong title!!!! ");
        this.title = title;
    }

    public boolean isActive() {return active;}

    public void setActive(boolean active){
        this.active = active;
    }

    public Date getTime(){
        if (isRepeated())
            return start;
        else
            return time;
    }

    public void setTime(Date time){
        if (time == null){
            throw new NumberFormatException("time must be positive ");
        }else
        {
            if (isRepeated()){
                interval = getRepeatInterval();
                end = time;
                start = time;

            }
            this.time = time;
        }
    }

    public Date getStartTime(){
        if (isRepeated()){
            return start;
        }
        else {
            return time;
        }
    }

    public Date getEndTime(){
        if (isRepeated()){
            return end;
        }
        else{
            return time;
        }
    }
    public int setInterval(int i){
       return interval;
    }

    public int getRepeatInterval(){
        if (isRepeated()){
            return interval;
        }
        else {
            return 0;
        }
    }

    public void setTime(Date start,Date end, int interval){
        if(start == null) {
            throw new ArithmeticException("start must be positive ");
        }

        if(end.compareTo(start)<=0){
            //throw new ArithmeticException("end must be later that start");
        }

        if (interval <=0){
            //throw new NumberFormatException("interval must be more than 0");
        }
        if (!isRepeated()){
            time=start;
        }
        this.start=start;
        this.end = end;
        this.interval=interval;
    }


    public boolean isRepeated(){
        if (interval == 0)
            return false;
        else
            return true;
    }

    public Date nextTimeAfter(Date current){
        Date temp = start;
        if(!isActive()){
            return null;
        }
        else {
            if (!isRepeated()){
                if (time.compareTo(current) >0){
                    return time;
                }
                else {
                    return null;
                }
            }
            else {
                if (current.compareTo(end) >= 0){
                    return null;
                }
                if (current.compareTo(temp) <0){
                    return temp;
                }
                else {
                    do {
                        temp =  new Date( temp.getTime() + getRepeatInterval() * 1000);
                    }
                    while (current.compareTo(temp) >=0 && current.compareTo(end) <0);
                    if(temp.compareTo(end) <=0){
                        return temp;
                    }
                    else{
                        return null;
                    }
                }
            }
        }
    }

    @Override
    public boolean equals (Object obj){
        if ((obj == null ) || !obj.getClass().equals(getClass())){
            return false;
        }
        Task task = (Task) obj;
        if(task.isActive()) {
            return (task.getTitle().equals(getTitle())) &&
                    (task.getStartTime().equals(getStartTime())) &&
                    (task.getEndTime().equals(getEndTime())) &&
                    (task.getRepeatInterval() == getRepeatInterval()) &&
                    (task.isActive() == isActive());
        }
                else {
                return (task.getTitle().equals(getTitle())) &&
                        (task.getStartTime().equals(getTime()));
            }
    }

    @Override
    public int hashCode() {
        int hash = getTitle().hashCode() + 2* getStartTime().hashCode() + 7 * getEndTime().hashCode() + 24 * getRepeatInterval();
        return hash;
    }

    @Override
    public String toString() {
        if(isRepeated()){
            return " Task = " + getTitle() + "TaskManagerController time = " + getStartTime() + "End Time = " + getEndTime() + "Interval = " + getRepeatInterval();
        }
        else
            return  " Task = " + getTitle() + " End Time = " + getEndTime();
    }
    /**
     * method clonirovanija  zadachi
     */
    @Override
    protected Task clone() throws CloneNotSupportedException {
        try {
            return (Task)super.clone();
        }
        catch (CloneNotSupportedException e){
            System.out.println(" not supported exception ");
        }
        return null;
    }

    @Override
    public void run() {
       System.out.println( "Task " + this.getTitle() +" is started ");
    }
}
