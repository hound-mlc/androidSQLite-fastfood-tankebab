package mlc.tankebab;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.gson.Gson;

import mlc.tankebab.dao.DAOKebab;
import mlc.tankebab.dao.DAOUsuarios;
import mlc.tankebab.src.Kebab;

public class Fav extends SuperApp {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        setContentView(R.layout.lay_fav);
        ConstraintLayout cl = findViewById(R.id.clFav);
        cambiarFondo(cl, username);


        String kb = DAOUsuarios.getInstance(getApplicationContext()).getKebabFavorito(username);
        Kebab fav = new Gson().fromJson(kb,Kebab.class);

        TextView txtNP = findViewById(R.id.textView8);
        TextView txtIng = findViewById(R.id.textView9);

        txtNP.setText(fav.getNombre()+"-"+fav.getPrecio()+"€");

        if (fav.getIngredientes().length()!=0){
            if (fav.getNombre().equals("PERSONALIZADO")) txtIng.setText(fav.getTipo()+" "+fav.getCarne()+", "+fav.getIngredientes());
            else txtIng.setText(fav.getCarne()+", "+fav.getIngredientes());
        }
        else {
            txtIng.setText(fav.getTipo()+" "+fav.getCarne()+" SOLO CARNE.");
        }

        Button btnPedir = findViewById(R.id.button7);
        btnPedir.setOnClickListener(v -> {
            Intent i = new Intent(getApplicationContext(), Confirm.class);
            i.putExtra("username",username);
            i.putExtra("chosen",fav);
            startActivity(i);
        });


    }
}
