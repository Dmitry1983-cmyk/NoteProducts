package com.example.testnoteproducts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;

public class FinanceActivity extends AppCompatActivity {

    private final static String FILE_NAME = "Gains.txt";
    Calendar dateAndTime=Calendar.getInstance();
    ArrayList<String> gainsList;
    TextView txt_gainName,txt_priceGain,currentDate;
    Button btn_addgain,btn_showcost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finance);

        gainsList=new ArrayList<String>();
        currentDate=findViewById(R.id.tmpDate);
        txt_gainName=findViewById(R.id.txtnameGain);
        txt_priceGain=findViewById(R.id.txtPriceGain);
        btn_addgain=findViewById(R.id.btnAddGain);
        btn_showcost=findViewById(R.id.btnShowCost);

        Intent intent = getIntent();
//        String weigth = intent.getStringExtra("user_weight");
//        String grow = intent.getStringExtra("user_grow");
//        String sex = intent.getStringExtra("user_sex");


        setInitialDate();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void addGainMethod(View view){
        if(txt_gainName.getText().length()!=0&& txt_priceGain.getText().length()!=0){
            gainsList.add(txt_gainName.getText().toString()+"\t"+txt_priceGain.getText().toString());

            /*--------------------save to File --------------------------*/
            FileOutputStream fos = null;
            try {
                fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
                //fos.write(txt_gainName.getText().toString().getBytes());
                fos.write(txt_gainName.getText().toString().getBytes());
                Toast.makeText(getApplicationContext(), "SAVED!", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            } finally {
                try {
                    if(fos != null)
                        fos.close();

                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
            /*--------------------end save to File ----------------------*/
        }else {
            Toast.makeText(this, "Поля не могут быть пустыми!", Toast.LENGTH_SHORT).show();
        }
    }

    public void showCostmethod(View view){

    }

    public void setDateGains(View view){
        if(txt_gainName.getText().length()!=0&& txt_priceGain.getText().length()!=0){
            new DatePickerDialog(FinanceActivity.this,
                    d,
                    dateAndTime.get(Calendar.YEAR),
                    dateAndTime.get(Calendar.MONTH),
                    dateAndTime.get(Calendar.DAY_OF_MONTH)).show();

        }else {
            Toast.makeText(this, "Поля не могут быть пустыми!", Toast.LENGTH_SHORT).show();
        }
    }
    private void setInitialDate(){
        currentDate.setText(DateUtils.formatDateTime(this,dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }
    DatePickerDialog.OnDateSetListener d= new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            dateAndTime.set(Calendar.YEAR,year);
            dateAndTime.set(Calendar.MONTH,month);
            dateAndTime.set(Calendar.DAY_OF_MONTH,dayOfMonth);
            setInitialDate();
        }
    };

}