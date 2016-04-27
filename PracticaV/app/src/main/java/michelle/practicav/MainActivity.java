package michelle.practicav;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText num1, num2, datos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        num1 = (EditText) findViewById(R.id.txtNum1);
        num2 = (EditText) findViewById(R.id.txtNum2);
        datos = (EditText) findViewById(R.id.txtDatos);

        Cargar();


    }

    public void Suma (View view) {
        op(1);
        Cargar();
    }

    public void  Resta (View view) {
        op(2);
        Cargar();
    }

    public void Multiplicacion (View view) {
        op(3);
        Cargar();
    }

    public void Division (View view) {
        op(4);
        Cargar();
    }

    private void op(int op) {
        String num = num1.getText().toString();
        String numt = num2.getText().toString();
        if (!num.equals("") && !numt.equals("")) {
            int answer_n = Integer.parseInt(num);
            int answer_nt = Integer.parseInt(numt);
            String result;
            switch (op) {
                case 1:
                    result =  Suma (answer_n, answer_nt);
                    Toast.makeText(this, "La Suma es : " + result, Toast.LENGTH_SHORT).show();
                    Guardar (answer_n + " + " + answer_nt + " = " + result);
                    break;
                case 2:
                    result = Resta (answer_n, answer_nt);
                    Toast.makeText(this, "La Resta es : " + result, Toast.LENGTH_SHORT).show();
                    Guardar (answer_n + " - " + answer_nt + " = " + result);
                    break;
                case 3:
                    result = Multiplicacion (answer_n, answer_nt);
                    Toast.makeText(this, "La multiplicacion es: " + result, Toast.LENGTH_SHORT).show();
                    Guardar (answer_n + " * " + answer_nt + " = " + result);
                    break;
                case 4:
                    result = Division (answer_n, answer_nt);
                    Toast.makeText(this, "La Division : " + result, Toast.LENGTH_SHORT).show();
                    Guardar (answer_n + " / " + answer_nt + " = " + result);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "Ingresa un numero ", Toast.LENGTH_SHORT).show();
        }
    }

    private String Suma (int num1, int num2) {
        int suma = num1 + num2;
        String result = "" + suma;
        return result;
    }

    private String Resta (int num1, int num2) {
        int resta = num1 - num2;
        String result = "" + resta;
        return result;
    }

    private String Multiplicacion (int num1, int num2) {
        int multiplicacion = num1 * num2;
        String result = "" + multiplicacion;
        return result;
    }

    private String  Division (int num1, int num2) {
        if (num2 != 0) {
            float division = num1 / num2;
            String result = "" + division;
            return result;
        } else {
            return "No se permite hacer esta division";
        }
    }

    private void Cargar (){
        String nomarchivo="OperRealizadas.txt";
        nomarchivo=nomarchivo.replace('/','-');
        boolean enco = false;
        String[] archivos = fileList();
        for(int f = 0;f<archivos.length;f++)
            if(nomarchivo.equals(archivos[f]))
                enco=true;
        if(enco==true){
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(nomarchivo));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String todo = "";
                while (linea != null) {
                    todo = todo + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                datos.setText(todo);
            } catch (IOException e) {
            }
        }else  {

        }
    }

    private void Guardar (String data_1){
        String nombrearchivo="OperRealizadas.txt";
        nombrearchivo=nombrearchivo.replace('/','-');
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    nombrearchivo, Activity.MODE_PRIVATE));
            archivo.write(datos.getText().toString()+"\n"+data_1);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }

    }


}
