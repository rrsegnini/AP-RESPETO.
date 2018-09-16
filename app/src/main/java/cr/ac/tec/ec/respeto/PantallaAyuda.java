package cr.ac.tec.ec.respeto;

import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaAyuda extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_ayuda);



        WindowManager windowmanager = (WindowManager)getApplicationContext().getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dimension = new DisplayMetrics();
        windowmanager.getDefaultDisplay().getMetrics(dimension);
        final int height = dimension.heightPixels;

        CardView denuncia = findViewById(R.id.ayuda_crdDenuncia);
        setHelpInfo();
    }

    private void setHelpInfo(){
        try {
            CardView denuncia = findViewById(R.id.ayuda_crdDenuncia);
            CardView funciona = findViewById(R.id.ayuda_crdFunciona);
            CardView comentario = findViewById(R.id.ayuda_crdComentario);
            CardView agresion = findViewById(R.id.ayuda_crdAgresion);

            denuncia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(denuncia);
                }
            });

            funciona.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(funciona);
                }
            });

            comentario.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(comentario);
                }
            });

            agresion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(agresion);
                }
            });
        }catch(Exception e){
            Toast.makeText(PantallaAyuda.this, e.getMessage(), Toast.LENGTH_SHORT);
        }

    }

    private void animateCardView(CardView cardview){
        int expandedSize = 400;
        int collapsedSize = 100;
        TextView question = (TextView)cardview.getChildAt(0);
        TextView description = (TextView)cardview.getChildAt(1);

        if (cardview.getHeight() != expandedSize) {
            //denuncia.setContentDescription("Prueba 1");
            expandView(cardview, expandedSize);
            question.setVisibility(View.INVISIBLE);
            description.setVisibility(View.VISIBLE);
        }else{
            expandView(cardview, collapsedSize);
            question.setVisibility(View.VISIBLE);
            description.setVisibility(View.INVISIBLE);
        }

    }


    private void collapseView(CardView cardview, int width) {

        ValueAnimator anim = ValueAnimator.ofInt(cardview.getMeasuredHeightAndState(),
                width);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = cardview.getLayoutParams();
                layoutParams.height = val;
                cardview.setLayoutParams(layoutParams);

            }
        });
        anim.start();
    }

    private void expandView(CardView cardview, int height) {

        ValueAnimator anim = ValueAnimator.ofInt(cardview.getMeasuredHeightAndState(),
                height);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = cardview.getLayoutParams();
                layoutParams.height = val;
                cardview.setLayoutParams(layoutParams);
            }
        });
        anim.start();

    }
}
