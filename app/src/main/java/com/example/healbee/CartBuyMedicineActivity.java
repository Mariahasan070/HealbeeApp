package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class CartBuyMedicineActivity extends AppCompatActivity {

    private static final String TAG = "CartBuyMedicineActivity";

    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;
    TextView totalCost;
    ListView lst;
    private DatePickerDialog datePickerDialog;
    private Button dateButton;
    private Button btnCheckOut;
    private Button btnBack;
    private ArrayList<String[]> packages = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_buy_medicine);

        dateButton = findViewById(R.id.buttonBMCartDate);
        btnCheckOut = findViewById(R.id.ButtonBMCartCheckOutCart);
        btnBack = findViewById(R.id.ButtonBMCarTBack);
        totalCost = findViewById(R.id.textViewBMtotalcostCart);
        lst = findViewById(R.id.listViewBMcart);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        Database db = new Database(getApplicationContext(), "healthcare", null, 1);
        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username, "medicine");

        // Clear previous data in packages
        packages.clear();

        // Fetch cart data and populate packages
        for (Object data : dbData) {
            String arrData = data.toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            String[] packageData = new String[5];
            packageData[0] = strData[0]; // Medicine Name
            packageData[4] = "Cost : " + strData[1] + "/-";  // Cost Info
            packages.add(packageData);
            totalAmount += Float.parseFloat(strData[1]);  // Accumulate the total cost
        }

        totalCost.setText("Total Cost : " + totalAmount);

        // Prepare list of items to display in ListView
        list = new ArrayList<>();
        for (String[] packageData : packages) {
            item = new HashMap<>();
            item.put("line1", packageData[0]);
            item.put("line5", packageData[4]);
            list.add(item);
        }

        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[]{"line1", "line5"},
                new int[]{R.id.line_a, R.id.line_e});

        lst.setAdapter(sa);

        lst.setOnItemClickListener((parent, view, position, id) -> {
            new AlertDialog.Builder(CartBuyMedicineActivity.this)
                    .setMessage("Do you want to remove this item from the cart?")
                    .setCancelable(true)
                    .setPositiveButton("Remove", (dialog, which) -> {
                        // Ensure the position is valid
                        if (position >= 0 && position < packages.size()) {
                            String medicineName = packages.get(position)[0];

                            // Log for debugging
                            Log.d(TAG, "Removing medicine: " + medicineName);

                            // Remove from the cart data (database)
                            int rowsAffected = db.removeItemFromCart(username, medicineName, "medicine");

                            if (rowsAffected > 0) {
                                // Remove the item from the list and array
                                packages.remove(position);
                                list.remove(position);

                                // Log list sizes for debugging
                                Log.d(TAG, "Updated packages list size: " + packages.size());
                                Log.d(TAG, "Updated list size: " + list.size());

                                // Notify adapter that data has changed
                                sa.notifyDataSetChanged();

                                // Recalculate total cost
                                recalculateTotalCost(db, username);
                            } else {
                                Log.e(TAG, "Failed to remove medicine: " + medicineName);
                            }
                        } else {
                            Log.e(TAG, "Invalid position: " + position);
                        }
                    })
                    .setNegativeButton("Cancel", null)
                    .show();
        });



        btnBack.setOnClickListener(view -> startActivity(new Intent(CartBuyMedicineActivity.this, BuyMedicineActivity.class)));

        btnCheckOut.setOnClickListener(view -> {
            Intent it = new Intent(CartBuyMedicineActivity.this, BuyMedicineBookActivity.class);
            it.putExtra("price", totalCost.getText());
            it.putExtra("date", dateButton.getText());
            startActivity(it);
        });

        // Date Picker setup
        initDatePicker();
        dateButton.setOnClickListener(view -> datePickerDialog.show());
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, i, i1, i2) -> {
            i1 = i1 + 1;
            dateButton.setText(i2 + "/" + i1 + "/" + i);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_DEVICE_DEFAULT_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000);  // Minimum date is tomorrow
    }

    private void recalculateTotalCost(Database db, String username) {
        float totalAmount = 0;
        ArrayList dbData = db.getCartData(username, "medicine");

        for (Object item : dbData) {
            String arrData = item.toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            totalAmount += Float.parseFloat(strData[1]);
        }

        totalCost.setText("Total Cost : " + totalAmount);
    }
}
