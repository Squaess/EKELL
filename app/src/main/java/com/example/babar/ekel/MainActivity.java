package com.example.babar.ekel;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra("NAME", model.getName());
        intent.putExtra("ID", model.getMealId());
        intent.putExtra("IMAGE", model.getImageID());
        intent.putExtra("PRICE", model.getPrice());
        Toast.makeText(this, "startuje nowa intetn", Toast.LENGTH_SHORT).show();
        startActivityForResult(intent, RETURN_RATING);
    }
}
