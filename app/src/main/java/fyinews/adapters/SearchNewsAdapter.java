package fyinews.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import co.fyinews.fyinewsapp.IndividualNewsFragment;
import co.fyinews.fyinewsapp.MainActivity;
import co.fyinews.fyinewsapp.R;
import co.fyinews.fyinewsapp.SearchFragment;
import fyinews.global.GlobalMethods;
import fyinews.models.Articles;

/**
 * Created by dakshkapur on 2018-06-11.
 */

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.MyViewHolder> {

    private List<Articles> searchNewsList;
    private Context mContext;

    public SearchNewsAdapter(Context context, List<Articles> searchNewsList){
        this.searchNewsList = searchNewsList;
        mContext = context;
    }

    @Override
    public SearchNewsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_actual_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(SearchNewsAdapter.MyViewHolder holder, int position) {
        final Articles article = searchNewsList.get(position);

        if(article.getUrlToImage() != null){
            Glide.with(holder.image.getContext())
                    .load(article.getUrlToImage())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_no_image)
                            .centerCrop())
                    .into(holder.image);

        }else{
            Glide.with(holder.image.getContext())
                    .load(R.mipmap.ic_no_image)
                    .into(holder.image);
        }
        holder.title.setText(article.getTitle());

        holder.source.setText(article.getSource().getName());
        holder.date.setText(GlobalMethods.getArticleDate(article.getPublishedAt()));

        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                fragmentJump(article);

            }
        });
    }

    private void fragmentJump(Articles mItemSelected) {
        IndividualNewsFragment mFragment = new IndividualNewsFragment();
        Bundle mBundle = new Bundle();
        mBundle.putParcelable("article_selected",  mItemSelected);
        mFragment.setArguments(mBundle);
        switchContent(R.id.frag_output, mFragment);
    }

    public void switchContent(int id, Fragment fragment) {
        if (mContext == null)
            return;
        if (mContext instanceof MainActivity) {
            MainActivity mainActivity = (MainActivity) mContext;
            Fragment frag = fragment;
            mainActivity.switchContent(id, frag);
        }

    }

    @Override
    public int getItemCount() {
        return searchNewsList.size();
    }




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, source, date;
        public ImageView image;
        public LinearLayout parentLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.newsTitle);
            image = (ImageView) itemView.findViewById(R.id.newsUrlToImage);
            source = (TextView) itemView.findViewById(R.id.newsSourceTopHeadline);
            date = (TextView) itemView.findViewById(R.id.newsDateTopHeadline);
            parentLayout = itemView.findViewById(R.id.parent_layout_item);

        }
    }
}
