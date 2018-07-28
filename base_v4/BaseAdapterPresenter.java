package com.dev.rahul.basefilesamples.base_v4;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rahul on 20/1/18.
 */

public abstract class BaseAdapterPresenter<V extends IBaseHolderView, E extends Object>
        implements IBaseAdapterPresenter<E> {

    private List<E> list;
    private BaseAdapter adapter;
    private V baseHolderView;

    public BaseAdapterPresenter() {
        this.list = new ArrayList<>();
    }

    public BaseAdapterPresenter(List<E> list) {
        this.list = list;
    }

    public void init(List<E> list) {
        this.list = list;
        if (adapter != null)
            adapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(IBaseHolderView baseHolderView) {
        this.baseHolderView = (V) baseHolderView;
    }

    public V view() {
        return baseHolderView;
    }

    @Override
    public void onAttachAdapter(BaseAdapter adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getCount() {
        return list != null ? list.size() : 0;
    }

    @Override
    public E getElementAtPos(int position) {
        return list.get(position);
    }

    @Override
    public void addNewItems(List<E> listNewItems) {
        int currentSize = getCount();
        list.addAll(listNewItems);
        adapter.notifyItemRangeInserted(currentSize, getCount());
    }

    @Override
    public void addItem(E data) {
        list.add(data);
        adapter.notifyItemInserted(getCount());
    }

    @Override
    public void removeItem(int position) {
        list.remove(position);
        adapter.notifyItemRemoved(position);
    }

    @Override
    public List<E> getAllElements() {
        return list;
    }
}
