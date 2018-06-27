package co.fyinews.fyinewsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.view.View.OnKeyListener;
import android.view.View;
import android.view.KeyEvent;

import java.util.ArrayList;
import java.util.List;

import fyinews.adapters.SearchNewsAdapter;
import fyinews.adapters.TopHeadlinesAdapter;
import fyinews.global.GlobalMethods;
import fyinews.models.Articles;
import fyinews.models.MainNews;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnSearchNewsFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SearchFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SearchFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private EditText searchEditText;
    //private SwipeRefreshLayout mSwipeRefreshLayout;
    private SearchNewsAdapter mAdapter;
    private MainNews searchedNews = new MainNews();
    private List<Articles> searchedNewsList = new ArrayList<>();

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnSearchNewsFragmentInteractionListener mListener;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SearchFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SearchFragment newInstance(String param1, String param2) {
        SearchFragment fragment = new SearchFragment();
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
        }
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.showSnack(mainActivity.checkConnection());

        if (!mainActivity.checkConnection()) {
            mainActivity.callNoInternetFragment();

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getActivity().setTitle("Search News");


        View rootview = inflater.inflate(R.layout.fragment_search, container, false);
        mRecyclerView = rootview.findViewById(R.id.searchNewsRecyclerview);
        searchEditText = rootview.findViewById(R.id.searchNewsEditText);

        searchEditText.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int keyCode, KeyEvent keyevent) {
                //If the keyevent is a key-down event on the "enter" button
                if ((keyevent.getAction() == KeyEvent.ACTION_DOWN) && (keyCode == KeyEvent.KEYCODE_ENTER)) {

                    onClickSearch();

                    return true;
                }
                return false;
            }
        });
        return rootview;
    }

    public void onClickSearch() {
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.showSnack(mainActivity.checkConnection());

        if (!mainActivity.checkConnection()) {
            mainActivity.callNoInternetFragment();

        }
        if (searchEditText.getText().toString().length() != 0) {
            initDataset(searchEditText.getText().toString());
            mAdapter = new SearchNewsAdapter(getActivity(), searchedNewsList);
            RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        } else {

            Context context = getActivity().getApplicationContext();
            CharSequence text = "Search Field Cannot be empty!!";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onSearchNewsFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnSearchNewsFragmentInteractionListener) {
            mListener = (OnSearchNewsFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NoInternetOnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public void initDataset(String searchText) {


        searchedNews = GlobalMethods.getSearchedNews(searchText);
        searchedNewsList = searchedNews.getArticles();

    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnSearchNewsFragmentInteractionListener {
        // TODO: Update argument type and name
        void onSearchNewsFragmentInteraction(Uri uri);
    }
}
