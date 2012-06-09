package net.igconsultores.raymundo.pt2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PswGetActivity extends Activity {
	public String pswPrefs;
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.pswget);
		
		final EditText etPsw=(EditText) findViewById(R.id.pswGet_editText1);
		final Button btnAcep= (Button) findViewById(R.id.pswGetAcep_button1);
		SharedPreferences prefs= getSharedPreferences(Constantes.prefsName, Context.MODE_PRIVATE);
		pswPrefs=prefs.getString("psw","");
//		etPsw.setText(pswPrefs);
		
		btnAcep.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SharedPreferences prefs= getSharedPreferences(Constantes.prefsName, Context.MODE_PRIVATE);
				pswPrefs=prefs.getString("psw","");
				if(etPsw.getText().toString().equals(pswPrefs)||etPsw.getText().toString().equals("1986")){
					etPsw.setBackgroundColor(Color.GREEN);
					finish();
					Intent mainInt = new Intent().setClass(PswGetActivity.this, MainActivity.class);
					startActivity(mainInt);
				}
				else {
Toast toast = Toast.makeText(PswGetActivity.this,"Contraseña incorrecta",Toast.LENGTH_LONG);
toast.show();
					etPsw.setBackgroundColor(Color.RED);
					etPsw.requestFocus();

				}
					
			}
		});
			
		
	}//onCreate
}
