package jikeshijian.datastructureandalgorithm.day01;

/**
 * @program: interview
 * @description:基于数组实现的顺序栈
 * @author: jiangyun
 * @create: 2019-12-09 15:18
 **/
public class ArrayStack {

    private String[] items;
    private int size;
    private int n;

    public ArrayStack(int n) {
        this.n = n;
        this.items = new String[n];
        this.size = 0;

    }

    public boolean push(String item) {

        if (size == n)
            return false;


        items[size] = item;
        size++;
        return true;


    }


    public String pop() {

        if (size == 0) return null;

        String tmp = items[size - 1];
        size--;
        return tmp;


    }


}