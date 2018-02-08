package ua.sumdu.j2se.AleksiiSpichak.tasks.model;

import java.util.Iterator;
import java.util.NoSuchElementException;


/**
 * Created by Zver on 21.11.2017.
 */
public class LinkedTaskList extends TaskList  {
    private Node head =  null;
    private int listsize = 0;


    @Override
    public void add(Task task) {
        Node list = new Node(task);
        if (task == null)
            throw new NullPointerException("ne dolgen bit null");
        if (head == null) {
            head = list;

        } else {
            Node add = this.head;
            while (add.getNext() != null)
                add = add.getNext();
            add.setNext(list);
        }
        listsize++;
    }

    @Override
    public boolean remove(Task task) {
        if (task == null || head == null )
            throw new NullPointerException("ne dolgen bit null");
        if (head.getTask().equals(task) ){
            head = head.getNext();
            listsize--;
            return true;
        } else {
            Node rem = this.head;
            while (rem.getNext() != null &&  !rem.getNext().getTask().equals(task)) {
                rem = rem.getNext();
            }
            if (rem.getNext() != null) {
                rem.setNext(rem.getNext().getNext());
                if (listsize != 0)
                    listsize--;
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return listsize;
    }

    @Override
    public  Task getTask(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("ne dolgen bit null");
        }
        Node count = head;
        if (count != null) {
            for (int i = 0; i < index; i++) {
                count = count.getNext();
            }
        } else {
            System.out.println("count = null");
        }
        return count.getTask();
    }


    public int hashCode() {
        int hashList = 0;
        //Node temp = head;
        for(Task task: this)
            hashList ^=task.hashCode();
        return hashList;
    }


    public boolean equals(Object obj) {
        if ((obj == null) || !obj.getClass().equals(getClass())) {
            return false;
        }
        LinkedTaskList tempList = (LinkedTaskList) obj; // privedeie tipov
        if (tempList.size() == size() ){
            Iterator firstIterator = tempList.iterator();
            Iterator sekIterator = iterator();
            while (firstIterator.hasNext()){
                if (!sekIterator.next().equals(firstIterator.next()))
                    return false;
            }
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Iterator<Task> iterator() {
        return new ListIterator(this); // this - spisok zada4
    }

    private class ListIterator implements Iterator<Task> {
        LinkedTaskList listTasok;
        private Node first = null;
        private Node next = null;
        private boolean delite = false;

        ListIterator(LinkedTaskList list){
            listTasok = list;
            next = listTasok.head;
        }

        @Override
        public boolean hasNext() {
            return next != null;
        }

        @Override
        public Task next() {
            if(!hasNext())
                throw new NoSuchElementException("ups");
            Task temp = next.getTask();
            first = next;
            next = next.getNext();
            return temp;
        }

        @Override
        public void remove() {
            if(first == null)
                throw new IllegalStateException("nelza udaliat");
            listTasok.remove(first.getTask());
            delite = true;
        }
    }
    @Override
    public LinkedTaskList clone()throws CloneNotSupportedException{
        LinkedTaskList retList = new LinkedTaskList ();
        for(Task task: this)
            retList.add(task.clone());
        return retList;
    }
}
