package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LabTestDetailsActivity extends AppCompatActivity {

    TextView tvPackageName, tvTotalCost;
    EditText edDetail;
    Button btnAddToCart, btnBack;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);

        // Initialize UI components
        tvPackageName = findViewById(R.id.textViewLTDtitle);
        tvTotalCost = findViewById(R.id.textViewTotalCost);
        edDetail = findViewById(R.id.editTextmultilineLTD);
        btnAddToCart = findViewById(R.id.ButtonLTDGotoCart);
        btnBack = findViewById(R.id.ButtonLTDBack);

        // Make the EditText non-editable
        edDetail.setKeyListener(null);

        // Retrieve intent data
        Intent intent = getIntent();
        String packageName = intent.getStringExtra("title");
        String details = intent.getStringExtra("details");
        String costString = intent.getStringExtra("cost");

        // Log the received intent data for debugging
        Log.d("LabTestDetailsActivity", "Package Name: " + packageName);
        Log.d("LabTestDetailsActivity", "Details: " + details);
        Log.d("LabTestDetailsActivity", "Cost String Received: " + costString);

        // Parse the cost to float, handling null or invalid values
        float cost = 0.0f; // Default value
        if (costString != null && !costString.isEmpty()) {
            // Remove any non-numeric characters (like "tk" or symbols)
            costString = costString.replaceAll("[^0-9.]", "");
            try {
                // Parse the cleaned-up string into a float
                cost = Float.parseFloat(costString);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Invalid cost value received", Toast.LENGTH_SHORT).show();
                Log.e("LabTestDetailsActivity", "Cost parsing failed", e);
            }
        }

        // Log the final parsed cost value
        Log.d("LabTestDetailsActivity", "Parsed Cost: " + cost);

        // Set data to UI components
        tvPackageName.setText(packageName);
        edDetail.setText(details);

        // Define finalCost after parsing cost
        final float finalCost = cost;
        tvTotalCost.setText(String.format("Total Cost: %.2f tk", finalCost));  // Show correct cost

        // Back button functionality
        btnBack.setOnClickListener(view -> startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class)));

        // Add to cart button functionality
        btnAddToCart.setOnClickListener(view -> {
            // Retrieve the username from shared preferences
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");

            // Ensure the username is not empty
            if (username.isEmpty()) {
                Toast.makeText(this, "User not logged in", Toast.LENGTH_SHORT).show();
                return;
            }

            // Get product details
            String product = tvPackageName.getText().toString();

            // Initialize the database
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);

            // Check if the product is already in the cart
            if (db.checkCart(username, product)) {
                Toast.makeText(LabTestDetailsActivity.this, "Product Already Added", Toast.LENGTH_SHORT).show();
            } else {
                // Add product to the cart
                db.AddToCart(username, product, finalCost, "lab");
                Toast.makeText(LabTestDetailsActivity.this, "Added to Cart with cost: " + String.format("%.2f tk", finalCost), Toast.LENGTH_SHORT).show();

                // Navigate back to the LabTestActivity
                startActivity(new Intent(LabTestDetailsActivity.this, LabTestActivity.class));
            }
        });
    }
}
