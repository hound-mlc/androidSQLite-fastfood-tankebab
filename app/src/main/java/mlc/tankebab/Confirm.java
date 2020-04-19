package mlc.tankebab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;

import mlc.tankebab.dao.DAOKebab;
import mlc.tankebab.dao.DAOUsuarios;
import mlc.tankebab.src.Kebab;

public class Confirm extends SuperApp {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        Kebab chosen = (Kebab)getIntent().getSerializableExtra("chosen");
        setContentView(R.layout.lay_confirm);
        ConstraintLayout cl = findViewById(R.id.clConfirm);
        cambiarFondo(cl, username);

        TextView txtNombre = findViewById(R.id.textView3);
        TextView txtIngredientes = findViewById(R.id.textView7);
        TextView txtPrecio = findViewById(R.id.textView6);

        txtNombre.setText(chosen.getNombre());

        if (chosen.getIngredientes().length()!=0){
            if (chosen.getNombre().equals("PERSONALIZADO")) txtIngredientes.setText(chosen.getTipo()+" "+chosen.getCarne()+" "+chosen.getIngredientes());
            else txtIngredientes.setText(chosen.getCarne()+" "+chosen.getIngredientes());
        }
        else {
            txtIngredientes.setText(chosen.getTipo()+" "+chosen.getCarne()+" SOLO CARNE.");
        }
        txtPrecio.setText(chosen.getPrecio()+"€");

        Button btnConfirm = findViewById(R.id.button6);
        btnConfirm.setOnClickListener(lmb -> {
                SharedPreferences sharedPreferences = getSharedPreferences("favActivated", Context.MODE_PRIVATE);

                boolean activado = sharedPreferences.getBoolean(username,false);

                if (activado){
                    DAOUsuarios.getInstance(getApplicationContext()).setKebabFavorito(new Gson().toJson(chosen), username);
                }

                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("PEDIDO REALIZADO")
                        .setMessage("Le llegará a domicilio en 30 minutos.")
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getApplicationContext(), MenuInicial.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                intent.putExtra("username",username);
                                startActivity(intent);
                            }
                        })
                        .show();
        });
    }


}
