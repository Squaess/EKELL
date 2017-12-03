package com.example.babar.ekel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONException;
import org.json.JSONObject;

public class OrderActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://10.0.2.2:8080/";
    private Spinner table_number;
    private int meal_id;
    private ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        assert b != null;
        String name = b.getString("NAME");
        meal_id =  b.getInt("ID");
        int resourceImg = b.getInt("IMAGE");
        double price = b.getDouble("PRICE");

        table_number = findViewById(R.id.table_number);
        adapter = ArrayAdapter.createFromResource(this, R.array.tables, R.layout.spinner_item);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        table_number.setAdapter(adapter);

        ImageView imageView = findViewById(R.id.order_image);
        TextView tv_name = findViewById(R.id.order_name);
        TextView tv_price = findViewById(R.id.order_price);

        imageView.setImageResource(resourceImg);
        tv_name.setText(name);
        String to_price = "Cena: " + String.valueOf(price)+" zł";
        tv_price.setText(to_price);

        Button send_order = findViewById(R.id.order_button);
        send_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customer_id = table_number.getSelectedItem().toString();

                Ion.with(getApplicationContext())
                        .load(BASE_URL+"add/order/"+customer_id+"/"+meal_id)
                        .asString().setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        try {
                            JSONObject json = new JSONObject(result);
                            Toast.makeText(getApplicationContext(),result , Toast.LENGTH_SHORT).show();
                        } catch (JSONException e1) {
                            Toast.makeText(getApplicationContext(),"Dodano zamówienie" , Toast.LENGTH_SHORT).show();
                            e1.printStackTrace();
                            end();
                        }


                    }
                });
            }
        });

    }

    void end(){
        finish();
    }


}
