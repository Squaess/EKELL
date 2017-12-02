package com.example.babar.ekel;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    public static final String BASE_URL = "http://10.0.2.2:8080/";
    public OnItemClick mCallback;
    ListView listView;
    RowAdapter rowAdapter;
    ArrayList<DataModel> mArray;

    private int[] imageresources = {R.drawable.picture_test, R.drawable.a1,
            R.drawable.a2, R.drawable.picture_test, R.drawable.picture_test, R.drawable.picture_test,
            R.drawable.picture_test, R.drawable.picture_test, R.drawable.a8, R.drawable.picture_test,
            R.drawable.picture_test, R.drawable.picture_test, R.drawable.a12, R.drawable.picture_test,
            R.drawable.picture_test, R.drawable.picture_test, R.drawable.picture_test, R.drawable.picture_test,
            R.drawable.picture_test, R.drawable.picture_test, R.drawable.picture_test};

    interface OnItemClick {
        void onSentText(DataModel model);
    }

    public ListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mCallback = (OnItemClick) getActivity();
        } catch (ClassCastException e) {
            throw new ClassCastException(getActivity().toString()
                    + " must implement TextClicked");
        }
    }

    @Override
    public void onDetach() {
        mCallback = null;
        super.onDetach();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedState) {
        super.onActivityCreated(savedState);
        listView = getActivity().findViewById(R.id.list_view);
        mArray = new ArrayList<>();
        fetchData();
        rowAdapter = new RowAdapter(getActivity(), R.layout.custom_row, mArray);
        listView.setAdapter(rowAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCallback.onSentText(mArray.get(position));
            }
        });
    }

    private void fetchData() {
        String urlString = BASE_URL+"get/meal/all";
        Ion.with(this).load(urlString).asString().setCallback(new FutureCallback<String>() {
            @Override
            public void onCompleted(Exception e, String result) {
                try {
                    JSONObject json = new JSONObject(result);
                    processData(json);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        });
    }

    private void processData(JSONObject json) {
        try {
            JSONArray array = json.getJSONArray("content");

            for (int i = 0; i<array.length(); i++) {
                JSONObject o = array.getJSONObject(i);
                DataModel dataModel = new DataModel(o.getBoolean("available"), o.getInt("mealId"),
                        o.getString("mealName"), o.getDouble("price"));
                dataModel.setImageId(imageresources[o.getInt("mealId")]);
                mArray.add(dataModel);
            }

            rowAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
