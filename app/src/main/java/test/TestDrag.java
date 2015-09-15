package test;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.dreamsocket.animation.Cube3D;
import com.dreamsocket.animationtest.R;

import java.util.ArrayList;

/**
 * Created by keithpeters on 9/3/15.
 */
public class TestDrag extends AppCompatActivity {
    protected Cube3D m_cube;
    protected float m_touchX;
    protected float m_rotation;
    protected int m_size = 800;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube_drag);


        this.m_cube = (Cube3D) this.findViewById(R.id.cube1);
        this.m_cube.setSize(this.m_size, this.m_size);

        this.m_cube.setDrawables(R.drawable.cat_1, R.drawable.cat_2, R.drawable.ewok, R.drawable.monkey);
//        this.m_cube.setOrientation(Cube3D.VERTICAL);
        this.m_rotation = 0;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                this.m_touchX = event.getX();
                break;

            case MotionEvent.ACTION_MOVE:
                float newX = event.getX();
                float delta = newX - this.m_touchX;
                this.m_rotation += delta / this.m_size * 90;
                this.m_touchX = newX;
                this.m_cube.rotate(this.m_rotation);
                break;

            case MotionEvent.ACTION_UP:
                break;

            default:
                break;
        }
        return super.onTouchEvent(event);
    }

}
