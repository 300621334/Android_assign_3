package shafiq.shafiq_ur_rehman_comp304_lab_assign3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.tasksmenu, menu);//"menu" to inflate into

        //instead of onOptionsItemSelected() to handle click on menu items, use Intent
         menu.findItem(R.id.task1).setIntent(new Intent(this, Task1.class));
         menu.findItem(R.id.task2).setIntent(new Intent(this, Task2.class));
         menu.findItem(R.id.task3).setIntent(new Intent(this, Task3.class));

        super.onCreateOptionsMenu(menu);//is it optional?
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        startActivity( item.getIntent() );
        //return super.onOptionsItemSelected(item);
        super.onOptionsItemSelected(item);
        return  true;
    }

    //Task-1, 2 or 3 clicked
    public void btnClk_SelectTask(View view) {

        Intent i = new Intent(this, Task1.class);
        startActivity(i);
    }
}

