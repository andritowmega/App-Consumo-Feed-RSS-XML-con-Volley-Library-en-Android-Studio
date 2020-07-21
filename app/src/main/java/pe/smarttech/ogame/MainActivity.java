package pe.smarttech.ogame;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {
    ProgressBar pbloading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pbloading = findViewById(R.id.progress);
        new CheckInfo().execute();
    }
    private class CheckInfo extends AsyncTask<Void, Integer, Boolean> {

        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
        @Override
        protected Boolean doInBackground(Void... params) {
            for(int i=0;i<101;i++){
                try {
                    Thread.sleep(50);
                } catch(InterruptedException e) {}
                publishProgress(i);
            }
            return true;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            int progreso = values[0].intValue();
            pbloading.setProgress(progreso);
        }
        @Override
        protected void onPreExecute() {

        }
        @Override
        protected void onPostExecute(Boolean result) {
            pbloading.setProgress(100);
            //Log.d("Response ",response);
            if(result){
                startActivity(new Intent(MainActivity.this,DataActivity.class));
                finish();
            }
            else{
                finish();

            }
        }
        @Override
        protected void onCancelled() {

        }
    }
    private void HiloTask()
    {
        try {
            Thread.sleep(2000);
        } catch(InterruptedException e) {}
    }
}
