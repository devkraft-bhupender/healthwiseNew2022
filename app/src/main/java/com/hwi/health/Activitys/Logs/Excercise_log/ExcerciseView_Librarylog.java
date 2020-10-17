package com.hwi.health.Activitys.Logs.Excercise_log;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayer.PlayerStyle;
import com.google.android.youtube.player.YouTubePlayerView;
import com.hwi.health.InterFaces.BaseUrl;
import com.hwi.health.InterFaces.Config;
import com.hwi.health.R;
import com.hwi.health.SharedPrefrecess.AllSharedPrefrences;
import com.hwi.health.Usages.MyIntent;
import com.hwi.health.Usages.RandomNumber;
import com.hwi.health.Activitys.Home.HomeActivity;
import com.hwi.health.Activitys.Logs.LogActivity;
import com.hwi.health.Activitys.More.MoreActivity;
import com.hwi.health.Activitys.Plans.PlansActivity;
import com.hwi.health.Activitys.Profile.Second.User_Profile;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class ExcerciseView_Librarylog extends YouTubeBaseActivity implements
        YouTubePlayer.OnInitializedListener,View.OnClickListener,BaseUrl {

    private YouTubePlayerView youTubeView;
    private YouTubePlayer youTubePlayer;
    private MyPlayerStateChangeListener myPlayerStateChangeListener;
    private MyPlaybackEventListener myPlaybackEventListener;
    private static final int RECOVERY_DIALOG_REQUEST = 1;
    LinearLayout home_L, profile_L, log_L, plans_L, more_L;
    String log_key, play_key,result,randnoo,exercise_type,user_id, link;
    ImageView start,stop,play,replay;
    LinearLayout lin_text,lin_time;
    Button yes,no;
    TextView timer;
    ImageView back_page;
    RelativeLayout video_page;

    public int counter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excercise_view_log);
        myPlayerStateChangeListener = new MyPlayerStateChangeListener();
        myPlaybackEventListener = new MyPlaybackEventListener();
        youTubeView = (YouTubePlayerView) findViewById(R.id.youtube_view);


        home_L = (LinearLayout) findViewById(R.id.home);
        profile_L = (LinearLayout) findViewById(R.id.profile);
        log_L = (LinearLayout) findViewById(R.id.log);
        plans_L = (LinearLayout) findViewById(R.id.plans);
        more_L = (LinearLayout) findViewById(R.id.more);
        start = (ImageView) findViewById(R.id.start);
        stop = (ImageView) findViewById(R.id.close);
        play = (ImageView) findViewById(R.id.play);
        replay = (ImageView) findViewById(R.id.replay);
        video_page = (RelativeLayout) findViewById(R.id.bottom2);
        back_page = (ImageView) findViewById(R.id.back_page);

        lin_text = (LinearLayout) findViewById(R.id.lin_text);
        lin_time = (LinearLayout) findViewById(R.id.lin_time);
        yes = (Button) findViewById(R.id.yes);
        no = (Button) findViewById(R.id.no);
        timer = (TextView) findViewById(R.id.timer);





        try{

            SharedPreferences sp2 = new AllSharedPrefrences(this).UserDataget();
            user_id = sp2.getString("Userid", "");
            Log.e("Userid....",user_id);
            SharedPreferences sp = getSharedPreferences("Log_url",MODE_PRIVATE);
            log_key =  sp.getString("Log_url_key","");
            play_key =  sp.getString("play_key","");
            exercise_type =  sp.getString("exercise_type","");
            link =  sp.getString("link","");
            Log.e("log_key....",link+"..."+log_key+"..."+play_key);


        }catch (Exception e){

        }


            home_L.setOnClickListener(this);
            profile_L.setOnClickListener(this);
            log_L.setOnClickListener(this);
            plans_L.setOnClickListener(this);
            more_L.setOnClickListener(this);
            start.setOnClickListener(this);
            stop.setOnClickListener(this);
            play.setOnClickListener(this);
            replay.setOnClickListener(this);
        back_page.setOnClickListener(this);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        new MyIntent(ExcerciseView_Librarylog.this, Library_exercise_list.class, R.anim.enter2, R.anim.exit2);
    }

    @Override
    public void onClick(View view) {
        if (view == home_L) {
            new MyIntent(ExcerciseView_Librarylog.this, HomeActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == profile_L) {
            new MyIntent(ExcerciseView_Librarylog.this, User_Profile.class, R.anim.enter2, R.anim.exit2);
        }if (view == log_L) {
            new MyIntent(ExcerciseView_Librarylog.this, LogActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == plans_L) {
            new MyIntent(ExcerciseView_Librarylog.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
        }if (view == more_L) {
            new MyIntent(ExcerciseView_Librarylog.this, MoreActivity.class, R.anim.enter2, R.anim.exit2);
        }
        if (view == back_page) {
            new MyIntent(ExcerciseView_Librarylog.this, Library_exercise_list.class, R.anim.enter, R.anim.exit);
        }

        if (view == start) {
            youTubeView.initialize(Config.DEVELOPER_KEY, this);
           // stop.setVisibility(View.VISIBLE);
            start.setVisibility(View.GONE);
            myPlaybackEventListener.onPlaying();



        }
        if (view == stop) {
            Toast.makeText(this, "stop", Toast.LENGTH_SHORT).show();
            myPlaybackEventListener.onPaused();
            stop.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
            play.setVisibility(View.VISIBLE);

        }
        if (view == play){
          //  myPlaybackEventListener.onPlaying();
            youTubePlayer.play();
            stop.setVisibility(View.VISIBLE);
            play.setVisibility(View.GONE);
            start.setVisibility(View.GONE);
        }
    }

   /* void ViewoPlayer(String link){
        MediaController mc = new MediaController(this);
        mc.setAnchorView(videoView);
        mc.setMediaPlayer(videoView);
        Uri video = Uri.parse(link);
        videoView.setMediaController(mc);
        videoView.setVideoURI(video);
        videoView.start();
    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {

            Intent in = new Intent(getApplicationContext(), Library_exercise_list.class);
            startActivity(in);
            overridePendingTransition(R.anim.enter2, R.anim.exit2);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                        YouTubeInitializationResult errorReason) {
        if (errorReason.isUserRecoverableError()) {
            errorReason.getErrorDialog(this, RECOVERY_DIALOG_REQUEST).show();
        } else {
            String errorMessage = String.format(
                    getString(R.string.error_player), errorReason.toString());
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                        YouTubePlayer player, boolean wasRestored) {

        youTubePlayer = player;
        player.setPlayerStyle(PlayerStyle.MINIMAL);
      /*  Toast.makeText(getApplicationContext(),
                "YouTubePlayer.onInitializationSuccess()",
                Toast.LENGTH_LONG).show();
*/
        youTubePlayer.setPlayerStateChangeListener(myPlayerStateChangeListener);
        youTubePlayer.setPlaybackEventListener(myPlaybackEventListener);
        if (!wasRestored) {

            // loadVideo() will auto play video
            // Use cueVideo() method, if you don't want to play it automatically

               player.loadVideo(link);



            // Hiding player controls

        }
   }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RECOVERY_DIALOG_REQUEST) {
            // Retry initialization if user performed a recovery action
            getYouTubePlayerProvider().initialize(Config.DEVELOPER_KEY, this);
        }
    }

    private YouTubePlayer.Provider getYouTubePlayerProvider() {
        return (YouTubePlayerView) findViewById(R.id.youtube_view);
    }

    private final class MyPlayerStateChangeListener implements YouTubePlayer.PlayerStateChangeListener {

        private void updateLog(String prompt){

        };

        @Override
        public void onAdStarted() {
            updateLog("onAdStarted()");
        }

        @Override
        public void onError(
                YouTubePlayer.ErrorReason arg0) {
            updateLog("onError(): " + arg0.toString());
        }

        @Override
        public void onLoaded(String arg0) {
            updateLog("onLoaded(): " + arg0);

        }

        @Override
        public void onLoading() {
            updateLog("onLoading()");

        }

        @Override
        public void onVideoEnded() {
            updateLog("onVideoEnded()");
            RandomNumber rn = new RandomNumber();
            randnoo = rn.randno();
            new Task().execute();
            Log.e("onVideoEnded","Enddddd");
            Toast.makeText(ExcerciseView_Librarylog.this, "VideoEnded", Toast.LENGTH_LONG).show();
            new MyIntent(ExcerciseView_Librarylog.this, Library_exercise_list.class, R.anim.enter, R.anim.exit);
           /* video_page.setVisibility(View.VISIBLE);
            yes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    lin_text.setVisibility(View.GONE);
                    lin_time.setVisibility(View.VISIBLE);
                    new CountDownTimer(10000, 1000){
                        public void onTick(long millisUntilFinished){
                            timer.setText(String.format(Locale.getDefault(), "Start Time %02d min: %02d sec",
                                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) % 60,
                                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) % 60));

                        }
                        public  void onFinish(){


                            Toast.makeText(ExcerciseView_Librarylog.this, "finish", Toast.LENGTH_LONG).show();
                            video_page.setVisibility(View.GONE);
                            stop.setVisibility(View.GONE);
                            play.setVisibility(View.GONE);
                            start.setVisibility(View.GONE);
                            replay.setVisibility(View.VISIBLE);
                        }
                    }.start();
                }
            });
            no.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new MyIntent(ExcerciseView_Librarylog.this, PlansActivity.class, R.anim.enter2, R.anim.exit2);
                }
            });*/
        }

        @Override
        public void onVideoStarted() {
            updateLog("onVideoStarted()");

        }

    }

    private final class MyPlaybackEventListener implements YouTubePlayer.PlaybackEventListener {

        private void updateLog(String prompt){

        };

        @Override
        public void onBuffering(boolean arg0) {
            updateLog("onBuffering():" + String.valueOf(arg0));
        }

        @Override
        public void onPaused() {
            updateLog("onPaused()");
            youTubePlayer.pause();
            play.setVisibility(View.VISIBLE);
            stop.setVisibility(View.GONE);
           // play.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPlaying() {
            updateLog("onPlaying()");
            stop.setVisibility(View.VISIBLE);
            play.setVisibility(View.GONE);
            //youTubePlayer.play();

           // stop.setVisibility(View.VISIBLE);



        }

        @Override
        public void onSeekTo(int arg0) {
            updateLog("onSeekTo(): " + String.valueOf(arg0));
        }

        @Override
        public void onStopped() {
            updateLog("onStopped()");
            youTubePlayer.pause();


        }

    }

    class Task extends AsyncTask<String, Void, String> {


        @Override
        protected String doInBackground(String... params) {
            result = "";

            try {
                URL url = new URL(URLS + Insert_Exercise_Info + randnoo);

                JSONObject postDataParams = new JSONObject();

                postDataParams.put("access_keys", AccessToken);
                postDataParams.put("user_id", user_id);
                postDataParams.put("exercise_type", exercise_type);
                postDataParams.put("exercise_number", play_key);


                Log.e("params..............", postDataParams.toString());


                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");

                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));

                writer.flush();
                writer.close();
                os.close();
                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {
                    String line;
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    while ((line = br.readLine()) != null) {
                        result += line;
                    }
                } else {
                    result = "";
                }
            } catch (Exception e) {
                Log.e("ERRR",e+"");
            }
            return result;
        }
        @Override
        protected void onPostExecute(String s) {

            try {
                JSONObject jobj = new JSONObject(result);
                String status = jobj.getString("status");
                String message = jobj.getString("message");


                if (status.equals("1")) {


                } else {

                    Toast.makeText(ExcerciseView_Librarylog.this, message, Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Log.e("VolleyError= ", e + "");
            }
        }
    }

    public String getPostDataString(JSONObject params) throws Exception {

        StringBuilder result = new StringBuilder();
        boolean first = true;

        Iterator<String> itr = params.keys();

        while (itr.hasNext()) {

            String key = itr.next();
            Object value = params.get(key);

            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(key, "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(value.toString(), "UTF-8"));

        }
        return result.toString();
    }

}
