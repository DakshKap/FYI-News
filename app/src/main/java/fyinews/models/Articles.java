package fyinews.models;

import android.os.Parcel;
import android.os.Parcelable;

public class Articles implements Parcelable  {
    private Source source;
    private String author;
    private String title;
    private String description;
    private String url;
    private String urlToImage;
    private String publishedAt;

    protected Articles(Parcel in) {
        author = in.readString();
        title = in.readString();
        description = in.readString();
        url = in.readString();
        urlToImage = in.readString();
        publishedAt = in.readString();
    }

    public static final Creator<Articles> CREATOR = new Creator<Articles>() {
        @Override
        public Articles createFromParcel(Parcel in) {
            return new Articles(in);
        }

        @Override
        public Articles[] newArray(int size) {
            return new Articles[size];
        }
    };

    public Source getSource(){
        return source;
    }
    public void setSource(Source input){
        this.source = input;
    }
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String input){
        this.author = input;
    }
    public String getTitle(){
        return title;
    }
    public void setTitle(String input){
        this.title = input;
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
    public String getUrlToImage(){
        return urlToImage;
    }
    public void setUrlToImage(String input){
        this.urlToImage = input;
    }
    public String getPublishedAt(){
        return publishedAt;
    }
    public void setPublishedAt(String input){
        this.publishedAt = input;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(author);
        parcel.writeString(title);
        parcel.writeString(description);
        parcel.writeString(url);
        parcel.writeString(urlToImage);
        parcel.writeString(publishedAt);
    }
}
