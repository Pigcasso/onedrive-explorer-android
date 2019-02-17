package com.microsoft.onedrive.apiexplorer;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * <pre>
 *     author : Julian
 *     time   : 2019/02/17
 *     desc   :
 *     version: 1.0
 * </pre>
 */

public class FileBrowserActivity extends AppCompatActivity implements ItemFragment.OnFragmentInteractionListener {

    private static final String ITEM_ID = "item_id";

    public static void start(Context context, String itemId) {
        Intent starter = new Intent(context, FileBrowserActivity.class);
        starter.putExtra(ITEM_ID, itemId);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_file_browser);

        Fragment fragment = getFragmentManager().findFragmentById(R.id.fragment);
        if (fragment == null) {
            fragment = ItemFragment.newInstance(getIntent().getStringExtra(ITEM_ID));
            getFragmentManager().beginTransaction()
                    .add(R.id.fragment, fragment)
                    .commit();
        }
    }

    @Override
    public void onFragmentInteraction(DisplayItem item) {
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment, ItemFragment.newInstance(item.getId()))
                .addToBackStack(null)
                .commit();
    }
}
