package css.cis3334.pizzaorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements updateViewInterface {

    RadioButton rbSmall;
    RadioButton rbMedium;
    RadioButton rbLarge;
    CheckBox chkbxCheese;
    CheckBox chkbxDelivery;
    TextView txtTotal;
    TextView txtStatus;
    Spinner selectTopping;
    PizzaOrderInterface pizzaOrderSystem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rbSmall = (RadioButton) findViewById(R.id.radioButtonSmall);
        rbMedium = (RadioButton) findViewById(R.id.radioButtonMedium);
        rbLarge = (RadioButton) findViewById(R.id.radioButtonLarge);

        chkbxCheese = (CheckBox) findViewById(R.id.checkBoxCheese);
        chkbxDelivery = (CheckBox) findViewById(R.id.checkBoxDeluvery);

        txtTotal = (TextView) findViewById(R.id.textViewTotal);
        txtStatus = (TextView) findViewById(R.id.textViewStatus);

        selectTopping = (Spinner) findViewById(R.id.spinner);

        pizzaOrderSystem = new PizzaOrder(this);
    }

    @Override
    public void updateView(String orderStatus) {
        txtStatus.setText("Order Status" + orderStatus);
    }

    public void onClickOrder(View view) {
        String pizzaSize = "";
        String selectTop = "";
        Boolean cheesy = false;
        String orderDescription;

        if (rbSmall.isChecked()) {
            pizzaSize = "small";
        }
        else if (rbMedium.isChecked()) {
            pizzaSize = "medium";
        }
        else if (rbLarge.isChecked()) {
            pizzaSize = "large";
        }

        selectTop = selectTopping.getSelectedItem().toString();

        if (chkbxCheese.isChecked()) {
            cheesy = true;
        }
        if (chkbxDelivery.isChecked()) {
            pizzaOrderSystem.setDelivery(true);
        }

        orderDescription = pizzaOrderSystem.OrderPizza(selectTop, pizzaSize, cheesy);
        //display a pop up message for a long period of time
        Toast.makeText(getApplicationContext(), "You have ordered a " + orderDescription , Toast.LENGTH_LONG).show();
        txtTotal.setText("Total Due: " + pizzaOrderSystem.getTotalBill().toString());
    }
}
