package com.nat.hw2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;



public class DocFragment extends Fragment {

    private static String LOG_TAG = "DocFragment";

    private static final String DOCUMENT_COUNT = "document_count";
    private TextView textView;

    public static DocFragment newInstance(int documentCount) {
        Log.d(DocFragment.LOG_TAG, "DocFragment newInstance()");
        Bundle arguments = new Bundle();
        arguments.putInt(DOCUMENT_COUNT,documentCount);
        DocFragment documentFragment = new DocFragment();
        documentFragment.setArguments(arguments);
        return documentFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(LOG_TAG, "DocFragment onCreateView()");
        View view = inflater.inflate(R.layout.doc_fragment, container, false);
        textView = (TextView) view.findViewById(R.id.text_view);
        int documentCount = getArguments().getInt(DOCUMENT_COUNT);
        textView.setText(String.format("Документ №%d", documentCount));
        return view;

    }

}


