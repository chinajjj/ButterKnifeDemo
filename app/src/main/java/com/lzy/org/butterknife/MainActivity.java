package com.lzy.org.butterknife;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.org.butterknife.adapter.HomeAdapter;
import com.lzy.org.butterknife.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindAnim;
import butterknife.BindArray;
import butterknife.BindBitmap;
import butterknife.BindColor;
import butterknife.BindDimen;
import butterknife.BindDrawable;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnFocusChange;
import butterknife.OnItemClick;
import butterknife.OnItemLongClick;
import butterknife.OnItemSelected;
import butterknife.OnTouch;
import butterknife.Unbinder;

/**
 * MainActivity
 *
 * @author linzhiyong
 * @email wflinzhiyong@163.com
 * @blog https://blog.csdn.net/u012527802
 * https://github.com/linzhiyong
 * https://www.jianshu.com/u/e664ba5d0800
 * @time 2018/7/12
 * @desc
 */
public class MainActivity extends AppCompatActivity {

    private Unbinder unbinder;

    @BindViews({R.id.textView1, R.id.textView2, R.id.textView3})
    List<TextView> viewList;

    @BindView(R.id.editText1)
    EditText editText;

    @BindView(R.id.listView1)
    ListView listView;

    private HomeAdapter adapter;

    @BindString(R.string.app_name)
    String appName;

    @BindArray(R.array.list)
    String[] array;

    @BindBitmap(R.mipmap.ic_launcher)
    Bitmap icon;

    @BindColor(R.color.colorAccent)
    int colorAccent;

    @BindDimen(R.dimen.width)
    int width;

    @BindAnim(R.anim.anim_translate_1)
    Animation translateAnimation;

    @BindDrawable(R.drawable.ic_launcher_background)
    Drawable drawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        List<User> userList = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            userList.add(new User("name" + i, 10 + i));
        }
        adapter = new HomeAdapter(this, userList);
        listView.setAdapter(adapter);

        // 方法一 设置属性值
        ButterKnife.apply(viewList, new ButterKnife.Action<TextView>() {
            @Override
            public void apply(@NonNull TextView view, int index) {
                view.setText("默认值");
                view.startAnimation(translateAnimation);
            }
        });

        // 方法二
        ButterKnife.apply(viewList, new ButterKnife.Setter<TextView, String>() {
            @Override
            public void set(@NonNull TextView view, String value, int index) {
                view.setText(value);
            }
        }, appName);
    }

    @OnClick(R.id.button1)
    public void onClick(View view) {
        showToast("点击了按钮：" + view.toString());
    }

    @OnTouch(R.id.textView1)
    public boolean onTouch(View view) {
        showToast("touch:" + view.toString());
        return true;
    }

    @OnFocusChange(R.id.editText1)
    public void onFocusChange(View view, boolean flag) {
        showToast("焦点改变...");
    }

    @OnClick({R.id.button1, R.id.button2, R.id.button3})
    public void onClickEx(View view) {
        switch (view.getId()) {
            case R.id.button1:
                break;

            case R.id.button2:
                break;

            case R.id.button3:
                break;
        }
    }

    @OnItemClick(R.id.listView1)
    public void onItemClickListener(int position) {
        showToast("ListView点击位置：" + position);
    }

    @OnItemLongClick(R.id.listView1)
    public boolean onItemLongClickListener(int position) {
        showToast("ListView长按位置：" + position);
        return true;
    }

    @OnItemSelected(R.id.spinner1)
    public void onItemSelectedClickListener(int position) {
        showToast("Spinner点击位置：" + position);
    }

    @OnItemSelected(value = R.id.spinner1, callback = OnItemSelected.Callback.NOTHING_SELECTED)
    public void onNothingSelectedClickListener() {
        showToast("未选择item");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
