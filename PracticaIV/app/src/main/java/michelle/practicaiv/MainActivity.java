package michelle.practicaiv;

import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    private EditText Etxt1,Etxt2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        Etxt1=(EditText)findViewById(R.id.EditTxt);
        Etxt2=(EditText)findViewById(R.id.EditTxt2);
    }

    public void Grabar(View v) {
        String nomarchivo = Etxt1.getText().toString();
        String contenido = Etxt2.getText().toString();
        try {
            File tarjeta = Environment.getExternalStorageDirectory();
            Toast.makeText(this, tarjeta.getAbsolutePath(), Toast.LENGTH_LONG).show();
            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(file));
            osw.write(contenido);
            osw.flush();
            osw.close();
            Toast.makeText(this, "Todos los datos se grabaron correctamente",
                    Toast.LENGTH_SHORT).show();
            Etxt1.setText("");
            Etxt2.setText("");
        } catch (IOException ioe) {
            Toast.makeText(this, "No se puede grabar",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void Recuperar(View v) {
        String nomarchivo = Etxt1.getText().toString();
        File tarjeta = Environment.getExternalStorageDirectory();
        File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
        try {
            FileInputStream fIn = new FileInputStream(file);
            InputStreamReader archivo = new InputStreamReader(fIn);
            BufferedReader br = new BufferedReader(archivo);
            String linea = br.readLine();
            String todo = "";
            while (linea != null) {
                todo = todo + linea + " ";
                linea = br.readLine();
            }
            br.close();
            archivo.close();
            Etxt2.setText(todo);

        } catch (IOException e) {
            Toast.makeText(this, "No se puede leer",
                    Toast.LENGTH_SHORT).show();
        }
    }

}
