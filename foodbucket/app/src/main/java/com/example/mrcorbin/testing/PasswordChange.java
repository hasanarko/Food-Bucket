package com.example.mrcorbin.testing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class PasswordChange extends AppCompatActivity {

    private TextInputLayout currentPassword,newPassword,confirmPassword;
    private DatabaseHelper myDataBase;
    private String uEmail="hasan@gmail.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_change);

        currentPassword=findViewById(R.id.currentPasswordId);
        newPassword=findViewById(R.id.newPasswordId);
        confirmPassword=findViewById(R.id.confirmPasswordId);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        myDataBase=new DatabaseHelper(this);
        SQLiteDatabase sqLiteDatabase= myDataBase.getWritableDatabase();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private boolean validateCurrentPassword()
    {
        String cPassword=currentPassword.getEditText().getText().toString().trim();
        if(cPassword.isEmpty())
        {
            currentPassword.setError("Field can't be empty");
            return false;
        }

        else
        {
            currentPassword.setError(null);
            return true;

        }

    }

    private boolean validateNewPassword()
    {
        String nPassword=newPassword.getEditText().getText().toString().trim();
        if(nPassword.isEmpty())
        {
            newPassword.setError("Field can't be empty");
            return false;
        }

        else
        {
            newPassword.setError(null);
            return true;

        }

    }

    private boolean validateConformPassword()
    {
        String conPassword=confirmPassword.getEditText().getText().toString().trim();
        if(conPassword.isEmpty())
        {
            confirmPassword.setError("Field can't be empty");
            return false;
        }
        else
        {
            confirmPassword.setError(null);
            return true;

        }

    }
    private void checkPassword(){
        boolean haspassword=false;
        String cp=currentPassword.getEditText().getText().toString().trim();
        String np=newPassword.getEditText().getText().toString().trim();
        String cP=confirmPassword.getEditText().getText().toString().trim();
        Cursor cursor=myDataBase.disPlayData();
        if(cursor.getCount()==0) {

        }else{
            while (cursor.moveToNext())
            {
                String email=cursor.getString(1).toString();
                String pass=cursor.getString(2).toString();
                String utype=cursor.getString(3).toString();
                if(cp.equals(pass) && email.equals(uEmail)){
                    haspassword=true;
                    break;
                }

            }
            if(haspassword==false){
                currentPassword.setError("Wrong password");
            }else {
                myDataBase.userPasswordUpdateData(uEmail,cP);
                myDataBase.FpasswordUpdateData(uEmail,cP);
                int rowId= myDataBase.RpasswordUpdateData(uEmail,cP);

                if(rowId<0) {
                    Toast.makeText(getApplicationContext(),"password can't Update",Toast.LENGTH_SHORT).show();

                }else{

                    Toast.makeText(getApplicationContext(),"Password successfully Updated",Toast.LENGTH_SHORT).show();
                }
            }

        }


    }

    public void conformInput(View v)
    {
        if(!validateCurrentPassword()| !validateNewPassword()| !validateConformPassword())
        {

        }
        else
        {
            String nPassword=newPassword.getEditText().getText().toString().trim();
            String cPassword=confirmPassword.getEditText().getText().toString().trim();
            if(!cPassword.equals(nPassword))
            {
                confirmPassword.setError("Password Missmatch");
            }
            else {

                checkPassword();
            }
        }

    }






}
