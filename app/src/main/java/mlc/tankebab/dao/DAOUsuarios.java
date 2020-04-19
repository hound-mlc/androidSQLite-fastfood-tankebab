package mlc.tankebab.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import mlc.tankebab.src.Usuario;

public class DAOUsuarios {

    private static DAOUsuarios dao = null;
    private Context cont;

    private DAOUsuarios(Context cont){
        this.cont=cont;
        /*
        this.lista.add(new Usuario("mut3dd","paquito", ""));
        this.lista.add(new Usuario("pepelu","pepelu",""));
        this.lista.add(new Usuario("roberto","raton",""));
        this.lista.add(new Usuario("luis","profe",""));*/
    }

    public boolean buscarUsuario(Usuario user){
        SQLiteDatabase db = DBConnection.getInstance(cont).getReadableDatabase();
        Cursor c = db.rawQuery("SELECT NOMBRE,PASS FROM USUARIO",null);

        if (c.moveToFirst()){
            boolean res=false;
            do {
                Usuario a = new Usuario();
                a.setUsuario(c.getString(c.getColumnIndex("NOMBRE")));
                a.setPass(c.getString(c.getColumnIndex("PASS")));
                if (a.equals(user)){
                    res=true;
                    break;
                }
            } while(c.moveToNext());
            return res;
        }
        else return false;
    }

    public void setKebabFavorito(String kebabFavorito, String usuario){
        SQLiteDatabase db = DBConnection.getInstance(cont).getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("KEBAB_FAV",kebabFavorito);
        db.update("USUARIO",cv,"NOMBRE='"+usuario+"'",null);
    }

    public String getKebabFavorito(String usuario){
        SQLiteDatabase db = DBConnection.getInstance(cont).getReadableDatabase();
        Cursor c = db.rawQuery("SELECT KEBAB_FAV FROM USUARIO WHERE NOMBRE='"+usuario+"'",null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex("KEBAB_FAV"));

    }

    public static synchronized DAOUsuarios getInstance(Context cont){
        if (dao == null)
            dao = new DAOUsuarios(cont);

        return dao;
    }
}
