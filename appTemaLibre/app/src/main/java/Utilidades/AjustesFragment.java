package Utilidades;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;
import com.example.apptemalibre.R;

public class AjustesFragment extends PreferenceFragmentCompat {

    public AjustesFragment () {

    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.preferencias, rootKey);
    }

}