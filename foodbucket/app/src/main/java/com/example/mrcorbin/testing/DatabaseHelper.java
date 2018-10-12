package com.example.mrcorbin.testing;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.media.Rating;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //database config
    final static private String databaseName = "Foodbucket.db";

    /************************* DONT FORGET TO CHANGE VERSION NUMBER *******************************/

    final static private int versionNumber = 71;

    //foodies column
    final static private String tableName = "foodies";
    final static private String foodiesId = "_foodies_id";
    final static private String foodiesEmail = "foodies_email";
    final static private String foodiesPassword = "foodies_password";
    final static private String foodiesPhoneNumber = "foodies_phone_number";
    final static private String fullName = "foodies_name";

    final static private String create_foodies_table = "CREATE TABLE " + tableName + " (" + foodiesId + " INTEGER PRIMARY KEY AUTOINCREMENT," + fullName + " VARCHAR(255)," + foodiesEmail + " VARCHAR(255) NOT NULL UNIQUE," + foodiesPassword + " VARCHAR(50)," + foodiesPhoneNumber + " INTEGER);";
    final static private String drop_foodies_table = "DROP TABLE IF EXISTS " + tableName;


    //restaurants column
    private static final String TABLE_NAME = "Restaurant";
    private static final String RESTAURANT_ID = "RESTAURANT_ID";
    private static final String RESTAURANT_NAME = "RESTAURANT_NAME";
    private static final String RESTAURANT_EMAIL = "RESTAURANT_EMAIL";
    private static final String RESTAURANT_PASSWORD = "RESTAURANT_PASSWORD";
    private static final String OWNER_TYPE = "OWNER_TYPE";
    private static final String PHONE_NUMBER = "PHONE_NUMBER";
    private static final String RESTAURANT_ADDRESS = "RESTAURANT_ADDRESS";
    private static final String ACCOUNT_STATUS = "ACCOUNT_STATUS";
    private static final String RESTAURANTS_IMAGE = "RESTAURANT_IMAGE";
    private static final String REST_RATING = "RESTAURANT_RATING";
    private static final String RESTAURANT_STATUS = "RESTAURANT_STATUS";
    private static final String OPENING_TIME = "OPEN_TIME";
    private static final String CLOSING_TIME = "CLOSE_TIME";
    private static final String restaurants_type = "TYPE";

   // private static final String create_restaurant_table = "CREATE TABLE " + TABLE_NAME + " (" + RESTAURANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RESTAURANT_NAME + " VARCHAR(255) NOT NULL, " + RESTAURANT_EMAIL + " VARCHAR(255) NOT NULL UNIQUE, " + RESTAURANT_PASSWORD + " VARCHAR(255) NOT NULL, " + OWNER_TYPE + " VARCHAR(255) NOT NULL, " + PHONE_NUMBER + " INTEGER, " + RESTAURANT_ADDRESS + " VARCHAR(255) NOT NULL, " + ACCOUNT_STATUS + " VARCHAR(255) NOT NULL," + RESTAURANTS_IMAGE + " BLOB," + REST_RATING + " VARCHAR(15) NOT NULL," + RESTAURANT_STATUS + " VARCHAR(10)," + OPENING_TIME + " VARCHAR(20)," + CLOSING_TIME + " VARCHAR(20),"+restaurants_type+" VARCHAR(255));";
    private static final String create_restaurant_table = "CREATE TABLE " + TABLE_NAME + " (" + RESTAURANT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + RESTAURANT_NAME + " VARCHAR(255) NOT NULL, " + RESTAURANT_EMAIL + " VARCHAR(255) NOT NULL UNIQUE, " + RESTAURANT_PASSWORD + " VARCHAR(255) NOT NULL, " + OWNER_TYPE + " VARCHAR(255) NOT NULL, " + PHONE_NUMBER + " INTEGER, " + RESTAURANT_ADDRESS + " VARCHAR(255) NOT NULL, " + ACCOUNT_STATUS + " VARCHAR(255) NOT NULL," + RESTAURANTS_IMAGE + " BLOB," + REST_RATING + " DOUBLE (8,2) NOT NULL," + RESTAURANT_STATUS + " VARCHAR(10)," + OPENING_TIME + " VARCHAR(20)," + CLOSING_TIME + " VARCHAR(20),"+restaurants_type+" VARCHAR(255));";

    private static final String drop_res_table = "DROP TABLE IF EXISTS " + TABLE_NAME;


    //user defined table and column
    private static final String loginTableName = "users";
    private static final String USER_ID = "_user_id";
    private static final String USER_EMAIL = "user_email";
    private static final String USER_PASSWORD = "user_password";
    private static final String USER_TYPE = "user_type";


    private static final String create_user_define_table = "CREATE TABLE " + loginTableName + " (" + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + USER_EMAIL + " VARCHAR(255) NOT NULL UNIQUE," + USER_PASSWORD + " VARCHAR(255)," + USER_TYPE + " VARCHAR(50));";
    private static final String drop_user_table = "DROP TABLE IF EXISTS " + loginTableName;


    //apps final cuisine table
    private static final String apps_cuisine_table = "cuisine";

    // cuisine column
    private static final String cuisine_id = "cuisine_id";
    private static final String cuisine_name = "cuisine_name";

    // cuisine table
    private static final String cuisine_selected_table = "selected_cuisine";
    private static final String cuisine_table = "cuisine";
    private static final String cuisine_type = "type";


    private static final String create_selected_cuisine = "CREATE TABLE " + cuisine_selected_table + " (" + cuisine_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + cuisine_name + " VARCHAR(20), " + RESTAURANT_ID + " INTEGER);";
    private static final String create_cuisine = "CREATE TABLE " + cuisine_table + " (" + cuisine_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + cuisine_name + " VARCHAR(20),"+cuisine_type+" VARCHAR(255));";

    // create cuisine table
    //private static final String create_cuisine = "CREATE TABLE "+cuisine_table+"("+cuisine_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+cuisine_name+" VARCHAR(20), "+RESTAURANT_ID+" INTEGER);";
    //private static final String create_cuisine = "CREATE TABLE "+cuisine_table+"("+cuisine_id+" INTEGER PRIMARY KEY AUTOINCREMENT,"+cuisine_name+" VARCHAR(255),"+RESTAURANT_ID+" INTEGER);";
    //  private static final String create_cuisine = "CREATE TABLE "+cuisine_table+"("+cuisine_id+" INTEGER PRIMARY KEY AUTOINCREMENT, "+cuisine_name+" VARCHAR(100), "+RESTAURANT_ID+" INTEGER);";


    // drop cuisine table
    private static final String drop_cuisine = "DROP TABLE IF EXISTS " + cuisine_table;
    private static final String drop_selected_cuisine = "DROP TABLE IF EXISTS " + cuisine_selected_table;


    // food table
    private static final String food_table = "food";

    // food column
    private static final String food_id = "food_id";
    private static final String food_name = "food_name";
    private static final String food_price = "food_price";
    private static final String food_rating = "food_rating";
    private static final String food_desc = "food_desc";
    private static final String food_type = "type";

    // create food table
    private static final String create_food = "CREATE TABLE " + food_table + " (" + food_id + " INTEGER PRIMARY KEY AUTOINCREMENT, " + food_name + " VARCHAR(100), " + food_price + " DOUBLE(5,2), " + food_rating + " DOUBLE(5,2), " + food_desc + " VARCHAR(200), " + cuisine_id + " INTEGER,"+food_type+" VARCHAR(255));";

    // drop food table
    private static final String drop_food = "DROP TABLE IF EXISTS " + food_table;



    ///image table
    private static final String imageGallaryTable="ImageGallery";
    private static final String imageId="image_id";
    private static final String imagePhoto="image";

    //rating table
    private static final String rating_table="rating_table";
    private static final String rating_id="rating_id";
    private static final String rating_number="rating_number";


    //review table
    private static final String review_table="review_table";
    private static final String review_id="review_id";
    private static final String review="review";

    //food rating table

    private static final String food_rating_table="food_rating_table";
    private static final String food_rating_id="food_rating_id";
    private static final String food_rating_col="food_rating";


    private Context context;

    public DatabaseHelper(Context context) {
        super(context, databaseName, null, versionNumber);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        try {

            sqLiteDatabase.execSQL(create_foodies_table);
            sqLiteDatabase.execSQL(create_restaurant_table);
            sqLiteDatabase.execSQL(create_user_define_table);
            sqLiteDatabase.execSQL(create_cuisine);
            sqLiteDatabase.execSQL(create_selected_cuisine);
            sqLiteDatabase.execSQL(create_food);
            Toast.makeText(context, "table created", Toast.LENGTH_SHORT).show();


        } catch (Exception e) {
            Toast.makeText(context, "Excpetion" + e, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        try {

            sqLiteDatabase.execSQL(drop_foodies_table);
            sqLiteDatabase.execSQL(drop_res_table);
            sqLiteDatabase.execSQL(drop_user_table);
            sqLiteDatabase.execSQL(drop_cuisine);
            sqLiteDatabase.execSQL(drop_selected_cuisine);
            sqLiteDatabase.execSQL(drop_food);

            Toast.makeText(context, "table upgraded", Toast.LENGTH_SHORT).show();
            onCreate(sqLiteDatabase);


        } catch (Exception e) {
            Toast.makeText(context, "Excpetion" + e, Toast.LENGTH_LONG).show();
        }

    }
    //foodies registration

    public long insertFoodiesData(String name,String email,String password,int phoneNumber){
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();

       contentValues.put(fullName,name);
       contentValues.put(foodiesEmail,email);
       contentValues.put(foodiesPassword,password);
       contentValues.put(foodiesPhoneNumber,phoneNumber);
       long rowid = sqLiteDatabase.insert(tableName,null,contentValues);
       // insertUsers(email,password,"foodie");

        return rowid;
    }
    /// restaurant registration

    public Long insertRestaurantData(String name,String email,String password,String ownerType,int phone ,String location,String accStatus,byte[] img,double rating,String restStatus,String openTime,String closeTime,String type) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



        accStatus = "pending";
        contentValues.put(RESTAURANT_NAME, name);
        contentValues.put(RESTAURANT_EMAIL, email);
        contentValues.put(RESTAURANT_PASSWORD, password);
        contentValues.put(OWNER_TYPE, ownerType);
        contentValues.put(PHONE_NUMBER, phone);
        contentValues.put(RESTAURANT_ADDRESS, location);


        contentValues.put(ACCOUNT_STATUS, accStatus);
        contentValues.put(RESTAURANTS_IMAGE, img);
        contentValues.put(REST_RATING, rating);
        contentValues.put(RESTAURANT_STATUS, restStatus);
        contentValues.put(OPENING_TIME, openTime);
        contentValues.put(CLOSING_TIME, closeTime);
        contentValues.put(restaurants_type, type);



        long rowId = db.insert(TABLE_NAME, null, contentValues);

        //insertUsers(email,password,"restaurants_owner");

        return rowId;
    }

    //inserting information for findfing user type

    public long insertUsers(String email,String password,String userType){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(USER_EMAIL, email);
        contentValues.put(USER_PASSWORD, password);
        contentValues.put(USER_TYPE, userType);

        long rowId = db.insert(loginTableName,null,contentValues);


        return rowId;
    }

    //fetch data for finding user type

    public Cursor displayDataForLogin(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String user_login = "SELECT * FROM "+loginTableName+" WHERE "+USER_EMAIL+"='"+email+"' AND "+USER_PASSWORD+"='"+password+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(user_login,null);

        return cursor;
    }


    //fetching foodies data for dashboard
    public Cursor queryOnFoodieTable(String foodie_email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String search_foodie = "SELECT * FROM "+tableName+" WHERE "+foodiesEmail+"='"+foodie_email+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(search_foodie,null);

        return cursor;
    }

    //fetching restaurants data for dashboard
    public Cursor queryOnRestaurantTable(String rest_email) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String search_restaurant = "SELECT * FROM "+TABLE_NAME+" WHERE "+RESTAURANT_EMAIL+"='"+rest_email+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(search_restaurant,null);

        return cursor;
    }


    public Cursor getRestData() {
        double min = 0.0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+TABLE_NAME+" where "+REST_RATING+" >"+min;
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);
        return cursor;
    }

    public Cursor getNewRestData() {
        double min = 0.0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+TABLE_NAME+" where "+REST_RATING+" ="+min;
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);
        return cursor;
    }

    public Cursor getRestDataForTop() {
        double min = 0.0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+TABLE_NAME+" where "+REST_RATING+" > "+min+" order by "+REST_RATING+" desc";
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);
        return cursor;
    }


    // get all cuisine results
/*
    public Cursor fetchCuisine(int resId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from "+cuisine_table+" where "+RESTAURANT_ID+"="+resId;
        return sqLiteDatabase.rawQuery(query, null);
    }
*/
    public List<MenuItem> cuisineList(int id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String select_cuisine = "SELECT * FROM "+cuisine_selected_table+" WHERE "+RESTAURANT_ID+"="+id;
        Cursor cursor = sqLiteDatabase.rawQuery(select_cuisine, null);

        List<MenuItem> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                MenuItem menu = new MenuItem();
                menu.setCuisineId(cursor.getInt(0));
                menu.setCuisineName(cursor.getString(1));
                item.add(menu);
            }while(cursor.moveToNext());
        }

        return item;
    }

    public List<Food> getFoodList(int id) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String select_food_list = "SELECT * FROM "+food_table+" WHERE "+cuisine_id+"="+id;
        Cursor cursor = sqLiteDatabase.rawQuery(select_food_list, null);

        List<Food> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Food list = new Food();
                list.setFoodId(cursor.getInt(0));
                list.setFoodName(cursor.getString(1));
                list.setFoodPrice(cursor.getDouble(2));
                list.setFoodRating(cursor.getDouble(3));
                list.setFoodDescription(cursor.getString(4));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;
    }

  /// insert cuisine
    public long insertCusine( String cusineName,int restId){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();



        contentValues.put(cuisine_name, cusineName);
        contentValues.put(RESTAURANT_ID, restId);


        long rowId = db.insert(cuisine_selected_table,null,contentValues);


        return rowId;
    }
    public long insertFoodItem(String foodName,double foodPrice,double foodRating,String foodDetails,int cuisineId,String type){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(food_name, foodName);
        contentValues.put(food_price, foodPrice);
        contentValues.put(food_rating, foodRating);
        contentValues.put(food_desc, foodDetails);
        contentValues.put(cuisine_id, cuisineId);
        contentValues.put(food_type, type);

        long rowId = db.insert(food_table,null,contentValues);
        return rowId;
    }

    public Cursor loadCusine(){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String search_foodie = "SELECT * FROM "+cuisine_table;
        Cursor cursor = sqLiteDatabase.rawQuery(search_foodie,null);

        return cursor;

    }

    public int deleteCuisine(String cuisineId){
        SQLiteDatabase database = this.getWritableDatabase();
        int deleteRow = database.delete(cuisine_selected_table,cuisine_id+" = ?",new String [] {cuisineId});

        return deleteRow;
    }

//    public int updateFoodItem(int foodId,String foodName,double foodPrice,double foodRating,String foodDetails,int cusineId){
//
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//
//        contentValues.put(food_id,foodId);
//        contentValues.put(food_name,foodName);
//        contentValues.put(food_price,foodPrice);
//        contentValues.put(food_rating,foodRating);
//        contentValues.put(food_desc,foodDetails);
//        contentValues.put(cuisine_id,cusineId);
//
//       int row = sqLiteDatabase.update(food_table,contentValues,food_id+"="+foodId,null);
//
//
//      // String query = "UPDATE "+food_table+" SET "+food_name+" = '"+foodName+"',"+food_price+" = "+foodPrice+","+food_desc+" = '"+foodDetails+"' WHERE "+food_id+" = '"+foodId+"'";
//
//       //Cursor row = sqLiteDatabase.rawQuery(query,null);
//
//
//        return row;
//
//    }


    public int deleteFoodItem(String foodId){
        SQLiteDatabase database = this.getWritableDatabase();
        int deleteRow = database.delete(food_table,food_id+" = ?",new String [] {foodId});

        return deleteRow;
    }

/// delete food while selected cuisine deleted
    public int deleteFoodItemWithSelectedCuisine(String cuisineId){
        SQLiteDatabase database = this.getWritableDatabase();
        int deleteRow = database.delete(food_table,cuisine_id+" = ?",new String [] {cuisineId});

        return deleteRow;
    }

    public boolean updateCuisine(String cuisineId,String cuisineName,String restaurantId){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(cuisine_id, cuisineId);
        values.put(cuisine_name, cuisineName);

        values.put(RESTAURANT_ID, restaurantId);


        database.update(cuisine_table, values,cuisine_id + " = ?", new String[]{cuisineId});
        return true;

    }


    //update phone number
    public boolean updateRestaurantPhoneNumber(String restId,String restPhoneNumber){


        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID,restId);
        values.put(PHONE_NUMBER,restPhoneNumber);

        database.update(TABLE_NAME,values,RESTAURANT_ID+" = ?",new String [] {restId});

        return true;
    }

//    public boolean updateRestaurantName(String restId,String restName){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put(RESTAURANT_ID,restId);
//        contentValues.put(RESTAURANT_NAME,restName);
//
//        sqLiteDatabase.update(TABLE_NAME,contentValues,RESTAURANT_ID+" = ?",new String []{restId});
//        return true;
//    }


    public boolean updateRestaurantName(String restId,String restName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID,restId);
        // values.put(name,personName);
        values.put(RESTAURANT_NAME,restName);
//        values.put(address,personAddress);

       // Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }

    public boolean updateResttaurantPhoneNumber(String restId,String restPhoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID,restId);
        // values.put(name,personName);
        values.put(PHONE_NUMBER,restPhoneNumber);
//        values.put(address,personAddress);

        //Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }


    public boolean updateResttaurantAddress(String restId,String restAddress){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID,restId);
        // values.put(name,personName);
        values.put(RESTAURANT_ADDRESS,restAddress);
//        values.put(address,personAddress);

        //Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }

    public boolean updateRestaurantPassword(String restId,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(RESTAURANT_ID,restId);
        // values.put(name,personName);
        values.put(RESTAURANT_PASSWORD,password);
//        values.put(address,personAddress);

        //Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }

    public boolean updateRestaurantAddressToLoginTable(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_EMAIL,email);
        // values.put(name,personName);
        values.put(USER_PASSWORD,password);
//        values.put(address,personAddress);

        //Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(loginTableName,values,USER_EMAIL+" = ? ",new String[]{email});
        return true;
    }




    public boolean updateFoodItem(String foodId,String foodName,String foodPrice,String foodDetails){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(food_id,foodId);
        values.put(food_name,foodName);
        values.put(food_price,foodPrice);
        values.put(food_desc,foodDetails);


        sqLiteDatabase.update(food_table,values,food_id+" = ? ",new String[]{foodId});
        return true;
    }

   /// restaurant opening time
    public boolean updateOpenRestStatus(String restId,String restStatus){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID,restId);
        values.put(RESTAURANT_STATUS,restStatus);




        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }


    /// restaurant closing time

    public boolean updateCloseRestStatus(String restId,String restStatus){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID,restId);
        values.put(RESTAURANT_STATUS,restStatus);




        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }



    public boolean updateTime(String restId,String openingTime,String closingTime){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID,restId);
        values.put(OPENING_TIME,openingTime);
        values.put(CLOSING_TIME,closingTime);




        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }
    public boolean updateOpeningTime(String restId,String openingTime){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID,restId);
        values.put(OPENING_TIME,openingTime);





        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }
    public boolean updateClosingTime(String restId,String closingTime){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(RESTAURANT_ID,restId);
        values.put(CLOSING_TIME,closingTime);




        sqLiteDatabase.update(TABLE_NAME,values,RESTAURANT_ID+" = ? ",new String[]{restId});
        return true;
    }

    // load all cuisine data

    public List<Cusine> loadCuisine(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String cuisine = "SELECT * FROM "+cuisine_table;
        Cursor cursor = sqLiteDatabase.rawQuery(cuisine, null);

        List<Cusine> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Cusine menu = new Cusine();
                menu.setCuisineId(cursor.getInt(0));
                menu.setCuisineName(cursor.getString(1));
                item.add(menu);
            }while(cursor.moveToNext());
        }

        return item;
    }


    public Cursor loadCuisinesName(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String cuisine = "SELECT * FROM "+cuisine_table;
        Cursor cursor = sqLiteDatabase.rawQuery(cuisine, null);

        return cursor;
    }


    public Cursor totalRestUnderSingleCuisine(String cuisineName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select count("+TABLE_NAME+"."+RESTAURANT_NAME+") as total_restaurant from "+cuisine_table+","+TABLE_NAME+","+cuisine_selected_table+" where "+cuisine_selected_table+"."+RESTAURANT_ID+"="+TABLE_NAME+"."+RESTAURANT_ID+" and "+cuisine_selected_table+"."+cuisine_name+" = "+cuisine_table+"."+cuisine_name+" and "+cuisine_table+"."+cuisine_name+"='"+cuisineName+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(query, null);

//        List<String> item = new ArrayList<>();
//
//        if(cursor.moveToFirst()) {
//            do {
//                String counter = cursor.getString(0);
//
//                item.add(counter);
//                //totalRestUnderSingleCuisine(menu.get);
//
//            }while(cursor.moveToNext());
//        }


        return cursor;

    }


    public List<MenuItem> loadCuisinesNames(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        // String cuisine = "SELECT * FROM "+cuisine_table;
        String cuisine="select * from "+cuisine_table;
        //loa select cuisine.cuisine_name,count(Restaurant.RESTAURANT_NAME) as total_restaurant from cuisine,Restaurant,selected_cuisine where cuisine.cuisine_name=selected_cuisine.cuisine_name and Restaurant.RESTAURANT_ID=selected_cuisine.RESTAURANT_ID group by cuisine.cuisine_name
        Cursor cursor = sqLiteDatabase.rawQuery(cuisine, null);

        List<MenuItem> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                MenuItem menu = new MenuItem();
                //menu.setCuisineId(cursor.getInt(0));
                menu.setCuisineId(cursor.getInt(0));
                menu.setCuisineName(cursor.getString(1));
                item.add(menu);
                //totalRestUnderSingleCuisine(menu.get);

            }while(cursor.moveToNext());
        }

        return item;
    }




    public List<MenuItem> loadAllCuisine(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
       // String cuisine = "SELECT * FROM "+cuisine_table;
        String cuisine="select "+cuisine_table+"."+cuisine_name+", count("+TABLE_NAME+"."+RESTAURANT_NAME+") as total_restaurant from "+cuisine_table+","+TABLE_NAME+","+cuisine_selected_table+" where "+TABLE_NAME+"."+RESTAURANT_ID+"= "+cuisine_selected_table+"."+RESTAURANT_ID+" and "+cuisine_table+"."+cuisine_name+" = "+cuisine_selected_table+"."+cuisine_name+" group by "+cuisine_table+"."+cuisine_name+"";
       //loa select cuisine.cuisine_name,count(Restaurant.RESTAURANT_NAME) as total_restaurant from cuisine,Restaurant,selected_cuisine where cuisine.cuisine_name=selected_cuisine.cuisine_name and Restaurant.RESTAURANT_ID=selected_cuisine.RESTAURANT_ID group by cuisine.cuisine_name
        Cursor cursor = sqLiteDatabase.rawQuery(cuisine, null);

        List<MenuItem> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                MenuItem menu = new MenuItem();
                //menu.setCuisineId(cursor.getInt(0));
                menu.setCuisineName(cursor.getString(0));
                menu.setTotalRest(cursor.getString(1));
                item.add(menu);
                //totalRestUnderSingleCuisine(menu.get);

            }while(cursor.moveToNext());
        }

        return item;
    }

    public Cursor loadSelectedCuisine(int restId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String cuisine = "select * from "+cuisine_table+","+cuisine_selected_table+" where "+cuisine_table+"."+cuisine_name+" = "+cuisine_selected_table+"."+cuisine_name+" and "+cuisine_selected_table+"."+RESTAURANT_ID+"="+restId;
        ;
        Cursor cursor = sqLiteDatabase.rawQuery(cuisine, null);

        return cursor;
    }


    ///searching all things for everyone
    public Cursor showRest(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //  String query = "SELECT * " + " FROM "+cuisine_table+" AS cuisine, "+food_table+" AS food" + "  WHERE cuisine."+cuisine_id+" = food."+cuisine_id;
        String query = "Select "+RESTAURANT_NAME+","+restaurants_type+" from "+TABLE_NAME;
        Cursor result = sqLiteDatabase.rawQuery(query,null);

        return result;

    }


    public Cursor showCuisine(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //  String query = "SELECT * " + " FROM "+cuisine_table+" AS cuisine, "+food_table+" AS food" + "  WHERE cuisine."+cuisine_id+" = food."+cuisine_id;
        String query = "Select "+cuisine_name+","+cuisine_type+" from "+cuisine_table;
        Cursor result = sqLiteDatabase.rawQuery(query,null);

        return result;

    }


    public Cursor showFood(String value){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //  String query = "SELECT * " + " FROM "+cuisine_table+" AS cuisine, "+food_table+" AS food" + "  WHERE cuisine."+cuisine_id+" = food."+cuisine_id;
        String query = "Select "+food_name+","+food_type+" from "+food_table+" group by "+food_name;
        Cursor result = sqLiteDatabase.rawQuery(query,null);

        return result;

    }


    public Cursor loadCuisinesForCheck(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //  String query = "SELECT * " + " FROM "+cuisine_table+" AS cuisine, "+food_table+" AS food" + "  WHERE cuisine."+cuisine_id+" = food."+cuisine_id;
        String query = "Select "+cuisine_type+" from "+cuisine_table;
        Cursor result = sqLiteDatabase.rawQuery(query,null);

        return result;

    }

    public Cursor loadFoodsForCheck(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //  String query = "SELECT * " + " FROM "+cuisine_table+" AS cuisine, "+food_table+" AS food" + "  WHERE cuisine."+cuisine_id+" = food."+cuisine_id;
        String query = "Select "+food_type+" from "+food_table;
        Cursor result = sqLiteDatabase.rawQuery(query,null);

        return result;

    }

    ///dashboard for foodies
    public boolean updateFoodieName(String email,String foodieName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(foodiesEmail,email);
        values.put(fullName,foodieName);


       // Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(tableName,values,foodiesEmail+" = ? ",new String[]{email});
        return true;
    }

    public boolean updateFoodiePhoneNumber(String email,String phoneNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(foodiesEmail,email);
        values.put(foodiesPhoneNumber,phoneNumber);


       // Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(tableName,values,foodiesEmail+" = ? ",new String[]{email});
        return true;
    }

    public boolean updateFoodiePassword(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(foodiesEmail,email);
        values.put(foodiesPassword,password);


        Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(tableName,values,foodiesEmail+" = ? ",new String[]{email});
        return true;
    }

    public boolean updateFoodiePasswordToUserTable(String email,String password){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(USER_EMAIL,email);
        values.put(USER_PASSWORD,password);


       //Toast.makeText(context, values.toString(), Toast.LENGTH_SHORT).show();
        sqLiteDatabase.update(loginTableName,values,USER_EMAIL+" = ? ",new String[]{email});
        return true;
    }



    /// ***** settings *****

    //User table
    public Cursor disPlayData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+loginTableName+"",null);
        return  cursor;

    }
    public int userPasswordUpdateData(String mail,String password){
        //FpasswordUpdateData(mail,password);
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_EMAIL,mail);
        contentValues.put(USER_PASSWORD,password);
        return sqLiteDatabase.update(loginTableName,contentValues,USER_EMAIL+"=?",new String[]{mail});
    }
    public int emailUpdateData(String mail,String id)
    {
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(USER_EMAIL,mail);
        return sqLiteDatabase.update(loginTableName,contentValues,USER_ID+"=?",new String[]{id});

    }
    public int deleteData(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(loginTableName,USER_EMAIL+"=?",new String[]{id});
    }
    //Foodies table
    public Cursor disPlayFoodiesData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+tableName+"",null);
        return  cursor;

    }
    public int FpasswordUpdateData(String mail,String password){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(foodiesEmail,mail);
        contentValues.put(foodiesPassword,password);
        return sqLiteDatabase.update(tableName,contentValues,foodiesEmail+"=?",new String[]{mail});
    }
    public int emailUpdateFoodies(String mail,String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(foodiesEmail,mail);
        return sqLiteDatabase.update(tableName,contentValues,foodiesId+"=?",new String[]{id});
    }
    public int FdeleteData(String Fmail){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(tableName,foodiesEmail+"=?",new String[]{Fmail});
    }
    //Restaurant table
    public Cursor disPlayRestaurentData(){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        Cursor cursor=sqLiteDatabase.rawQuery("SELECT * FROM "+TABLE_NAME+"",null);
        return  cursor;

    }
    public int RpasswordUpdateData(String mail,String password){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(RESTAURANT_EMAIL,mail);
        contentValues.put(RESTAURANT_PASSWORD,password);
        return sqLiteDatabase.update(TABLE_NAME,contentValues,RESTAURANT_EMAIL+"=?",new String[]{mail});
    }

    public int emailUpdateRestaurent(String mail,String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(RESTAURANT_EMAIL,mail);
        return sqLiteDatabase.update(TABLE_NAME,contentValues,RESTAURANT_ID+"=?",new String[]{id});
    }

    public int RdeleteData(String Fmail){

        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(TABLE_NAME,RESTAURANT_EMAIL+"=?",new String[]{Fmail});
    }



    // cuisine list searching result
    public List<Restaurants> cuisineListForSearching(String cuisineName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selectCuisine = "select * from "+TABLE_NAME+","+cuisine_selected_table+" where "+TABLE_NAME+"."+RESTAURANT_ID+"="+cuisine_selected_table+"."+RESTAURANT_ID+" and "+cuisine_selected_table+"."+cuisine_name+"='"+cuisineName+"' order by "+TABLE_NAME+"."+REST_RATING+" desc";
        //String q ="select "+TABLE_NAME+".* from "+cuisine_selected_table+","+TABLE_NAME+" where "+cuisine_selected_table+"."+RESTAURANT_ID+" = "+TABLE_NAME+"."+RESTAURANT_ID+" and "+cuisine_selected_table+"."+cuisine_name+"='"+cuisineName+"' order by "+TABLE_NAME+"."+REST_RATING+" desc";

        Cursor cursor = sqLiteDatabase.rawQuery(selectCuisine, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants menu = new Restaurants();
              //  menu.setRestId(cursor.getInt(0));
                menu.setRestName(cursor.getString(1));
                menu.setRestId(cursor.getInt(0));
                menu.setRestImage(cursor.getBlob(8));
                menu.setRestRating(Double.parseDouble(cursor.getString(9)));
                menu.setRestAddress(cursor.getString(6));
                item.add(menu);
            }while(cursor.moveToNext());
        }

        return item;
    }


    //food list searching result for popular restaurant
    public List<Restaurants> foodListForPopularSearching(String foodName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        String selectFood = "select * from "+cuisine_selected_table+","+TABLE_NAME+","+food_table+" where "+cuisine_selected_table+"."+cuisine_id+"="+food_table+"."+cuisine_id+" and "+cuisine_selected_table+"."+RESTAURANT_ID+"="+TABLE_NAME+"."+RESTAURANT_ID+" and "+food_table+"."+food_name+"='"+foodName+"' order by "+food_table+"."+food_rating+" desc";

        Cursor cursor = sqLiteDatabase.rawQuery(selectFood, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestName(cursor.getString(4));
                list.setRestId(cursor.getInt(2));
                list.setRestImage(cursor.getBlob(11));
                list.setRestRating(Double.parseDouble(cursor.getString(12)));
                list.setRestAddress(cursor.getString(9));
                list.setFoodPrice(cursor.getDouble(19));

                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;
    }
    //food list searching result for high to low
    public List<Restaurants> foodListForHighToLowSearching(String foodName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        String selectFood = "select * from "+cuisine_selected_table+","+TABLE_NAME+","+food_table+" where "+cuisine_selected_table+"."+cuisine_id+"="+food_table+"."+cuisine_id+" and "+cuisine_selected_table+"."+RESTAURANT_ID+"="+TABLE_NAME+"."+RESTAURANT_ID+" and "+food_table+"."+food_name+"='"+foodName+"' order by "+food_table+"."+food_price+" desc";

        Cursor cursor = sqLiteDatabase.rawQuery(selectFood, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestName(cursor.getString(4));
                list.setRestId(cursor.getInt(2));
                list.setRestImage(cursor.getBlob(11));

                list.setRestRating(Double.parseDouble(cursor.getString(12)));
                list.setRestAddress(cursor.getString(9));
                list.setFoodPrice(cursor.getDouble(19));

                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;
    }

    //food list searching result for low to high restaurant
    public List<Restaurants> foodListForLowToHighSearching(String foodName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();


        String selectFood = "select * from "+cuisine_selected_table+","+TABLE_NAME+","+food_table+" where "+cuisine_selected_table+"."+cuisine_id+"="+food_table+"."+cuisine_id+" and "+cuisine_selected_table+"."+RESTAURANT_ID+"="+TABLE_NAME+"."+RESTAURANT_ID+" and "+food_table+"."+food_name+"='"+foodName+"' order by "+food_table+"."+food_price+" asc";

        Cursor cursor = sqLiteDatabase.rawQuery(selectFood, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestName(cursor.getString(4));
                list.setRestId(cursor.getInt(2));
                list.setRestImage(cursor.getBlob(11));

                list.setRestRating(Double.parseDouble(cursor.getString(12)));
                list.setRestAddress(cursor.getString(9));
                list.setFoodPrice(cursor.getDouble(19));

                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;
    }


    public List<Restaurants> RestaurantListForSearching(String restName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String selectFood = "select * from "+TABLE_NAME+" where "+RESTAURANT_NAME+"='"+restName+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(selectFood, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestName(cursor.getString(1));
                list.setRestId(cursor.getInt(0));
                list.setRestImage(cursor.getBlob(8));

                list.setRestRating(Double.parseDouble(cursor.getString(9)));
                list.setRestAddress(cursor.getString(6));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;
    }


   // searching restaurants
    public Cursor searchRest(int restId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        //  String query = "SELECT * " + " FROM "+cuisine_table+" AS cuisine, "+food_table+" AS food" + "  WHERE cuisine."+cuisine_id+" = food."+cuisine_id;
        String q = "Select * from "+TABLE_NAME+" where "+RESTAURANT_ID+"="+restId;

        Cursor result = sqLiteDatabase.rawQuery(q,null);

        return result;

    }

    //update rating for restaurants;

    public long insertRestRating(double ratingNumber,int foodieId,int restId){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        //contentValues.put(rating_id,ratingId);
        contentValues.put(rating_number,ratingNumber);
        contentValues.put(foodiesId,foodieId);
        contentValues.put(RESTAURANT_ID,restId);
        long rowid = sqLiteDatabase.insert(rating_table,null,contentValues);


        return rowid;
    }


    public boolean updateRestaurantProfileImage(String id, byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(RESTAURANT_ID, id);
        contentValues.put(RESTAURANTS_IMAGE, img);

        db.update(TABLE_NAME, contentValues,RESTAURANT_ID + " = ?", new String[]{id});
        return true;
    }



    // fetch image gallery images
    public Cursor getData(int restId) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+imageGallaryTable+" where "+RESTAURANT_ID+" = "+restId;
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);
        return cursor;
    }

    //upload image in gallery (insert)
    public long uploadGallaryImage(String id,byte[] img) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(imagePhoto, img);
        contentValues.put(RESTAURANT_ID, id);

        long rowId = db.insert(imageGallaryTable,null, contentValues);
        return rowId;
    }

    public Cursor searchFood(String foodName){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readFoodData = "select * from "+food_table+" where "+food_name+"='"+foodName+"'";
        Cursor cursor = sqLiteDatabase.rawQuery(readFoodData,null);
        return cursor;
    }

    public boolean updateRating(int ratingId,double ratingNumber){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String updateQuery ="update "+rating_table+" set "+rating_number+"="+ratingNumber+" where "+rating_id+"="+ratingId;
        Cursor c= sqLiteDatabase.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();

        return true;
    }

    public boolean updateRatingToRestaurantTable(int restId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String updateQuery ="update "+TABLE_NAME+" set "+REST_RATING+"=(select avg("+rating_number+") from "+rating_table+" where "+RESTAURANT_ID+"="+restId+") where "+RESTAURANT_ID+"="+restId;
        Cursor c= sqLiteDatabase.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();

        return true;
    }

    public Cursor readRatingTable(int foodieId,int restId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String q = "select * from "+rating_table+" where "+foodiesId+"="+foodieId+" and "+RESTAURANT_ID+"="+restId;
        Cursor cursor = sqLiteDatabase.rawQuery(q,null);
        return cursor;
    }

    //load cuisine table
    public Cursor loadCuisineTable(){

    return null;
    }


    //load seleted cuisine table through cuisine names
//    public Cursor findRestaurantUnderSelectedCuisine(String cuisineName){
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String q ="select "+TABLE_NAME+"."+RESTAURANT_NAME+" from "+cuisine_selected_table+","+TABLE_NAME+" where "+cuisine_selected_table+"."+RESTAURANT_ID+"="+TABLE_NAME+"."+RESTAURANT_ID+" and "+cuisine_selected_table+"."+cuisine_name+"='"+cuisineName+"'";
//        Cursor cursor = sqLiteDatabase.rawQuery(q,null);
//        return cursor;
//    }

    public List<Restaurants> findRestaurantUnderSelectedCuisine(String cuisineName) {

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String q ="select "+TABLE_NAME+".* from "+cuisine_selected_table+","+TABLE_NAME+" where "+cuisine_selected_table+"."+RESTAURANT_ID+"="+TABLE_NAME+"."+RESTAURANT_ID+" and "+cuisine_selected_table+"."+cuisine_name+"='"+cuisineName+"' order by "+TABLE_NAME+"."+REST_RATING+" desc";
        Cursor cursor = sqLiteDatabase.rawQuery(q, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestId(cursor.getInt(0));
                list.setRestName(cursor.getString(1));
                list.setRestEmail(cursor.getString(2));
                list.setRestPhone(cursor.getInt(5));
                list.setRestImage(cursor.getBlob(8));
                list.setRestStatus(cursor.getString(10));
                list.setRestOpeningTime(cursor.getString(11));
                list.setRestClosingTime(cursor.getString(12));
               list.setRestRating(Double.parseDouble(cursor.getString(9)));
                list.setRestAddress(cursor.getString(6));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;
    }

    public List<Restaurants> findRestUnderCuisine(String cuisineName){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String q ="select "+TABLE_NAME+".* from "+cuisine_selected_table+","+TABLE_NAME+" where "+cuisine_selected_table+"."+RESTAURANT_ID+" = "+TABLE_NAME+"."+RESTAURANT_ID+" and "+cuisine_selected_table+"."+cuisine_name+"='"+cuisineName+"' order by "+TABLE_NAME+"."+REST_RATING+" desc";
        Cursor cursor = sqLiteDatabase.rawQuery(q, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
/*                list.setRestName(cursor.getString(4));
                list.setRestId(cursor.getInt(3));
//
                list.setRestRating(Double.parseDouble(cursor.getString(12)));
                list.setRestAddress(cursor.getString(9));*/

                list.setRestId(cursor.getInt(0));
                list.setRestName(cursor.getString(1));
                list.setRestEmail(cursor.getString(2));
                list.setRestPhone(cursor.getInt(5));
                list.setRestImage(cursor.getBlob(8));
                list.setRestStatus(cursor.getString(10));
                list.setRestOpeningTime(cursor.getString(11));
                list.setRestClosingTime(cursor.getString(12));
                list.setRestRating(Double.parseDouble(cursor.getString(9)));
                list.setRestAddress(cursor.getString(6));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;


    }


    public Cursor loadDataFromFoodiesAndRating(int restId,int foodieId){

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select "+tableName+"."+fullName+","+rating_table+"."+rating_number+","+rating_table+"."+rating_id+" from "+tableName+","+rating_table+" where "+tableName+"."+foodiesId+" = "+rating_table+"."+foodiesId+" and "+rating_table+"."+RESTAURANT_ID+"="+restId+" and "+tableName+"."+foodiesId+"="+foodieId;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);


        return cursor;
    }



    public long insertDataIntoReviews(int foodieId,double ratingNumber,String userReview,int ratingId){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(foodiesId,foodieId);
        contentValues.put(review,userReview);
        contentValues.put(rating_number,ratingNumber);
        contentValues.put(rating_id,ratingId);

        long rowid = sqLiteDatabase.insert(review_table,null,contentValues);


        return rowid;
    }



    public List<Review> findReviews(int restId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String q = "select * from "+review_table+","+rating_table+" where "+review_table+"."+rating_id+"="+rating_table+"."+rating_id+" and "+rating_table+"."+RESTAURANT_ID+"="+restId;
        String q = "select * from "+review_table+", "+rating_table+", "+tableName+" where "+review_table+"."+rating_id+"="+rating_table+"."+rating_id+" and "+review_table+"."+foodiesId+"="+tableName+"."+foodiesId+" and "+rating_table+"."+RESTAURANT_ID+"="+restId;
        Cursor cursor = sqLiteDatabase.rawQuery(q, null);

        List<Review> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Review list = new Review();
                list.setReviewId(cursor.getInt(0));
                list.setReviewerName(cursor.getString(10));
//
                list.setRatingNumber(cursor.getDouble(3));
                list.setReview(cursor.getString(2));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;


    }


    public List<Review> findMyReviews(int restId,int foodieId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        //String q = "select "+review_table+"."+rating_number+","+review_table+"."+reviewer_name+","+review_table+"."+review+" from "+review_table+","+rating_table+" where "+review_table+"."+rating_id+"="+rating_table+"."+rating_id+" and "+rating_table+"."+RESTAURANT_ID+"="+restId+" and "+rating_table+"."+foodiesId+"="+foodieId;
        String q = "select * from "+review_table+", "+rating_table+", "+tableName+" where "+review_table+"."+rating_id+"="+rating_table+"."+rating_id+" and "+review_table+"."+foodiesId+"="+tableName+"."+foodiesId+" and "+rating_table+"."+RESTAURANT_ID+"="+restId+" and "+tableName+"."+foodiesId+"="+foodieId;
        Cursor cursor = sqLiteDatabase.rawQuery(q, null);

        List<Review> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Review list = new Review();
                list.setReviewId(cursor.getInt(0));
                list.setReviewerName(cursor.getString(10));
//
                list.setRatingNumber(cursor.getDouble(3));
                list.setReview(cursor.getString(2));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;


//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        String q = "select * from "+review_table+","+rating_table+" where "+review_table+"."+rating_id+"="+rating_table+"."+rating_id+" and "+rating_table+"."+RESTAURANT_ID+"="+restId;
//        Cursor cursor = sqLiteDatabase.rawQuery(q, null);
//
//        List<Review> item = new ArrayList<>();
//
//        if(cursor.moveToFirst()) {
//            do {
//                Review list = new Review();
//                list.setReviewId(cursor.getInt(0));
//                list.setReviewerName(cursor.getString(1));
////
//                list.setRatingNumber(cursor.getDouble(3));
//                list.setReview(cursor.getString(2));
//                item.add(list);
//            }while(cursor.moveToNext());
//        }
//
//        return item;


    }

    public Cursor findRestaurantById(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from "+TABLE_NAME+" where "+RESTAURANT_ID+" = "+id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        return cursor;
    }

    public Cursor findFoodieById(int id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String query = "select * from "+tableName+" where "+foodiesId+" = "+id;
        Cursor cursor = sqLiteDatabase.rawQuery(query,null);

        return cursor;
    }


    public List<Restaurants> getRestListData() {
        double min = 0.0;


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+TABLE_NAME+" where "+REST_RATING+" >"+min;
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestId(cursor.getInt(0));
                list.setRestName(cursor.getString(1));
                list.setRestEmail(cursor.getString(2));
                list.setRestPhone(cursor.getInt(5));
                list.setRestImage(cursor.getBlob(8));
                list.setRestStatus(cursor.getString(10));
                list.setRestOpeningTime(cursor.getString(11));
                list.setRestClosingTime(cursor.getString(12));
                list.setRestRating(Double.parseDouble(cursor.getString(9)));
                list.setRestAddress(cursor.getString(6));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;



    }


    public List<Restaurants> getTopRatedListData() {

        double min = 0.0;
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+TABLE_NAME+" where "+REST_RATING+">"+min+" order by "+REST_RATING+" desc";
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestId(cursor.getInt(0));
                list.setRestName(cursor.getString(1));
                list.setRestEmail(cursor.getString(2));
                list.setRestPhone(cursor.getInt(5));
                list.setRestImage(cursor.getBlob(8));
                list.setRestStatus(cursor.getString(10));
                list.setRestOpeningTime(cursor.getString(11));
                list.setRestClosingTime(cursor.getString(12));
                list.setRestRating(Double.parseDouble(cursor.getString(9)));
                list.setRestAddress(cursor.getString(6));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;



    }


    public List<Restaurants> getNewlyOpenedListData() {
        double min = 0.0;

        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+TABLE_NAME+" where "+REST_RATING+" ="+min;
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);

        List<Restaurants> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                Restaurants list = new Restaurants();
                list.setRestId(cursor.getInt(0));
                list.setRestName(cursor.getString(1));
                list.setRestEmail(cursor.getString(2));
                list.setRestPhone(cursor.getInt(5));
                list.setRestImage(cursor.getBlob(8));
                list.setRestStatus(cursor.getString(10));
                list.setRestOpeningTime(cursor.getString(11));
                list.setRestClosingTime(cursor.getString(12));
                list.setRestRating(Double.parseDouble(cursor.getString(9)));
                list.setRestAddress(cursor.getString(6));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;



    }

    /// food rating


    public long insertFoodRating(int foodId,int cuisineId,int restId,double ratingNumber){


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        contentValues.put(food_id,foodId);
        contentValues.put(cuisine_id,cuisineId);

        contentValues.put(RESTAURANT_ID,restId);
        contentValues.put(food_rating,ratingNumber);


        long rowid = sqLiteDatabase.insert(food_rating_table,null,contentValues);


        return rowid;
    }


    public boolean updateRatingToFoodTable(int restId,int cuisineId,int foodId){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String updateQuery ="update "+food_table+" set "+food_rating+"=(select avg("+food_rating_col+") from "+food_rating_table+" where "+RESTAURANT_ID+"="+restId+" and "+cuisine_id+"="+cuisineId+") where "+food_id+"="+foodId;
        Cursor c= sqLiteDatabase.rawQuery(updateQuery, null);

        c.moveToFirst();
        c.close();

        return true;
    }



    // delete image by id
    public int deletePhoto(String id){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        return sqLiteDatabase.delete(imageGallaryTable,imageId+"=?",new String[]{id});
    }

    // load image gallery

    public List<gallery> loadImageGallery(int restId) {


        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String readData = "select * from "+imageGallaryTable+" where "+RESTAURANT_ID+"="+restId;
        Cursor cursor = sqLiteDatabase.rawQuery(readData, null);

        List<gallery> item = new ArrayList<>();

        if(cursor.moveToFirst()) {
            do {
                gallery list = new gallery();

                list.setImageId(cursor.getInt(0));
                list.setImagePhoto(cursor.getBlob(1));
                list.setRestaurant_id(cursor.getInt(2));
                item.add(list);
            }while(cursor.moveToNext());
        }

        return item;



    }

}
