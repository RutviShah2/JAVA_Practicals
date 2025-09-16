package MultithreadedProgramming;
import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

class Coder implements Runnable {
    private String name;
    private Leaderboard leaderboard;
    private Random random = new Random();

    public Coder(String name, Leaderboard leaderboard) {
        this.name = name;
        this.leaderboard = leaderboard;
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < 5; i++) {
                int score = random.nextInt(1000); 
                leaderboard.addScore(new ScoreEntry(name, score));
                Thread.sleep(300);
            }
        } catch (InterruptedException e) {
            System.out.println(name + " interrupted.");
        }
    }
}

class ScoreEntry implements Comparable<ScoreEntry> {
    String coder;
    int score;

    public ScoreEntry(String coder, int score) {
        this.coder = coder;
        this.score = score;
    }

    @Override
    public int compareTo(ScoreEntry other) {
        return Integer.compare(this.score, other.score);
    }

    @Override
    public String toString() {
        return coder + " : " + score;
    }
}

class Leaderboard {
    private final PriorityBlockingQueue<ScoreEntry> topScores =
            new PriorityBlockingQueue<>(10);

    // Only keeps top-10 scores
    public synchronized void addScore(ScoreEntry entry) {
        if (topScores.size() < 10) {
            topScores.offer(entry);
        } else {
            ScoreEntry lowest = topScores.peek();
            if (lowest != null && entry.score > lowest.score) {
                topScores.poll();
                topScores.offer(entry);
            }
        }
    }

    public void printFinalLeaderboard() {
        System.out.println("\n=== Final Top 10 Leaderboard ===");
        topScores.stream()
                 .sorted((a, b) -> Integer.compare(b.score, a.score)) 
                 .forEach(System.out::println);
        System.out.println("================================");
    }
}

public class CodingLeaderboardSimulation {
    public static void main(String[] args) {
        Leaderboard leaderboard = new Leaderboard();

        Thread c1 = new Thread(new Coder("Rutvi", leaderboard));
        Thread c2 = new Thread(new Coder("Poojan", leaderboard));
        Thread c3 = new Thread(new Coder("Pooja", leaderboard));
        Thread c4 = new Thread(new Coder("CoderX", leaderboard));

        c1.start();
        c2.start();
        c3.start();
        c4.start();

        try {
            c1.join();
            c2.join();
            c3.join();
            c4.join();
        } catch (InterruptedException e) {
            System.out.println("Main interrupted.");
        }
        leaderboard.printFinalLeaderboard();
        System.out.println("24DCS120_RUTVI SHAH");
    }
}
