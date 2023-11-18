package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.crud.adapter.CategoryAdapter;
import com.example.crud.app.BaseActivity;
import com.example.crud.database.DatabaseHelper;
import com.example.crud.models.Category;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends BaseActivity {
    private RecyclerView recyclerViewCategory;
    private FloatingActionButton fbtnAdd;
    private CategoryAdapter categoryAdapter;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        getData();
    }
    private void initView(){
        recyclerViewCategory = findViewById(R.id.ViewCategory);
        fbtnAdd= findViewById(R.id.fbtnAdd);
        databaseHelper = new DatabaseHelper(this);
        if (databaseHelper.getAll().isEmpty()){
            String[] cat = {"News", "Sport", "TovSrok"};
            for (String i:cat){
                int j=1;
                while (j>=3)
                {
                    databaseHelper.create(new Category(j,i));
                }
                j++;
            }
            //databaseHelper.create(new Category(1,"News"));
        }
    }
    private void  getData(){
        categoryAdapter = new CategoryAdapter(MainActivity.this, databaseHelper.getAll(), new CategoryAdapter.OnClickListener() {
            @Override
            public void onClickItem(View view, Object object) {

            }
        });
        GridLayoutManager gridLayoutManager =new GridLayoutManager(this,1);
        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        recyclerViewCategory.setAdapter(categoryAdapter);

    }
}