package com.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockQueueDemo {

    public static void main(String[] args) {
        BlockingQueue<Integer> queue = new LinkedBlockingQueue(5);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
        queue.add(1);
    }
}
