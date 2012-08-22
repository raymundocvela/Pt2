
/*
 * Establecera la configuración inicial del dispositivo
 * y la mandará al ws
 */
package net.igconsultores.raymundo.pt2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class ConfActivity extends Activity {
	public int progresssb;
	public String wsGetDataUrl="http://igconsultores.net/raymundo/wsgetdata.php";
	public SharedPreferences prefs;
	public String usr, usrUpdate,desc, descUpdate,inst, instUpdate;
	
	/**
	 * @see android.app.Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// TODO Put your code here
		setContentView(R.layout.conf);
		
		final EditText etUsr=(EditText) findViewById(R.id.confUsr_editText1);
		final EditText etDesc=(EditText) findViewById(R.id.confDesc_editText2);
		final EditText etComp=(EditText) findViewById(R.id.confComp_editText);
		etComp.requestFocus();
		final TextView tvMues= (TextView) findViewById(R.id.confMues_textView5);
		final SeekBar sbMues=(SeekBar) findViewById(R.id.confMues_seekBar1);
		
		prefs=getSharedPreferences("datos", Context.MODE_WORLD_WRITEABLE);
		progresssb=prefs.getInt(Constantes.keyMuestreo, 0);
		sbMues.setProgress(progresssb);
		tvMues.setText(progresssb +"min");
		final Button btnAcep=(Button) findViewById(R.id.confAcep_button1);
		
		final String existUsr=prefs.getString("usr", "sin dato").toString();
		if(prefs.getBoolean("update", false)==true){
			
		}
		
		
		if(!existUsr.equals("sin dato")){
			etUsr.setText(prefs.getString("usr", "sin dato").toString());
			etDesc.setText(prefs.getString("desc", "sin dato").toString());
			etComp.setText(prefs.getString("comp", "sin dato").toString());
			tvMues.setText(progresssb+"min");
			sbMues.setProgress(prefs.getInt(Constantes.keyMuestreo, 0));
		}
		
		
		sbMues.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				progresssb=sbMues.getProgress()+1;
				// TODO Auto-generated method stub
				tvMues.setText(Integer.toString(progress+1)+"min");
			}
		});
		
		btnAcep.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String usr=etUsr.getText().toString();
				String desc=etDesc.getText().toString();
				String comp=etComp.getText().toString();
				
						
				//if(etUsr.getText().toString().equals("")||etDesc.getText().toString().equals("")){
				if(usr.equals("")||desc.equals("")||comp.equals("")){
					Toast toast = Toast.makeText(ConfActivity.this, "Todos los datos son obligatorios",Toast.LENGTH_LONG);
					toast.show();
				}
				else{
					//Si es una actualizacion

					
					
					
					SharedPreferences prefs= getSharedPreferences(Constantes.prefsName, Context.MODE_WORLD_WRITEABLE);
					SharedPreferences.Editor editor =prefs.edit();
					//Bundle bundle=getIntent().getExtras();
					//String psw=bundle.getString("psw");
					//editor.putInt("psw",Integer.parseInt(psw));
//PONER ESTO EN EL IF 					
					editor.putString("usr",usr);
					editor.putString("desc",desc);
					editor.putString("comp",comp);
					//progresssb=sbMues.getProgress();
					editor.putInt("mues",progresssb);
					editor.putString("bestProv","GPS_PROVIDER");
					editor.commit();
					Log.d("prefs", "preferencias guardadas-progressb"+progresssb);
					String psw=prefs.getString("psw", "sindato");
					Log.e("sendData",usr+"-"+comp+"-"+desc+"-"+Integer.toString(progresssb)+"-"+psw);
					String responsePhp=sendData(usr, comp, desc, Integer.toString(progresssb),psw);
					Log.e("responsePhp",responsePhp);
					
					if(responsePhp.contains("_1")){
						finish();
						Intent intMain = new Intent(ConfActivity.this, MainActivity.class);
						startActivity(intMain);	
					}
					else{
						etUsr.setBackgroundColor(Color.RED);
						etUsr.requestFocus();
						Toast.makeText(ConfActivity.this, "El nombre de usuario "+usr+" ya existe, intenta con otro o revisa tu conexión de internet ", Toast.LENGTH_LONG).show();
						Log.d("responsePhp","usuario no insertado"+responsePhp);
					}
					
				}

			}
		});
		
	}//onCreate
	
	
	public String sendData(String usr, String comp, String desc, String mues, String psw){
		HttpClient httpClient= new DefaultHttpClient();
		HttpPost httpPost=new HttpPost(wsGetDataUrl);
		InputStream is=null;
		String responsePhp="";
		try{
			//Datos a enviar
			List<NameValuePair> nvp= new ArrayList<NameValuePair>();
			nvp.add(new BasicNameValuePair("usr", usr));
			nvp.add(new BasicNameValuePair("comp", comp));
			nvp.add(new BasicNameValuePair("desc", desc));
			nvp.add(new BasicNameValuePair("mues", mues));
			nvp.add(new BasicNameValuePair("psw", psw));
			
			httpPost.setEntity(new UrlEncodedFormEntity(nvp));
			
			//Si responde, ejecutamos
			HttpResponse httpResponse=httpClient.execute(httpPost);
			//obtenemos respusta
			HttpEntity httpEntity=httpResponse.getEntity();
			is=httpEntity.getContent();
						
		}catch (ClientProtocolException e) {
Log.e("webservice","ClientProtocol"+e.toString());			// TODO: handle exception
		}catch (IOException e) {
			// TODO: handle exception
Log.e("webservice","ioException"+e.toString());			
		}
		
		//convertimos respuesta a string
		try{
			BufferedReader bf=new BufferedReader
					(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb=new StringBuilder();
			String line=null;
			while((line=bf.readLine())!=null){
				sb.append(line+"\n");
			}
			is.close();
			responsePhp=sb.toString();
		}catch (Exception e) {
			// TODO: handle exception
Log.e("responsePhp",e.toString());			
		}
		return responsePhp;
	}
	
	
	
}//fin
