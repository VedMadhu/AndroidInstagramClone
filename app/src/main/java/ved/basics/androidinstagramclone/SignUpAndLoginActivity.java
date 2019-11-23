package ved.basics.androidinstagramclone;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;


public class SignUpAndLoginActivity extends AppCompatActivity {

    private TextView userNameLogin,passWordLogin, userNameSignUp,passWordSignUp;
    private Button loginButton, signUpButton;

    private TextView clickableText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_and_login);
        clickableText = findViewById(R.id.userNameSignUp);
        userNameLogin = findViewById(R.id.userNameLogin);
        userNameSignUp = findViewById(R.id.userNameSignUp);
        passWordLogin = findViewById(R.id.passwordLogin);
        passWordSignUp = findViewById(R.id.passwordSignUp);
        loginButton = findViewById(R.id.loginButton);
        signUpButton = findViewById(R.id.signUpButton);

        signUpButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

                ParseUser appUser = new ParseUser();
                appUser.setUsername(userNameSignUp.getText().toString());
                appUser.setPassword(passWordSignUp.getText().toString());
                appUser.signUpInBackground(new SignUpCallback() {
                    @Override
                    public void done(ParseException e) {
                        if(e == null){
                            Toast.makeText(SignUpAndLoginActivity.this,
                                    "You have signed in Successfully",
                                    Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Toast.makeText(SignUpAndLoginActivity.this,
                                    "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ParseUser.logInInBackground(userNameLogin.getText().toString(),
                        passWordLogin.getText().toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser user, ParseException e) {
                                if(e == null)
                                {
                                    Toast.makeText(SignUpAndLoginActivity.this,
                                            "You have logged in", Toast.LENGTH_SHORT).show();
                                }
                                else{
                                    Toast.makeText(SignUpAndLoginActivity.this,
                                            "" + e.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                );
            }
        });

    }


}
