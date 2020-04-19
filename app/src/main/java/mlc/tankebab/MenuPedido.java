package mlc.tankebab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.constraintlayout.widget.ConstraintLayout;

import mlc.tankebab.dao.DAOUsuarios;

public class MenuPedido extends SuperApp {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        setContentView(R.layout.menu_pizzas);
        ConstraintLayout cl = findViewById(R.id.clMenu);
        cambiarFondo(cl,username);

        ImageView btnFav = findViewById(R.id.imageView3);
        ImageView btnPred = findViewById(R.id.imageView4);
        ImageView btnPers = findViewById(R.id.imageView6);

        btnPred.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), PredCarta.class);
            i.putExtra("username",username);
            startActivity(i);
        });

        btnPers.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Pers.class);
            i.putExtra("username",username);
            startActivity(i);
        });

        SharedPreferences sharedPreferences = getSharedPreferences("favActivated", Context.MODE_PRIVATE);


        Boolean favActivated = sharedPreferences.getBoolean(username,false);
        if (favActivated == false || DAOUsuarios.getInstance(getApplicationContext()).getKebabFavorito(username).equals("")) btnFav.setImageResource(R.drawable.cv_fav_disable);
        else {
            btnFav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(getApplicationContext(), Fav.class);
                    i.putExtra("username",username);
                    startActivity(i);
                }
            });
        }



    }

}
