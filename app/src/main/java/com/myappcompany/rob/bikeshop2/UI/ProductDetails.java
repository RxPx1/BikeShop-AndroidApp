package com.myappcompany.rob.bikeshop2.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.myappcompany.rob.bikeshop2.R;
import com.myappcompany.rob.bikeshop2.database.Repo;
import com.myappcompany.rob.bikeshop2.entities.Product;

public class ProductDetails extends AppCompatActivity {

    EditText editName;
    EditText editPrice;
    String name;
    double price;
    int id;
    Product product;
    Repo repo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        editName = findViewById(R.id.productname);
        editPrice = findViewById(R.id.productprice);
        id = getIntent().getIntExtra("id", -1);
        name = getIntent().getStringExtra("name");
        price = getIntent().getDoubleExtra("price", 1);
        editName.setText(name);
        editPrice.setText(Double.toString(price));
        repo = new Repo(getApplication());
        final PartAdapter partAdapter = new PartAdapter(this);
        RecyclerView recyclerView = findViewById(R.id.partrecyclerview);
        recyclerView.setAdapter(partAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        try {
            partAdapter.setParts(repo.getAllParts());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        Button button1 = findViewById(R.id.saveproduct);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(id == -1){
                    product = new Product(0,editName.getText().toString(), Double.parseDouble(editPrice.getText().toString()));
                    repo.insert(product);

                } else {
                    product = new Product(id, editName.getText().toString(), Double.parseDouble(editPrice.getText().toString()));
                    repo.update(product);

                }

            }
        });

        FloatingActionButton fab4 = findViewById(R.id.addButton2);
        fab4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(ProductDetails.this, PartDetails.class);
                startActivity(intent4);
            }
        });
    }
}