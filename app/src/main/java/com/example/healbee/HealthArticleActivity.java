package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HealthArticleActivity extends AppCompatActivity {

    // Data for health articles
    private String[][] health_articles = {
            {"WHO", "Click for more details", "https://www.who.int/bangladesh/health-topics"},
            {"What are the symptoms of diabetes?", "Click for more details", "https://my.clevelandclinic.org/health/diseases/7104-diabetes"},
            {"What Is High Blood Pressure?", "Click for more details", "https://www.nhlbi.nih.gov/health/high-blood-pressure"},
            {"Healthy Eating Plate", "Click for more details", "https://nutritionsource.hsph.harvard.edu/healthy-eating-plate/"},
            {"What to know about exercise and how to start", "Click for more details", "https://www.medicalnewstoday.com/articles/153390"}
    };

    private int[] images = {
            R.drawable.a,  // Replace with actual drawable resources
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a
    };

    LinearLayout listContainer;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health_article);

        // Initialize the LinearLayout container
        listContainer = findViewById(R.id.listContainer);

        // Initialize the Back button
        btnBack = findViewById(R.id.ButtonHABack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HealthArticleActivity.this, HomeActivity.class));
            }
        });

        // Dynamically populate the LinearLayout container with articles
        for (int i = 0; i < health_articles.length; i++) {
            // Create a new TextView for each article title
            TextView titleTextView = new TextView(this);
            titleTextView.setText(health_articles[i][0]);
            titleTextView.setTextSize(25f);
            titleTextView.setTextColor(Color.BLACK);
            titleTextView.setPadding(16, 16, 16, 8);
            titleTextView.setBackgroundColor(Color.parseColor("#6CADA7")); // Background for each item
            titleTextView.setGravity(Gravity.CENTER_VERTICAL);

            // Add click listener to navigate to details
            final int index = i; // Use final for lambda
            titleTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(HealthArticleActivity.this, HealthArticleDetailsActivity.class);
                    intent.putExtra("text1", health_articles[index][0]); // Title
                    intent.putExtra("image", images[index]); // Image
                    intent.putExtra("url", health_articles[index][2]); // URL for details
                    startActivity(intent);
                }
            });

            // Add the TextView to the LinearLayout container
            listContainer.addView(titleTextView);

            // Add a separator view
            View separator = new View(this);
            separator.setLayoutParams(new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    2 // Thickness of separator
            ));
            separator.setBackgroundColor(Color.LTGRAY);
            listContainer.addView(separator);
        }
    }
}
