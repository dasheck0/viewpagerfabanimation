package com.dasheck.viewpagerfabproject;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScreenSlidePagerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScreenSlidePagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScreenSlidePagerFragment extends Fragment {

    public static final String BUNDLE_KEY = "BundleLey";

    private int position;
    private TextView textView;

    public static ScreenSlidePagerFragment newInstance(int position) {
        ScreenSlidePagerFragment fragment = new ScreenSlidePagerFragment();

        Bundle args = new Bundle();
        args.putInt(BUNDLE_KEY, position);
        fragment.setArguments(args);

        return fragment;
    }

    public ScreenSlidePagerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getArguments() != null) {
            position = getArguments().getInt(BUNDLE_KEY);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_screen_slide_pager, container, false);


        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        textView = (TextView) getView().findViewById(R.id.itemText);
        textView.setText(String.valueOf(position));
    }
}
