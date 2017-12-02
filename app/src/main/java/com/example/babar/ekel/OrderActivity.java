package com.example.babar.ekel;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("NAME");
        int id =  b.getInt("ID");
        int resourceImg = b.getInt("IMAGE");
        double price = b.getDouble("PRICE");

        ImageView imageView = findViewById(R.id.order_image);
        TextView tv_name = findViewById(R.id.order_name);
        TextView tv_price = findViewById(R.id.order_price);

        imageView.setImageResource(resourceImg);
        tv_name.setText(name);
        tv_price.setText(String.valueOf(price));

    }


}
