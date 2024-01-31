package neuratruck.taisyu.joystick;

import io.github.controlwear.virtual.joystick.android.JoystickView;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;

public class CustomJoystickView extends JoystickView {
    private Paint circlePaint;

    public CustomJoystickView(Context context) {
        super(context);
        init();
    }

    public CustomJoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CustomJoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        // グレーの円の塗りつぶし色を設定
        //circlePaint = new Paint();
        //circlePaint.setColor(Color.parseColor("#CCCCCC"));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // グレーの円を描画
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY) - 40; // 半径を調整
        circlePaint.setColor(Color.BLACK);
        canvas.drawCircle(centerX, centerY, radius, circlePaint);

        super.onDraw(canvas); // スーパークラスの描画を呼び出す
    }
}
