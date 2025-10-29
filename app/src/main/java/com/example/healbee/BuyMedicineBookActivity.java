package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class BuyMedicineBookActivity extends AppCompatActivity {

    EditText edname, edaddress, edcontact, edpincode;
    Button btnBooking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_medicine_book);

        edname = findViewById(R.id.editTextBookMDUsername);
        edaddress = findViewById(R.id.editTextBookMDAddress);
        edcontact = findViewById(R.id.editTextBookMDContact);
        edpincode = findViewById(R.id.editTextBookMDPincode); // Initialize edpincode

        btnBooking = findViewById(R.id.buttonBookMD);

        Intent intent = getIntent();
        String[] price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        String data = intent.getStringExtra("date").toString();

        btnBooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username", "").toString();
                String fullname = edname.getText().toString().trim();
                String address = edaddress.getText().toString().trim();
                String contact = edcontact.getText().toString().trim();
                String pincodestr = edpincode.getText().toString().trim();

                // Ensure no field is empty
                if (fullname.isEmpty() || address.isEmpty() || contact.isEmpty() || pincodestr.isEmpty()) {
                    Toast.makeText(BuyMedicineBookActivity.this, "Please fill all details.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate contact number
                if (contact.length() != 11 || !contact.matches("\\d+")) {
                    Toast.makeText(BuyMedicineBookActivity.this, "Invalid contact number. Please enter an 11-digit number.", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Validate pincode
                int pincode;
                try {
                    pincode = Integer.parseInt(pincodestr);
                } catch (NumberFormatException e) {
                    Toast.makeText(BuyMedicineBookActivity.this, "Invalid pincode.", Toast.LENGTH_SHORT).show();
                    return;
                }

                float amount = Float.parseFloat(price[1]);

                // All validations passed
                Database db = new Database(getApplicationContext(), "healthcare", null, 1);
                db.AddOrder(username, fullname, address, contact, pincode, "", data, amount, "medicine");
                db.removeCart(username, "medicine");
                Toast.makeText(BuyMedicineBookActivity.this, "Your booking is done successfully.", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(BuyMedicineBookActivity.this, HomeActivity.class));
            }
        });
    }
}
