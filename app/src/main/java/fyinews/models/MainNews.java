package fyinews.models;

import java.util.List;

/**
 * Created by dakshkapur on 2018-03-02.
 */


public class MainNews{
    private String status;
    private int totalResults;
    private List<Articles> articles;

    public String getStatus(){
        return status;
    }
    public void setStatus(String input){
        this.status = input;
    }
    public int getTotalResults(){
        return totalResults;
    }
    public void setTotalResults(int input){
        this.totalResults = input;
    }
    public List<Articles> getArticles(){
        return articles;
    }
    public void setArticles(List<Articles> input){
        this.articles = input;
    }
}


