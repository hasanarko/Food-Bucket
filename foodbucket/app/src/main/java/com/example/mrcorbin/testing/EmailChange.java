package com.example.mrcorbin.testing;

import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.regex.Pattern;

public class EmailChange extends AppCompatActivity {


    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,4}$");
    private LinearLayout lpassword,lemail;
    private TextInputLayout inputLayout,password,cPassword;
    private View eview,pview;
    private DatabaseHelper myDataBase;
    private  String id;
    private String userType;
    private String uEmail="masud@gmail.com";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_change);



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
    private boolean validateEmail()
    {
        String email=inputLayout.getEditText().getText().toString().trim();
        if(email.isEmpty())
        {
            inputLayout.setError("Field can't be empty");
            return false;
        }
        else if(!EMAIL_PATTERN.matcher(email).matches()){
            inputLayout.setError("insert valid email address");
            return false;
        }

        else
        {
            inputLayout.setError(null);
            return true;

        }

    }

    private boolean validateConformPassword()
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

    private boolean validatecheckPassword()
    {
        String conPassword=cPassword.getEditText().getText().toString().trim();
        if(conPassword.isEmpty())
        {
            cPassword.setError("Field can't be empty");
            return false;
        }
        else
        {
            cPassword.setError(null);
            return true;

        }

    }
    private boolean passwordExist()
    {
        String cp=cPassword.getEditText().getText().toString().trim();
        Cursor cursor=myDataBase.disPlayData();
        if(cursor.getCount()==0) {
            return false;
        }else{
            while (cursor.moveToNext())
            {
                String email=cursor.getString(1).toString();
                String pass=cursor.getString(2).toString();
                id=cursor.getString(0).toString();
                userType=cursor.getString(3).toString();

                if(cp.equals(pass) && email.equals(uEmail)){
                    return true;
                }

            }
            return false;
        }

    }
    public void PasswordCheck()
    {
        pview = getLayoutInflater().inflate(R.layout.custom_change_password, null);
        cPassword = this.pview.findViewById(R.id.confirmPasswordId);
        final AlertDialog.Builder alert = new AlertDialog.Builder(EmailChange.this);
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
                        email();
                        dialog.dismiss();
                    }else{
                        cPassword.setError("Wrong password");
                    }
                }
            }

        });
    }
    public void email()
    {
        eview = getLayoutInflater().inflate(R.layout.custom_alert, null);
        inputLayout = eview.findViewById(R.id.new_email_text);
        final AlertDialog.Builder alert = new AlertDialog.Builder(EmailChange.this);
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

        alert.setView(eview);
        final AlertDialog dialog = alert.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateEmail()) {

                } else {
                    if(emailExists()){
                        inputLayout.setError("Email already exists in database");
                    }else {
                        String em=inputLayout.getEditText().getText().toString().trim();
                        myDataBase.emailUpdateData(em,id);
                        int rowId= updateUserFromAllTable();
                        if(rowId<0) {
                            Toast.makeText(getApplicationContext(),"Email  can't Update",Toast.LENGTH_SHORT).show();

                        }else{
                            Toast.makeText(getApplicationContext(),"Email successfully Updated",Toast.LENGTH_SHORT).show();

                        }
                        dialog.dismiss();
                    }

                }

            }
        });
    }

    private boolean emailExists(){
        String em=inputLayout.getEditText().getText().toString().trim();
        Cursor cursor=myDataBase.disPlayData();
        if(cursor.getCount()==0) {
            return false;
        }else{
            while (cursor.moveToNext())
            {
                String email=cursor.getString(1).toString();
                if(em.equals(email)){
                    return true;
                }

            }
            return false;
        }
    }

    public void Click(View v) {
        PasswordCheck();


    }
    private boolean UserDataFindout( String type) {
        if (type.equals("foodie")) {
            Cursor cursor = myDataBase.disPlayFoodiesData();
            if (cursor.getCount() == 0) {
                return false;
            } else {
                while (cursor.moveToNext()) {
                    id = cursor.getString(0).toString();
                    String email = cursor.getString(2).toString();
                    if (email.equals(uEmail)) {
                        return true;
                    }

                }

            }
        } else if (type.equals("restaurants_owner")) {
            Cursor cursor = myDataBase.disPlayRestaurentData();
            if (cursor.getCount() == 0) {
                return false;
            } else {
                while (cursor.moveToNext()) {
                    id = cursor.getString(0).toString();
                    String email = cursor.getString(2).toString();
                    if (email.equals(uEmail)) {
                        return true;
                    }

                }

            }
        }
        return false;
    }


    private int updateUserFromAllTable()
    {
        if(userType.equals("foodie"))
        {
            if(!UserDataFindout("foodie"))
            {

            }
            else
            {
                String em=inputLayout.getEditText().getText().toString().trim();
                return myDataBase.emailUpdateFoodies(em,id);
            }

        }
        else if(userType.equals("restaurants_owner"))
        {
            if(!UserDataFindout("restaurants_owner"))
            {

            }
            else
            {
                String em=inputLayout.getEditText().getText().toString().trim();
                return myDataBase.emailUpdateRestaurent(em,id);
            }
        }
        return 2;
    }

}
