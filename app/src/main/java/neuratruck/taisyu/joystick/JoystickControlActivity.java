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

public class JoystickControlActivity extends Activity{
    private BluetoothAdapter bluetoothAdapter;
    private BluetoothDevice bluetoothDevice;
    private BluetoothSocket bluetoothSocket;
    private OutputStream outputStream;

    private JoystickView joystickLeft, joystickRight;
    //private TextView textViewLeft, textViewRight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joystick_control);

// 左スティックのStrengthを表示するTextView
        //TextView strengthTextViewLeft = findViewById(R.id.strengthTextViewLeft);

// 左スティックのJoystickView
        JoystickView joystickLeft = findViewById(R.id.joystickLeft);

        joystickLeft.setOnMoveListener(new JoystickView.OnMoveListener() {
            @Override
            public void onMove(int angle, int strength) {
                // Handle left joystick movement
                int value = strengthToValue(strength);
                // Strengthが変更されたときにTextViewに新しい値を設定
                //strengthTextViewLeft.setText("Strength: " + strength);
                sendValueViaBluetooth("Left:" + value);
            }
        });
// 右スティックのStrengthを表示するTextView
        //TextView strengthTextViewRight = findViewById(R.id.strengthTextViewRight);

// 右スティックのJoystickView
        JoystickView joystickRight = findViewById(R.id.joystickRight);
        joystickRight.setOnMoveListener(new JoystickView.OnMoveListener() {
                @Override
                public void onMove(int angle, int strength) {
                    // Handle right joystick movement
                    int value = strengthToValue(strength);
                    // Strengthが変更されたときにTextViewに新しい値を設定
                    //strengthTextViewRight.setText("Strength: " + strength);

                    sendValueViaBluetooth("Left:" + value);
                }
        });
        // Initialize Bluetooth connection (similar to your existing code)
        setupBluetoothConnection();

    }

    private void setupBluetoothConnection() {
        // Your existing Bluetooth setup code
    }

    private int strengthToValue(int strength) {
        // 0〜100のStrengthを-128〜128の範囲に変換
        return (int) ((strength / 100.0) * 256) - 128;
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

    private void sendValueViaBluetooth(String command) {
        try {
            if (outputStream != null) {
                outputStream.write(command.getBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}