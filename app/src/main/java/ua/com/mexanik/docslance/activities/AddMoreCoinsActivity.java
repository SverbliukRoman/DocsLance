package ua.com.mexanik.docslance.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import in.shadowfax.proswipebutton.ProSwipeButton;
import ua.com.mexanik.docslance.R;

public class AddMoreCoinsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_more_coins);

        final ProSwipeButton proSwipeBtn = (ProSwipeButton) findViewById(R.id.awesome_btn);
        proSwipeBtn.setOnSwipeListener(new ProSwipeButton.OnSwipeListener() {
            @Override
            public void onSwipeConfirm() {
                // user has swiped the btn. Perform your async operation now
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {



                        Toast.makeText(getApplicationContext(), "Processing some blockchain magic!", Toast.LENGTH_SHORT).show();
                        proSwipeBtn.showResultIcon(true); // false if task failed
                        Toast.makeText(getApplicationContext(), "Transaction was successful!", Toast.LENGTH_SHORT).show();


                        finish();
                    }
                }, 3000);
            }
        });
    }
}
