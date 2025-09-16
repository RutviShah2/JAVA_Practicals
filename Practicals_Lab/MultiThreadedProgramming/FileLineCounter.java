package MultithreadedProgramming;
import java.io.*;

class LineCountTask implements Runnable {
    private String fileName;

    public LineCountTask(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public void run() {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) {
                count++;
            }
            System.out.println(Thread.currentThread().getName() +
                               " -> File: " + fileName +
                               " | Lines: " + count);
        } catch (IOException e) {
            System.out.println("Error reading file: " + fileName);
        }
    }
}

public class FileLineCounter {
    public static void main(String[] args) {
        String file1 = "ThreadDemo.java";       
        String file2 = "BankSimulation.java";
        String file3 = "FileLineCounter.java";

        Thread t1 = new Thread(new LineCountTask(file1), "Thread-1");
        Thread t2 = new Thread(new LineCountTask(file2), "Thread-2");
        Thread t3 = new Thread(new LineCountTask(file3), "Thread-3");

        t1.start();
        t2.start();
        t3.start();
        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            System.out.println("Main thread interrupted.");
        }

        System.out.println("24DCS120_RUTVI SHAH");
    }
}
