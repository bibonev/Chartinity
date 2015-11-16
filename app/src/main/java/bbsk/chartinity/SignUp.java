package bbsk.chartinity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by simeonkostadinov on 25/10/15.
 */
public class SignUp extends Activity {

    // sign up form - save user's details in the database

    // attribute which make the connection between signUp clas and databaseHandler class
    DatabaseHandler handler = new DatabaseHandler(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_chartinity);
    }
    public void onSignUpClick(View v){

        EditText username = (EditText)findViewById(R.id.username_signUp);
        EditText name = (EditText)findViewById(R.id.name_signUp);
        EditText email = (EditText)findViewById(R.id.email_signUp);
        EditText password = (EditText)findViewById(R.id.password_signUp);
        EditText confirm_password = (EditText)findViewById(R.id.confirmPassword_signUp);

        String userStr = username.getText().toString();
        String nameStr = name.getText().toString();
        String emailStr = email.getText().toString();
        String passStr = password.getText().toString();
        String confPassStr = confirm_password.getText().toString();


        if(userStr.isEmpty() || nameStr.isEmpty() || emailStr.isEmpty() || passStr.isEmpty() || confPassStr.isEmpty()){
            Toast empty = Toast.makeText(SignUp.this, "You haven't filled the form", Toast.LENGTH_SHORT);
            empty.show();
        }else if(!passStr.equals(confPassStr)){
            Toast password_match = Toast.makeText(SignUp.this, "Password doesn't match!", Toast.LENGTH_SHORT);
            password_match.show();
        }else{

            /**
             * if the details pass every requirement they are saved in a variable of type signUpForm
             */

            SignUpForm form = new SignUpForm();
            form.setUsername(userStr);
            form.setName(nameStr);
            form.setEmail(emailStr);
            form.setPassword(passStr);

            // set the form in the database
            handler.insertSignUpForm(form);

            Intent i = new Intent(SignUp.this, LogIn.class);
            startActivity(i);

            finish();
        }
    }
}
