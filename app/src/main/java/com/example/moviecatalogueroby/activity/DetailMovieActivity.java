package com.example.moviecatalogueroby.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.moviecatalogueroby.R;
import com.example.moviecatalogueroby.model.MovieModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailMovieActivity extends AppCompatActivity {

    public static final String DETAIL_MOVIE = "detail_movie";

    ImageView imgMovie;
    int photo;
    TextView title;
    private ArrayList<MovieModel> movieModelArrayList;
    int i;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

         imgMovie = findViewById(R.id.img_photo);
         title = findViewById(R.id.txt_name);
        MovieModel movieModel = new MovieModel();

//        Log.i("DETAIL", "detail movie :" + DETAIL_MOVIE);
        movieModel = getIntent().getParcelableExtra("movies");
//       movieModelArrayList = bundle.getParcelable(DETAIL_MOVIE);
//        photo = movieModel.getPhoto();

//        imgMovie.setImageDrawable(photo));
//        Picasso.with(this)
//                .load(movieModelArrayList.get(photo).getPhoto())
//                .into(imgMovie);


//        title.setText(movieModelArrayList.getClass().getName());

        imgMovie.setImageResource(movieModel.getPhoto());

    }
}
