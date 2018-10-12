package com.example.mrcorbin.testing;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class settingsActivity extends AppCompatActivity {


    private ListView listView;
    private AlertDialog.Builder alert;
    private DatabaseHelper myDataBase;
    private View pview;
    private String uEmail="";
    private String fType;
    private String rType="";
    private TextInputLayout password;
    String userTypes;


    SharedPreferences sessionLogin;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);




        // session

        sessionLogin = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor = sessionLogin.edit();

        fType =sessionLogin.getString("foodies_type",null);
        rType =sessionLogin.getString("restaurant_type",null);

        //uEmail = getIntent().getExtras("");

        Bundle bundle = getIntent().getExtras();

        if(bundle != null){

            uEmail = sessionLogin.getString("user_email",null);
           // fType = bundle.getString("f_type");
            //rType = bundle.getString("r_type");

        }


        listView=findViewById(R.id.accountSettingListViewId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDataBase=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= myDataBase.getWritableDatabase();
        final String[] accountSetting=getResources().getStringArray(R.array.accountSetting);
        ArrayAdapter adapter=new ArrayAdapter(this,R.layout.sampletextview,R.id.sampleTextId,accountSetting);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i==0)
                {
                    Intent intent=new Intent(settingsActivity.this,EmailChange.class);
                    startActivity(intent);
                }
                else if(i==1)
                {
                    Intent intent=new Intent(settingsActivity.this,PasswordChange.class);
                    startActivity(intent);
                }
                else if(i==2)
                {
                    alert=new AlertDialog.Builder(settingsActivity.this);
                    alert.setTitle("Alert Titel");
                    alert.setMessage("Do you want to delete your account ?");
                    alert.setIcon(R.drawable.alert);
                    alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            PasswordCheck();
                        }
                    });

                    alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();

                        }
                    });
                    final AlertDialog alertDialog=alert.create();
                    alertDialog.show();


                }
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }
    public void PasswordCheck()
    {
        pview = getLayoutInflater().inflate(R.layout.custom_change_password, null);
        password= this.pview.findViewById(R.id.confirmPasswordId);
        final AlertDialog.Builder alert = new AlertDialog.Builder(settingsActivity.this);
        alert.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        alert.setView(pview);
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validatecheckPassword()) {

                } else {
                    if (passwordExist()) {

                        if(fType.equals(userTypes)){
                            myDataBase.deleteData(uEmail);
                            myDataBase.FdeleteData(uEmail);


                        }
//                        else if(rType.equals("restaurants_owner")){
//                            myDataBase.deleteData(uEmail);
//                            myDataBase.RdeleteData(uEmail);
//                        }


                        Intent intent = new Intent(settingsActivity.this,MainActivity.class);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(),"Account successfully Deleted",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }else{
                        password.setError("Wrong password");
                    }
                }
            }

        });
    }
    private boolean passwordExist(){

        String cp=password.getEditText().getText().toString().trim();
        Cursor cursor=myDataBase.disPlayData();
        if(cursor.getCount()==0) {
            return false;
        }else{
            while (cursor.moveToNext())
            {
                String email=cursor.getString(1).toString();
                String pass=cursor.getString(2).toString();
                userTypes= cursor.getString(3);

                if(cp.equals(pass) && email.equals(uEmail)){
                    return true;
                }

            }
            return false;
        }

    }
    private boolean validatecheckPassword()
    {
        String conPassword=password.getEditText().getText().toString().trim();
        if(conPassword.isEmpty())
        {
            password.setError("Field can't be empty");
            return false;
        }
        else
        {
            password.setError(null);
            return true;

        }

    }
}
