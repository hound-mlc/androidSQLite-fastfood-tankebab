package mlc.tankebab;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import mlc.tankebab.dao.DAOUsuarios;
import mlc.tankebab.src.Usuario;

public class Login extends AppCompatActivity {

    EditText userTxt, passTxt;
    CheckBox chkbox;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        final SharedPreferences sharedPreferences = getSharedPreferences("LoginPrefered", Context.MODE_PRIVATE);
        String usuario="", pass="";
        if (sharedPreferences != null) {
            usuario = sharedPreferences.getString("usuario","");
            if (!usuario.equals("")){
                pass = sharedPreferences.getString("pass","");
            }
        }
        setContentView(R.layout.login);

        userTxt = findViewById(R.id.editText2);
        passTxt = findViewById(R.id.editText);
        chkbox = findViewById(R.id.checkBox);

        if (!usuario.equals("") && !pass.equals("")){
            userTxt.setText(usuario);
            passTxt.setText(pass);
            chkbox.setChecked(true);
        }

        Button btnIntro = findViewById(R.id.button);
        btnIntro.setOnClickListener(v -> {

            Usuario user = new Usuario(userTxt.getText().toString(),passTxt.getText().toString());
            if (DAOUsuarios.getInstance(getApplicationContext()).buscarUsuario(user)) {

                if (chkbox.isChecked()){
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usuario", userTxt.getText().toString());
                    editor.putString("pass", passTxt.getText().toString());
                    editor.commit();
                }
                else {
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("usuario", "");
                    editor.putString("pass", "");
                    editor.commit();
                }

                Intent i = new Intent(getApplicationContext(),MenuInicial.class);
                i.putExtra("username",user.getUsuario());
                startActivity(i);
            }
            else Toast.makeText(getBaseContext(),"Usuario Erroneo",Toast.LENGTH_LONG).show();
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("SALIR")
                .setMessage("¿Está seguro que quiere cerrar la aplicación?.")
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
