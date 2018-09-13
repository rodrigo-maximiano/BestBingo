package br.edu.ifsl.bestbingo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String NUMBERS_DRAWN = "numbersDrawn";

    private int numberDrawn;
    private TextView numberDrawnTextView;
    private TextView numbersDrawnTextView;
    private TextView numbersDrawnTextView2;
    private Button raffleButton;
    private String column;

    private StringBuilder sb = new StringBuilder();

    private List<Integer> numbersDrawn = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        raffleButton = findViewById(R.id.raffleButton);

        numberDrawnTextView = findViewById(R.id.numberDrawnTextView);

        numbersDrawnTextView = findViewById(R.id.numbersDrawnTextView);

        numbersDrawnTextView2 = findViewById(R.id.numbersDrawnTextView2);

    }

    public void raffle(View view) {
        if (view.getId() == R.id.raffleButton){
            numberDrawn = (int) (Math.random() * 74 + 1);
            if (!numbersDrawn.contains(numberDrawn)){
                if (numberDrawn <= 15){
                    column = "B";
                }else if (numberDrawn <= 30){
                    column = "I";
                }else if (numberDrawn <= 45){
                    column = "N";
                }else if (numberDrawn <= 60){
                    column = "G";
                }else {
                    column = "O";
                }
                numbersDrawn.add(numberDrawn);
                numberDrawnTextView.setText("Último numero sorteado - Coluna - " + column + " Numero - " + numberDrawn);
                if (numbersDrawn.size()==1) {
                    sb.append(numberDrawn);
                }else {
                    sb.append(" - ").append(numberDrawn);
                }
            }
        }
        numbersDrawnTextView2.setText(sb.toString());
        if (numbersDrawn.size() >= 75) {
            Toast.makeText(this, "Todos os numeros foram sorteados.", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        ArrayList<Integer> numbersDrawnArray = new ArrayList<>(numbersDrawn.size());
        for (Integer n:numbersDrawn) {
            numbersDrawnArray.add(n);
        }
        outState.putIntegerArrayList(NUMBERS_DRAWN, numbersDrawnArray);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null) {
            ArrayList<Integer> numbersDrawnRestored = savedInstanceState.getIntegerArrayList(NUMBERS_DRAWN);
            numbersDrawn.addAll(numbersDrawnRestored);
        }
    }
}

