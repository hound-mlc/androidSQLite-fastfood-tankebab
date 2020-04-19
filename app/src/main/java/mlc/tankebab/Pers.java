package mlc.tankebab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.ArrayList;

import mlc.tankebab.src.Carne;
import mlc.tankebab.src.Ingrediente;
import mlc.tankebab.src.Kebab;
import mlc.tankebab.src.TipoKebab;

public class Pers extends SuperApp {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        setContentView(R.layout.lay_pers);
        ConstraintLayout cl = findViewById(R.id.clPers);
        cambiarFondo(cl, username);

        ArrayList<CheckBox> arrIng = new ArrayList<CheckBox>();
        rellenarIngredientes(arrIng,cl);

        ArrayList<RadioButton> arrTipo = new ArrayList<RadioButton>();
                arrTipo.add(findViewById(R.id.rbDurum));
                arrTipo.add(findViewById(R.id.rbDoner));
                arrTipo.add(findViewById(R.id.rbLahma));

        ArrayList<RadioButton> arrCarne = new ArrayList<RadioButton>();
                arrCarne.add(findViewById(R.id.rbPollo));
                arrCarne.add(findViewById(R.id.rbTernera));
                arrCarne.add(findViewById(R.id.rbMixto));

        ponerTextosTipos(arrTipo);
        ponerTextosCarne(arrCarne);

        Button btnPedir = findViewById(R.id.button8);
        btnPedir.setOnClickListener(v -> {

            RadioGroup rgTipo = findViewById(R.id.rgTipo);

            RadioGroup rgCarne = findViewById(R.id.rgCarne);

            Kebab pers = new Kebab(getTipoFromRG(rgTipo),getCarneFromRG(rgCarne),getIngredientesFromList(arrIng));

            Intent i = new Intent(getApplicationContext(), Confirm.class);
            i.putExtra("username",username);
            i.putExtra("chosen",pers);
            startActivity(i);

        });
    }

    public void rellenarIngredientes(ArrayList<CheckBox> arrIng, ConstraintLayout cl){
        int cont=0;
        for (int i = 0; i<cl.getTouchables().size();i++){
            if (cl.getTouchables().get(i) instanceof CheckBox){
                String txt = Ingrediente.values()[cont].toString();
                ((CheckBox) cl.getTouchables().get(i)).setText(txt);
                arrIng.add((CheckBox) cl.getTouchables().get(i));
                cont++;
            }
        }
    }

    public void ponerTextosCarne(ArrayList<RadioButton> arrCarne){
        for (int i=0; i<arrCarne.size();i++){
            arrCarne.get(i).setText(Carne.values()[i].toString());
        }
    }

    public void ponerTextosTipos(ArrayList<RadioButton> arrTipo){
        for (int i=0; i<arrTipo.size();i++){
            arrTipo.get(i).setText(TipoKebab.values()[i].toString());
        }
    }

    public String getIngredientesFromList(ArrayList<CheckBox> arrIng) {
        ArrayList<Ingrediente> arrIngChosen = new ArrayList<Ingrediente>();
        arrIng.stream().forEach(e ->{
            if (e.isChecked()) arrIngChosen.add(Ingrediente.valueOf(e.getText().toString()));
        });

        String ult = "";
        for (int i=0; i<arrIngChosen.size();i++){
            ult += "\n"+arrIngChosen.get(i).toString();
        }
        return ult;
    }

    public Carne getCarneFromRG(RadioGroup rgCarne){
        RadioButton selectedCarne = findViewById(rgCarne.getCheckedRadioButtonId());
        Carne c = Carne.valueOf(selectedCarne.getText().toString());
        return c;
    }

    public TipoKebab getTipoFromRG(RadioGroup rgTipo){
        RadioButton selectedTipo = findViewById(rgTipo.getCheckedRadioButtonId());
        TipoKebab t = TipoKebab.valueOf(selectedTipo.getText().toString());
        return t;
    }
}
