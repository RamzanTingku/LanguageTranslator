package com.example.ramzanullah.languagetranslator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;

import java.util.Locale;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, TextToSpeech.OnInitListener {

    //String text;
    Language myText;
    Locale myVoice;
    private TextToSpeech tts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // final SwipeRefreshLayout swipeView = (SwipeRefreshLayout) findViewById(R.id.swipe);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Language, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        tts = new TextToSpeech(this, this);
        ((Button) findViewById(R.id.bTranslateSpeech)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                speakOut(((TextView) findViewById(R.id.tvTranslatedText)).getText().toString());
            }
        });


        ((Button) findViewById(R.id.bTranslateText)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                class bgStuff extends AsyncTask<Void, Void, Void> {

                    String translatedText = "";

                    @Override
                    protected Void doInBackground(Void... params) {

                        try {

                            String text = ((EditText) findViewById(R.id.etUserText)).getText().toString();
                            translatedText = translate(text);
                        } catch (Exception e) {

                            e.printStackTrace();
                            translatedText = e.toString();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        ((TextView) findViewById(R.id.tvTranslatedText)).setText(translatedText);
                        super.onPostExecute(result);
                    }

                }

                new bgStuff().execute();
            }
        });



      /* swipeView.setColorScheme(android.R.color.holo_blue_dark, android.R.color.holo_blue_light, android.R.color.holo_green_light, android.R.color.holo_green_dark);
        swipeView.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

            @Override
            public void onRefresh() {

                swipeView.setRefreshing(true);
                ( new Handler()).postDelayed(new Runnable() {

                    @Override
                    public void run() {
                        swipeView.setRefreshing(false);
                    }
                }, 3000);
            }
        }); */

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        // TextView Text = (TextView) view;
        //text = (String) Text.getText();

        switch (position) {

            case 1:
                myText = Language.ARABIC;

                break;
            case 2:
                myText = Language.BULGARIAN;
                break;
            case 3:
                myText = Language.CATALAN;
                break;
            case 4:
                myText = Language.CHINESE_SIMPLIFIED;
                myVoice = Locale.SIMPLIFIED_CHINESE;
                break;
            case 5:
                myText = Language.DANISH;
                break;
            case 6:
                myText = Language.DUTCH;
                break;
            case 7:
                myText = Language.ENGLISH;
                myVoice = Locale.ENGLISH;
                break;
            case 8:
                myText = Language.FRENCH;
                myVoice = Locale.FRENCH;
                break;
            case 9:
                myText = Language.GERMAN;
                myVoice = Locale.GERMAN;
                break;
            case 10:
                myText = Language.GREEK;
                break;
            case 11:
                myText = Language.HEBREW;

                break;
            case 12:
                myText = Language.HINDI;
                myVoice = Locale.ENGLISH;

                break;
            case 13:
                myText = Language.INDONESIAN;


                break;
            case 14:
                myText = Language.ITALIAN;
                myVoice = Locale.ITALIAN;
                break;
            case 15:
                myText = Language.JAPANESE;
                myVoice = Locale.JAPANESE;
                break;
            case 16:
                myText = Language.KOREAN;
                myVoice = Locale.KOREAN;
                break;
            case 17:
                myText = Language.LATVIAN;
                myVoice = Locale.ENGLISH;

                break;
            case 18:
                myText = Language.MALAY;

                break;
            case 19:
                myText = Language.NORWEGIAN;

                break;
            case 20:
                myText = Language.PERSIAN;

                break;
            case 21:
                myText = Language.PORTUGUESE;


                break;
            case 22:
                myText = Language.RUSSIAN;

                break;
            case 23:
                myText = Language.SPANISH;
                break;
            case 24:
                myText = Language.SLOVENIAN;

                break;
            case 25:
                myText = Language.THAI;

                break;
            case 26:
                myText = Language.TURKISH;

                break;
            case 27:
                myText = Language.URDU;

                break;
            case 28:
                myText = Language.UKRAINIAN;

                break;
            case 29:
                myText = Language.VIETNAMESE;
                break;

        }
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        Toast.makeText(getApplicationContext(), "You selected Nothing", Toast.LENGTH_SHORT).show();

    }


    public String translate(String text) throws Exception {

        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
        Translate.setClientId("RAMZAN123ULLAH456"); //Change this
        Translate.setClientSecret("B47r0hFnvF9pqQVdxRPLubqKeDR8L27VV2kkmwtPgsA="); //change


        //String translatedText = Translate.execute(text,Language.fromString(text));

        String translatedText = null;
        translatedText = Translate.execute(text, myText);


        return translatedText;


    }


    public void onInit(int status) {
        // TODO Auto-generated method stub
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(myVoice);


        } else if (status == TextToSpeech.LANG_MISSING_DATA
                || status == TextToSpeech.LANG_NOT_SUPPORTED) {
            Toast.makeText(getApplicationContext(), "This is not a Supported Voice Language", Toast.LENGTH_SHORT).show();
        }


    }

    private void speakOut(String text) {
        tts.speak(text, TextToSpeech.QUEUE_FLUSH, null);
    }


    public void quiteActivity(View view) {
        App.quiteApp(this);
    }
}


class App {

    public static void quiteApp(AppCompatActivity appCompatActivity) {

        appCompatActivity.finish();
    }

}
