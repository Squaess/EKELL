package com.example.babar.ekel;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends FragmentActivity implements ListFragment.OnItemClick {

    static final int RETURN_RATING = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportFragmentManager().beginTransaction().add(R.id.fragment_container, new ListFragment()).commit();
    }

    @Override
    public void onSentText(DataModel model) {
        if(model.isAvailable()) {
            Intent intent = new Intent(this, OrderActivity.class);
            intent.putExtra("NAME", model.getName());
            intent.putExtra("ID", model.getMealId());
            intent.putExtra("IMAGE", model.getImageID());
            intent.putExtra("PRICE", model.getPrice());
            startActivityForResult(intent, RETURN_RATING);
        } else {
            LayoutInflater inflater = getLayoutInflater();
            View layout = inflater.inflate(R.layout.custom_toast,
                    (ViewGroup) findViewById(R.id.custom_toast_container));

            TextView text = layout.findViewById(R.id.text);
            text.setText(R.string.error1);

            Toast toast = new Toast(getApplicationContext());
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.setDuration(Toast.LENGTH_LONG);
            toast.setView(layout);
            toast.show();

        }
    }
}
