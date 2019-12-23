package jikeshijian.datastructureandalgorithm.day02;

/**
 * @program: interview
 * @description:
 * @author: jiangyun
 * @create: 2019-12-10 16:13
 **/
public class ArrayQueue {

    private String[] items;
    private int n;
    private int head = 0;
    private int tail = 0;

    public ArrayQueue(int n) {
        this.n = n;
        items = new String[n];
    }


    public String dequeue() {

        if (tail == head) return null;
        String tmp = items[head];
        head++;
        return tmp;

    }

    private boolean enqueue(String item) {

        if (tail == n) {

            if (head == 0) return false;

            for (int i = head; i < tail; i++) {

                items[i - head] = items[i];

            }

            tail-=head;
            head=0;
            items[tail]=item;
            tail++;
        }
        return true;

    }
}