package com.example.scrapingtest2;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class AsyncHttpRequest extends AsyncTask<URL, Void, String> {
    //フィールドと変数の設定
    private Activity mainActivity;
    String url1 = "https://www.cloudgate.jp/ua.php";


    //コンストラクタの設定
    public AsyncHttpRequest(Activity activity) {
        this.mainActivity = activity;
    }


    @Override
    protected String doInBackground(URL... urls) {
        final URL url = urls[0];
        HttpURLConnection con = null;

        try {
            //httpコネクトの設定
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setInstanceFollowRedirects(false);
            con.connect();
            final int statusCode = con.getResponseCode();
            if (statusCode != HttpURLConnection.HTTP_OK) {
                System.err.println("正常に接続できていません。statusCode:" + statusCode);
                return null;
            }

            // Jsoupで対象URLの情報を取得する。
            Document doc = Jsoup.connect(url1).get();
            String title = doc.text();
            return  title;

        } catch (IOException e) {
            e.printStackTrace();
            return null;

        } finally {
            if (con != null) {
                con.disconnect();
            }
        }
    }


    //ボタンを押した時の動作
    @Override
    protected void onPostExecute(String result) {
        TextView tv = mainActivity.findViewById(R.id.messageTextView);
        tv.setText(result);
    }
}
