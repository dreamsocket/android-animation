package test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.dreamsocket.animation.AnimationSequencer;
import com.dreamsocket.animationtest.R;

public class TestSequencer extends AppCompatActivity {
    protected TextView m_text;
    protected View m_rect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.m_text = (TextView) this.findViewById(R.id.text);
        this.m_rect = this.findViewById(R.id.rect);

        sequence();
//        parallel();
//        sequenceMultipleTarget();
//        parallelMultipleTarget();
//        sequenceWithParallel();
//        parallelWithSequences();
    }


    protected void sequence() {
        // will do all of these in sequence
        AnimationSequencer.newSequence()
                .target(this.m_text)
                .alpha(0)
                .alpha(1)
                .x(600)
                .x(1000, 1000, 1000)
                .y(100)
                .moveTo(700, 300)
                .scaleX(2)
                .scaleY(2)
                .scale(1)
                .rotationX(360)
                .rotationY(360)
                .rotation(360, 1000)
                .setStartDelay(3000)
                .start();
    }


    protected void parallel() {
        // will do all of these at once
        AnimationSequencer.newParallel()
                .target(this.m_rect)
                .x(600)
                .y(300)
                .scaleX(2)
                .scaleY(0.5f)
                .alpha(0.5f)
                .rotation(90)
                .setStartDelay(2000)
                .start();
    }

    protected void sequenceMultipleTarget() {
        // will do all of these in sequence, but targeting different objects
        AnimationSequencer.newSequence()
                .target(this.m_text)
                .y(200)
                .x(500)
                .rotation(360)

                .target(this.m_rect)
                .alpha(0.5f)
                .y(300)
                .alpha(1)
                .rotationY(360)

                .target(this.m_text)
                .scale(2)
                .x(100)
                .scale(1)

                .setStartDelay(2000)
                .start();
    }

    protected void parallelMultipleTarget() {
        // will do all of this at once, targeting different objects
        AnimationSequencer.newParallel()
                .target(this.m_text)
                .x(500)
                .rotationY(360)

                .target(this.m_rect)
                .y(400)
                .rotationX(360)

                .setStartDelay(2000)
                .start();
    }

    protected void sequenceWithParallel() {
        AnimationSequencer.newSequence()
                // will do these in sequence
                .target(this.m_text)
                .y(300)

                .target(this.m_rect)
                .y(300)

                // at this point in the sequence, these actions will be performed all at once
                .insert(AnimationSequencer.newParallel()
                                .target(this.m_text)
                                .rotationY(720, 2000)

                                .target(this.m_rect)
                                .rotationX(720, 2000)
                )

                // following the parallel animation, these will continue in sequence
                .target(this.m_text)
                .y(0)

                .target(this.m_rect)
                .y(0)

                .setStartDelay(2000)
                .start();
    }

    protected void parallelWithSequences() {
        // will play both of these sequences at the same time
        AnimationSequencer.newParallel()
                .insert(AnimationSequencer.newSequence()
                        .target(this.m_text)
                        .y(400)
                        .x(300)
                        .rotationY(360)
                        .alpha(0)
                        .alpha(1)
                )
                .insert(AnimationSequencer.newSequence()
                        .target(this.m_rect)
                        .x(100)
                        .y(300)
                        .rotationX(360)
                        .alpha(0)
                        .alpha(1)
                )

                .setStartDelay(2000)
                .start();
    }
}
