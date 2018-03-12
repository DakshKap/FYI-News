package fyinews.adapters;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


import co.fyinews.fyinewsapp.R;
import fyinews.global.GlobalMethods;
import fyinews.models.Articles;
import fyinews.models.MainNews;


/**
 * Created by dakshkapur on 2018-03-03.
 */

public class TopHeadlinesAdapter extends RecyclerView.Adapter<TopHeadlinesAdapter.MyViewHolder> {

    private MainNews topHeadlines;
    private List<Articles> topHeadlinesList;


    public TopHeadlinesAdapter(List<Articles> topHeadlinesList){
        this.topHeadlinesList = topHeadlinesList;
    }

    public TopHeadlinesAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_actual_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TopHeadlinesAdapter.MyViewHolder holder, int position) {
        Articles article = topHeadlinesList.get(position);
        holder.title.setText(article.getTitle());

        Bitmap image = GlobalMethods.getBitmapImageFromUrl(article.getUrlToImage());
        holder.image.setImageBitmap(image);
        holder.source.setText(article.getSource().getName());
        holder.date.setText(GlobalMethods.calculateDateTimeDifference(article.getPublishedAt()));
    }

    @Override
    public int getItemCount() {
        return topHeadlinesList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title,source, date;
        public ImageView image;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.newsTitle);
            image = (ImageView) itemView.findViewById(R.id.newsUrlToImage);
            source = (TextView) itemView.findViewById(R.id.newsSourceTopHeadline);
            date = (TextView) itemView.findViewById(R.id.newsDateTopHeadline);

        }
    }
}
