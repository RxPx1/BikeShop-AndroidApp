package com.myappcompany.rob.bikeshop2.UI;

import static com.myappcompany.rob.bikeshop2.R.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myappcompany.rob.bikeshop2.R;
import com.myappcompany.rob.bikeshop2.database.Repo;
import com.myappcompany.rob.bikeshop2.entities.Product;

import java.util.List;


public class ProductList extends AppCompatActivity {
    private Repo repo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_product_list);
        RecyclerView recyclerView = findViewById(id.productrecyclerview);
        final ProductAdapter productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        repo = new Repo(getApplication());
        List<Product> allProducts;
        try {
            allProducts = repo.getAllProducts();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        productAdapter.setProducts(allProducts);


        FloatingActionButton fab1 = findViewById(R.id.addButton1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent(ProductList.this, ProductDetails.class);
                startActivity(intent1);
            }
        });


    }

    @Override
    protected void onResume(){
        super.onResume();
        List<Product> allProducts = null;
        try {
            allProducts = repo.getAllProducts();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        RecyclerView recyclerView = findViewById(id.productrecyclerview);
        final ProductAdapter productAdapter = new ProductAdapter(this);
        recyclerView.setAdapter(productAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        productAdapter.setProducts(allProducts);

    }
}