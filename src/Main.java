import concurrency.ConcurrencyQueue;


public class Main {
    public static void main(String[] args) {
        ConcurrencyQueue<Integer> concurrencyQueue = new ConcurrencyQueue<>(3);

        Runnable producer = () -> {
            try {
                for (int i = 0; i < 100; i++) {
                    concurrencyQueue.enqueue(i);
                    System.out.println("Поставлен " + i);
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };


        Runnable consumer = () -> {
            try {
                for (int i = 0; i < 100; i++) {
                    int item = concurrencyQueue.dequeue();
                    System.out.println("Использован " + item);
                    Thread.sleep(200);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };

        Thread producerThread = new Thread(producer);
        Thread consumerThread = new Thread(consumer);

        producerThread.start();
        consumerThread.start();
    }
}