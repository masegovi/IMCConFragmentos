package com.mariosegovia.android.imc;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;

public class imcFragment extends Fragment {
    private final String KEY_IMC="imc";
    private EditText mCampoPeso;
    private EditText mCampoestatura;
    private Button mBotonCalcular;
    private Button  mBotonLimpiar;
    private TextView mImcTextView;
    private TextView mNutricionalTextView;
    private double imc;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragments_imc,container,false);
        mCampoPeso = view.findViewById(R.id.campo_peso);
        mCampoestatura = view.findViewById(R.id.campo_estatura);
        mBotonCalcular = view.findViewById(R.id.boton_calcular);
        mImcTextView = view.findViewById(R.id.imc_text_view);
        mNutricionalTextView= view.findViewById(R.id.nutricional_text_view);

        mBotonCalcular.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String s = mCampoPeso.getText().toString();
                double peso = Double.parseDouble(s);
                String s1 = mCampoestatura.getText().toString();
                double estatura = Double.parseDouble(s1);
                imc = peso / Math.pow(estatura,2);
                //mImcTextView.setText(Double.toString(imc));
                DecimalFormat df = new DecimalFormat("#.00");
                String res = df.format(imc).toString();
                mImcTextView.setText(res);

                if(imc <18.5){
                    mNutricionalTextView.setText("Ud tiene peso bajo");
                }else if(imc >= 18.5 && imc < 24.99){
                    mNutricionalTextView.setText("Ud tiene peso normal");
                }else if(imc >= 25.0 && imc < 29.99){
                    mNutricionalTextView.setText("Ud tiene Sobrepeso");
                }else if(imc >= 30.0 && imc < 39.99){
                    mNutricionalTextView.setText("Ud tiene obesidad");
                }else{
                    mNutricionalTextView.setText("Ud tiene obesidad extrema");
                }
            }
        });
        mBotonLimpiar = view.findViewById(R.id.boton_limpiar);
        mBotonLimpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                mCampoPeso.setText(" ");
                mCampoestatura.setText(" ");
                mImcTextView.setText(" ");
                mNutricionalTextView.setText("  ");
            }
        });

        if(savedInstanceState != null){
            imc = savedInstanceState.getDouble(KEY_IMC);
            mImcTextView.setText(Double.toString(imc));
        }else{
            imc = 0.0;
            mImcTextView.setText("  ");
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putDouble(KEY_IMC, imc);
    }
}
