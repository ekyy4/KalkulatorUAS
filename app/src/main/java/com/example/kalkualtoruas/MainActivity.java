package com.example.kalkualtoruas;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<ItemList> mExampleList;
    private RecyclerView mRecyclerView;
    private SharedPreferenceAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    RadioGroup operasiGroup;
    RadioButton tambah, kurang, kali, bagi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
        buildRecyclerView();
        setInsertButton();

        Button buttonDelete = findViewById(R.id.delete);
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteData();
            }
        });

        operasiGroup = findViewById(R.id.operasiGroup);
        tambah = findViewById(R.id.radioTambah);
        kurang = findViewById(R.id.radioKurang);
        kali = findViewById(R.id.radioKali);
        bagi = findViewById(R.id.radioBagi);
    }

    private void saveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(mExampleList);
        editor.putString("task list", json);
        editor.apply();
    }

    private void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<ItemList>>() {}.getType();
        mExampleList = gson.fromJson(json, type);

        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
    }

    private void buildRecyclerView() {
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new SharedPreferenceAdapter(mExampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void setInsertButton() {
        Button buttonInsert = findViewById(R.id.kirim);
        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText line1 = findViewById(R.id.Number1);
                EditText line2 = findViewById(R.id.Number2);
                insertItem(line1.getText().toString(), line2.getText().toString());
                saveData();
            }
        });
    }

    private void insertItem(String line1, String line2) {
        int hasil = 0;
        if (tambah.isChecked()) {
            hasil = Integer.parseInt(line1) + Integer.parseInt(line2);
        } else if (kurang.isChecked()) {
            hasil = Integer.parseInt(line1) - Integer.parseInt(line2);
        } else if (kali.isChecked()) {
            hasil = Integer.parseInt(line1) * Integer.parseInt(line2);
        } else if (bagi.isChecked()) {
            hasil = Integer.parseInt(line1) / Integer.parseInt(line2);
        }

        String operasi = "";
        if (tambah.isChecked()) {
            operasi = "+";
        } else if (kurang.isChecked()) {
            operasi = "-";
        } else if (kali.isChecked()) {
            operasi = "x";
        } else if (bagi.isChecked()) {
            operasi = ":";
        }

        mExampleList.add(new ItemList(line1, operasi, line2, String.valueOf(hasil)));
        mAdapter.notifyItemInserted(mExampleList.size());
    }

    @SuppressLint("NotifyDataSetChanged")
    private void deleteData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mExampleList.clear();
        mAdapter.notifyDataSetChanged();
    }

}