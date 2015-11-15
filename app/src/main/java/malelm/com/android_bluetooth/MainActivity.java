package malelm.com.android_bluetooth;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends Activity {
    Button bn;

    BluetoothAdapter b_adapter ;
    int BLUETOOTH_REQUEST = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bn = (Button) findViewById(R.id.button);

        bn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // getDefaultAdapter returns a handler to the local Bluetooth adapter or null
                // if the device does not support bluetooth
                b_adapter =  BluetoothAdapter.getDefaultAdapter();

                if(b_adapter == null){
                    Toast.makeText(getBaseContext(), "No bluetooth adapter found", Toast.LENGTH_LONG).show();
                }else{
                    // check if the bluetooth is already off
                    if(!b_adapter.isEnabled()) {
                        Intent i = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE);
                        startActivityForResult(i ,  BLUETOOTH_REQUEST);

                    }else{
                        Toast.makeText(getBaseContext(), "Bluetooth adapter is on", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

    }

    public void onActivityResult(int request_code , int result_code, Intent intent){
        if(request_code == BLUETOOTH_REQUEST ){
            // if the request_code equals RESULT_OK that means the bluetooth adapter is turned on
            if(result_code == RESULT_OK){
                Toast.makeText(getBaseContext(), "bluetooth have been successfully turned on", Toast.LENGTH_LONG).show();
            }
            if(result_code==RESULT_CANCELED){
                Toast.makeText(getBaseContext(), "Bluetooth turned on failed", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
