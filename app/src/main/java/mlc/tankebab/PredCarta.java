package mlc.tankebab;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;

import mlc.tankebab.dao.DAOKebab;
import mlc.tankebab.src.Kebab;

public class PredCarta extends SuperApp {

    Kebab chosen;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        setContentView(R.layout.pred_carta);
        ConstraintLayout cl = findViewById(R.id.clCarta);
        cambiarFondo(cl,username);

        ArrayAdapter<Kebab> adp = new ArrayAdapter<Kebab>(this, android.R.layout.simple_selectable_list_item, DAOKebab.getInstance(getApplicationContext()).getListaKebab());
        ListView lv = findViewById(R.id.listCarta);
        lv.setAdapter(adp);

        LinearLayout ll = findViewById(R.id.llDetails);
        ll.setVisibility(View.INVISIBLE);



        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (ll.getVisibility() == View.INVISIBLE) ll.setVisibility(View.VISIBLE);

                TextView tvNombre = findViewById(R.id.textView4);
                TextView tvIngredientes = findViewById(R.id.textView5);
                ImageView imgKebab = findViewById(R.id.imageView9);

                String busqueda = lv.getItemAtPosition(position).toString().substring(0, lv.getItemAtPosition(position).toString().indexOf('-')).trim();

                //chosen = DAOKebab.getInstance(getApplicationContext()).getKebab(busqueda);
                chosen = (Kebab)lv.getItemAtPosition(position);
                if (chosen != null){
                    tvNombre.setText(chosen.getNombre());
                    tvIngredientes.setText(chosen.getCarne()+", "+chosen.getIngredientes());
                    imgKebab.setImageResource(chosen.getPic());
                }
            }
        });

        Button btnPedir = findViewById(R.id.button3);
        btnPedir.setOnClickListener(v -> {
            if (chosen != null){
                Intent i = new Intent(getApplicationContext(), Confirm.class);
                i.putExtra("username",username);
                i.putExtra("chosen",chosen);
                startActivity(i);
            }
            else {
                new AlertDialog.Builder(this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("PEDIDO VACÍO")
                        .setMessage("Debe elegir un producto para el pedido.")
                        .setPositiveButton("OK", null)
                        .show();
            }
        });
    }
}
