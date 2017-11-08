package com.example.yassine.tictactoe;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private boolean tour = true;
    private boolean winner = false;
    private int table[][] = new int[3][3];
    private TextView message;
    private TextView cases[][] = new TextView[3][3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button replay = (Button)findViewById(R.id.replay);
        replay.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                replay();
            }
        });


        cases[0][0] = (TextView)findViewById(R.id.case11);
        cases[0][1] = (TextView)findViewById(R.id.case12);
        cases[0][2] = (TextView)findViewById(R.id.case13);
        cases[1][0] = (TextView)findViewById(R.id.case21);
        cases[1][1] = (TextView)findViewById(R.id.case22);
        cases[1][2] = (TextView)findViewById(R.id.case23);
        cases[2][0] = (TextView)findViewById(R.id.case31);
        cases[2][1] = (TextView)findViewById(R.id.case32);
        cases[2][2] = (TextView)findViewById(R.id.case33);


        for (int i=0 ; i<3 ; i++){
            for (int j=0 ; j<3 ; j++){
                cases[i][j].setOnClickListener(new View.OnClickListener() {

                    public void onClick(View v) {
                        onClickCase(v);
                    }
                });
            }
        }

        replay();
    }




    public void onClickCase(View v){
        if (winner)
            return;

        String id = String.valueOf(v.getResources().getResourceName(v.getId()));
        id = id.substring(4);
        TextView vv = (TextView) v;
        id = id.split("/")[1];
        String x = id.substring(4,5);
        String y = id.substring(5);
        int row = Integer.parseInt(x);
        int col = Integer.parseInt(y);

        if (table[row-1][col-1] != -1)
            return;

        if (tour){
            table[row-1][col-1] = 0;
            vv.setText("O");
            message.setText("Joueur 2(X) : A vous de jouer");
        }else{
            table[row-1][col-1] = 1;
            vv.setText("X");
            message.setText("Joueur 1(O) : A vous de jouer");
        }


        if(checkWinner()){
            winner = true;
            if (tour)
                message.setText("Joueur 1(O) : Vous avez gagné");
            else
                message.setText("Joueur 2(X) : Vous avez gagné");
        }

        if (checkTableFull())
            message.setText("Egalité");


        tour = !tour;

    }



    public boolean checkWinner(){
        int test = (tour)? 0 : 1;

        for (int i = 0 ; i<3 ; i++) {
            if (table[i][0] == test && table[i][1] == test && table[i][2] == test)
                return true;
        }

        for (int i = 0 ; i<3 ; i++) {
            if (table[0][i] == test && table[1][i] == test && table[2][i] == test)
                return true;
        }

        if ( table[0][0]==test && table[1][1]==test && table[2][2]==test)
            return true;

        if ( table[0][2]==test && table[1][1]==test && table[2][0]==test)
            return true;

        return false;
    }

    public boolean checkTableFull(){
        for (int i=0 ; i<3 ; i++){
            for (int j=0 ; j<3 ; j++){
                if (table[i][j] == -1)
                    return false;
            }
        }

        return true;
    }


    void replay(){
        for (int i=0 ; i<3 ; i++){
            for (int j=0 ; j<3 ; j++){
                table[i][j] = -1;
                cases[i][j].setText("");
            }
        }

        message = (TextView)findViewById(R.id.message);
        message.setText("Joueur 1(O) : A vous de jouer");
        tour = true;
        winner = false;

    }
}
