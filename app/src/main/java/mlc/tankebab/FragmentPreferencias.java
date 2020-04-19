package mlc.tankebab;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.preference.ListPreference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.SwitchPreferenceCompat;

import java.util.List;

public class FragmentPreferencias extends PreferenceFragmentCompat {

    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.config, rootKey);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("favActivated", Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getActivity().getSharedPreferences("fondoChosen", Context.MODE_PRIVATE);
        String username = getActivity().getIntent().getStringExtra("username");

        final SharedPreferences.Editor editorFav = sharedPreferences.edit();
        final SharedPreferences.Editor editorFondo = sharedPreferences1.edit();

        SwitchPreferenceCompat swFav = (SwitchPreferenceCompat) findPreference("fav");
        ListPreference lpBack = (ListPreference) findPreference("fondo");

        lpBack.setValue(sharedPreferences1.getString(username,"taco"));
        swFav.setChecked(sharedPreferences.getBoolean(username,false));

        swFav.setOnPreferenceChangeListener((preference, newValue) -> {
            editorFav.putBoolean(username,(Boolean)newValue);
            editorFav.commit();
            return true;
        });

        lpBack.setOnPreferenceChangeListener((preference, newValue) -> {
            editorFondo.putString(username, newValue.toString());
            editorFondo.commit();
            System.exit(0);
            return true;
        });
    }


}
