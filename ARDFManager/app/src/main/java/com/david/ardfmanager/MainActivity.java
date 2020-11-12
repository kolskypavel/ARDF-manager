package com.david.ardfmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.david.ardfmanager.competitors.Competitor;
import com.david.ardfmanager.competitors.CompetitorsListAdapter;
import com.david.ardfmanager.competitors.competitors_fragment;
import com.david.ardfmanager.tracks.Track;
import com.david.ardfmanager.tracks.TracksListAdapter;
import com.david.ardfmanager.tracks.trackAddActivity;
import com.david.ardfmanager.tracks.tracks_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Track> tracksList;
    public static TracksListAdapter tracksListAdapter;
    public static ArrayList<Competitor> competitorsList;
    public static CompetitorsListAdapter competitorsListAdapter;

    public static final int INTENT_ADD_TRACK = 1000;
    public static final int INTENT_ADD_COMPETITOR = 1001;

    public static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/ARDFData";

    public static File dir = new File(path);
    public static File file = new File(path + "/data.json");


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.tracks, R.id.competitors, R.id.readouts, R.id.results2)
                .build();
        final NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        dir.mkdir();
        tracksList = new ArrayList<>();
        tracksListAdapter = new TracksListAdapter(this, R.layout.track_view_layout, tracksList);
        competitorsList = new ArrayList<>();
        competitorsListAdapter = new CompetitorsListAdapter(this, R.layout.competitor_view_layout, competitorsList);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String s = navController.getCurrentDestination().getLabel().toString();

                if(s == getResources().getString(R.string.title_tracks)){

                    Intent intent = new Intent(MainActivity.this, trackAddActivity.class);
                    startActivityForResult(intent, INTENT_ADD_TRACK);

                }else if(s == getResources().getString(R.string.title_competitors)){

                    Competitor c = new Competitor("pokus2");
                    competitorsList.add(0, c);

                }

                setAllAdapters();
            }
        });



        fab.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this, "Dlouhej", Toast.LENGTH_SHORT).show();
                try {
                    exportJSON();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return false;
            }
        });

    }

    public void exportJSON() throws IOException, JSONException {

        JSONArray jsonTracksArray = new JSONArray();
        for (int i = 0; i < tracksList.size(); i++) {
            jsonTracksArray.put(tracksList.get(i).trackToJSON());
        }

        JSONArray jsonCompetitorsArray = new JSONArray();
        for (int i = 0; i < competitorsList.size(); i++) {
            jsonCompetitorsArray.put(competitorsList.get(i).competitorToJSON());
        }

        JSONObject JSONObject = new JSONObject();
        JSONObject.put("tracks", jsonTracksArray);
        JSONObject.put("competitors", jsonCompetitorsArray);

        FileWriter fileWriter = new FileWriter(file);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        bufferedWriter.write(JSONObject.toString());
        bufferedWriter.close();

    }

    public void importJSON(){
        
    }


    public void setAllAdapters() {
        if (tracks_fragment.mListView != null) {
            tracks_fragment.mListView.setAdapter(tracksListAdapter);
        }
        if (competitors_fragment.mListView != null) {
            competitors_fragment.mListView.setAdapter(competitorsListAdapter);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {

            switch (requestCode) {
                case INTENT_ADD_TRACK:
                    Track t = new Track(data.getStringExtra("name"));
                    tracksList.add(0, t);
                    setAllAdapters();
                    break;
                /*
                case INTENT_ADD_COMPETITOR:
                    int kolik = data.getIntExtra("kolik", 0);
                    Track t = new Track(String.valueOf(kolik));
                    tracksList.add(0, t);
                    setAllAdapters();
                    break;*/

            }
        } else {
            Toast.makeText(MainActivity.this, "Zrušeno", Toast.LENGTH_SHORT).show();
        }

    }
}
