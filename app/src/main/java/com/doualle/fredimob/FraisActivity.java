package com.doualle.fredimob;

import android.graphics.Color;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class FraisActivity extends AppCompatActivity {

    ArrayList<HashMap<String, String>> listeFrais;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_frais);

        listeFrais = new ArrayList<>();

        Intent intent = this.getIntent();
        String json = intent.getStringExtra("json");
        try {
            JSONObject reader = new JSONObject(json);
            JSONArray fraisJSON = reader.getJSONArray("frais");
            String prenom = reader.getString("prenom");
            System.out.println(prenom);

            for (int i = 0; i < fraisJSON.length(); i++) {
                JSONObject c = fraisJSON.getJSONObject(i);
                String date = c.getString("date_frais");
                String trajet = c.getString("trajet_frais");
                String cout = c.getString("cout_frais");

                HashMap<String, String> frais = new HashMap<>();
                frais.put("date", date);
                frais.put("trajet", trajet);
                frais.put("cout", cout);

                listeFrais.add(frais);
            }

            TableLayout table = (TableLayout) findViewById(R.id.tableFrais);
            for(int i=0;i<listeFrais.size();i++)
            {
                TableRow row=new TableRow(this);
                row.setBackgroundColor(Color.GRAY);        // part1
                row.setLayoutParams(new TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT));
                HashMap fr = listeFrais.get(i);

                TextView dateFrais =new TextView(this);
                dateFrais.setPadding(5, 5, 5, 5);
                dateFrais.setText(""+fr.get("date")+"; ");
                row.addView(dateFrais);

                TextView coutFrais =new TextView(this);
                coutFrais.setPadding(5, 5, 5, 5);
                coutFrais.setText(""+fr.get("cout") + "€");
                row.addView(coutFrais);

                table.addView(row);
                System.out.println(fr);
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
