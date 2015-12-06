package bbsk.chartinity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

/**
 * Created by simeonkostadinov on 25/10/15.
 */
public class LogIn extends Activity {


    // initializing variable for SharedPreferences - Saving log in details
    public static final String PREFS_NAME = "LoginPrefs";
    public static final String PREFS_ID = "Verifying Id";
    public static Activity back_press;
    private EditText username;
    private EditText password;
    DatabaseHandler handler = new DatabaseHandler(this);


    private String currentId;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.login_chartinity);
        back_press = this;

        Button button = (Button) findViewById(R.id.button_go_signUp);
        button.setPaintFlags(button.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        // check if the user have already logged in and if so, he skip the log in process
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
        }
    }

    protected void onStart(){
        super.onStart();

        username = (EditText)findViewById(R.id.username_logIn);

        password = (EditText)findViewById(R.id.password_logIn);
        password.setTypeface(Typeface.DEFAULT);

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
        }

    }


    public void onLogInButtonClick(View v){

        String userStr = username.getText().toString();

        String passStr = password.getText().toString();

        String user_match_pass = handler.passwordMatching(userStr);

        currentId = handler.getCurrentId(userStr);

        SharedPreferences verify_id = getSharedPreferences(PREFS_ID, Context.MODE_PRIVATE);
        SharedPreferences.Editor verId = verify_id.edit();
        verId.putString("id", currentId);
        verId.commit();

        //System.out.println("Hello" + currentId);

        if (passStr.equals(user_match_pass)){

            if(userStr.isEmpty() || passStr.isEmpty()) {

                Toast logIn_miss_info = Toast.makeText(LogIn.this, "Username and password are empty! ", Toast.LENGTH_SHORT);
                logIn_miss_info.show();

            }else{

                // saving username and password in order the user to log in only once
                SharedPreferences settings = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = settings.edit();
                editor.putString("logged", "logged");
                editor.commit();

                Intent i = new Intent(LogIn.this, MainActivity.class);
                i.putExtra("Username", userStr);
                startActivity(i);

            }

            }else{

            Toast logIn_unsuccessful = Toast.makeText(LogIn.this, "Username and password don't match!", Toast.LENGTH_SHORT);
            logIn_unsuccessful.show();
        }

    }


    public void onSignUp_LogIn_ButtonClick(View v){
        Intent i = new Intent(LogIn.this,SignUp.class);
        startActivity(i);

    }
}
