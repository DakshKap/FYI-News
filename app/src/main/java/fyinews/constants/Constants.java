package fyinews.constants;

/**
 * Created by dakshkapur on 2018-02-10.
 */

public class Constants {
    private static final String API_KEY = "4ca1f8efee8a4452a791df27f99163f0";
    private static final String API_SOURCES_URL = "https://newsapi.org/v2/sources?language=en&apiKey="+ API_KEY;
    //private static final String API_TOP_HEADLINES_GENERAL_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&apiKey=" + API_KEY;

    private static final String API_TOP_HEADLINES_GENERAL_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=general&apiKey=" + API_KEY;
    private static final String API_TOP_HEADLINES_BUSINESS_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=business&apiKey=" + API_KEY;
    private static final String API_TOP_HEADLINES_ENTERTAINMENT_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=entertainment&apiKey=" + API_KEY;
    private static final String API_TOP_HEADLINES_HEALTH_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=health&apiKey=" + API_KEY;
    private static final String API_TOP_HEADLINES_SCIENCE_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=science&apiKey=" + API_KEY;
    private static final String API_TOP_HEADLINES_TECHNOLOGY_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=technology&apiKey=" + API_KEY;
    private static final String API_TOP_HEADLINES_SPORTS_CA_URL = "https://newsapi.org/v2/top-headlines?country=ca&category=sports&apiKey=" + API_KEY;


    private static  String API_SEARCH_NEWS_CA_URL = "https://newsapi.org/v2/everything?q=searchKey&language=en&pageSize=100&apiKey=" + API_KEY;

    public static String getApiSourcesUrl() {
        return API_SOURCES_URL;
    }

    public static String getApiTopHeadlinesGeneralCaUrl() {
        return API_TOP_HEADLINES_GENERAL_CA_URL;
    }

    public static String getApiTopHeadlinesBusinessCaUrl() {
        return API_TOP_HEADLINES_BUSINESS_CA_URL;
    }

    public static String getApiTopHeadlinesEntertainmentCaUrl() {
        return API_TOP_HEADLINES_ENTERTAINMENT_CA_URL;
    }

    public static String getApiTopHeadlinesHealthCaUrl() {
        return API_TOP_HEADLINES_HEALTH_CA_URL;
    }

    public static String getApiTopHeadlinesScienceCaUrl() {
        return API_TOP_HEADLINES_SCIENCE_CA_URL;
    }

    public static String getApiTopHeadlinesTechnologyCaUrl() {
        return API_TOP_HEADLINES_TECHNOLOGY_CA_URL;
    }

    public static String getApiTopHeadlinesSportsCaUrl() {
        return API_TOP_HEADLINES_SPORTS_CA_URL;
    }

    public static String getApiSearchNewsCaUrl() {
        return API_SEARCH_NEWS_CA_URL;
    }

    public static void setApiSearchNewsCaUrl(String toBeReplaced,String searchKey) {
        API_SEARCH_NEWS_CA_URL = API_SEARCH_NEWS_CA_URL.replace(toBeReplaced, searchKey);
    }

}
