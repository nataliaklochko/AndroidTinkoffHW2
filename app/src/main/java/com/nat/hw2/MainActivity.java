package com.nat.hw2;


import androidx.fragment.app.FragmentManager;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class MainActivity extends AppCompatActivity {

    private static String LOG_TAG = "MainActivity";
    private static final String DOCUMENT_COUNT = "document_count";

    private FragmentManager fragmentManager = getSupportFragmentManager();
    private MenuItem delete;
    private int documentCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(DOCUMENT_COUNT, fragmentManager.getBackStackEntryCount());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main, menu);
        delete = menu.findItem(R.id.action_delete);
        checkEnabled();
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_add) {
            onMenuAddClicked();
        } else if (item.getItemId() == R.id.action_delete) {
            onMenuDeleteClicked();
        }
        fragmentManager.executePendingTransactions();
        checkEnabled();
        return super.onOptionsItemSelected(item);
    }

    private void onMenuAddClicked() {
        Log.d(LOG_TAG, "onMenuAddClicked()");
        documentCount = fragmentManager.getBackStackEntryCount();
        fragmentManager.beginTransaction()
                .add(R.id.container, DocFragment.newInstance(documentCount + 1))
                .addToBackStack(null)
                .commit();
        Log.d(LOG_TAG, "Document added");
    }

    private void onMenuDeleteClicked() {
        Log.d(LOG_TAG, "onMenuDeleteClicked()");
        fragmentManager.popBackStack();
        Log.d(LOG_TAG, "Document deleted.");
    }

    private void checkEnabled() {
        documentCount = fragmentManager.getBackStackEntryCount();
        if (documentCount == 0) {
            delete.setEnabled(false);
        } else if (documentCount == 1) {
            delete.setEnabled(true);
        }
    }

}
