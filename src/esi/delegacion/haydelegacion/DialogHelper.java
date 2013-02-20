package esi.delegacion.haydelegacion;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

public class DialogHelper {
	
	public static AlertDialog noConnectionDialog(Context context){
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle("Sin conexi�n");
		builder.setMessage("No hay conexi�n a internet, revise que tenga buena cobertura o este conectado correctamente a la se�al de WiFi");

		builder.setPositiveButton("OK", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();

			}
		});

		return builder.create();
	}
	
	
}
