package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Task-1, 2 or 3 clicked
    public void btnClk_SelectTask(View view) {

        Intent i = new Intent(this, Task1.class);
        startActivity(i);
    }
}

