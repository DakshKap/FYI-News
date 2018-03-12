package fyinews.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import co.fyinews.fyinewsapp.R;
import fyinews.models.SourcesDetail;

/**
 * Created by dakshkapur on 2018-02-20.
 */

public class NewsBySourceAdapter extends RecyclerView.Adapter<NewsBySourceAdapter.MyViewHolder> {

    private List<SourcesDetail> sourcesDetailList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView name, description;

        public MyViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.nameNewsBySourceItem);
            description = (TextView) view.findViewById(R.id.descriptionNewsBySourceItem);

        }
    }

    public NewsBySourceAdapter(List<SourcesDetail> sourcesDetailList){

        this.sourcesDetailList = sourcesDetailList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.news_by_source_item, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        SourcesDetail source = sourcesDetailList.get(position);

        holder.name.setText(source.getName());
        holder.description.setText(source.getDescription());

    }

    @Override
    public int getItemCount() {
        return sourcesDetailList.size();
    }


}
