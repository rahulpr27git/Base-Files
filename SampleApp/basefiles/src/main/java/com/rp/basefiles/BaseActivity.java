package com.rp.basefiles;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;
import android.widget.Toast;

import com.rp.util.fragment.FragmentBuilder;

/**
 * Created by rahul on 4/1/18.
 */

public abstract class BaseActivity extends AppCompatActivity
        implements IBaseView, SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipeRefreshLayout;
    private Context context = this;

    private Snackbar snackbar;
    private ActionBar actionBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        FragmentBuilder.initManager(getSupportFragmentManager());
    }

    @Override
    public void onAttachSwipeRefreshLayout(SwipeRefreshLayout swipeRefreshLayout) {
        this.swipeRefreshLayout = swipeRefreshLayout;
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @Override
    public void enableBackButton() {
        actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
    }

    @Override
    public void disableBackButton() {
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(false);
            actionBar.setDisplayShowHomeEnabled(false);
        }
    }

    @Override
    public void setTitle(String title) {
        if (actionBar != null) actionBar.setTitle(title);
    }

    @Override
    public void setSubTitle(String subTitle) {
        if (actionBar != null) actionBar.setSubtitle(subTitle);
    }

    @Override
    public void onShowLoading() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void onHideLoading() {
        if (swipeRefreshLayout != null)
            swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onFailed(@NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(@NonNull String error) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSuccess(@NonNull String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initializeSnackBar(View view) {
        snackbar = Snackbar.make(view, "Please try again", Snackbar.LENGTH_LONG);
    }

    @Override
    public void showSnackBarMessage(String message) {
        try {
            snackbar.setText(message);
            snackbar.setAction("Dismiss", view -> {

            });
            snackbar.show();
        } catch (NullPointerException e) {
            Toast.makeText(
                    context,
                    "Please initialize the snackbar before showing. call initializeSnackBar(View view)",
                    Toast.LENGTH_SHORT
            ).show();
        }
    }

    @Override
    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager =(InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    public Bundle getBundleData() {
        return getIntent() != null ? getIntent().getExtras() : null;
    }

    @Override
    public boolean isConnectedToNetwork() {
        ConnectivityManager cm =
                (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = null;
        if (cm != null) {
            activeNetwork = cm.getActiveNetworkInfo();
        }
        return (activeNetwork != null && activeNetwork.isConnected());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        context = null;
        swipeRefreshLayout = null;
    }

    public TextView changeToolbarFont(Toolbar toolbar) {
        for (int i = 0; i < toolbar.getChildCount(); i++) {
            View view = toolbar.getChildAt(i);
            if (view instanceof TextView) {
                TextView tv = (TextView) view;
                if (tv.getText().equals(toolbar.getTitle())) {
                    return tv;
                }
            }
        }
        return null;
    }

    public abstract int getLayoutRes();
}
