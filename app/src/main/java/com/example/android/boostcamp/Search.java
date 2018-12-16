package com.example.android.boostcamp;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.AsyncTask;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Minjeong Kim on 2018-12-16.
 */

public class Search extends AsyncTask<String,Void,String> {
    private List<Movie> movieList;
    private Context mContext;
    private RecyclerViewAdapter mAdapter;
    private CustomProgressDialog pd;
    private MovieParser parser;
    private  String clientID = "36LmOWPEqKuEwRVifVNF";
    private String clientSecret = "gL2LlmNEKh";
    StringBuffer response = new StringBuffer();
    private String query;

    public Search(Context mContext,RecyclerViewAdapter mAdapter){
        this.mContext = mContext;
        this.mAdapter = mAdapter;
        parser = new MovieParser();
    }
    @Override
    protected void onPreExecute(){
        super.onPreExecute();
        pd = new CustomProgressDialog(mContext);
        pd.show();
    }
    @Override
    protected String doInBackground(String... strings) {
        try {
            query = strings[0];
            String text = URLEncoder.encode(query, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/movie.json?query="+ text; // json 결과
            URL url = new URL(apiURL);
            HttpURLConnection con =(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientID);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }
            String inputLine;

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();
            System.out.println(response.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }catch (MalformedURLException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        return response.toString();
    }

    @Override
    protected void onPostExecute(String param){
        super.onPostExecute(param);
        pd.dismiss();
        movieList=parser.parse(param);
        if(movieList.size()>0){
            mAdapter.swap(movieList);
            mAdapter.notifyDataSetChanged();
        }
        else {
            View view = View.inflate(mContext, R.layout.custom_toast, null);
            TextView tv = (TextView) view.findViewById(R.id.toastText);
            tv.setText(String.format(mContext.getString(R.string.toastMessage), query));
            Toast t = new Toast(mContext);
            t.setView(view);
            t.setDuration(Toast.LENGTH_SHORT);
            t.show();
        }
    }
}
