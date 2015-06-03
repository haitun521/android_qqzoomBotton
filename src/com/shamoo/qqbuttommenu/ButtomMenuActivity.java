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
			
	        // ����һ��PopuWidow����  
			popup = new PopupWindow(view,this.getWindowManager().getDefaultDisplay().getWidth(),415);
			
			// ���ý����ڵ�����  
			popup.setFocusable(true);  
			// ����������������ʧ  
			popup.setOutsideTouchable(true);
			
			// ���õ�����ʧ�¼�����
			popup.setOnDismissListener(new OnDismissListener() {
				public void onDismiss() {
					// TODO Auto-generated method stub
					image.setImageResource(R.drawable.toolbar_plus);
				}
			});
			// �����Ϊ�˵��������Back��Ҳ��ʹ����ʧ�����Ҳ�����Ӱ����ı���  
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
			///������ʾPopupWindow��λ��λ��View�����·���x,y��ʾ����ƫ����
			popup.showAsDropDown(parent, Gravity.CENTER, -10);
		}
	}  
}