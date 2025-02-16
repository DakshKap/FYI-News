package co.fyinews.fyinewsapp;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import fyinews.global.ConnectivityReceiver;
import fyinews.global.GlobalMethods;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoInternetOnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoInternetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoInternetFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private NoInternetOnFragmentInteractionListener mListener;

    public NoInternetFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NoInternetFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NoInternetFragment newInstance(String param1, String param2) {
        NoInternetFragment fragment = new NoInternetFragment();
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
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview = inflater.inflate(R.layout.fragment_no_internet, container, false);

        Button button = (Button)rootview.findViewById(R.id.buttonRetryInternet);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {

                    MainActivity mainActivity = (MainActivity) getContext();
                    mainActivity.showSnack(checkConnection());

                    if(checkConnection()){
                        mainActivity.callTopHeadlinesFragment("General");
                    }

            }
        });

        getActivity().setTitle("No Internet");

        // Inflate the layout for this fragment
        return rootview;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.noInternetOnFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof NoInternetOnFragmentInteractionListener) {
            mListener = (NoInternetOnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement NoInternetOnFragmentInteractionListener");
        }
    }


    private boolean checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        return isConnected;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
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
    public interface NoInternetOnFragmentInteractionListener {
        // TODO: Update argument type and name
        void noInternetOnFragmentInteraction(Uri uri);
    }
}
