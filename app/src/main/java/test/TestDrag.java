package test;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;

import com.dreamsocket.animation.Cube3D;
import com.dreamsocket.animationtest.R;

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

        View side1 = new View(this);
        side1.setBackground(getDrawable(R.drawable.cat_1));

        View side2 = new View(this);
        side2.setBackground(getDrawable(R.drawable.cat_2));

        this.m_cube = (Cube3D) this.findViewById(R.id.cube1);
        this.m_cube.setSize(this.m_size, this.m_size);

        this.m_cube.setViews(side1, side2);
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

    //        ValueAnimator animator = ValueAnimator.ofFloat(-90, 90);
//        animator.setDuration(4000);
//        animator.setStartDelay(2000);
//        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                Float value = (Float) animation.getAnimatedValue();
//                m_cube.rotate(value);
//            }
//        });
//        animator.setRepeatCount(100);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.start();
//    }
}
