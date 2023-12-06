
/*
 * @author: Vo Huu Tuan
 * @since:  06/12/2023 8:07 SA
 * @github:  https://github.com/hidenobi
 * @update:
 *
 * */


import java.time.Instant;

public class WebCrawler implements Runnable {

    private String rootUrl;
    private WebCrawlerNonThreaded crawlerNonThreaded;

    public WebCrawler(String rootUrl) {
        Instant instant = Instant.now();
        this.crawlerNonThreaded = new WebCrawlerNonThreaded(instant);
        this.rootUrl = rootUrl;
    }

    @Override
    public void run() {
        crawlerNonThreaded.crawl(rootUrl);
    }

    public int getNumberOfUrl() {
        return crawlerNonThreaded.getNumberOfUrl();
    }
}
