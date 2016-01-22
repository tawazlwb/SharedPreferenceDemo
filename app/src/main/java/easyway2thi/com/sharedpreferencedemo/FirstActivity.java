package easyway2thi.com.sharedpreferencedemo;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.SeekBar;

public class FirstActivity extends AppCompatActivity {

    EditText message;
    SeekBar seekBar;

    float font_size;
    String font_color;
    String text_info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_layout);

        message = (EditText) findViewById(R.id.id_message);
        seekBar = (SeekBar) findViewById(R.id.seek_bar);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                message.setTextSize(TypedValue.COMPLEX_UNIT_PX, progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                font_size = message.getTextSize();
            }
        });

        SharedPreferences sharedPreferences = FirstActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        text_info = sharedPreferences.getString(getString(R.string.TEXT_INFO), "");
        font_size = sharedPreferences.getFloat(getString(R.string.FONT_SIZE), 50);
        font_color = sharedPreferences.getString(getString(R.string.FONT_COLOR), "");

        message.setText(text_info);
        message.setTextSize(TypedValue.COMPLEX_UNIT_PX, font_size);
        if(font_size == 50){
            seekBar.setProgress(0);
        }
        else{
            seekBar.setProgress((int) font_size);
        }

        switch (font_color){
            case "RED" :
                message.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                break;
            case "GREEN" :
                message.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                break;
            case "BLUE" :
                message.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                break;
            default :
                message.setTextColor(getResources().getColor(android.R.color.black));
                break;
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void changeColor(View view){
        switch (view.getId()){
            case R.id.id_red_color :
                message.setTextColor(getResources().getColor(android.R.color.holo_red_dark));
                font_color = "RED";
                break;
            case R.id.id_green_color :
                message.setTextColor(getResources().getColor(android.R.color.holo_green_dark));
                font_color = "GREEN";
                break;
            case R.id.id_blue_color :
                message.setTextColor(getResources().getColor(android.R.color.holo_blue_dark));
                font_color = "BLUE";
                break;
        }
    }

    public void saveSittings(View view){
        SharedPreferences sharedPreferences = FirstActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putFloat(getString(R.string.FONT_SIZE), font_size);
        editor.putString(getString(R.string.FONT_COLOR), font_color);
        editor.putString(getString(R.string.TEXT_INFO), message.getText().toString());
        editor.commit();
    }

    public void clearSittings(View view){
        SharedPreferences sharedPreferences = FirstActivity.this.getSharedPreferences(getString(R.string.PREF_FILE),MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
