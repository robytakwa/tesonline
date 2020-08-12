package com.example.moviecatalogueroby.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import com.example.moviecatalogueroby.model.MovieModel;
import com.example.moviecatalogueroby.R;
import com.example.moviecatalogueroby.adapter.MovieAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private MovieAdapter adapter;
    private String[] dataName;
    private String[] dataDescription;
    private TypedArray dataPhoto;
    private ArrayList<MovieModel> movieModelArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = findViewById(R.id.lv_list);
        adapter = new MovieAdapter();
        listView.setAdapter(adapter);

        prepare();
        addItem();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent moveWithObjectIntent = new Intent(MainActivity.this,  DetailMovieActivity.class);
                moveWithObjectIntent.putParcelableArrayListExtra("movies", movieModelArrayList);
                startActivity(moveWithObjectIntent);
                Toast.makeText(MainActivity.this, movieModelArrayList.get(i).getName(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void prepare() {
        dataName = getResources().getStringArray(R.array.data_name);
        dataDescription = getResources().getStringArray(R.array.data_description);
        dataPhoto = getResources().obtainTypedArray(R.array.data_photo);
    }

    private void addItem() {
        movieModelArrayList = new ArrayList<>();

        for (int i = 0; i < dataName.length; i++) {
            MovieModel movies = new MovieModel();
            movies.setPhoto(dataPhoto.getResourceId(i, -1));
            movies.setName(dataName[i]);
            movies.setDescription(dataDescription[i]);
            movieModelArrayList.add(movies);


        }
        adapter.setMovies(movieModelArrayList);
    }
}
