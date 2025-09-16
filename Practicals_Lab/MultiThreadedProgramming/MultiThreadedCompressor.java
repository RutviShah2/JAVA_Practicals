package MultithreadedProgramming;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;
import java.util.zip.Deflater;


public class MultiThreadedCompressor {
    static class CompressionResult {
        final int chunkIndex;
        final int originalSize;
        final int compressedSize;
        final long timeNs;

        CompressionResult(int chunkIndex, int originalSize, int compressedSize, long timeNs) {
            this.chunkIndex = chunkIndex;
            this.originalSize = originalSize;
            this.compressedSize = compressedSize;
            this.timeNs = timeNs;
        }
    }

    static class CompressionTask implements Callable<CompressionResult> {
        private final int index;
        private final String block;

        CompressionTask(int index, String block) {
            this.index = index;
            this.block = block;
        }

        @Override
        public CompressionResult call() {
            byte[] input = block.getBytes(StandardCharsets.UTF_8);
            long start = System.nanoTime();
            byte[] compressed = compress(input);
            long end = System.nanoTime();
            return new CompressionResult(index, input.length, compressed.length, end - start);
        }

        private byte[] compress(byte[] data) {
            Deflater deflater = new Deflater(Deflater.BEST_SPEED);
            deflater.setInput(data);
            deflater.finish();
            try (ByteArrayOutputStream baos = new ByteArrayOutputStream(data.length)) {
                byte[] buffer = new byte[1024];
                while (!deflater.finished()) {
                    int count = deflater.deflate(buffer);
                    baos.write(buffer, 0, count);
                }
                deflater.end();
                return baos.toByteArray();
            } catch (Exception e) {
                deflater.end();
                throw new RuntimeException("Compression failed", e);
            }
        }
    }

    private static List<String> generateBlocks(int blocks, int blockSizeBytes) {
        List<String> list = new ArrayList<>(blocks);
        String base = "The quick brown fox jumps over the lazy dog. ";
        StringBuilder builder = new StringBuilder();
        while (builder.length() < blockSizeBytes) builder.append(base);
        String repetitive = builder.toString().substring(0, blockSizeBytes);

        Random rnd = new Random(12345);
        for (int i = 0; i < blocks; i++) {
            StringBuilder sb = new StringBuilder(blockSizeBytes);
            if (i % 3 == 0) {
                sb.append(repetitive);
            } else if (i % 3 == 1) {
                sb.append(repetitive.substring(0, blockSizeBytes - 100));
                for (int k = 0; k < 100; k++) sb.append((char) ('a' + rnd.nextInt(26)));
            } else {
                for (int k = 0; k < blockSizeBytes; k++) sb.append((char) ('a' + rnd.nextInt(26)));
            }
            list.add(sb.toString());
        }
        return list;
    }

    private static List<CompressionResult> singleThreadCompress(List<String> blocks) {
        List<CompressionResult> results = new ArrayList<>();
        long t0 = System.nanoTime();
        for (int i = 0; i < blocks.size(); i++) {
            CompressionTask task = new CompressionTask(i, blocks.get(i));
            try {
                CompressionResult r = task.call(); 
                results.add(r);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        long t1 = System.nanoTime();
        long totalNs = t1 - t0;
        System.out.printf("Single-threaded total time: %.3f sec%n", totalNs / 1e9);
        return results;
    }

    private static List<CompressionResult> multiThreadCompress(List<String> blocks, int poolSize) throws InterruptedException, ExecutionException {
        ExecutorService executor = Executors.newFixedThreadPool(poolSize);
        try {
            List<Future<CompressionResult>> futures = new ArrayList<>();
            long t0 = System.nanoTime();
            for (int i = 0; i < blocks.size(); i++) {
                futures.add(executor.submit(new CompressionTask(i, blocks.get(i))));
            }
            
            List<CompressionResult> results = new ArrayList<>(blocks.size());
            for (Future<CompressionResult> f : futures) {
                results.add(f.get()); 
            }
            long t1 = System.nanoTime();
            long totalNs = t1 - t0;
            System.out.printf("Multi-threaded (pool=%d) total time: %.3f sec%n", poolSize, totalNs / 1e9);
            return results;
        } finally {
            executor.shutdownNow();
        }
    }

    private static void printSummary(List<CompressionResult> seq, List<CompressionResult> par) {
        int totalOrigSeq = seq.stream().mapToInt(r -> r.originalSize).sum();
        int totalCompSeq = seq.stream().mapToInt(r -> r.compressedSize).sum();

        int totalOrigPar = par.stream().mapToInt(r -> r.originalSize).sum();
        int totalCompPar = par.stream().mapToInt(r -> r.compressedSize).sum();

        System.out.println("\n=== Summary ===");
        System.out.printf("Chunks: %d%n", seq.size());
        System.out.printf("Total original bytes (seq):   %d%n", totalOrigSeq);
        System.out.printf("Total compressed bytes (seq): %d%n", totalCompSeq);
        System.out.printf("Total original bytes (par):   %d%n", totalOrigPar);
        System.out.printf("Total compressed bytes (par): %d%n", totalCompPar);
        System.out.println("==============================");
    }

    public static void main(String[] args) {
        final int NUM_BLOCKS = 20;       
        final int BLOCK_SIZE = 60_000;   
        final int cores = Runtime.getRuntime().availableProcessors();
        final int poolSize = Math.max(1, cores);

        System.out.println("MultiThreadedCompressor simulation");
        System.out.printf("Blocks: %d, Block size: %d bytes, CPU cores: %d, poolSize: %d%n",
                NUM_BLOCKS, BLOCK_SIZE, cores, poolSize);

        List<String> blocks = generateBlocks(NUM_BLOCKS, BLOCK_SIZE);

        try {
            new CompressionTask(-1, blocks.get(0)).call();
        } catch (Exception ignored) {}

        long s0 = System.nanoTime();
        List<CompressionResult> singleResults = singleThreadCompress(blocks);
        long s1 = System.nanoTime();
        double seqTimeSec = (s1 - s0) / 1e9;

        List<CompressionResult> parallelResults = null;
        double parTimeSec = Double.NaN;
        try {
            long p0 = System.nanoTime();
            parallelResults = multiThreadCompress(blocks, poolSize);
            long p1 = System.nanoTime();
            parTimeSec = (p1 - p0) / 1e9;
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("\n=== Timing ===");
        System.out.printf("Sequential time: %.3f sec%n", seqTimeSec);
        System.out.printf("Parallel time:   %.3f sec%n", parTimeSec);
        if (!Double.isNaN(parTimeSec) && parTimeSec > 0) {
            System.out.printf("Speedup: %.2fx%n", seqTimeSec / parTimeSec);
        }
        printSummary(singleResults, parallelResults);

        System.out.println("\n24DCS120_RUTVI SHAH");
    }
}
