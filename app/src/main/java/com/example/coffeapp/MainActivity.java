package com.example.coffeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int numofcoffe = 0;
    int priceofcoffe = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public int calculateTotalCost(boolean hasWhippedCream, boolean haschocalate) {
        if (hasWhippedCream == true) {
            priceofcoffe = priceofcoffe + 1;
        }
        if (haschocalate == true) {
            priceofcoffe = priceofcoffe + 2;
        }
        //calc total price
        int totalpice = priceofcoffe * numofcoffe;
        return totalpice;

    }

    //submit order code
    public void SubmitOrder(View view) {
        //call the method disply and pass an int
        //disply(numofcoffe);
        //TextView priceTextview = (TextView) findViewById(R.id.price_text_view);
        //priceTextview.setText("$"+numofcoffe*priceofcoffe);

        CheckBox whippedCreamCheckBox = (CheckBox)
                findViewById(R.id.whippedCreamCheckBox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        CheckBox chocalatecheckbox = (CheckBox)
                findViewById(R.id.chocalatecheckbox);
        boolean haschocalate = chocalatecheckbox.isChecked();
        int totalpice = calculateTotalCost(hasWhippedCream, haschocalate);
        EditText nameOfUser = (EditText) findViewById(R.id.NameOfUser);
        String name = nameOfUser.getText().toString();
        String message = "add whipped cream? " + hasWhippedCream + "\n" +
                "add chocolate? " + haschocalate + "\n" +
                "Quantity: " + numofcoffe + "\n" +
                "Total: $" + totalpice + "\n" +
                "Thank you";
        //create new intent to bundle off values off to other java page
        Intent intent = new Intent(this, DisplayOrderDetails.class);
        intent.putExtra("name", name);
        intent.putExtra("message", message);
        intent.putExtra("saleAmount",Integer.toString(totalpice));
        startActivity(intent);
    }

    public void disply(int number) {
        TextView quantityTextview = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextview.setText("" + number);

    }

    public void increment(View view) {
        numofcoffe++;
        if (numofcoffe >= 10) {
            numofcoffe = 10;
        }
        disply(numofcoffe);
    }

    public void decrement(View view) {
        numofcoffe--;
        if (numofcoffe <= 0) {
            numofcoffe = 1;
        }
        disply(numofcoffe);
    }

}
