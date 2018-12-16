package com.example.android.boostcamp;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Minjeong Kim on 2018-12-05.
 */

public class MovieParser {
    private List<Movie> movieList;

    public MovieParser(){
        movieList = new ArrayList<>();
    }

    public List<Movie> parse(String input){
        try {
            JSONArray jarray = new JSONObject(input).getJSONArray("items");
            for(int i=0;i<jarray.length();i++){
                JSONObject jObject = jarray.getJSONObject(i);
                Movie newMovie = new Movie();
                String returnActor = jObject.optString("actor");
                newMovie.setActor(returnActor.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
                String returnDirector =jObject.optString("director");
                newMovie.setDirector(returnDirector.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
                newMovie.setImage(jObject.optString("image"));
                newMovie.setPubDate(jObject.optString("pubDate"));
                String returnTitle = jObject.optString("title");
                newMovie.setTitle(returnTitle.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", ""));
                newMovie.setUserRating(jObject.optInt("userRating"));
                newMovie.setLink(jObject.optString("link"));
                movieList.add(newMovie);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieList;
    }
}
