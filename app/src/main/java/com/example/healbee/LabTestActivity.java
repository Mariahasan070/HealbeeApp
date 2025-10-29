package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LabTestActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Full Body Checkup", "999"},
            {"Blood Glucose Fasting", "299"},
            {"Covid-19 Antibody", "899"},
            {"Thyroid Check", "499"},
            {"Immunity Check", "699"},
            {"Liver Function Test", "650"},
            {"Kidney Function Test", "450"},
            {"Complete Blood Count (CBC)", "300"},
            {"Vitamin D Test", "350"},
            {"Cholesterol Test", "400"},
            {"Blood Pressure Monitoring", "150"},
            {"ECG (Electrocardiogram)", "500"},
            {"Lipid Profile", "350"},
            {"Diabetes Test", "750"},
            {"HIV Test", "1200"}
    };

    private String[] package_details = {
            "Full Body Checkup\nIncludes: Complete Hemogram, HbA1c, Iron Steroid, Kidney Function Test, LDH, Serum, Lipid Profile, Liver Function Test",
            "Blood Glucose Fasting\nA fasting blood glucose test to check for diabetes.",
            "Covid-19 Antibody\nTest for COVID-19 antibodies to see if you have been previously infected.",
            "Thyroid Check\nIncludes T3 & T4 thyroid profile to check for thyroid dysfunction.",
            "Immunity Check\nTests to evaluate your immunity against various pathogens.",
            "Liver Function Test\nA blood test that measures enzyme levels to check liver health.",
            "Kidney Function Test\nTests that evaluate kidney function by measuring creatinine and urea levels.",
            "Complete Blood Count (CBC)\nMeasures your overall health, including red and white blood cell counts.",
            "Vitamin D Test\nA blood test to check your vitamin D levels.",
            "Cholesterol Test\nA test to measure LDL, HDL, and total cholesterol levels in your blood.",
            "Blood Pressure Monitoring\nA service to regularly monitor your blood pressure for hypertension.",
            "ECG (Electrocardiogram)\nMonitors your heart's rhythm and electrical activity.",
            "Lipid Profile\nTests the different types of fats in the blood including cholesterol.",
            "Diabetes Test\nThis test includes both fasting blood glucose and HbA1c levels to diagnose diabetes.",
            "HIV Test\nA test to determine if HIV antibodies are present in your blood."
    };

    private EditText editTextSearch;
    private LinearLayout listContainer;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);

        editTextSearch = findViewById(R.id.editTextSearch);
        listContainer = findViewById(R.id.listContainer);
        btnBack = findViewById(R.id.buttonBack);
        ImageButton buttonGoToCart = findViewById(R.id.buttonGoToCart);

        populateLabTestList("");

        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                populateLabTestList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });

        btnBack.setOnClickListener(v -> startActivity(new Intent(LabTestActivity.this, HomeActivity.class)));

        buttonGoToCart.setOnClickListener(v -> startActivity(new Intent(LabTestActivity.this, CartLabActivity.class)));
    }

    private void populateLabTestList(String query) {
        listContainer.removeAllViews();

        for (int i = 0; i < packages.length; i++) {
            if (query.isEmpty() || packages[i][0].toLowerCase().contains(query.toLowerCase())) {
                LinearLayout labTestItem = new LinearLayout(this);
                labTestItem.setOrientation(LinearLayout.VERTICAL);
                labTestItem.setPadding(16, 16, 16, 16);
                labTestItem.setBackground(getDrawable(R.drawable.btngotocart)); // Use a custom drawable for better aesthetics
                labTestItem.setClickable(true);
                labTestItem.setFocusable(true);

                TextView title = new TextView(this);
                title.setText(packages[i][0]);
                title.setTextSize(18);
                title.setTextColor(getResources().getColor(android.R.color.black));

                TextView cost = new TextView(this);
                cost.setText("Cost: tk" + packages[i][1]);
                cost.setTextSize(16);
                cost.setTextColor(getResources().getColor(android.R.color.white));

                labTestItem.addView(title);
                labTestItem.addView(cost);

                final int index = i;
                labTestItem.setOnClickListener(v -> {
                    Intent intent = new Intent(LabTestActivity.this, LabTestDetailsActivity.class);
                    intent.putExtra("title", packages[index][0]); // Pass package name
                    intent.putExtra("details", package_details[index]); // Pass details
                    intent.putExtra("cost", packages[index][1]); // Pass cost value
                    startActivity(intent);
                });

                listContainer.addView(labTestItem);
            }
        }
    }

}
