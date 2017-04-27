package com.example.ramzanullah.languagetranslator;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.util.Locale;

public class AnytoEnglish extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private TextToSpeech tts2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anyto_english);
        Button btnTranslateSpeech2 = (Button) findViewById(R.id.bTranslateSpeech2);
        Button btnTranslateText2 = (Button) findViewById(R.id.bTranslateText2);

        final EditText metUserText2 = (EditText) findViewById(R.id.etUserText2);
        final TextView mtvTranslatedText2 = (TextView) findViewById(R.id.tvTranslatedText2);

        tts2 = new TextToSpeech(this, this);

        btnTranslateSpeech2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                speakOut(((TextView) findViewById(R.id.tvTranslatedText2)).getText().toString());
            }
        });

        btnTranslateText2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class bgStuff extends AsyncTask<Void, Void, Void> {

                    String translatedText2 = "";

                    @Override
                    protected Void doInBackground(Void... params) {

                        try {

                            String text = metUserText2.getText().toString();
                            translatedText2 = translate(text);
                        } catch (Exception e) {

                            e.printStackTrace();
                            translatedText2 = e.toString();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        mtvTranslatedText2.setText(translatedText2);
                        super.onPostExecute(result);
                    }

                }

                new bgStuff().execute();
            }
        });


    }

    public String translate(String text) throws Exception {

        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
        Translate.setClientId("RAMZAN123ULLAH456"); //Change this
        Translate.setClientSecret("B47r0hFnvF9pqQVdxRPLubqKeDR8L27VV2kkmwtPgsA="); //change


        //String translatedText = Translate.execute(text,Language.fromString(text));

        String translatedText = null;
        translatedText = Translate.execute(text, Language.ENGLISH);


        return translatedText;
    }

    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts2.setLanguage(Locale.ENGLISH);


        } else if (status == TextToSpeech.LANG_MISSING_DATA
                || status == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(getApplicationContext(), "This is not a Supported Voice Language", Toast.LENGTH_SHORT).show();
        }


    }


    private void speakOut(String text) {
        tts2.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }

    public void toAny2(View v1) {
        Intent i21 = new Intent(AnytoEnglish.this, MainActivity.class);
        startActivity(i21);
    }

    public void toEnglish2(View v2) {
        Intent i22 = new Intent(AnytoEnglish.this, AnytoEnglish.class);
        startActivity(i22);
    }


    public void toGoogle2(View v3) {
        Intent i23 = new Intent(AnytoEnglish.this, Google.class);
        startActivity(i23);
    }

    public void quiteActivity(View view) {
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}


