package bbsk.chartinity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.content.SharedPreferences;

/**
 * Created by simeonkostadinov on 25/10/15.
 */
public class LogIn extends Activity {


    // initializing variable for SharedPreferences - Saving log in details
    public static final String PREFS_NAME = "LoginPrefs";


    DatabaseHandler handler = new DatabaseHandler(this);


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);


        // check if the user have already logged in and if so, he skip the log in process
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        if (settings.getString("logged", "").toString().equals("logged")) {
            Intent intent = new Intent(LogIn.this, MainActivity.class);
            startActivity(intent);
        }



    }
    public void onLogInButtonClick(View v){

        EditText username = (EditText)findViewById(R.id.username_logIn);
        String userStr = username.getText().toString();

        EditText password = (EditText)findViewById(R.id.password_logIn);
        String passStr = password.getText().toString();

        String user_match_pass = handler.passwordMatching(userStr);

        if(passStr.equals(user_match_pass)){

            // saving username and password in order the user to log in only once
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("logged", "logged");
            editor.commit();

            Intent i = new Intent(LogIn.this, MainActivity.class);
            i.putExtra("Username", userStr);
            startActivity(i);



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
