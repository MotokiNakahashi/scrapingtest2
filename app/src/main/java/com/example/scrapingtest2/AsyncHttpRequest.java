package com.example.scrapingtest2;

import android.app.Activity;
import android.os.AsyncTask;
import android.webkit.WebView;
import android.widget.TextView;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public final class AsyncHttpRequest extends AsyncTask<URL, Void, String> {
    //フィールドと変数の設定
    private Activity mainActivity;

    // ユーザ名・パスワード
    final String USERNAME ="c611701410";
    final String PASS ="moto125487tok";

    // ログインページのURL
    final String LOGIN_URL = "https://localidp.ait230.tokushima-u.ac.jp/idp/profile/SAML2/Redirect/SSO?execution=e1s1";
    String mainUrl = "https://manaba.lms.tokushima-u.ac.jp/ct/home";

    final String UA ="Mozilla/5.0 (Linux; Android 10; Android SDK built for x86) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.185 Mobile Safari/537.36 ";

    // ユーザーエージェント


    //コンストラクタの設定
    public AsyncHttpRequest(Activity activity) {
        this.mainActivity = activity;
    }


    @Override
    protected String doInBackground(URL... urls) {
        final URL url = urls[0];
        HttpURLConnection con = null;

        try {
            Connection.Response response = Jsoup.connect(LOGIN_URL)
                    .method(Connection.Method.GET)
                    .execute();
            response = Jsoup.connect(LOGIN_URL)
                    .data("j_username", USERNAME, "j_password", PASS)
                    .userAgent(UA)
                    .cookies(response.cookies())
                    .method(Connection.Method.POST)
                    .execute();

            // ログインページから取得したクッキーを使ってアクセス
            Document doc = Jsoup.connect(mainUrl)
                    .userAgent(UA)
                    .cookies(response.cookies())
                    .get();


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
