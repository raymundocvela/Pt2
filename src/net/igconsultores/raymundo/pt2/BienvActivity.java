package net.igconsultores.raymundo.pt2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class BienvActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.bienv);
		
		final Button contBtn = (Button) 
				findViewById(R.id.bienv_contbtn);
		
		contBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent pswSetIntent = new Intent().setClass
						(BienvActivity.this, PswSetActivity.class);
				finish();
				startActivity(pswSetIntent);
				
			}
		});
		
	}//oncreate
}
