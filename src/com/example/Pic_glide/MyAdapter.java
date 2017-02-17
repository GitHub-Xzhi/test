package com.example.Pic_glide;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MyAdapter extends BaseAdapter
{
	Context context;
	String[] imageUrls;
	
	public MyAdapter(Context context, String[] imageUrls)
	{
		super();
		this.context = context;
		this.imageUrls = imageUrls;
	}
	class MyView
	{
		ImageView imageView;
		TextView textView;
	}

	public int getCount()
	{
		return imageUrls.length;
	}
	
	public Object getItem(int position)
	{
		// TODO Auto-generated method stub
		return imageUrls[position];
	}
	
	public long getItemId(int position)
	{
		// TODO Auto-generated method stub
		return position;
	}
	public View getView(int position, View convertView, ViewGroup parent)
	{
		MyView mView = null;
		if(convertView == null)
		{
			convertView = LayoutInflater.from(context).inflate(R.layout.item, null);
			mView = new MyView();
			mView.imageView = (ImageView) convertView.findViewById(R.id.imageView1);
			mView.textView = (TextView) convertView.findViewById(R.id.textView1);
			convertView.setTag(mView);
		}
		else
		{
			mView = (MyView)convertView.getTag();
		}
		
		mView.textView.setText("Item " + (position + 1));
		
		Glide.with(context)
	    .load(imageUrls[position])
        .centerCrop()
        .placeholder(R.drawable.ic_stub)
        .crossFade()
        .into(mView.imageView);
		return convertView;
	}
}
