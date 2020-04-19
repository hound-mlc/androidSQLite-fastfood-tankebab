package mlc.tankebab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import mlc.tankebab.R;
import mlc.tankebab.src.Carne;
import mlc.tankebab.src.Ingrediente;
import mlc.tankebab.src.Kebab;
import mlc.tankebab.src.TipoKebab;
import mlc.tankebab.src.Usuario;

public class DBConnection extends SQLiteOpenHelper {

    private static final String nombreDB="tankebab";
    private static final int VERSION=1;

    private static DBConnection dao = null;

    private DBConnection(@Nullable Context context) {
        super(context, nombreDB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS KEBAB (NOMBRE_KEBAB TEXT PRIMARY KEY, TIPO_KEBAB TEXT NOT NULL, CARNE TEXT NOT NULL, INGREDIENTES TEXT NOT NULL, PRECIO DECIMAL(3,2) NOT NULL, PIC INT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS USUARIO (NOMBRE TEXT PRIMARY KEY, PASS TEXT NOT NULL, KEBAB_FAV TEXT DEFAULT '')");
        insertarKebab(db, new Kebab(TipoKebab.DURUM, Carne.POLLO, R.drawable.durum_pollo));
        insertarKebab(db, new Kebab(TipoKebab.DURUM, Carne.TERNERA, R.drawable.durum_ternera));
        insertarKebab(db, new Kebab(TipoKebab.DURUM, Carne.MIXTO, R.drawable.durum_mixto));
        insertarKebab(db, new Kebab(TipoKebab.DONER, Carne.POLLO, R.drawable.doner_pollo));
        insertarKebab(db, new Kebab(TipoKebab.DONER, Carne.TERNERA, R.drawable.doner_ternera));
        insertarKebab(db, new Kebab(TipoKebab.DONER, Carne.MIXTO, R.drawable.doner_mixto));
        insertarKebab(db, new Kebab(TipoKebab.LAHMACUN, Carne.POLLO, R.drawable.lahmacun_pollo));
        insertarKebab(db, new Kebab(TipoKebab.LAHMACUN, Carne.TERNERA, R.drawable.lahmacun_ternera));
        insertarKebab(db, new Kebab(TipoKebab.LAHMACUN, Carne.MIXTO, R.drawable.lahmacun_mixto));
        insertarKebab(db, new Kebab(TipoKebab.DURUM, Carne.POLLO, Ingrediente.QUESO_FETA+","+Ingrediente.SALSA_YOGURT+","+Ingrediente.SALSA_PICANTE+","+Ingrediente.PATATAS, "De la casa"));
        insertarUsuario(db, new Usuario("luis","profe"));
        insertarUsuario(db, new Usuario("manu","yo"));
        insertarUsuario(db, new Usuario("pepelu","lol"));

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE KEBAB");
        db.execSQL("DROP TABLE USUARIO");
        onCreate(db);
    }

    private void insertarKebab(SQLiteDatabase db, Kebab k){
        ContentValues cv = new ContentValues();
        cv.put("NOMBRE_KEBAB",k.getNombre());
        cv.put("TIPO_KEBAB",k.getTipo().toString());
        cv.put("CARNE",k.getCarne().toString());
        cv.put("INGREDIENTES",k.getIngredientes());
        cv.put("PRECIO",k.getPrecio());
        cv.put("PIC",k.getPic());
        db.insert("KEBAB",null,cv);
    }

    private void insertarUsuario(SQLiteDatabase db, Usuario u){
        ContentValues cv = new ContentValues();
        cv.put("NOMBRE",u.getUsuario());
        cv.put("PASS",u.getPass());
        cv.put("KEBAB_FAV",u.getKebab_fav());
        db.insert("USUARIO",null,cv);
    }

    public static DBConnection getInstance(Context c){
        if (dao == null) dao = new DBConnection(c);
        return dao;
    }
}
