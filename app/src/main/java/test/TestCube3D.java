package test;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.dreamsocket.animation.Cube3D;
import com.dreamsocket.animationtest.R;

/**
 * Created by keithpeters on 9/3/15.
 */
public class TestCube3D extends AppCompatActivity {
    protected Cube3D m_cube1;
    protected Cube3D m_cube2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cube);

        View side1 = new View(this);
        side1.setBackground(getDrawable(R.drawable.cat_1));

        View side2 = new View(this);
        side2.setBackground(getDrawable(R.drawable.cat_2));

        View side3 = new View(this);
        side3.setBackground(getDrawable(R.drawable.ewok));

        View side4 = new View(this);
        side4.setBackground(getDrawable(R.drawable.monkey));

        this.m_cube1 = (Cube3D)this.findViewById(R.id.cube1);
        this.m_cube1.setSize(400, 400);

        this.m_cube1.setViews(side1, side2);

        this.m_cube2 = (Cube3D)this.findViewById(R.id.cube2);
        this.m_cube2.setSize(400, 400);
        this.m_cube2.setOrientation(Cube3D.VERTICAL);

        this.m_cube2.setViews(side3, side4);

        ValueAnimator animator = ValueAnimator.ofFloat(-90, 90);
        animator.setDuration(4000);
        animator.setStartDelay(2000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Float value = (Float) animation.getAnimatedValue();
                m_cube1.rotate(value);
                m_cube2.rotate(value);
            }
        });
        animator.setRepeatCount(100);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.start();
    }
}
