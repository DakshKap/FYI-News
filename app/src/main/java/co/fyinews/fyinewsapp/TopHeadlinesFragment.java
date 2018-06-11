package co.fyinews.fyinewsapp;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import fyinews.adapters.TopHeadlinesAdapter;
import fyinews.global.GlobalMethods;
import fyinews.models.Articles;
import fyinews.models.MainNews;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TopHeadlinesFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TopHeadlinesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TopHeadlinesFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TopHeadlinesAdapter mAdapter;
    private String newsCategory;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private MainNews topHeadlinesCanada = new MainNews();
    private List<Articles> topHeadlinesList = new ArrayList<>();

    private OnFragmentInteractionListener mListener;

    public TopHeadlinesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TopHeadlinesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TopHeadlinesFragment newInstance(String param1, String param2) {
        TopHeadlinesFragment fragment = new TopHeadlinesFragment();
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
        newsCategory = this.getArguments().getString("newsCategory");
        //initDataset(newsCategory);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_top_headlines, container, false);

        getActivity().setTitle(newsCategory + " Headlines");


        mSwipeRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_container);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.newsTopHeadlinesRecyclerview);

        mSwipeRefreshLayout.post(new Runnable() {

            @Override
            public void run() {

                mSwipeRefreshLayout.setRefreshing(true);

                MainActivity mainActivity = (MainActivity) getContext();
                mainActivity.showSnack(mainActivity.checkConnection());

                if (!mainActivity.checkConnection()) {
                    mainActivity.callNoInternetFragment();

                }
                // Fetching data from server
                initDataset(newsCategory);

                mAdapter = new TopHeadlinesAdapter(getActivity(), topHeadlinesList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
                mRecyclerView.setLayoutManager(mLayoutManager);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayoutManager.VERTICAL));
                mRecyclerView.setAdapter(mAdapter);
            }
        });


        // Inflate the layout for this fragment
        return rootView;


    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onTopHeadlineSelected(1);
        }
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        MainActivity mainActivity = (MainActivity) getContext();
        mainActivity.showSnack(mainActivity.checkConnection());

        if (!mainActivity.checkConnection()) {


        }

        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
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

    @Override
    public void onRefresh() {

        initDataset(newsCategory);
        mRecyclerView.getAdapter().notifyDataSetChanged();

    }

    public void initDataset(String newsCategory) {

        // ProgressBar pb = (ProgressBar)
        topHeadlinesCanada = GlobalMethods.getTopHeadlinesCanada(newsCategory);
        topHeadlinesList = topHeadlinesCanada.getArticles();
        mSwipeRefreshLayout.setRefreshing(false);
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onTopHeadlineSelected(int id);

    }
}
