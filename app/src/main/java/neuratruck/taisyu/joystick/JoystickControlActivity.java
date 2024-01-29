package neuratruck.taisyu.joystick;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import java.io.OutputStream;
import java.util.UUID;

public class JoystickControlActivity extends Activity {
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick_control);

        // Initialize Bluetooth connection (similar to your existing code)
        setupBluetoothConnection();

        // Setup Joystick View
        JoystickView joystick = findViewById(R.id.joystickView);
        final TextView textViewAngle = findViewById(R.id.textViewAngle);
        final TextView textViewStrength = findViewById(R.id.textViewStrength);

        joystick.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                // Calculate the value to be sent over Bluetooth
                int value = calculateValue(angle, strength);
                textViewAngle.setText("Angle: " + angle);
                textViewStrength.setText("Strength: " + strength);

                // Send value over Bluetooth
                sendValueViaBluetooth(value);
            }
        });
    }

    private void setupBluetoothConnection() {
        // Your existing Bluetooth setup code
    }

    private int calculateValue(int angle, int strength) {
        // Calculate the value based on angle and strength
        // This is a simple example. You'll need to adjust the logic based on your specific needs
        int calculatedValue = (int)(255 * (strength / 100.0));
        if (angle > 180) {
            calculatedValue *= -1;
        }
        return calculatedValue;
    }

    private void sendValueViaBluetooth(int value) {
        try {
            if (outputStream != null) {
                outputStream.write(value);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}