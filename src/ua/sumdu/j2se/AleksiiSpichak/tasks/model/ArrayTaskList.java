package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Zver on 09.08.2017.
 */
public class ArrayTaskList extends TaskList {


    Task[] tasks = new Task[0];

    public ArrayTaskList() {

    }

    public ArrayTaskList(Task[] incoming) {
        tasks = incoming;
    }

    @Override
    public void add(Task task) {
        int count = size() +1;
        Task[] newTasks = new Task[count];
        for (int i = 0; i < size(); ++i) {
            newTasks[i] = tasks[i];

        }

        newTasks[size()] = task;

        tasks = newTasks;
    }

    @Override
    public boolean remove(Task task) {
        int count = size() - 1;
        boolean exists = false;
        boolean removed = false;
        for (int i = 0; i < size(); i++) {
            if (tasks[i].equals(task)) {
                exists = true;
            }
        }
        if (size() != 0 & exists) {
            Task[] task1 = new Task[count];
            int c = 0;
            for (int i = 0; i < size(); i++) {
                if (!tasks[i].equals(task) || (tasks[i].equals(task) && removed)) {
                    task1[c] = tasks[i];
                    c++;
                } else {
                    removed = true;
                }
            }
            tasks = task1;
            return true;
        } else
            return false;
    }

    @Override
    public int size() {
        return tasks.length;
    }

    @Override
    public Task getTask(int index) {

        return tasks[index];
    }


    public int hashCode() {
        int arrayHash = 0;
        for (Task task : this) {
            arrayHash ^= task.hashCode();
        }
        return arrayHash;
    }


    public boolean equals(Object obj) {
        if ((obj == null) || !obj.getClass().equals(getClass())) {
            return false;

        }
        ArrayTaskList arr = (ArrayTaskList) obj;
        if (arr==(obj)){
            Iterator first = this.iterator();
            Iterator second = arr.iterator();
            while(first.hasNext()){
                if (second.next().equals(first.next()));
                return true;
            }
            return true;
        }
        else
            return false;
    }

    @Override
    public Iterator<Task> iterator(){
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<Task>{

        private int count = 0;
        private boolean removed = false;

        /**
         * Returns true if the iteration has more elements;
         */
        @Override
        public boolean hasNext(){
            return (count < size());
        }

        /**
         * Returns the next element in the iteration;
         */
        @Override
        public Task next(){
            if (!hasNext())
                throw new NoSuchElementException();

            removed = true;
            return tasks[count++];
        }

        /**
         * Removes from the underlying collection the last element returned by the iterator
         */
        @Override
        public void remove(){
            if (removed)
                throw new IllegalStateException();
            else
                ArrayTaskList.this.remove(tasks[--count]);
            removed = false;

        }
    }
    @Override
    public ArrayTaskList clone() throws CloneNotSupportedException {
        ArrayTaskList copy =(ArrayTaskList)super.clone();
        int i = 0;
        for (Task task: this){
            copy.tasks[i]= task.clone();
            i++;
        }
        return copy;
    }
}
