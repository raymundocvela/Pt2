package net.igconsultores.raymundo.pt2;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
//		final Button btnEdit=(Button) findViewById(R.id.confRo_button1Edit);
//		final Button btnDelPrefs=(Button) findViewById(R.id.button1_confRo_DelPrefs);
		final SharedPreferences prefs=getSharedPreferences("datos", Context.MODE_WORLD_WRITEABLE);
		etUsr.setText(prefs.getString("usr", "sin dato").toString());
		etDesc.setText(prefs.getString("desc", "sin dato").toString());
		etComp.setText(prefs.getString("comp", "sin dato").toString());
		tvTime.setText(prefs.getInt(Constantes.keyMuestreo, 0)+"min");
				
		//tvTime.setText(prefs.getString("mues", null));
		/*
		 * Establecer metodo para poder actualizar los datos del dispositivo, user2 compañia2				
		 */
		
/*		
		btnEdit.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub				
			}
		});
				
		btnDelPrefs.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub						     
			}
		});
*/
		
	} //fin Oncreate
	
	/**
	 * Inflate Menu from XML
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu_conf_read_only, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {  
		final AlertDialog.Builder builder = new AlertDialog.Builder(ConfReadOnlyActivity.this);
		final AlertDialog alertDialog;
		final SharedPreferences prefs=getSharedPreferences("datos", Context.MODE_WORLD_WRITEABLE);
		switch (item.getItemId()) {  
		case R.id.itemEdit:  			
			builder.setMessage(R.string.confReadAlertEditMsg)
			.setCancelable(false)
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					prefs.edit().putBoolean("update", true);
					finish();
					Intent intConfW=new Intent(ConfReadOnlyActivity.this,ConfActivity.class);
					startActivity(intConfW);				     
				}
			})
			.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// put your code here 
					dialog.cancel();
				}
			});
			alertDialog = builder.create();
			alertDialog.show(); 
			break;
		case R.id.itemDelPrefs: 
			builder.setMessage(R.string.confReadAlertDelMsg)
			.setCancelable(false)
			.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					 prefs.edit().clear().commit();
				     finish();
				     Intent intBienv = new Intent(ConfReadOnlyActivity.this, BienvActivity.class);
				     //intBienv.addFlags(Intent.FLAG_ACTIVITY_);
				     startActivity(intBienv);
				     
				}
			})
			.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					// put your code here 
					dialog.cancel();
				}
			});
			alertDialog = builder.create();
			alertDialog.show();  
			break;
		default:
			// put your code here	  
		}  
		return false;  
	}
	
}//Fin Activity
