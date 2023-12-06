
/*
 * @author: Vo Huu Tuan
 * @since:  06/12/2023 8:20 SA
 * @github:  https://github.com/hidenobi
 * @update:
 *
 * */


import java.time.Instant;
import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.newVirtualThreadPerTaskExecutor;

public class Main {
    public static void main(String[] args) throws Exception {
        demoWithThreadAndRunnable("https://en.wikipedia.org/wiki/Travelling_salesman_problem");
        demoWithVirtualThread("https://en.wikipedia.org/wiki/Travelling_salesman_problem");
    }

    static void demoWithThreadAndRunnable(String rootUrl) throws InterruptedException {
        long startTime = System.nanoTime();
        WebCrawler webCrawler = new WebCrawler(rootUrl);
        Thread newThread = new Thread(webCrawler);
        newThread.start();
        newThread.join();
        long endTime = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println("Visited " + webCrawler.getNumberOfUrl() + " Urls  in " + totalTime / 1000000 + " ms");
    }

    static void demoWithVirtualThread(String rootUrl) {
        Instant start = Instant.now();
        WebCrawlerNonThreaded crawler = new WebCrawlerNonThreaded(start);
        ExecutorService executor = newVirtualThreadPerTaskExecutor();
        executor.submit(() -> {
            System.out.println("isRunning");
            long startTime = System.nanoTime();
            crawler.crawl(rootUrl);
            long endTime = System.nanoTime();
            long totalTime = endTime - startTime;
            System.out.println("Visited " + crawler.getNumberOfUrl() + " Urls  in " + totalTime / 1000000 + " ms");
        });
        executor.shutdown();
    }
}
