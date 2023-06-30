package com.myappcompany.rob.bikeshop2.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.myappcompany.rob.bikeshop2.R;
import com.myappcompany.rob.bikeshop2.database.Repo;
import com.myappcompany.rob.bikeshop2.entities.Part;
import com.myappcompany.rob.bikeshop2.entities.Product;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Product product = new Product(0, "bicycle", 100.00);
//        Repo repo = new Repo(getApplication());
//        repo.insert(product);

        Button button1 = findViewById(R.id.enterButton);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProductList.class);
                startActivity(intent);
            }
        });


    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.addSampleData) {
            Product product = new Product(0, "bicycle", 100.00);
            Repo repo = new Repo(getApplication());
            repo.insert(product);
            Part part = new Part(0, "wheel", 10.00, 1);
            repo.insert(part);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}