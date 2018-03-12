package fyinews.models;

/**
 * Created by dakshkapur on 2018-02-10.
 */

public class SourcesDetail {
    private String id;
    private String name;
    private String description;
    private String url;
    private String category;
    private String language;
    private String country;

    public String getId(){
        return id;
    }
    public void setId(String input){
        this.id = input;
    }
    public String getName(){
        return name;
    }
    public void setName(String input){
        this.name = input;
    }
    public String getDescription(){
        return description;
    }
    public void setDescription(String input){
        this.description = input;
    }
    public String getUrl(){
        return url;
    }
    public void setUrl(String input){
        this.url = input;
    }
    public String getCategory(){
        return category;
    }
    public void setCategory(String input){
        this.category = input;
    }
    public String getLanguage(){
        return language;
    }
    public void setLanguage(String input){
        this.language = input;
    }
    public String getCountry(){
        return country;
    }
    public void setCountry(String input){
        this.country = input;
    }
}
