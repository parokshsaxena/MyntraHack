package com.example.parokshsaxena.myntrahack;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class MainActivity extends Activity {

    public final static String apiURL = "http://10.0.2.2:5000/getUser";
    public final static String EXTRA_MESSAGE = "com.example.parokshsaxena.myntrahack.MESSAGE";

    private class CallAPI extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... uri){
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse response;
            String responseString = null;
            try {
                response = httpclient.execute(new HttpGet(uri[0]));
                StatusLine statusLine = response.getStatusLine();
                if(statusLine.getStatusCode() == HttpStatus.SC_OK){
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    responseString = out.toString();
                    out.close();
                } else{
                    //Closes the connection.
                    response.getEntity().getContent().close();
                    throw new IOException(statusLine.getReasonPhrase());
                }
            } catch (ClientProtocolException e) {
                System.out.println("client protocol exception");
                System.out.println(e);
                responseString  = getString(R.string.defaultResponse);
            } catch (IOException e) {
                System.out.println("ioexception");
                System.out.println(e);
                responseString  = getString(R.string.defaultResponse);
            }
            return responseString;
        }

        protected void onPostExecute(String result) {
            Intent intent = new Intent(getApplicationContext(), ResultDisplayPage.class);
            intent.putExtra(EXTRA_MESSAGE, result);
            startActivity(intent);
        }

    } // end CallAPI

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    // This is the method that is called when the submit button is clicked
    public void getUserDetails(View view) {
        EditText queryEditText = (EditText) findViewById(R.id.search_query);
        String query = queryEditText.getText().toString();

        if( query != null && !query.isEmpty()) {
            String urlString = apiURL + "?user=" + query;
            new CallAPI().execute(urlString);
        }
    }

}
