package esi.delegacion.haydelegacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogHelper {
	
	public static AlertDialog noConnectionDialog(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Sin conexión");
		builder.setMessage("No hay conexión a internet, revise que tenga buena cobertura o este conectado correctamente a la señal de WiFi");

		builder.setPositiveButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		return builder.create();
	}
	
	
}
