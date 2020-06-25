package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button[][] button = new Button[3][3];
    boolean player1Turn = true;
    int roundCount = 0;
    int player1Points;
    int player2Points;
    TextView player1TextView;
    TextView player2TextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1TextView = findViewById(R.id.text_view_p1);
        player2TextView = findViewById(R.id.text_view_p2);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3;j++){
                String buttonID = "button" + i + j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                button[i][j] = findViewById(resID);
                button[i][j].setOnClickListener(this);
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (!((Button)v).getText().toString().equals("")){
            return;
        }
        if (player1Turn){
            ((Button) v).setText("X");
            player1Turn = false;
            roundCount+=1;
        }
        else{
            ((Button)v).setText("O");
            player1Turn = true;
            roundCount+=1;
        }
        if (checkForWinner() == 1){
            if (!player1Turn){
                Toast.makeText(this,"X won",Toast.LENGTH_LONG).show();
                player1Points+=1;
                player1TextView.setText("Player 1: " + Integer.toString(player1Points));
            }
            else if (checkForWinner() == 0){
                Toast.makeText(this,"O won",Toast.LENGTH_LONG).show();
                player2Points+=1;
                player2TextView.setText("Player2: " + Integer.toString(player2Points));

            }

        }
        if (checkForWinner() == -1){
            Toast.makeText(this,"Draw",Toast.LENGTH_LONG).show();
        }

    }
    public int checkForWinner(){
        String[][] field = new String[3][3];
        for (int i = 0; i < 3;i++){
            for (int j = 0; j < 3;j++){
                field[i][j] = button[i][j].getText().toString();
            }
        }
        for(int i = 0; i < 3; i++ ){
            if(field[i][0].equals(field[i][1]) && field[i][2].equals(field[i][0]) && !field[i][0].equals("")){
                return 1;
            }
        }
        for (int i = 0; i < 3; i++){
            if (field[0][i].equals(field[1][i]) && field[2][i].equals(field[0][i]) && !field[0][i].equals("")){
                return 1;
            }
        }
        if(field[0][0].equals(field[1][1]) && field[2][2].equals(field[0][0]) && !field[0][0].equals("")){
            return 1;
        }
        else if(field[0][2].equals(field[1][1]) && field[2][0].equals(field[0][2]) && !field[0][2].equals("")){
            return 1;
        }
        if (roundCount == 9){
            return -1;
        }
        return -2;

    }
    public void reset(View view){
        Button buttonReset = findViewById(R.id.button_reset);
        resetBoard();
    }
    public void resetBoard(){
        for(int i = 0; i < 3;i++){
            for (int j = 0; j <3;j++){
                button[i][j].setText("");
            }
        }
        player1Turn = true;
    }
}