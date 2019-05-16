package sg.edu.rp.c346.ps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnInsert, btnShowList;
    TextView tvTitle,tvSinger,tvYear;
    EditText etTitle,etSinger,etYear;
    RadioGroup rg;
    RadioButton rb,rb2,rb3,rb4,rb5;
    ListView lv;
    ArrayAdapter aa;
    ArrayList<String> al;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnInsert = findViewById(R.id.btnInsert);
        btnShowList = findViewById(R.id.btnShowList);
        etTitle = findViewById(R.id.etTitle);
        etSinger = findViewById(R.id.etSinger);
        etYear = findViewById(R.id.etYear);

        al = new ArrayList<String>();

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);

                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                String year = etYear.getText().toString();
                DBHelper dbh = new DBHelper(MainActivity.this);
                long row_affected = dbh.insertSong(title,singer,year);
                dbh.close();
            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DBHelper dbh = new DBHelper(MainActivity.this);
                al.clear();
                al.addAll(dbh.getAllSongs());
                dbh.close();

                String txt = "";

                for(int i = 0; i< al.size(); i++){
                    String tmp = al.get(i);
                    txt += tmp + "\n";
                }

                aa.notifyDataSetChanged();
            }
        });
    }
}
