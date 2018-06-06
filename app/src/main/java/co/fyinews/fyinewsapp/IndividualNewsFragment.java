package co.fyinews.fyinewsapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import fyinews.global.GlobalMethods;
import fyinews.models.Articles;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link IndividualNewsFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link IndividualNewsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class IndividualNewsFragment extends android.support.v4.app.Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private Articles selectedArticle;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private IndividualNewsFragmentInteractionListener mListener;

    public IndividualNewsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment IndividualNewsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static IndividualNewsFragment newInstance(String param1, String param2) {
        IndividualNewsFragment fragment = new IndividualNewsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            selectedArticle = getArguments().getParcelable("article_selected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_individual_news, container, false);

        //selectedArticle.setUrl(null);
        ImageView image = rootView.findViewById(R.id.image_indv_news);
        TextView source = rootView.findViewById(R.id.textViewSource);
        TextView author = rootView.findViewById(R.id.textViewAuthor);
        TextView date = rootView.findViewById(R.id.textViewDate);
        TextView description = rootView.findViewById(R.id.textViewDescription);
        TextView title = rootView.findViewById(R.id.textViewTitle);
        Button button = rootView.findViewById(R.id.btnOriginalArticle);


        if(selectedArticle.getUrl() == null){
            button.setVisibility(View.GONE);
        }

        if(selectedArticle.getUrlToImage() != null){
            Glide.with(image.getContext())
                    .load(selectedArticle.getUrlToImage())
                    .apply(new RequestOptions()
                            .placeholder(R.mipmap.ic_no_image)
                            .centerCrop())
                    .into(image);

        }else{
            Glide.with(image.getContext())
                    .load(R.mipmap.ic_no_image)
                    .apply(new RequestOptions()
                            .override(800, 500))
                    .into(image);
        }
        title.setText(selectedArticle.getTitle());
        source.setText(selectedArticle.getSource().getName());
        author.setText(selectedArticle.getAuthor());
        date.setText(GlobalMethods.getArticleDate(selectedArticle.getPublishedAt()));
        description.setText(selectedArticle.getDescription());

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Uri uri = Uri.parse(selectedArticle.getUrl()); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
            }
        });
        // Inflate the layout for this fragment
        return rootView;


    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.individualNewsFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof IndividualNewsFragmentInteractionListener) {
            mListener = (IndividualNewsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement IndividualNewsFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface IndividualNewsFragmentInteractionListener {
        // TODO: Update argument type and name
        void individualNewsFragmentInteraction(Uri uri);
    }
}
