package mlc.tankebab;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        llamarLogin();
    }

    private void llamarLogin() {
        new android.os.Handler().postDelayed(
            new Runnable() {
                public void run() {
                    Intent a = new Intent(getBaseContext(),Login.class);
                    startActivity(a);
                    finish();
                }
            }, 2000);

    }
}
