package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class BuyMedicineActivity extends AppCompatActivity {

    private String[][] packages = {
            {"Uprise-D3", "60"},
            {"Healthvit Chromium Picolinate 200mcg Capsule", "305"},
            {"Vitamin B Complex Capsule", "448"},
            {"Inlife Vitamin E Wheat Germ Oil Capsule", "539"},
            {"Tata 1mg Calcium + Vitamin D3", "30"},
            {"Dolo 650 Tablets", "30"},
            {"Crocine 650 Advance Tablets", "50"},
            {"Strepsils for Sore Throat", "40"},
            {"Bromhexine Syrup", "75"},
            {"Amlodipine 5mg Tablet", "25"},
            {"Losartan 50mg Tablet", "40"},
            {"Bisoprolol 5mg Tablet", "45"},
            {"Olmesartan 20mg Tablet", "50"},
            {"Nifedipine 10mg Tablet", "30"},
            {"Metformin 500mg Tablet", "20"},
            {"Gliclazide 80mg Tablet", "30"},
            {"Insulin Glargine Injection", "600"},
            {"Pioglitazone 15mg Tablet", "40"},
            {"Dapagliflozin 10mg Tablet", "450"},
            {"Systane Ultra Eye Drops", "300"},
            {"Refresh Tears Eye Drops", "250"},
            {"Olopatadine Eye Drops", "350"},
            {"Brinzolamide Eye Drops", "450"},
            {"Latanoprost Eye Drops", "500"},
            {"Otrivin Nasal Spray", "120"},
            {"Fluticasone Nasal Spray", "150"}
    };

    private String[] package_details = {
            "Building and keeping bones and teeth strong, reducing fatigue/stress, and boosting immunity.",
            "Helps regulate insulin and supports healthy metabolism.",
            "Provides relief from vitamin B deficiencies, aids in red blood cell formation, and supports the nervous system.",
            "Promotes skin health, reduces blemishes and pigmentation, and protects against sun rays.",
            "Reduces the risk of calcium deficiency, promotes joint flexibility, and prevents osteoporosis.",
            "Dolo 650 Tablets help relieve pain and fever by blocking the release of certain chemicals.",
            "Effective for reducing fever and suitable for people with heart conditions or high blood pressure.",
            "Provides relief from bacterial throat infections and a soothing recovery experience.",
            "Helps relieve cough by thinning mucus and clearing the airway passages.",
            "Amlodipine helps lower high blood pressure and prevents strokes, heart attacks, and kidney problems.",
            "Losartan controls blood pressure and protects the kidneys in diabetic patients.",
            "Bisoprolol reduces blood pressure and improves heart function in heart-related issues.",
            "Olmesartan effectively lowers blood pressure and reduces the risk of strokes.",
            "Nifedipine relaxes blood vessels, improving blood flow and reducing blood pressure.",
            "Metformin controls blood sugar levels in type 2 diabetes effectively.",
            "Gliclazide enhances insulin production to regulate blood sugar levels.",
            "Insulin Glargine provides long-lasting blood sugar control for diabetic patients.",
            "Pioglitazone helps improve insulin sensitivity and glucose regulation.",
            "Dapagliflozin helps lower blood sugar by eliminating excess glucose through urine.",
            "Systane Ultra Eye Drops provide long-lasting relief for dry eyes.",
            "Refresh Tears Eye Drops restore moisture and relieve eye irritation.",
            "Olopatadine Eye Drops relieve symptoms of allergic conjunctivitis.",
            "Brinzolamide Eye Drops reduce high intraocular pressure in glaucoma patients.",
            "Latanoprost Eye Drops lower eye pressure and prevent vision loss in glaucoma patients.",
            "Otrivin Nasal Spray provides quick relief from nasal congestion.",
            "Fluticasone Nasal Spray reduces inflammation and relieves nasal allergies."
    };

    private ArrayList<String> cartItems = new ArrayList<>();

    LinearLayout listContainer;
    Button btnBack;
    EditText searchEditText;
    ImageButton buttonGoToCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine);

        listContainer = findViewById(R.id.listContainer);
        btnBack = findViewById(R.id.buttonBack);
        buttonGoToCart = findViewById(R.id.buttonGoToCart); // Corrected reference
        searchEditText = findViewById(R.id.editTextSearch);

        btnBack.setOnClickListener(view -> startActivity(new Intent(BuyMedicineActivity.this, HomeActivity.class)));

        buttonGoToCart.setOnClickListener(view -> {
            Intent intent = new Intent(BuyMedicineActivity.this, CartBuyMedicineActivity.class);
            intent.putStringArrayListExtra("cartItems", cartItems);
            startActivity(intent);
        });

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                filterMedicines(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {}
        });

        showMedicines(packages);
    }

    private void showMedicines(String[][] medicineList) {
        listContainer.removeAllViews();
        LayoutInflater inflater = getLayoutInflater();
        for (int i = 0; i < medicineList.length; i++) {
            View itemView = inflater.inflate(R.layout.multi_lines, listContainer, false);

            TextView line1 = itemView.findViewById(R.id.line_a);
            TextView line5 = itemView.findViewById(R.id.line_e);
            TextView lineB = itemView.findViewById(R.id.line_b);
            TextView lineC = itemView.findViewById(R.id.line_c);
            TextView lineD = itemView.findViewById(R.id.line_d);

            lineB.setVisibility(View.GONE);
            lineC.setVisibility(View.GONE);
            lineD.setVisibility(View.GONE);

            line1.setText(medicineList[i][0]);
            line5.setText("Total Cost: " + medicineList[i][1] + "/-");

            final int finalI = i;
            itemView.setOnClickListener(view -> {
                cartItems.add(medicineList[finalI][0]);

                Intent intent = new Intent(BuyMedicineActivity.this, BuyMedicineDetailsActivity.class);
                intent.putExtra("text1", packages[finalI][0]);
                intent.putExtra("text2", package_details[finalI]);
                intent.putExtra("text3", packages[finalI][1]);
                startActivity(intent);
            });

            listContainer.addView(itemView);
        }
    }

    private void filterMedicines(String query) {
        String[][] filteredMedicines = filterArray(packages, query);
        showMedicines(filteredMedicines);
    }

    private String[][] filterArray(String[][] array, String query) {
        ArrayList<String[]> filteredList = new ArrayList<>();
        for (String[] item : array) {
            if (item[0].toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        return filteredList.toArray(new String[filteredList.size()][]);
    }
}
