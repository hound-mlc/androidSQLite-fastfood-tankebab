package mlc.tankebab.src;

import android.service.autofill.FillEventHistory;

import java.io.Serializable;
import java.util.Arrays;

import mlc.tankebab.R;
import mlc.tankebab.src.Ingrediente;
import mlc.tankebab.src.TipoKebab;

public class Kebab implements Serializable {

    private TipoKebab tipo;
    private float precio;
    private String ingredientes;
    private Carne carne;
    private String nombre="";
    private int pic;

    public Kebab(){
    }

    public Kebab(TipoKebab tipo, Carne carne, int pic){
        this.tipo=tipo;
        this.precio=4.50f;
        this.carne=carne;
        this.ingredientes = Ingrediente.LECHUGA+","+Ingrediente.VERDURAS+","+Ingrediente.SALSA_YOGURT+","+Ingrediente.SALSA_PICANTE;
        if (carne == Carne.MIXTO){
            this.nombre = tipo+" "+carne;
        }
        else this.nombre = tipo+" DE "+carne;
        this.pic=pic;
    }

    public Kebab(TipoKebab tipo, Carne carne, String ingredientes){
        this.tipo=tipo;
        this.carne=carne;
        this.precio=5.50f;
        this.ingredientes=ingredientes;
        this.nombre="PERSONALIZADO";
        if (this.tipo == TipoKebab.DURUM) this.pic = R.drawable.durum_pers;
        else if (this.tipo == TipoKebab.DONER) this.pic = R.drawable.doner_pers;
        else this.pic = R.drawable.lahmacun_pers;
    }


    public Kebab(TipoKebab tipo, Carne carne, String ingredientes, String nombre){
        this.tipo=tipo;
        this.carne=carne;
        this.precio=5.50f;
        this.ingredientes=ingredientes;
        this.nombre=nombre;
        this.pic = R.drawable.durum_casa;
    }

    public TipoKebab getTipo() {
        return tipo;
    }

    public void setTipo(TipoKebab tipo) {
        this.tipo = tipo;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public Carne getCarne() {
        return carne;
    }

    public void setCarne(Carne carne) {
        this.carne = carne;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return this.nombre +" - "+this.precio+" €";
    }
}
