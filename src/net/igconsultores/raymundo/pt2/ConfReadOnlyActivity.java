package net.igconsultores.raymundo.pt2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ConfReadOnlyActivity extends Activity {
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.confreadonly);
		final EditText etUsr=(EditText) findViewById(R.id.confRo_editText1Usr);
		final EditText etDesc=(EditText) findViewById(R.id.confRo_editText2Desc);
		final EditText etComp=(EditText) findViewById(R.id.confRo_editText3Comp);
		final TextView tvTime=(TextView) findViewById(R.id.confRo_textView6time);
		final Button btnEdit=(Button) findViewById(R.id.confRo_button1Edit);
		final Button btnDelPrefs=(Button) findViewById(R.id.button1_confRo_DelPrefs);
		
		
		final SharedPreferences prefs=getSharedPreferences("datos", Context.MODE_WORLD_WRITEABLE);
		etUsr.setText(prefs.getString("usr", "sin dato").toString());
		etDesc.setText(prefs.getString("desc", "sin dato").toString());
		etComp.setText(prefs.getString("comp", "sin dato").toString());
		tvTime.setText(prefs.getInt(Constantes.keyMuestreo, 0)+"min");
		
		//tvTime.setText(prefs.getString("mues", null));
		/*
		 * Establecer meotodo para poder actualizar los datos del dispositivo, user2 compañia2				
		 */
		
		
		btnEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
				Intent intConfW=new Intent(ConfReadOnlyActivity.this,ConfActivity.class);
				startActivity(intConfW);
			}
		});
		
		
		btnDelPrefs.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		        
		      prefs.edit().clear().commit();
		      finish();
			}
		});
		
	}
}
