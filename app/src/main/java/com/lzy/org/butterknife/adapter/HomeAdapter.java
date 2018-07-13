package com.lzy.org.butterknife.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lzy.org.butterknife.R;
import com.lzy.org.butterknife.entity.User;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * HomeAdapter
 *
 * @author linzhiyong
 * @email wflinzhiyong@163.com
 * @blog https://blog.csdn.net/u012527802
 * https://github.com/linzhiyong
 * https://www.jianshu.com/u/e664ba5d0800
 * @time 2018/7/12
 * @desc
 */
public class HomeAdapter extends BaseAdapter {

    private List<User> userList;

    private LayoutInflater inflater;

    public HomeAdapter(Context context, List<User> userList) {
        this.userList = userList == null ? new ArrayList<User>() : userList;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return position > userList.size() - 1 ? null : userList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_item_1, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        User user = userList.get(position);
        viewHolder.nameView.setText(user.getName());
        viewHolder.ageView.setText(String.valueOf(user.getAge()));

        return convertView;
    }

    final class ViewHolder {

        @BindView(R.id.nameView)
        TextView nameView;

        @BindView(R.id.ageView)
        TextView ageView;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }

    }

}
