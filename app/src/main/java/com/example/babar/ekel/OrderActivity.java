package com.example.babar.ekel;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

public class OrderActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://10.0.2.2:8080/";
    private EditText table_number;
    private int meal_id;
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

        ImageView imageView = findViewById(R.id.order_image);
        TextView tv_name = findViewById(R.id.order_name);
        TextView tv_price = findViewById(R.id.order_price);

        imageView.setImageResource(resourceImg);
        tv_name.setText(name);
        tv_price.setText(String.valueOf(price));

        Button send_order = findViewById(R.id.order_button);
        send_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String customer_id = String.valueOf(table_number.getText());

                Ion.with(getApplicationContext()).load(BASE_URL+"add/order/"+customer_id+"/"+meal_id).asString().setCallback(new FutureCallback<String>() {
                    @Override
                    public void onCompleted(Exception e, String result) {
                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }


}
