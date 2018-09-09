package com.rp.util.fragment;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

public final class Builder {

    private FragmentBuilder fragmentBuilder;

    Builder(FragmentBuilder fragmentBuilder) {
        this.fragmentBuilder = fragmentBuilder;
    }

    public FragmentTransaction replace(@IdRes int id, @NonNull Fragment fragment) {
        return fragmentBuilder.replace(id, fragment);
    }

    public FragmentTransaction replace(@IdRes int id, @NonNull Fragment fragment, @NonNull String tag) {
        return fragmentBuilder.replace(id, fragment, tag);
    }

    public FragmentTransaction remove(@NonNull String tag) {
        return fragmentBuilder.remove(tag);
    }

    public void removeAll() {

    }
}
