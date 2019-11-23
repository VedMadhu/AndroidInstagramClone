package ved.basics.androidinstagramclone;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.List;


public class SignUp extends AppCompatActivity {

    private String s;
    private TextView name;
    private TextView kickSpeed;
    private TextView punchSpeed;
    private TextView txtGetData;
    private Button getAllData;
    private String allData;
    private Button getWhereButton;
    private Button switchToLogin;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtGetData = findViewById(R.id.txtGetData);
        getAllData = findViewById(R.id.getAllData);
        switchToLogin = findViewById(R.id.switchToLogin);
        s = " ";
        getWhereButton = findViewById(R.id.getWhereButton);

        switchToLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUp.this, SignUpAndLoginActivity.class);
                startActivity(intent);

            }
        });

        getAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                allData = "";

                ParseQuery<ParseObject> queryAll = ParseQuery.getQuery("Boxer");
                queryAll.whereGreaterThan("punch_speed",0);
                queryAll.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {
                        if(e==null){
                            if(objects.size() > 0){

                                for(ParseObject parseObject : objects){
                                    allData = allData + parseObject.get("name") + "\n";
                                }

                                FancyToast.makeText(SignUp.this,
                                        "All Data Arrived Successfully"
                                        ,FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,
                                        false).show();
                                Toast.makeText(SignUp.this,allData, FancyToast.LENGTH_SHORT ).show();

                            }
                        }
                    }
                });
            }
        });

        txtGetData.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                txtGetData.setText("getting data from the server");
                ParseQuery<ParseObject> parseQuery = ParseQuery.getQuery("Boxer");
                parseQuery.getInBackground("zHXpaPQ0qh", new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject object, ParseException e) {
                        if(object != null && e == null){
                                txtGetData.setText("name is :" + object.get("name") );
                        }
                        else{
                            txtGetData.setText(e.getMessage() + "");
                        }
                    }
                });
            }
        });

        getWhereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ParseQuery<ParseObject> allQuery = ParseQuery.getQuery("Boxer");
                allQuery.setLimit(3);
                allQuery.findInBackground(new FindCallback<ParseObject>() {
                    @Override
                    public void done(List<ParseObject> objects, ParseException e) {

                        if(e==null){

                            if(objects.size() >= 0){
                                Toast.makeText(SignUp.this, "Object size > 0", Toast.LENGTH_SHORT);

                                for(ParseObject parseObject : objects){
                                    s = s + parseObject.get("name") + "\n";
                                }

                                FancyToast.makeText(SignUp.this,
                                        "All Data Arrived Successfully"
                                        ,FancyToast.LENGTH_LONG,
                                        FancyToast.SUCCESS,
                                        false).show();
                                Toast.makeText(SignUp.this,s, FancyToast.LENGTH_SHORT ).show();
                            }
                        }

                        else{
                            Toast.makeText(SignUp.this, e.getMessage() + "", Toast.LENGTH_SHORT).show();
                        }
                    }
                });



            }
        });
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
