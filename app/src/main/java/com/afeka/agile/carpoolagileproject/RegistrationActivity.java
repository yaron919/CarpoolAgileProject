package com.afeka.agile.carpoolagileproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class RegistrationActivity extends AppCompatActivity {

    private Intent intentToMain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        final EditText name = findViewById(R.id.reg_name);
        final EditText age = findViewById(R.id.age_reg);
        final EditText carModel = findViewById(R.id.car_reg);
        final EditText seatNumber = findViewById(R.id.seatNumber_reg);
        final EditText userName = findViewById(R.id.username_reg);
        final EditText password = findViewById(R.id.password_reg);
        Button confirmButton = findViewById(R.id.registration_button);

        intentToMain = new Intent(this,MainMenuActivity.class);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean validData = true;
                /** TO DO - ADD DB FireBase connection **/
                String textName = name.getText().toString();
                validData = !textName.isEmpty();
                String textAge = age.getText().toString();
                validData &= DataCheck.confirmAge(textAge);
                String textCarModel = carModel.getText().toString();
                String textSeatNumber = seatNumber.getText().toString();
                validData &= DataCheck.confirmSeatNumber(textSeatNumber);
                String textUserName= userName.getText().toString();
                validData &= DataCheck.checkUser(textUserName);
                String textPassword = password.getText().toString();

                if(validData){
                    addUser(textName,textAge,textCarModel,textSeatNumber,textPassword,textUserName);
                }else
                    Toast.makeText(getApplicationContext(),R.string.unValidData,Toast.LENGTH_LONG).show();

            }
        });

    }

    void addUser(final String name,final String age,final String carModel,final String seatNumber,final String password,final String userNameChosen){

        //Get datasnapshot at your "users" root node
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("users");
        ref.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
                        ArrayList<String> userNames = new ArrayList<>();
                        //Get map of users in datasnapshot
                        userNames=collectUsers((Map<String,Object>) dataSnapshot.getValue());
                        if(!userNames.contains(userNameChosen)){
                            User user = new User(name,age,carModel,seatNumber,userNameChosen,password);
                            database.child("users").push().setValue(user);
                            Toast.makeText(getApplicationContext(),R.string.registration_complete,Toast.LENGTH_LONG).show();
                            startActivity(intentToMain);

                        }else{
                            Toast.makeText(getApplicationContext(),"User already exist please change user name",Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //handle databaseError
                    }
                });

    }




    private ArrayList<String> collectUsers(Map<String,Object> users) {

        ArrayList<String> userNames = new ArrayList<>();

        //iterate through each user, ignoring their UID
        for (Map.Entry<String, Object> entry : users.entrySet()){
            //Get user map
            Map singleUser = (Map) entry.getValue();
            //Get userName field and append to list
            userNames.add((String) singleUser.get("userName"));
        }
        return userNames;

    }
}
