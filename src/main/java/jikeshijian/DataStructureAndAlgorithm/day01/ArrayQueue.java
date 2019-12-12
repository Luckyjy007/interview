package jikeshijian.DataStructureAndAlgorithm.day01;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-09 18:55
 **/
public class ArrayQueue {

    public ArrayQueue(int n) {
        this.n = n;
        this.items = new String[n];
    }

    private String[] items;
    private int n = 0;
    private int tail = 0;
    private int head = 0;


    public boolean enqueue(String item) {

        if (tail == n) return false;
        items[tail] = item;
        tail++;
        return true;
    }

    public String dequeue(){

        if (head==tail) return null;

        String tmp = items[head];
        head++;
        return tmp;

    }

}