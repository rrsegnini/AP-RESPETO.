package cr.ac.tec.ec.respeto;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class PantallaNumEmergencia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla_num_emergencia);

        setHelpInfo();
        setCallButtons();
    }

    private void setHelpInfo() {
        try {
            CardView n911 = findViewById(R.id.emergencia_crd911);
            CardView nCruzRoja = findViewById(R.id.emergencia_crdCruzRoja);
            CardView nRural = findViewById(R.id.emergencia_crdRural);
            CardView nPolicia = findViewById(R.id.emergencia_crdPolicia);
            CardView nINAMU = findViewById(R.id.emergencia_crdINAMU);


            n911.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(n911);
                }
            });

            nCruzRoja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(nCruzRoja);
                }
            });

            nRural.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(nRural);
                }
            });

            nPolicia.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(nPolicia);
                }
            });

            nINAMU.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    animateCardView(nINAMU);
                }
            });
        } catch (Exception e) {
            Toast.makeText(PantallaNumEmergencia.this, e.getMessage(), Toast.LENGTH_SHORT);
        }

    }

    private void setCallButtons() {
        Button c911 = findViewById(R.id.emergencia_btn911);
        Button cPolicia = findViewById(R.id.emergencia_btnPolicia);
        Button cCruzRoja = findViewById(R.id.emergencia_btnCruzRoja);
        Button cRural = findViewById(R.id.emergencia_btnRural);
        Button cInamu = findViewById(R.id.emergencia_btnINAMU);

        c911.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call911();
            }
        });
        cPolicia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callPolicia();
            }
        });
        cCruzRoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call1CruzRoja();
            }
        });
        cRural.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callRural();
            }
        });
        cInamu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callINAMU();
            }
        });
    }

    private void animateCardView(CardView cardview) {
        int expandedSize = 300;
        int collapsedSize = 250;
        TextView question = (TextView) cardview.getChildAt(0);
        LinearLayout descriptionLayout1 = (LinearLayout) cardview.getChildAt(1);
        LinearLayout descriptionLayout2 = (LinearLayout) descriptionLayout1.getChildAt(1);
        Button description = (Button) descriptionLayout2.getChildAt(1);


        if (cardview.getHeight() != expandedSize) {
            //denuncia.setContentDescription("Prueba 1");
            expandView(cardview, expandedSize);
            question.setVisibility(View.INVISIBLE);
            description.setVisibility(View.VISIBLE);
        } else {
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

    private void call911() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:911"));
        startActivity(intent);
    }

    private void callPolicia() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:117"));
        startActivity(intent);
    }

    private void call1CruzRoja() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:128"));
        startActivity(intent);
    }

    private void callRural() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:127"));
        startActivity(intent);
    }

    private void callINAMU() {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:2527-8400"));
        startActivity(intent);
    }
}
