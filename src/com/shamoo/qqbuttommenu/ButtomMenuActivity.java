package com.shamoo.qqbuttommenu;

import com.shamoo.qqbuttommenu.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;

public class ButtomMenuActivity extends Activity {
	FrameLayout fmpan,fm;
	
    ImageView image;
    PopupWindow popup;
	
    public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tab);	
		initView();
		
		fm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				image.setImageResource(R.drawable.toolbar_plusback);
				showWindow(fmpan);
		    }
		});
	}

	private void initView() {
		fmpan = (FrameLayout)findViewById(R.id.tab1);
		fm = (FrameLayout)findViewById(R.id.btn_ck);
		image = (ImageView)findViewById(R.id.image1);
		
	}
	
	private void showWindow(View parent) {  
		if(popup == null) {  
			LayoutInflater layoutInflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);  
			View view = layoutInflater.inflate(R.layout.write_tab, null);
			Log.i("TAG", LayoutParams.WRAP_CONTENT+"");
			
	        // 创建一个PopuWidow对象  
			popup = new PopupWindow(view,this.getWindowManager().getDefaultDisplay().getWidth(),415);
			
			// 设置焦点在弹窗上  
			popup.setFocusable(true);  
			// 设置允许在外点击消失  
			popup.setOutsideTouchable(true);
			
			// 设置弹窗消失事件监听
			popup.setOnDismissListener(new OnDismissListener() {
				public void onDismiss() {
					// TODO Auto-generated method stub
					image.setImageResource(R.drawable.toolbar_plus);
				}
			});
			// 这个是为了点击“返回Back”也能使其消失，并且并不会影响你的背景  
			popup.setBackgroundDrawable(new BitmapDrawable());
			popup.setTouchInterceptor(new OnTouchListener() {  
				public boolean onTouch(View view, MotionEvent event) {  
					if(event.getAction() == MotionEvent.ACTION_OUTSIDE) {  
						popup.dismiss();
		    	  		image.setImageResource(R.drawable.toolbar_plus);     	    
		    	  		return true;  
		    	  	}  
					return false;  
				}  
			});
		}
		if(!popup.isShowing()) {
			///设置显示PopupWindow的位置位于View的左下方，x,y表示坐标偏移量
			popup.showAsDropDown(parent, Gravity.CENTER, -10);
		}
	}  
}