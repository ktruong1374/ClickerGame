package com.example.clickergame;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView cashAmount;
    TextView upgCost;
    int cash = 0;
    int upgradeMultiplier = 1;
    int upgradeCost = 25;

    TextView autoClickerCost;
    int autoClick = 1;
    int autoMult = 1;
    int delay = 0;
    int period = 1000;
    int autoUpgradeCost = 50;
    int autoUpgradeMult = 1;
    Timer timer = new Timer();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = findViewById(R.id.tapArea);
        Button btnUpgrade = findViewById(R.id.btnUpgrade);
        Button btnAuto = findViewById(R.id.btnAuto);

        btn.setOnClickListener(this);
        btnUpgrade.setOnClickListener(this);
        btnAuto.setOnClickListener(this);

        }

    @Override
    public void onClick(View view) {
        cashAmount = (TextView) findViewById(R.id.cashView);
        cashAmount.setText("$" + cash);

        Log.i("MyApp", "$" + cash);
        Log.i("MyApp", autoClick + "");
        switch (view.getId()) {
            //basic tap function, increases cash per tap
            case R.id.tapArea:
                cash = cash + upgradeMultiplier;
                cashAmount = (TextView) findViewById(R.id.cashView);
                cashAmount.setText("$" + cash);
                break;
            //each tap increases cash gained per tap by 1, upgrade cost takes away from existing cash
            case R.id.btnUpgrade:
                upgCost = (TextView) findViewById(R.id.upgCost);
                upgCost.setText("$" + upgradeCost);

                if(cash >= upgradeCost) {               //can only upgrade if enough cash is available
                    cash = cash - upgradeCost;
                    upgradeMultiplier++;
                    upgradeCost = upgradeCost * upgradeMultiplier;
                    upgCost.setText("$" + upgradeCost);
                    cashAmount.setText("$" + cash);
                }
                break;
            case R.id.btnAuto:
                if(cash >= autoUpgradeCost) {
                    timer.scheduleAtFixedRate(new TimerTask() {
                        public void run() {
                            autoClick = autoClick * autoMult;
                            cash = cash + autoClick;
                            cashAmount.setText("$" + cash);
                        }
                    }, delay, period);
                    autoClickerCost = (TextView) findViewById(R.id.autoClickerCost);
                    cash = cash - autoUpgradeCost;
                    autoUpgradeMult++;
                    autoUpgradeCost = autoUpgradeMult * autoUpgradeCost;
                    autoClickerCost.setText("$" + autoUpgradeCost);
                    cashAmount.setText("$" + cash);
                }
                break;
        }
    }
}