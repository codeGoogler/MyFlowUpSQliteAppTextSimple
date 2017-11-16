package com.yyh.db.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yyh.db.R;
import com.yyh.db.impl.Good;

import java.util.List;


/**
 * 类功能描述：</br>
 * 适配器adapter
 * 博客地址：http://blog.csdn.net/androidstarjack
 * 公众号：终端研发部
 * @author yuyahao
 * @version 1.0 </p> 修改时间：2017/10/12 </br> 修改备注：</br>
 */
public class Mydapter extends BaseAdapter {

    private List<Good> newsList;
    Activity activity;
    LayoutInflater inflater = null;
    public Mydapter(Activity activity, List<Good> newsList) {
        this.activity = activity;
        this.newsList = newsList;
        inflater = LayoutInflater.from(activity);
    }
    @Override
    public int getCount() {
        return newsList == null ? 0 : newsList.size();
    }

    @Override
    public Object getItem(int position) {
        if (newsList != null && newsList.size() != 0) {
            return newsList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            holder = new  ViewHolder();
            convertView = inflater.inflate(R.layout.item,null);
            holder.tv_num = (TextView) convertView.findViewById(R.id.tv_num);
            holder.tv_dec = (TextView) convertView.findViewById(R.id.tv_dec );
            holder.tv_name = (TextView) convertView.findViewById(R.id.tv_name );
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Good model = newsList.get(position);
        holder.tv_num.setText(model.getNum()+"升级：:"+model.getEextraCoumen());
        holder.tv_dec.setText(model.getgDes());
        holder.tv_name.setText(model.getgName());


        return convertView;
    }
    class ViewHolder {
        TextView tv_num;
        TextView tv_dec,tv_name;
    }


    public void updateAdapterData(List<Good> list) {
        this.newsList = list;
        notifyDataSetChanged();
    }


}
