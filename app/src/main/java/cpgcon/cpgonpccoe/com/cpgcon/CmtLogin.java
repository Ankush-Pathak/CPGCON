package cpgcon.cpgonpccoe.com.cpgcon;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.content.Intent;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import android.os.*;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

//import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class CmtLogin extends AppCompatActivity {
    WebView cmt;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    Handler handler;
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cmt_login);
        cmt = (WebView) findViewById(R.id.webView);
        cmt.getSettings().setJavaScriptEnabled(true);

        ConnectivityManager check = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);

        final ProgressDialog progressDialog = new ProgressDialog(CmtLogin.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage("Loading...");
        progressDialog.setCancelable(true);
        progressDialog.setIndeterminate(true);


        int flag = 1;


        if (check.getActiveNetworkInfo() != null
                && check.getActiveNetworkInfo().isAvailable()
                && check.getActiveNetworkInfo().isConnected()) {
            flag = 0;

        } else {

            Toast.makeText(CmtLogin.this, "No Active Internet Connection.", Toast.LENGTH_LONG).show();
            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(CmtLogin.this,MainActivity.class);
                    intent.putExtra("key",1);
                    startActivity(intent);
                    finish();
                }
            }, 1500);

        }


        if (flag == 0) {


            cmt.loadUrl("https://cmt3.research.microsoft.com/User/Login?ReturnUrl=%2FCPGCON2016");


           /* while(cmt.getProgress()!=50)
            {
                progressDialog.setProgress(cmt.getProgress());
            }*/
            cmt.setWebViewClient(new WebViewClient() {
                public void onPageStarted(WebView view, String url, Bitmap favicon) {
                    progressDialog.show();
                }

                public void onPageFinished(WebView view, String url) {
                    progressDialog.dismiss();
                }
            });


        }


        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(CmtLogin.this,MainActivity.class);
        intent.putExtra("key",1);
        startActivity(intent);
        finish();
    }
}


