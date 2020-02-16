package com.example.pickup;

import java.util.concurrent.atomic.AtomicBoolean;

public class ClientRunnable implements Runnable {
    private Thread worker;
    private AtomicBoolean running = new AtomicBoolean(false);
    private int interval;
    public PickUpClient client;
    public Updateable updateable;

    public ClientRunnable(PickUpClient client, Updateable updateable) {
        this.client = client;
        interval = 1000;
        this.updateable = updateable;
    }

    public void start() {
        worker = new Thread(this);
        worker.start();
    }

    public void stop() {
        running.set(false);
    }

    public void interrupt() {
        running.set(false);
        worker.interrupt();
    }

    boolean isRunning() {
        return running.get();
    }

    boolean isStopped() {
        return !running.get();
    }

    @Override
    public void run() {
        running.set(true);
        while (running.get()) {
            try {
                Thread.sleep(interval);
            } catch (InterruptedException e){
                Thread.currentThread().interrupt();
                System.out.println(
                        "Thread was interrupted, Failed to complete operation");
            }
            try {
                client.update();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
            updateable.update();
        }
    }
}
