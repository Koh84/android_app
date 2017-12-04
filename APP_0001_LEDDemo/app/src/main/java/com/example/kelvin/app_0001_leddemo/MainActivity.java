package com.example.kelvin.app_0001_leddemo;

import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;
import android.os.ILedService;
import android.os.ServiceManager;

public class MainActivity extends AppCompatActivity {

    private boolean ledon = false;
    private Button button = null;
    private CheckBox checkBoxLed1 = null;
    private CheckBox checkBoxLed2 = null;
    private CheckBox checkBoxLed3 = null;
    private CheckBox checkBoxLed4 = null;
    private ILedService iLedService = null;

    class MyButtonListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            ledon = !ledon;
            if(ledon) {
                button.setText("ALL OFF");
                checkBoxLed1.setChecked(true);
                checkBoxLed2.setChecked(true);
                checkBoxLed3.setChecked(true);
                checkBoxLed4.setChecked(true);

                try {
                    for(int i = 0; i<4; i++)
                        iLedService.ledCtrl(i,1);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
            else{
                button.setText("ALL ON");
                checkBoxLed1.setChecked(false);
                checkBoxLed2.setChecked(false);
                checkBoxLed3.setChecked(false);
                checkBoxLed4.setChecked(false);

                try {
                    for(int i = 0; i<4; i++)
                        iLedService.ledCtrl(i,0);
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        try {
            // Check which checkbox was clicked
            switch(view.getId()) {
                case R.id.LED1:
                    if (checked) {
                        // Put some meat on the sandwich
                        Toast.makeText(getApplicationContext(), "LED1 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0,1);
                    }
                    else {
                        // Remove the meat
                        Toast.makeText(getApplicationContext(), "LED1 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(0,0);
                    }
                    break;
                case R.id.LED2:
                    if (checked) {
                        // Put some meat on the sandwich
                        Toast.makeText(getApplicationContext(), "LED2 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1,1);
                    }
                    else {
                        // Remove the meat
                        Toast.makeText(getApplicationContext(), "LED2 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(1,0);
                    }
                    break;
                case R.id.LED3:
                    if (checked) {
                        // Put some meat on the sandwich
                        Toast.makeText(getApplicationContext(), "LED3 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2,1);
                    }
                    else {
                        // Remove the meat
                        Toast.makeText(getApplicationContext(), "LED3 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(2,0);
                    }
                    break;
                case R.id.LED4:
                    if (checked) {
                        // Put some meat on the sandwich
                        Toast.makeText(getApplicationContext(), "LED4 on", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3,1);
                    }
                    else {
                        // Remove the meat
                        Toast.makeText(getApplicationContext(), "LED4 off", Toast.LENGTH_SHORT).show();
                        iLedService.ledCtrl(3,0);
                    }
                    break;
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iLedService = ILedService.Stub.asInterface(ServiceManager.getService("led"));

        button = (Button) findViewById(R.id.BUTTON);
        button.setOnClickListener(new MyButtonListener());

        checkBoxLed1 = (CheckBox) findViewById(R.id.LED1);
        checkBoxLed2 = (CheckBox) findViewById(R.id.LED2);
        checkBoxLed3 = (CheckBox) findViewById(R.id.LED3);
        checkBoxLed4 = (CheckBox) findViewById(R.id.LED4);



/*
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                ledon = !ledon;
                if(ledon)
                    button.setText("ALL OFF");
                else
                    button.setText("ALL ON");
            }
        });
        */
    }
}
