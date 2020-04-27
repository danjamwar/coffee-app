package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayOrderDetails extends AppCompatActivity {
    String name;
    String message;
    String strTotalPrice;
    CoffeeDBHandler dbHandler = new CoffeeDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_order_details);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        message = intent.getStringExtra("message");
        strTotalPrice = intent.getStringExtra("saleAmount");
        String output = "name: " + name + message;
        TextView displayText = (TextView) findViewById(R.id.displayText);
        displayText.setText(output);
    }

    public void email(View view) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "coffee order for " + name);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
        }
    }

    public void addButtonClick(View view) {
        //method to save data in sqlite
        Order order = new Order(name, Integer.parseInt(strTotalPrice));
        dbHandler.addOrder(order);
        Toast.makeText(getApplicationContext(), "data saved", Toast.LENGTH_SHORT).show();
    }

    public void salesReport(View view) {
        String dbString = dbHandler.databaseToString();
        Intent salesIntent = new Intent(this, DisplaySalesDetails.class);
        salesIntent.putExtra("db", dbString);
        startActivity(salesIntent);
    }
}
