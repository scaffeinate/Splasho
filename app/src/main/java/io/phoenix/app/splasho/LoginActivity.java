package io.phoenix.app.splasho;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by sudharti on 10/21/17.
 */

public class LoginActivity extends AppCompatActivity {

    private static final String CONSUMER_TOKEN = "consumer_token";
    private static final String SKIP_LOGIN = "skip_login";
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        if (mSharedPreferences.getBoolean(SKIP_LOGIN, false) ||
                mSharedPreferences.getString(CONSUMER_TOKEN, null) != null) {
            skipLoginScreen();
        }
    }

    public void onLoginWithUnsplash(View view) {

    }

    public void onSkipLogin(View view) {
        mEditor.putBoolean(SKIP_LOGIN, true);
        mEditor.commit();
        skipLoginScreen();
    }

    private void skipLoginScreen() {
        Intent mainIntent = new Intent(this, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }
}
