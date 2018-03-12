package fyinews.global;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.format.DateUtils;
import android.util.Log;

import com.google.gson.Gson;

import java.io.InputStream;
import java.net.URL;
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

    public static MainNews getTopHeadlinesCanada(){
        ApiCallHandlerSources apiCallHandlerNews = new ApiCallHandlerSources();

        String topHeadlinesJSON = null;

        try {
            topHeadlinesJSON = apiCallHandlerNews.execute(Constants.getApiTopHeadlinesCaUrl()).get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        MainNews topHeadlinesCanada = new Gson().fromJson(topHeadlinesJSON,MainNews.class);


        return topHeadlinesCanada;
    }

    public static Bitmap getBitmapImageFromUrl(String url){
        DownLoadImageTask dit = new DownLoadImageTask(url);
        Bitmap result = null;

        try {
            result = dit.execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        return result;
    }

    public static String calculateDateTimeDifference(String dateTime){

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



    static class DownLoadImageTask extends AsyncTask<String,Void,Bitmap> {
        String imageUrl;

        public DownLoadImageTask(String imageUrl){
            this.imageUrl = imageUrl;
        }

        /*
            doInBackground(Params... params)
                Override this method to perform a computation on a background thread.
         */
        protected Bitmap doInBackground(String...urls){

            Bitmap logo = null;
            try{
                InputStream is = new URL(imageUrl).openStream();
                /*
                    decodeStream(InputStream is)
                        Decode an input stream into a bitmap.
                 */
                logo = BitmapFactory.decodeStream(is);
            }catch(Exception e){ // Catch the download exception
                e.printStackTrace();
            }
            return logo;
        }



    }



}
