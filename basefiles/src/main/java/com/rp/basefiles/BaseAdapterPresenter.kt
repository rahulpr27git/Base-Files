package com.rp.basefiles

import android.support.v7.recyclerview.extensions.AsyncListDiffer
import android.support.v7.util.DiffUtil
import com.rp.util.adapter.RAdapterAsyncDiffCallback
import com.rp.util.adapter.RAdapterPayloadWatcher


abstract class BaseAdapterPresenter<V : IBaseHolderView, E> : IBaseAdapterPresenter<E> {

    private var baseHolderView: V? = null

    private var mDiffer: AsyncListDiffer<E>? = null
    private var DIFF_CALLBACK: DiffUtil.ItemCallback<E>? = null

    init {
        this.DIFF_CALLBACK = RAdapterAsyncDiffCallback<E>()
    }

    override fun onAttach(baseHolderView: IBaseHolderView) {
        this.baseHolderView = baseHolderView as V
    }

    override fun onAttachAdapter(adapter: BaseAdapter<*, *>) {
        mDiffer = AsyncListDiffer<E>(adapter, DIFF_CALLBACK!!)
    }

    override fun onAttachDiffCallback(callback: DiffUtil.ItemCallback<E>) {
        this.DIFF_CALLBACK = callback
    }

    override fun onAttachPayloadWatcher(payloadWatcher: RAdapterPayloadWatcher<E>) {
        if (DIFF_CALLBACK is RAdapterAsyncDiffCallback<*>)
            (DIFF_CALLBACK as RAdapterAsyncDiffCallback<E>)
                    .addPayloadWatcher(payloadWatcher)
    }

    fun view(): V? {
        return baseHolderView
    }

    override fun update(list: List<E>) {
        mDiffer!!.submitList(list)
    }

    override val count: Int
        get() = mDiffer?.currentList?.size ?: 0

    override fun addNewList(listNewItems: List<E>) {
        val oldList = ArrayList<E>(mDiffer!!.currentList)
        oldList.addAll(listNewItems)
        mDiffer!!.submitList(oldList)
    }

    override fun addItem(data: E) {
        val oldList = ArrayList<E>(mDiffer!!.currentList)
        oldList.add(data)
        mDiffer!!.submitList(oldList)
    }

    override fun addItemAt(position: Int, data: E) {
        val oldList = ArrayList<E>(mDiffer!!.currentList)
        oldList.add(position, data)
        mDiffer!!.submitList(oldList)
    }

    override fun removeItem(position: Int) {
        val oldList = ArrayList<E>(mDiffer!!.currentList)
        oldList.removeAt(position)
        mDiffer!!.submitList(oldList)
    }

    override fun getFrom(position: Int): E {
        return mDiffer!!.currentList.get(position)
    }

    override val all: List<E>
        get() = mDiffer?.currentList as List<E>
}