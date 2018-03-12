package fyinews.constants;

/**
 * Created by dakshkapur on 2018-02-10.
 */

public class Constants {
    private static final String API_KEY = "4ca1f8efee8a4452a791df27f99163f0";
    private static final String API_SOURCES_URL = "https://newsapi.org/v2/sources?language=en&apiKey="+ API_KEY;
    private static final String API_TOP_HEADLINES_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&apiKey=" + API_KEY;

    public static String getApiSourcesUrl() {
        return API_SOURCES_URL;
    }

    public static String getApiTopHeadlinesCaUrl() {
        return API_TOP_HEADLINES_CA_URL;
    }
}
