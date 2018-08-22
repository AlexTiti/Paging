package com.example.library.base.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.library.global.GlobalApplication;
import com.example.library.utils.AppUtils;

import java.util.Objects;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import me.yokeyword.fragmentation.SupportFragment;


/**
 * @author Administrator
 */
public abstract class BaseCompatFragment extends SupportFragment {

    protected String TAG;
    protected Context mContext;
    protected Activity mActivity;
    protected GlobalApplication mApplication;
    private Unbinder binder;
    private CompositeDisposable mCompositeDisposable;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        if (getLayoutView() != null) {
            return getLayoutView();
        } else {
            return inflater.inflate(getLayoutId(), container, false);
        }
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        binder = ButterKnife.bind(this, view);
        mCompositeDisposable = new CompositeDisposable();
        getBundle(getArguments());
        initData();
        initUI(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (binder != null) {
            binder.unbind();
        }
        clearCompositeDisposable();

    }

    /**
     * 添加订阅
     *
     * @param disposable 订阅的对象
     */
    public void addCompositeDisposable(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);

    }

    /**
     * 取消订阅
     */
    private void clearCompositeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }


    /**
     * 获取xml文件
     *
     * @return
     */
    @LayoutRes
    public abstract int getLayoutId();

    private View getLayoutView() {
        return null;
    }

    /**
     * 检测网络问题
     */
    public void checkNetWork() {

    }


    /**
     * 得到Activity传进来的值
     */
    private void getBundle(Bundle bundle) {
    }

    /**
     * 初始化UI
     *
     * @param view               onCreateView()
     * @param savedInstanceState Bundle
     */
    public abstract void initUI(View view, @Nullable Bundle savedInstanceState);

    /**
     * 在监听器之前把数据准备好
     */
    public void initData() {
        mContext = AppUtils.INSTANCE.getContext();
        mApplication = (GlobalApplication) mActivity.getApplication();
    }

    /**
     * 处理回退事件
     *
     * @return true 事件已消费
     * <p>
     * false 事件向上传递
     */
    @Override
    public boolean onBackPressedSupport() {
        if (Objects.requireNonNull(getFragmentManager()).getBackStackEntryCount() > 1) {
            pop();
        } else {
            return false;
        }
        return true;
    }


}
