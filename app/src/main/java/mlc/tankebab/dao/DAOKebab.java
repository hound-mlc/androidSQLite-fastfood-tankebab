package mlc.tankebab.dao;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mlc.tankebab.R;
import mlc.tankebab.src.Carne;
import mlc.tankebab.src.Ingrediente;
import mlc.tankebab.src.Kebab;
import mlc.tankebab.src.TipoKebab;

public class DAOKebab {

    private static DAOKebab dao = null;
    private ArrayList<Kebab> lista;
    private Context cont;

    private DAOKebab(Context cont){
        this.cont=cont;
        lista = new ArrayList<Kebab>();
        /*
        this.lista.add(new Kebab(TipoKebab.DURUM, Carne.POLLO, R.drawable.durum_pollo));
        this.lista.add(new Kebab(TipoKebab.DURUM, Carne.TERNERA, R.drawable.durum_ternera));
        this.lista.add(new Kebab(TipoKebab.DURUM, Carne.MIXTO, R.drawable.durum_mixto));
        this.lista.add(new Kebab(TipoKebab.DONER, Carne.POLLO, R.drawable.doner_pollo));
        this.lista.add(new Kebab(TipoKebab.DONER, Carne.TERNERA, R.drawable.doner_ternera));
        this.lista.add(new Kebab(TipoKebab.DONER, Carne.MIXTO, R.drawable.doner_mixto));
        this.lista.add(new Kebab(TipoKebab.LAHMACUN, Carne.POLLO, R.drawable.lahmacun_pollo));
        this.lista.add(new Kebab(TipoKebab.LAHMACUN, Carne.TERNERA, R.drawable.lahmacun_ternera));
        this.lista.add(new Kebab(TipoKebab.LAHMACUN, Carne.MIXTO, R.drawable.lahmacun_mixto));
        this.lista.add(new Kebab(TipoKebab.DURUM, Carne.POLLO, Ingrediente.QUESO_FETA+","+Ingrediente.SALSA_YOGURT+","+Ingrediente.SALSA_PICANTE+","+Ingrediente.PATATAS, "De la casa"));*/
    }

    public static DAOKebab getInstance(Context cont){
        if (dao == null) dao = new DAOKebab(cont);
        return dao;
    }

    public ArrayList<Kebab> getListaKebab(){
        SQLiteDatabase db = DBConnection.getInstance(cont).getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM KEBAB",null);

        if (c.moveToFirst()){
            do {
                Kebab k = new Kebab();
                k.setNombre(c.getString(c.getColumnIndex("NOMBRE_KEBAB")));
                k.setTipo(TipoKebab.valueOf(c.getString(c.getColumnIndex("TIPO_KEBAB"))));
                k.setCarne(Carne.valueOf(c.getString(c.getColumnIndex("CARNE"))));
                k.setIngredientes(c.getString(c.getColumnIndex("INGREDIENTES")));
                k.setPrecio(c.getFloat(c.getColumnIndex("PRECIO")));
                k.setPic(c.getInt(c.getColumnIndex("PIC")));
                lista.add(k);
            } while (c.moveToNext());
        }
        return this.lista;
    }
}
