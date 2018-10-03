package com.ajay.creditcardvalidation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText et_card_number;

  public static  ArrayList<String> listOfPattern = new ArrayList<String>();

    static {

        String ptVisa = "^4[0-9]{6,}$";
        listOfPattern.add(ptVisa);
        String ptMasterCard = "^5[1-5][0-9]{5,}$";
        listOfPattern.add(ptMasterCard);
        String ptAmeExp = "^3[47][0-9]{5,}$";
        listOfPattern.add(ptAmeExp);
        String ptDinClb = "^3(?:0[0-5]|[68][0-9])[0-9]{4,}$";
        listOfPattern.add(ptDinClb);
        String ptDiscover = "^6(?:011|5[0-9]{2})[0-9]{3,}$";
        listOfPattern.add(ptDiscover);
        String ptJcb = "^(?:2131|1800|35[0-9]{3})[0-9]{3,}$";
        listOfPattern.add(ptJcb);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_card_number = (EditText) findViewById(R.id.et_card_number);



        et_card_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.d("DEBUG", "beforeTextChanged : "+s);

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d("DEBUG", "afterTextChanged : "+s);
               boolean mValid = isValid(s.toString());
                String ccNum = s.toString();
                for(String p:listOfPattern){
                    if(ccNum.matches(p)){
                        Log.d("DEBUG", "afterTextChanged : discover"+"  "+mValid);
                        break;
                    }
                }
            }
        });

    }


    public boolean isValid(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int n = Integer.parseInt(cardNumber.substring(i, i + 1));
            if (alternate) {
                n *= 2;
                if (n > 9) {
                    n = (n % 10) + 1;
                }
            }
            sum += n;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
