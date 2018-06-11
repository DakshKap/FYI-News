package fyinews.global;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Switch;

import com.google.gson.Gson;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutionException;

import fyinews.constants.Constants;
import fyinews.handlers.ApiCallHandlerSources;
import fyinews.models.MainNews;
import fyinews.models.SourcesDetail;
import fyinews.models.SourcesModel;

/**
 * Created by dakshkapur on 2018-02-20.
 */

public final class GlobalMethods {

    public static List<SourcesDetail> getSourcesFromApi(){
        List<SourcesDetail> sourcesDetailFetched = new ArrayList<>();
        //Get JSON Sources data from newsapi.org
        ApiCallHandlerSources apiCallHandlerSources = new ApiCallHandlerSources();
        String newsSourcesResponseJSON = null;
        try {
            newsSourcesResponseJSON = apiCallHandlerSources.execute(Constants.getApiSourcesUrl()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }


        //Convert JSON to JAVA Objects
        SourcesModel allNewsSources = new Gson().fromJson(newsSourcesResponseJSON,SourcesModel.class);

        //Sources newsBySource
        sourcesDetailFetched =allNewsSources.getSources();

        return sourcesDetailFetched;
    }




    public static MainNews getTopHeadlinesCanada(String newsCategory){

        ApiCallHandlerSources apiCallHandlerNews = new ApiCallHandlerSources();
        String API_URL = "";
        String topHeadlinesJSON = null;
        switch (newsCategory) {
            case "General":
                API_URL = Constants.getApiTopHeadlinesGeneralCaUrl();
                break;
            case "Entertainment":
                API_URL = Constants.getApiTopHeadlinesEntertainmentCaUrl();
                break;
            case "Business":
                API_URL = Constants.getApiTopHeadlinesBusinessCaUrl();
                break;
            case "Health":
                API_URL = Constants.getApiTopHeadlinesHealthCaUrl();
                break;
            case "Science":
                API_URL = Constants.getApiTopHeadlinesScienceCaUrl();
                break;
            case "Sports":
                API_URL = Constants.getApiTopHeadlinesSportsCaUrl();
                break;
            case "Technology":
                API_URL = Constants.getApiTopHeadlinesTechnologyCaUrl();
                break;
        }


        try {
            topHeadlinesJSON = apiCallHandlerNews.execute(API_URL).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        MainNews topHeadlinesCanada = new Gson().fromJson(topHeadlinesJSON,MainNews.class);


        return topHeadlinesCanada;
    }



    public static String getArticleDate(String dateTime){

        String[] monthName = { "Jan", "Feb", "Mar", "Apr", "May", "June", "July",
                "Aug", "Sept", "Oct", "Nov", "Dec" };
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
        Date articleDateTime = new Date();
        String[] dateTimeArray = dateTime.split("T",1);
        try {
             articleDateTime = df.parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(articleDateTime);
        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String month = monthName[c.get(Calendar.MONTH)];
        String year = String.valueOf(c.get(Calendar.YEAR));

        if(c.get(Calendar.DAY_OF_MONTH) <10){
            return month+"-0"+day+"-"+year;
        }else{
            return month+"-"+day+"-"+year;
        }



    }





}
