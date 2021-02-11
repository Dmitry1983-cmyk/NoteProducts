package com.example.testnoteproducts;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView productsList;
    ArrayList<String> products =new ArrayList<>() ;

    Calendar dateAndTime=Calendar.getInstance();
    TextView currentTime,currentDate,txt_product,txt_price;
    Button btn_setDate,btn_setTime,btn_addProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_setDate=findViewById(R.id.btn_SetDate);
        btn_setTime=findViewById(R.id.btn_SetTime);
        btn_addProduct=findViewById(R.id.btn_AddProduct);
        currentTime=findViewById(R.id.tmp_time);
        currentDate=findViewById(R.id.tmp_date);
        productsList=findViewById(R.id.productList);
        txt_product=findViewById(R.id.txtProduct);
        txt_price=findViewById(R.id.txtPrice);

        products.add("Apple:45");
        products.add("Orange:45");
        products.add("Banana:35");
        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, products);
        productsList.setAdapter(adapter);

        productsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), products.get(position), Toast.LENGTH_LONG).show();
                products.remove(products.get(position));
            }
        });

        setInitialTime();
        setInitialDate();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
/*----------------------------set Time ----------------------------------*/
    public void setTime(View v){
        new TimePickerDialog(MainActivity.this,
                t,
                dateAndTime.get(Calendar.HOUR_OF_DAY),
                dateAndTime.get(Calendar.MINUTE),
                true).show();
//        btn_setDate.setEnabled(false);
//        btn_setTime.setEnabled(false);
//        btn_addProduct.setEnabled(true);
    }
    private void setInitialTime(){
        currentTime.setText(DateUtils.formatDateTime(this,dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_TIME));
    }

    TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            dateAndTime.set(Calendar.HOUR_OF_DAY, hourOfDay);
            dateAndTime.set(Calendar.MINUTE, minute);
            setInitialTime();
        }
    };
    /*----------------------------end set Time ----------------------------------*/

    /*----------------------------set Date ----------------------------------*/
    public void setDate(View v){
        new DatePickerDialog(MainActivity.this,
                d,
                dateAndTime.get(Calendar.YEAR),
                dateAndTime.get(Calendar.MONTH),
                dateAndTime.get(Calendar.DAY_OF_MONTH)).show();

//        btn_setDate.setEnabled(false);
//        btn_setTime.setEnabled(false);
//        btn_addProduct.setEnabled(true);
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
    private void setInitialDate(){
        currentDate.setText(DateUtils.formatDateTime(this,dateAndTime.getTimeInMillis(),DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR));
    }

    /*----------------------------end set Date ----------------------------------*/

    /*----------------------------add Product ----------------------------------*/
    public void addProductsmethod(View v){
        products.add(txt_product.getText().toString()+ " / " +currentDate.getText()  +" / " + currentTime.getText()+":"+ txt_price.getText().toString());
        txt_price.setText("");
        txt_product.setText("");

        ArrayAdapter<String> adapter = new ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, products);
        productsList.setAdapter(adapter);
    }
    /*----------------------------end add Product ----------------------------------*/
}