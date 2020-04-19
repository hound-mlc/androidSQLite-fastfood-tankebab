package mlc.tankebab;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public class SuperApp extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    public void cambiarFondo(ConstraintLayout cl, String username){

        SharedPreferences sharedPreferences = getSharedPreferences("fondoChosen", Context.MODE_PRIVATE);
        String imgStr = sharedPreferences.getString(username,"taco");
        Drawable a = getResources().getDrawable(getResources().getIdentifier(imgStr,"drawable",getPackageName()));
        cl.setBackground(a);
    }
}
