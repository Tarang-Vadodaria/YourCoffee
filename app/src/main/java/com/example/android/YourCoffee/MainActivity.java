package com.example.android.YourCoffee;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.android.YourCoffee.R;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;
    public int priceOfCup = 10;
    int price = 0;
    String str="Sup!!";

    public boolean hasChocolate = false;
    public boolean hasWhippedCream = false;

    @Override
//displays a toast message incase of invalid inputs


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */

    public int calculatePrice(int quantity, int priceOfCup) {
        if (hasWhippedCream)
            priceOfCup += 5;
        if (hasChocolate)
            priceOfCup += 5;
        price = (quantity * priceOfCup);
        return price;
    }
     /*
    @param price,cream,chocolate,name
    returns text summary
     */

    private String crateOrderSummary(int price, boolean cream, boolean chocolate, String name) {
        str = "Name: " + name + "\n" + "Quantity:" + quantity + "\nHas whipped Cream?: " + cream + "\nHas Chocolate?: " + chocolate;
        str += "\n" + "Total: Rs " + price + "\n" + "Thank you!!";
        return str;
    }
    public void submitOrder(View view) {
        CheckBox whippedCreamCheckBox = (CheckBox) findViewById(R.id.WhippedCream);
        CheckBox ChocolateCheckBox = (CheckBox) findViewById(R.id.Chocolate);
        EditText Name_customer = (EditText) findViewById(R.id.Name_view);
        String Name = Name_customer.getText().toString();

        hasChocolate = ChocolateCheckBox.isChecked();
        hasWhippedCream = whippedCreamCheckBox.isChecked();
        calculatePrice(quantity, priceOfCup);
         str= crateOrderSummary(price, hasWhippedCream, hasChocolate, Name);
        String mailId="";
        Intent intent = new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto",mailId, null));
          intent.putExtra(intent.EXTRA_SUBJECT,"Just java app for"+Name);
        intent.putExtra(intent.EXTRA_TEXT,str);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
        displayMessage(str);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int numbers) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + numbers);
    }

    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "Cannot order more cups", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity += 1;
        display(quantity);
    }

    public void decrement(View view) {
        if (quantity ==0) {
            Toast.makeText(this, "Cannot order any less cups", Toast.LENGTH_SHORT).show();
            return;
        }
        quantity -= 1;
        display(quantity);

    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }




}