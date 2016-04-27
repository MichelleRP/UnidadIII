package michelle.practicaii;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Edtxt1;
    private TextView TxtVw2, TxtVw3;
    private int Num;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            Edtxt1 = (EditText) findViewById(R.id.EditTxt);
            TxtVw2 = (TextView) findViewById(R.id.txtVew2);
            TxtVw3 = (TextView) findViewById(R.id.txtVew3);
            SharedPreferences prefe = getSharedPreferences("datos", Context.MODE_PRIVATE);
            String v = String.valueOf(prefe.getInt("puntos", 0));
            TxtVw2.setText(v);
            Num = 1 + (int) (Math.random() * 50);
        }

        public void Comprobar (View v) {
            int valor = Integer.parseInt(Edtxt1.getText().toString());
            if (Num == valor) {
                int puntosactual = Integer.parseInt(TxtVw2.getText()
                        .toString());
                puntosactual++;
                TxtVw2.setText(String.valueOf(puntosactual));
                TxtVw3.setText("Excelente ganaste. Ahora piensa otro numero");
                Edtxt1.setText("");
                SharedPreferences preferencias =getSharedPreferences("datos", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferencias.edit();
                editor.putInt("puntos", puntosactual);
                editor.commit();
            } else {
                if (valor > Num) {
                    TxtVw3.setText("El numero que ingresaste se pasa.");
                } else {
                    TxtVw3.setText("El numero que ingresaste le falta .");
                }
            }
        }
    }
