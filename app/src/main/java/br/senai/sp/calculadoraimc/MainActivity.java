package br.senai.sp.calculadoraimc;

import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    private TextInputLayout layoutTxtPeso;
    private TextInputLayout layoutTxtAltura;

    private EditText txtPeso;
    private EditText txtAltura;

    private TextView txtImc;
    private TextView txtResultado;
    private TextView txtResumo;

    private ImageButton btCalcular;
    private ImageButton btLimpar;
    private RelativeLayout cardView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutTxtAltura = findViewById(R.id.layout_txt_altura);
        layoutTxtPeso = findViewById(R.id.layout_txt_peso);
        txtPeso = findViewById(R.id.txt_peso);
        txtAltura = findViewById(R.id.txt_altura);
        txtImc = findViewById(R.id.view_imc);
        txtResultado = findViewById(R.id.view_imc_resultado);
        txtResumo = findViewById(R.id.view_resumo);
        btCalcular = findViewById(R.id.bt_calcular);
        btLimpar = findViewById(R.id.bt_limpar);

        btCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validar() == true){
                    calcularImc();
                }
            }
        });
        btLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpar();
            }
        });

    }

    private void calcularImc(){
        DecimalFormat df = new DecimalFormat(".#");
        double imc = 0;
        double peso = Double.parseDouble(txtPeso.getText().toString());
        double altura = Double.parseDouble(txtAltura.getText().toString());

       // imc = peso / (altura * altura);
        imc = peso / (Math.pow(altura, 2));
        txtImc.setText(String.valueOf(df.format(imc)));

        //cardView.setVisibility(View.visible)
        if(imc < 15){
            txtResultado.setText(getResources().getText(R.string.abaixo_peso_1));
            txtResumo.setText(getResources().getText(R.string.desc_abaixo_peso_1));
        }
        if(imc > 15 && imc < 18.50){
            txtResultado.setText(getResources().getText(R.string.abaixo_peso));
            txtResumo.setText(getResources().getText(R.string.desc_abaixo_peso));        }
        if(imc > 18.5 && imc < 24.99){
            txtResultado.setText(getResources().getText(R.string.peso_normal));
            txtResumo.setText(getResources().getText(R.string.desc_peso_normal));        }
        if(imc > 24.99 && imc < 29.99){
            txtResultado.setText(getResources().getText(R.string.acima_peso));
            txtResumo.setText(getResources().getText(R.string.desc_acima_peso));        }
        if(imc > 29.99 && imc < 39.99) {
            txtResultado.setText(getResources().getText(R.string.obesidade_2));
            txtResumo.setText(getResources().getText(R.string.desc_obesidade_2));        }
        if(imc > 40){
            txtResultado.setText(getResources().getText(R.string.obesidade_3));
            txtResumo.setText(getResources().getText(R.string.desc_obesidade_3));        }

    }

    private boolean validar(){
    boolean validado = true;
        if(txtPeso.getText().toString().isEmpty()){
            layoutTxtPeso.setErrorEnabled(true);
            layoutTxtPeso.setError("Por favor digite seu peso");
            validado = false;
        }else{
            layoutTxtPeso.setErrorEnabled(false);
        }
        if(txtAltura.getText().toString().isEmpty()){
            layoutTxtAltura.setErrorEnabled(true);
            layoutTxtAltura.setError("Por favor digite sua altura");
            validado = false;
        }else{
            layoutTxtAltura.setErrorEnabled(false);
        }
        return validado;
    }

    public void limpar(){
        txtPeso.setText("");
        txtAltura.setText("");
        txtImc.setText("");
        txtResultado.setText("");
        txtResumo.setText("");
        txtPeso.requestFocus();

    }



}
