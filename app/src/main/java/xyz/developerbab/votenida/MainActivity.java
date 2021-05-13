package xyz.developerbab.votenida;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import xyz.developerbab.votenida.Model.Province;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText etnames, etphone, etemail;
    private Button btncontinue;
    private String names, phone, jsonresponseprovince, sex, nid, intara, akarere,email;

    private String yearr, month, date;
    private int mYear, mMonth, mDay;
    private CheckBox cxmale, cxfemale;

    private RecyclerView recyclerViewprovince;

    private ArrayList<Province> list_data;
    private Spinner spinner_province, spinner_district;
    private ProgressBar pbprovince;
    private TextView tvdob;
    private Button btndob;
    private static final int TIME_INTERVAL = 3000;
    private long mBackPressed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        Intent i=getIntent();
        intara=i.getStringExtra("province");
        akarere=i.getStringExtra("district");


        etnames = findViewById(R.id.etnames);
        etphone = findViewById(R.id.etphoner);
        etemail = findViewById(R.id.etemail);

        spinner_district = findViewById(R.id.spinner_district);
        spinner_province = findViewById(R.id.spinner_province);


        List<String> prov = new ArrayList<String>();
        prov.add("1");
        prov.add("2");
        prov.add("3");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, prov);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_province.setAdapter(dataAdapter);
        spinner_province.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        List<String> dis = new ArrayList<String>();
        dis.add("1");
        dis.add("2");
        dis.add("3");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dis);

        // Drop down layout style - list view with radio button
        dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner_district.setAdapter(dataAdapter2);
        spinner_district.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        tvdob = findViewById(R.id.tvdob);
        btndob = findViewById(R.id.btndob);
        btndob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// Get Current Date
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                tvdob.setText( year+"-"+ (monthOfYear + 1)+"-" +dayOfMonth );

                                month = (monthOfYear + 1) + "";
                                yearr = year + "";
                                date = dayOfMonth + "-" + (monthOfYear + 1) + "-" + year;

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();


            }
        });
        cxmale = findViewById(R.id.cxmale);
        cxfemale = findViewById(R.id.cxfemale);
        cxmale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "M";
                    cxfemale.setChecked(false);
                }
            }
        });

        cxfemale.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    sex = "F";
                    cxmale.setChecked(false);
                }
            }
        });

        btncontinue = findViewById(R.id.btncontinue);
        btncontinue.setOnClickListener(this);



    }

    @Override
    public void onBackPressed() {
        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
            super.onBackPressed();
            return;

        } else {
            Toast.makeText(getBaseContext(), "press back again to exit", Toast.LENGTH_SHORT).show();
        }

        mBackPressed = System.currentTimeMillis();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btncontinue:

                names = etnames.getText().toString().trim();
                phone = etphone.getText().toString().trim();
                email=etemail.getText().toString().trim();
                if (names.isEmpty() || phone.isEmpty() || email.isEmpty()) {
                    Toast.makeText(this, "Please,all options are necessary", Toast.LENGTH_LONG).show();

                    Snackbar.make(v, "Please enter all fields", Snackbar.LENGTH_LONG)
                            .setAction("Fail ", null).show();
                } else {
                    if ((phone.length()<10)||(email.length()<16)||(phone.length()>10)||(email.length()>16)){
                        Toast.makeText(this, "Please enter valid phone number or NID", Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(MainActivity.this, BiometricActivity.class);

                        intent.putExtra("name", names);
                        intent.putExtra("nid", email);
                        intent.putExtra("phone", phone);
                        intent.putExtra("sex", sex);
                        intent.putExtra("dob", tvdob.getText().toString().trim());
                        intent.putExtra("province", intara);
                        intent.putExtra("district", akarere);

                        etphone.getText().clear();
                        etemail.getText().clear();
                        etnames.getText().clear();

                        startActivity(intent);

                    }
                }
                break;
        }
    }

}
