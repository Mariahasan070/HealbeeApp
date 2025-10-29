package com.example.healbee;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {

    private String[][] doctor_details1 =
            {
                    {"Doctor Name :  \n" +
                            "Dr. A. K. Azad","Hospital Address : Ibn Sina D. Lab & Consultation Center | Doyagonj","MBBS\n" +
                            "FCPS (Physical Medicine)","Mobile No : 09610-009615","1000"},
                    {"Doctor Name : Dr.Masum Ahmed","Hospital Address : City Digital Diagnostic Center,Sylhet","MBBS(SU), PGT (Paediatrics)Children Disease","Mobile No : +8801712211303","900"},
                    {"Doctor Name : Dr. M S Alam (Utsha)","Hospital Address : Dhaka Medical College & Hospital","MBBS, FCPS (Medicine), FCPS ( Gastroenterology, Thesis), CCD (BIRDEM), EDC (BIRDEM), C-CARD","Mobile No :+8801872777770","1300"},
                    {"Doctor Name : Dr. Rashedul Hassan Kanak","Hospital Address :Green Life Medical College & Hospital","MBBS, BCS (Health), FCPS (Medicine)","Mobile No : +8801790118855","1500"},
                    {"Doctor Name : Dr. Farhana Afroz","Hospital Address : Birdem General Hospital & Ibrahim Medical College","MBBS, FCPS (Medicine)","Mobile No : +8809666787801","800" }
            };


    private String[][] doctor_details2 =
        {
                {"Doctor Name: Tamanna Chowdhury", "Hospital Address: Dhaka", "Exp: 15 years", "Mobile No: 01712345678", "800"},
                {"Doctor Name: Chowdhury Tasneem Hasin", "Hospital Address: Dhaka", "Exp: 10 years", "Mobile No: 01812345678", "900"},
                {"Doctor Name: Aktara Begum Dulee", "Hospital Address: Dhanmondi, Dhaka", "Exp: 12 years", "Mobile No: 01912345678", "850"},
                {"Doctor Name: Nazmun Nahar Begum", "Hospital Address: Dhaka", "Exp: 20 years", "Mobile No: 01612345678", "1000"},
                {"Doctor Name: Sumaiya Shahnaz", "Hospital Address: Dhanmondi, Dhaka", "Exp: 8 years", "Mobile No: 01798765432", "750"},
                {"Doctor Name: Farzana Ahmed", "Hospital Address: Dhaka", "Exp: 7 years", "Mobile No: 01898765432", "700"},
                {"Doctor Name: Sayeda Shirina", "Hospital Address: Dhaka", "Exp: 18 years", "Mobile No: 01987654321", "950"}
        };

    private String[][] doctor_details3 =
        {
                {"Doctor Name: Dr. Ayesha Rahman", "Hospital Address: Dhanmondi, Dhaka", "Exp: 10 years", "Mobile No: 01711223344", "1500"},
                {"Doctor Name: Dr. Shamsul Alam", "Hospital Address: Banani, Dhaka", "Exp: 12 years", "Mobile No: 01811223344", "2000"},
                {"Doctor Name: Dr. Tahmid Chowdhury", "Hospital Address: Gulshan, Dhaka", "Exp: 8 years", "Mobile No: 01911223344", "1800"},
                {"Doctor Name: Dr. Nusrat Jahan", "Hospital Address: Uttara, Dhaka", "Exp: 15 years", "Mobile No: 01611223344", "2500"},
                {"Doctor Name: Dr. Saif Hassan", "Hospital Address: Mirpur, Dhaka", "Exp: 7 years", "Mobile No: 01722334455", "1400"},
                {"Doctor Name: Dr. Rafiul Islam", "Hospital Address: Malibagh, Dhaka", "Exp: 5 years", "Mobile No: 01822334455", "1300"},
                {"Doctor Name: Dr. Anika Rahim", "Hospital Address: Bashundhara, Dhaka", "Exp: 9 years", "Mobile No: 01922334455", "1700"},
                {"Doctor Name: Dr. Tanvir Ahmed", "Hospital Address: Mohammadpur, Dhaka", "Exp: 11 years", "Mobile No: 01622334455", "1900"}
        };
    private String[][] doctor_details4 =
        {
                {"Doctor Name : Prof. Dr. S.M.G. Kibria", "Hospital Address : Lake View Clinic, Gulshan-2, Dhaka", "Exp : 25 years", "Mobile No : 01711-402445", "3000"},
                {"Doctor Name : Dr. Mohammad Farid Hossain", "Hospital Address : Evercare Hospital, Dhaka", "Exp : 20 years", "Mobile No : 10678", "2800"},
                {"Doctor Name : Prof. (Dr.) S. M. Abu Zafar", "Hospital Address : Evercare Hospital, Dhaka", "Exp : 18 years", "Mobile No : 10678", "3500"},
                {"Doctor Name : Dr. Abu Sayeed Mohammad", "Hospital Address : York Hospital Limited, Dhaka", "Exp : 46 years", "Mobile No : 01656789012", "1000"},
                {"Doctor Name : Dr. Mohammad Shah Alam", "Hospital Address : City Eye Hospital & Diabetes Center, Dhaka", "Exp : 35 years", "Mobile No : 01756789012", "1000"},
                {"Doctor Name : Brig. Gen. Prof. Dr. MD", "Hospital Address : Universal Medical College & Hospital Ltd., Dhaka", "Exp : 32 years", "Mobile No : 01856789012", "4000"},
                {"Doctor Name : Dr. Mahmud Chowdhury", "Hospital Address : Bangladesh Specialized Hospital Ltd., Dhaka", "Exp : 20 years", "Mobile No : 01956789012", "2800"},
                {"Doctor Name : Dr. Sandip Chakrabarti", "Hospital Address : Dhaka Medical College Hospital, Dhaka", "Exp : 15 years", "Mobile No : 01656789012", "2500"}



        };
    private String[][] doctor_details5 =
        {
                {"Doctor Name : Dr. Mohammad Shafiqul Islam", "Hospital Address : Evercare Hospital, Dhaka", "Exp : 20 years", "Mobile No : 01755512345", "3500"},
                {"Doctor Name : Prof. Dr. Md. Shahidul Islam", "Hospital Address : United Hospital, Dhaka", "Exp : 25 years", "Mobile No : 01855512345", "4000"},
                {"Doctor Name : Dr. Syed Shafqat Alam", "Hospital Address : Square Hospital, Dhaka", "Exp : 18 years", "Mobile No : 01655512345", "3000"},
                {"Doctor Name : Dr. Khurshid Alam", "Hospital Address : National Heart Foundation Hospital, Dhaka", "Exp : 30 years", "Mobile No : 01755598765", "5000"},
                {"Doctor Name : Dr. Taufiq Rahman", "Hospital Address : Ibrahim Cardiac Hospital, Dhaka", "Exp : 15 years", "Mobile No : 01855598765", "2500"},
                {"Doctor Name : Dr. Hasan Mahmud", "Hospital Address : Lab Aid Cardiac Hospital, Dhaka", "Exp : 22 years", "Mobile No : 01955512345", "3500"},
                {"Doctor Name : Dr. Zubair Ahmed", "Hospital Address : BIRDEM Hospital, Dhaka", "Exp : 20 years", "Mobile No : 01755554321", "4000"},
                {"Doctor Name : Dr. Shahinur Rahman", "Hospital Address : Dhaka Medical College Hospital, Dhaka", "Exp : 28 years", "Mobile No : 01655554321", "4500"}
        };

    LinearLayout listContainerDD;
    TextView tv;
    Button btn;
    String[][] doctor_details = {};
    HashMap<String, String> item;
    ArrayList<HashMap<String, String>> list;
    SimpleAdapter sa;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_details);

        tv = findViewById(R.id.textViewDDtitle);
        btn = findViewById(R.id.ButtonDDBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        tv.setText(title);

        // Setting doctor details based on the title
        if (title.compareTo("Family Physician") == 0) {
            doctor_details = doctor_details1;
        } else if (title.compareTo("Dietician") == 0) {
            doctor_details = doctor_details2;
        } else if (title.compareTo("Dentist") == 0) {
            doctor_details = doctor_details3;
        } else if (title.compareTo("Surgeon") == 0) {
            doctor_details = doctor_details4;
        } else if (title.compareTo("Cardiologist") == 0) {
            doctor_details = doctor_details5;
        }

        // Additional conditions for other titles here...

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DoctorDetailsActivity.this, FindDocotorActivity.class));
            }
        });

        // Create the list of doctors dynamically
        // Create the list of doctors dynamically
        list = new ArrayList<>();
        for (int i = 0; i < doctor_details.length; i++) {
            item = new HashMap<>();
            item.put("line1", doctor_details[i][0]);
            item.put("line2", doctor_details[i][1]);
            item.put("line3", doctor_details[i][2]);
            item.put("line4", doctor_details[i][3]);
            item.put("line5", "Cost Fee : " + doctor_details[i][4] + "/-");
            list.add(item);
        }

// Setting up the SimpleAdapter
        sa = new SimpleAdapter(this, list, R.layout.multi_lines,
                new String[] { "line1", "line2", "line3", "line4", "line5" },
                new int[] { R.id.line_a, R.id.line_b, R.id.line_c, R.id.line_d, R.id.line_e });

// Instead of ListView, we use LinearLayout to display the dynamic content
        listContainerDD = findViewById(R.id.listContainerDD);
        for (int i = 0; i < list.size(); i++) {
            View itemView = getLayoutInflater().inflate(R.layout.multi_lines, listContainerDD, false);
            HashMap<String, String> currentItem = list.get(i);
            ((TextView) itemView.findViewById(R.id.line_a)).setText(currentItem.get("line1"));
            ((TextView) itemView.findViewById(R.id.line_b)).setText(currentItem.get("line2"));
            ((TextView) itemView.findViewById(R.id.line_c)).setText(currentItem.get("line3"));
            ((TextView) itemView.findViewById(R.id.line_d)).setText(currentItem.get("line4"));
            ((TextView) itemView.findViewById(R.id.line_e)).setText(currentItem.get("line5"));

            // Set onClickListener for the current itemView
            final int index = i; // Capture the index for use in the OnClickListener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Create the Intent to open BookAppointmentActivity
                    Intent intent1 = new Intent(DoctorDetailsActivity.this, BookAppointmentActivity.class);
                    intent1.putExtra("text1", title); // Pass the title
                    intent1.putExtra("text2", doctor_details[index][0]); // Doctor Name
                    intent1.putExtra("text3", doctor_details[index][1]); // Hospital Address
                    intent1.putExtra("text4", doctor_details[index][3]); // Mobile No
                    intent1.putExtra("text5", doctor_details[index][4]); // Fee
                    startActivity(intent1);
                }
            });

            listContainerDD.addView(itemView);
        }

    }
}
