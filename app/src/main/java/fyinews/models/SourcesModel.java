package fyinews.models;

import java.util.List;

/**
 * Created by dakshkapur on 2018-02-10.
 */




public class SourcesModel{
    private String status;
    private List<SourcesDetail> sources;

    public String getStatus(){
        return status;
    }
    public void setStatus(String input){
        this.status = input;
    }
    public List<SourcesDetail> getSources(){
        return sources;
    }
    public void setSources(List<SourcesDetail> input){
        this.sources = input;
    }
}

