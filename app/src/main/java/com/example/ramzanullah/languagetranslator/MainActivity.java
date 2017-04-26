package com.example.ramzanullah.languagetranslator;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.memetix.mst.language.Language;
import com.memetix.mst.translate.Translate;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    String text;
   // private Map<String, Language> myText;
   // private TextView Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Language, R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        /*try {
            myText = Language.values(Language.fromString(text));
        } catch (Exception e) {
            e.printStackTrace();
       }*/
        ((Button) findViewById(R.id.bTranslate)).setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub


                class bgStuff extends AsyncTask<Void, Void, Void> {

                    String translatedText = "";

                    @Override
                    protected Void doInBackground(Void... params) {
                        // TODO Auto-generated method stub
                        try {
                            String text = ((EditText) findViewById(R.id.etUserText)).getText().toString();
                            translatedText = translate(text);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                            translatedText = e.toString();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        // TODO Auto-generated method stub
                        ((TextView) findViewById(R.id.tvTranslatedText)).setText(translatedText);
                        super.onPostExecute(result);
                    }

                }

                new bgStuff().execute();
            }
        });


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        TextView Text = (TextView) view;
        text = (String) Text.getText();
    }


    @Override
    public void onNothingSelected (AdapterView < ? > parent){

    }


    public String translate (String text) throws Exception {

        // Set the Client ID / Client Secret once per JVM. It is set statically and applies to all services
        Translate.setClientId("RAMZAN123ULLAH456"); //Change this
        Translate.setClientSecret("B47r0hFnvF9pqQVdxRPLubqKeDR8L27VV2kkmwtPgsA="); //change


        String translatedText = Translate.execute(text,Language.fromString(text));

        return translatedText;


    }

}
