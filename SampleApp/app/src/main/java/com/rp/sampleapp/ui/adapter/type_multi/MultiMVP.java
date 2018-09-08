package com.rp.sampleapp.ui.adapter.type_multi;

import com.rp.basefiles.IBaseAdapterPresenter;
import com.rp.basefiles.IBaseHolderView;
import com.rp.sampleapp.pojo.MultiData;
import com.rp.sampleapp.pojo.SingleData;

public interface MultiMVP {

    interface IViewOne extends IBaseHolderView {

    }

    interface IViewTwo extends IBaseHolderView {

    }

    interface IPresenter<E extends MultiData> extends IBaseAdapterPresenter<E> {

    }
}
