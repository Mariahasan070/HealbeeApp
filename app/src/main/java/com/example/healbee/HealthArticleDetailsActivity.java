package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class HealthArticleDetailsActivity extends AppCompatActivity {

    TextView tv1;
    ImageView img1;
    WebView webView;
    Button btnBack, btnOpenLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article_details);

        tv1 = findViewById(R.id.textViewHADLtitle);
        img1 = findViewById(R.id.ImageViewHAD);
        webView = findViewById(R.id.webViewArticle);
        btnBack = findViewById(R.id.ButtonHADBack);
        btnOpenLink = findViewById(R.id.btnOpenLink);

        Intent intent = getIntent();
        tv1.setText(intent.getStringExtra("text1"));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            int resId = bundle.getInt("text2");
            img1.setImageResource(resId);
        }

        final String url = getIntent().getStringExtra("url");

        // Open article in WebView
        webView.loadUrl(url);

        // If user wants to open the article in a browser
        btnOpenLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, android.net.Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticleDetailsActivity.this, HealthArticleActivity.class));
            }
        });
    }
}
