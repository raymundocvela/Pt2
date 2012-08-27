package net.igconsultores.raymundo.pt2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;


public class SplashActivity extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//eliminar pantalla titulo app
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//Quitamos barra de notificaciones
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		/*Comprobaremos si es la 1era vez
		 * que se ejecuta la aplicación
		 * en el dispositivo mediante el uso de las preferencias
		 *
		 */
		final SharedPreferences prefs = getSharedPreferences("datos",Context.MODE_PRIVATE);
		//prefs.edit().clear().commit();
		TimerTask tmTsk=new TimerTask() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				//if(!existe||prefs.getInt("psw", 9999)==1234){
				if(prefs.contains("psw")&&prefs.contains("usr")){
					finish();
					Intent pswGetInt = new Intent().setClass(SplashActivity.this, PswGetActivity.class);
					startActivity(pswGetInt);

/*					Intent mainInt = new Intent().setClass(SplashActivity.this, MainActivity.class);
					startActivity(mainInt);*/
				}
				else {
					// Muestras pantalla bienvenida
					finish();
					Intent bienvInt = new Intent().setClass(SplashActivity.this, BienvActivity.class);
					startActivity(bienvInt);
				}
			}
		};

		Timer tm = new Timer();
		tm.schedule(tmTsk, 2000);//en milisegundos
	}//Fin onCreate
}//fin Activity