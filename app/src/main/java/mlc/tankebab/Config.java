package mlc.tankebab;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class Config extends AppCompatActivity {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String username = getIntent().getStringExtra("username");
        setContentView(R.layout.lay_config);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.layconst, new FragmentPreferencias())
                .commit();

    }

}
