package mlc.tankebab;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

public class MenuInicial extends SuperApp {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        setContentView(R.layout.menu_inicial);
        ConstraintLayout cl = findViewById(R.id.miCl);
        cambiarFondo(cl,username);
        Button btnWeb = findViewById(R.id.button2);
        btnWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("http://diasdeestambul.es/"));
                startActivity(i);
            }
        });

        Button btnPedido = findViewById(R.id.button4);
        btnPedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), MenuPedido.class);
                i.putExtra("username",username);
                startActivity(i);
            }
        });

        Button btnConfig = findViewById(R.id.button5);
        btnConfig.setOnClickListener(e -> {
            Intent i = new Intent(getApplicationContext(),Config.class);
            i.putExtra("username",username);
            startActivity(i);
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("CERRAR SESIÓN")
                .setMessage("Si continúa, cerrará sesión.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
