package net.igconsultores.raymundo.pt2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PswSetActivity extends Activity {
	String psw;
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.pswset);
		
		final EditText etPsw1= (EditText) findViewById(R.id.pswSet_editText1);
		final TextView txtConf= (TextView) findViewById(R.id.pswSetConf_textView3);
		final EditText etPsw2= (EditText) findViewById(R.id.pswSet_editText2);
		final Button BtnCont= (Button) findViewById(R.id.pswSetCont_button1);
		

//Validamos psw		
		etPsw1.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				psw= etPsw1.getText().toString();
				if( etPsw1.length()==4){
//Pasa un error cuando ingresas 0000, solo pone "0"					
					//if(etPsw1.length()==4 && !psw.equals("0000")){
					etPsw1.setBackgroundColor(Color.GREEN);
					//psw= etPsw1.getText().toString();
					txtConf.setVisibility(View.VISIBLE);
					etPsw2.setVisibility(View.VISIBLE);
					etPsw2.requestFocus();
					
				}
				else {
					etPsw1.setBackgroundColor(Color.WHITE);
					etPsw2.setVisibility(View.INVISIBLE);
					txtConf.setVisibility(View.INVISIBLE);
				}
					
			}
		});
		
		etPsw2.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub

			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(etPsw2.length()==4 &&
						psw.equals(etPsw2.getText().toString())){
					etPsw2.setBackgroundColor(Color.GREEN);
					BtnCont.setVisibility(View.VISIBLE);
					BtnCont.requestFocus();
					hKeyb(etPsw2);
				}
				
				else if (etPsw2.length()==4) {			
					etPsw2.setBackgroundColor(Color.RED);
					Toast toast = Toast.makeText
							(PswSetActivity.this, 
									"Las contraseñas no coinciden" +
									", Vuelve a intentarlo"+
									etPsw1.getText()+
									etPsw2.getText(),
									Toast.LENGTH_LONG);
					toast.show();
					etPsw2.setText("");
					etPsw2.requestFocus();
				}
				else etPsw2.setBackgroundColor(Color.WHITE);
			}
		});
		
		BtnCont.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intConf= new Intent().setClass(PswSetActivity.this, ConfActivity.class);

				SharedPreferences prefs= getSharedPreferences("datos", Context.MODE_WORLD_WRITEABLE);
				SharedPreferences.Editor editor =prefs.edit();
				editor.putString("psw", psw);
				editor.commit();
Log.e("psw","Contraseña establecida"+prefs.getString("psw", null));
				//pasamos el contexto
				//intConf.putExtra("psw", psw);
				finish();
				startActivity(intConf);
												
			}
		});
		
	}//onCreate


	public void hKeyb (View v){
		//Lineas para ocultar el teclado virtual (Hide keyboard)
		InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
	}


}



