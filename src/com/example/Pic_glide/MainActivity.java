
package com.example.Pic_glide;

import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.app.Activity;
import android.database.Cursor;
import android.util.Log;
import android.view.Menu;
import android.widget.ListView;


public class MainActivity extends Activity
{
	MyAdapter adapter;
	ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		listView = (ListView)findViewById(R.id.listView1);
		adapter = new MyAdapter(this, mm());
		listView.setAdapter(adapter);
	}
	
	public String[] mm()
	{
		String[] imageUrls;
		ArrayList<String> listImgPath = getImgPathList();
		//list转换成数组
		imageUrls = (String[]) listImgPath.toArray(new String[listImgPath
				.size()]);
		for (int i = 0; i < imageUrls.length; i++) {
			imageUrls[i] = "file://" + imageUrls[i];
		}
		return imageUrls;
		
		// 配置图片加载及显示选项（还有一些其他的配置，查阅doc文档吧）
		//是你
	}
	
	/**
	 * 
	 * 扫描sd卡获取本地图片地址列表
	 * 
	 * @return list String类型存储图片地址
	 */
	private ArrayList<String> getImgPathList() {
		
		//selection: 指定查询条件
		String selection = MediaStore.Images.Media.DATA + " like ?";
		//设定查询目录
		String path=Environment.getExternalStorageDirectory()+"/SmartClass1/";
		//定义selectionArgs：
		String[] selectionArgs = {path+"%"};
		
		ArrayList<String> list = new ArrayList<String>();
		Cursor cursor = getContentResolver().query(
				MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
		new String[] { "_id", "_data" }, selection, selectionArgs, null);
		while (cursor.moveToNext()) {
			list.add(cursor.getString(1));// 将图片路径添加到list中
			Log.i("", ">>LL:"+cursor.getString(1).toString());
		}
		cursor.close();
		return list;
	}
}
