package net.igconsultores.raymundo.pt2;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;


public class SplashActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //eliminar pantalla titulo app
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        
        setContentView(R.layout.splash);
        
        /*Comprobaremos si es la 1era vez
         * que se ejecuta la aplicación
         * en el dispositivo mediante el uso de las preferencias
         *
        */
        
        final SharedPreferences prefs = getSharedPreferences("datos",Context.MODE_PRIVATE);
//prefs.edit().clear().commit();
        
        final boolean existe = prefs.contains("psw");
//Toast toast = Toast.makeText(SplashActivity.this,"existe" +existe,Toast.LENGTH_LONG);
//toast.show();
        TimerTask tmTsk=new TimerTask() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				//if(!existe||prefs.getInt("psw", 9999)==1234){
				if(!existe){
		            // Muestras el diágolo de petición de datos
		        	finish();
					Intent bienvInt = new Intent().setClass(SplashActivity.this, BienvActivity.class);
		        	startActivity(bienvInt);
		        }
		        else {
		        	
		        	finish();
		        	
		        	//Establecer psw ADMIN
		        	
//		        	Intent pswGetInt = new Intent().setClass(SplashActivity.this, PswGetActivity.class);
//		        	startActivity(pswGetInt);
		        	
		        	
		        	//para fines practicos omitiremos el pedir contraseña en fase de pruebas
		        	Intent mainInt = new Intent().setClass(SplashActivity.this, MainActivity.class);
					startActivity(mainInt);
		        	
				}

			}
		};
        
        Timer tm = new Timer();
        tm.schedule(tmTsk, 1500);
         
    }//Fin onCreate
}//fin Activity