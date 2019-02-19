package com.nat.hw2;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MainActivity";
    private static final String DOCUMENT_COUNT = "document_count";

    private int documentCount = 0;
    private FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        documentCount = savedInstanceState.getInt(DOCUMENT_COUNT);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            onMenuAddClicked();
            return true;
        } else if (item.getItemId() == R.id.action_delete) {
            onMenuDeleteClicked();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClicked() {
        documentCount++;
        Log.d(LOG_TAG, "onMenuAddClicked()");
        fragmentManager.beginTransaction()
                .add(R.id.container, DocFragment.newInstance(documentCount))
                .addToBackStack(null)
                .commit();
        Log.d(LOG_TAG, "Document added");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DOCUMENT_COUNT, documentCount);
    }

    private void onMenuDeleteClicked() {
        Log.d(LOG_TAG, "onMenuDeleteClicked()");
        if (documentCount > 0) {
            documentCount--;
            fragmentManager.popBackStack();
            Log.d(LOG_TAG, "Document deleted.");
        } else {
            Log.d(LOG_TAG, "No documents on the stack.");
        }
    }

}
