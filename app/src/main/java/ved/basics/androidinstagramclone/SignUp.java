package ved.basics.androidinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;


public class SignUp extends AppCompatActivity {


    private TextView name;
    private TextView kickSpeed;
    private TextView punchSpeed;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void saveButtonClicked(View view)
    {
          ParseObject boxer = new ParseObject("Boxer");
     //   boxer.put("punch_speed", 200);

        name = findViewById(R.id.name);
        kickSpeed = findViewById(R.id.saveButton);
        punchSpeed = findViewById(R.id.punchSpeed);


        boxer.put("name", name.getText().toString());
        boxer.put("Punch_Speed", punchSpeed.getText().toString());
        boxer.put("Kick_Speed", kickSpeed.getText().toString());



        boxer.saveEventually(new SaveCallback(){
            @Override
            public void done(ParseException e) {
                if(e==null){
                    FancyToast.makeText(SignUp.this,
                            "Saved"
                            ,FancyToast.LENGTH_LONG,
                            FancyToast.SUCCESS,
                            true).show();

                }
                else{
                    FancyToast.makeText(SignUp.this,e.getMessage().toString(),
                            FancyToast.LENGTH_LONG,
                            FancyToast.ERROR,
                            true).show();
                }
            }
        });
    }
}
