package com.cassidy.agromarket.utilities;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

import com.cassidy.agromarket.models.Analysis;
import com.cassidy.agromarket.models.Cart;
import com.cassidy.agromarket.models.Lesson;
import com.cassidy.agromarket.models.News;
import com.cassidy.agromarket.models.OrderProducts;
import com.cassidy.agromarket.models.Payments;
import com.cassidy.agromarket.models.Product;
import com.cassidy.agromarket.models.Transport;
import com.cassidy.agromarket.models.User;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    /**
     * Database declaration zone
     **/
    private static final String DATABASE_NAME = "AGROMARKET.db";

    /**
     * Version declaration zone
     **/
    private static final int DATABASE_VERSION = 5;
    static final String TAG = "DatabaseHelper";


    /**
     * tables declaration zone
     **/
    public static final String TABLE_SELLERS = "seller";
    public static final String TABLE_ADMIN = "admin";
   public static final String TABLE_CUSTOMERS = "customers";
    public static final String TABLE_PRODUCTS = "products";
    public static final String TABLE_POPULAR_PRODUCTS = "popularProducts";
    public static final String TABLE_CART = "cart";
    public static final String TABLE_ORDER = "orders";
    public static final String TABLE_PAYMENTS = "payments";
    public static final String TABLE_ANALYSIS = "analysis";
    public static final String TABLE_NEWS = "news";
    public static final String TABLE_TRANSPORTS = "transports";
    public static final String TABLE_LESSON = "lessons";
    public static final String TABLE_SEARCH = "search";


    /**
     * table fields declaration
     **/
    // sellers table fields
    public static final String COLUMN_SELLER_ID = "seller_id";
    public static final String COLUMN_SELLER_FIRST_NAME = "first_name";
    public static final String COLUMN_SELLER_LAST_NAME = "last_name";
    public static final String COLUMN_SELLER_GENDER = "gender";
    public static final String COLUMN_SELLER_AGE = "age";
    public static final String COLUMN_SELLER_IMAGE = "seller_image";
    public static final String COLUMN_SELLER_REGION = "region";
    public static final String COLUMN_SELLER_MOBILE = "phoneNumber";
    public static final String COLUMN_SELLER_PASSWORD = "password";

    //customers table fields
    public static final String COLUMN_CUSTOMER_ID = "customer_id";
    public static final String COLUMN_CUSTOMER_FIRST_NAME = "first_name";
    public static final String COLUMN_CUSTOMER_LAST_NAME = "last_name";
    public static final String COLUMN_CUSTOMER_GENDER = "gender";
    public static final String COLUMN_CUSTOMER_AGE = "age";
    public static final String COLUMN_CUSTOMER_REGION = "region";
    public static final String COLUMN_CUSTOMER_MOBILE= "phoneNumber";
    public static final String COLUMN_CUSTOMER_PASSWORD = "password";

    // admin table fields
    public static final String COLUMN_ADMIN_NAME = "admin_name";
    public static final String COLUMN_ADMIN_MOBILE = "phoneNumber";
    public static final String COLUMN_ADMIN_IMAGE = "admin_image";
    public static final String COLUMN_ADMIN_EMAIL = "admin_email";
    public static final String COLUMN_ADMIN_PASSWORD = "password";

    //products table fields
    public  static final String COLUMN_PRODUCT_ID = "product_id";
    public  static final String COLUMN_PRODUCT_TITLE = "productTitle";
    public  static final String COLUMN_SELLER_NAME = "seller";
    public  static final String COLUMN_PRODUCT_LOCATION = "location";
    public  static final String COLUMN_PRODUCT_CATEGORY = "category";
    public  static final String COLUMN_PRODUCT_DESCRIPTION = "description";
    public  static final String COLUMN_PRODUCT_PRICE = "price";
    public  static final String COLUMN_PRODUCT_IMAGE = "image";
    public  static final String COLUMN_PRODUCT_QUANTITY = "productQuantity";

    // order table fields
    public static final String COLUMN_ORDER_ID = "order_id";
    public static final String COLUMN_ORDER_DATE = "order_date";

    // cart table fields
    public static final String COLUMN_CART_ID = "cart_id";

    // payments table fields
    public static final String COLUMN_PAYMENT_MODE = "paymentGateway";
    public static final String COLUMN_DELIVERY_CHARGES = "deliveryCharges";

    // analysis table fields
    public static final String COLUMN_MARKET_NAME = "market_name";
    public static final String COLUMN_DEMAND = "demand";
    public static final String COLUMN_SUPPLY = "supply";
    public static final String COLUMN_DOCUMENT = "document";

    //news table fields
    public static final String COLUMN_NEWS_TITLE = "news_title";
    public static final String COLUMN_NEWS_DATE = "news_date";

    //lesson table field
    public static final String COLUMN_LESSON_ID = "lesson_id";
    public static final String COLUMN_LESSON_NAME = "lesson_name";
    public static final String COLUMN_LESSON_VIDEO = "lesson_video";

    //transport table fields
    public static final String COLUMN_TRANSPORT_TYPE = "transport_type";
    public static final String COLUMN_TRANSPORT_ADDRESS = "transport_address";
    public static final String COLUMN_TRANSPORT_NAME = "transport_name";
    public static final String COLUMN_TRANSPORT_COMPANY = "transport_company";
    public static final String COLUMN_TRANSPORT_IMAGE = "transport_image";
    private SQLiteOpenHelper databaseHelper;
    private ContentResolver contentResolver;


    // Constructor
    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create all tables here
        db.execSQL(CREATE_TABLE_ADMIN);
        db.execSQL(CREATE_TABLE_SELLERS);
        db.execSQL(CREATE_TABLE_CUSTOMERS);
        db.execSQL(CREATE_TABLE_PRODUCTS);
        db.execSQL(CREATE_TABLE_POPULAR_PRODUCTS);
        db.execSQL(CREATE_TABLE_CART);
        db.execSQL(CREATE_TABLE_ORDER);
        db.execSQL(CREATE_TABLE_PAYMENTS);
        db.execSQL(CREATE_TABLE_ANALYSIS);
        db.execSQL(CREATE_TABLE_NEWS);
        db.execSQL(CREATE_TABLE_LESSON);
        db.execSQL(CREATE_TABLE_TRANSPORTS);
        db.execSQL(CREATE_TABLE_SEARCH);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop all tables and recreate them on upgrade
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADMIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SELLERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CUSTOMERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POPULAR_PRODUCTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CART);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ORDER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PAYMENTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ANALYSIS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NEWS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_LESSON);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TRANSPORTS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SEARCH);

        // Create new tables
        onCreate(db);
    }

    /**
     * table creation zone
     */

    //creation of admin table
    private static final String CREATE_TABLE_ADMIN = "CREATE TABLE " + TABLE_ADMIN + "(" +
            COLUMN_ADMIN_NAME + " TEXT," +
            COLUMN_ADMIN_MOBILE + " TEXT DEFAULT '0688172822', " +
            COLUMN_ADMIN_EMAIL + " TEXT," +
            COLUMN_ADMIN_PASSWORD + " TEXT DEFAULT 'mauzo'," +
            COLUMN_ADMIN_IMAGE + "BLOB "+
            ")";

    // creation of seller table
    private static final String CREATE_TABLE_SELLERS = "CREATE TABLE " + TABLE_SELLERS + "("
            + COLUMN_SELLER_FIRST_NAME + " TEXT, "
            + COLUMN_SELLER_LAST_NAME + " TEXT, "
            + COLUMN_SELLER_GENDER + " TEXT, "
            + COLUMN_SELLER_AGE + " INTEGER, "
            + COLUMN_SELLER_REGION + " TEXT, "
            + COLUMN_SELLER_IMAGE + " BLOB, " // Add space before BLOB
            + COLUMN_SELLER_MOBILE + " INTEGER PRIMARY KEY, "
            + COLUMN_SELLER_PASSWORD + " TEXT" +
            ")";

    //creation of customers table
    private static final String CREATE_TABLE_CUSTOMERS = "CREATE TABLE " + TABLE_CUSTOMERS + "("
            + COLUMN_CUSTOMER_FIRST_NAME + " TEXT, "
            + COLUMN_CUSTOMER_LAST_NAME + " TEXT, "
            + COLUMN_CUSTOMER_GENDER + " TEXT, "
            + COLUMN_CUSTOMER_AGE + " INTEGER, "
            + COLUMN_CUSTOMER_REGION + " TEXT, "
            + COLUMN_CUSTOMER_MOBILE + " INTEGER PRIMARY KEY, "
            + COLUMN_CUSTOMER_PASSWORD + " TEXT" +
            ")";
    // creation of table products

    private static final String CREATE_TABLE_PRODUCTS = "CREATE TABLE " + TABLE_PRODUCTS + "("
            + COLUMN_PRODUCT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
            + COLUMN_PRODUCT_TITLE + " TEXT,"
            + COLUMN_SELLER_NAME + " TEXT,"
            + COLUMN_PRODUCT_LOCATION + " TEXT,"
            + COLUMN_PRODUCT_CATEGORY + " TEXT,"
            + COLUMN_PRODUCT_DESCRIPTION + " TEXT,"
            + COLUMN_PRODUCT_PRICE + " TEXT,"
            + COLUMN_PRODUCT_IMAGE + " BLOB" + ")";


    // creation of popular_products table
    private static final String CREATE_TABLE_POPULAR_PRODUCTS = "CREATE TABLE " + TABLE_POPULAR_PRODUCTS + "(" +
            COLUMN_PRODUCT_IMAGE + " BLOB, " + // Add space before BLOB
            COLUMN_PRODUCT_PRICE + " INTEGER, " + // Add space before INTEGER
            COLUMN_PRODUCT_ID + " TEXT, " + // Add space before TEXT
            "FOREIGN KEY(" + COLUMN_PRODUCT_ID + ") REFERENCES " +
            TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_ID +
            "))";


    // creation of  order table
    private static final String CREATE_TABLE_ORDER ="CREATE TABLE " + TABLE_ORDER + "(" +

            COLUMN_ORDER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            COLUMN_PRODUCT_TITLE + " TEXT, " +
            COLUMN_PRODUCT_PRICE + " INTEGER, " +
            COLUMN_PRODUCT_IMAGE + " BLOB, " +
            COLUMN_PRODUCT_QUANTITY + " INTEGER, " + // Add a comma here
            COLUMN_SELLER_NAME + " TEXT, " +
            COLUMN_PAYMENT_MODE + " TEXT, " +
            "FOREIGN KEY(" + COLUMN_PRODUCT_TITLE + ") REFERENCES " +
            TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_TITLE + ")," +
            "FOREIGN KEY(" + COLUMN_PAYMENT_MODE + ") REFERENCES " +
            TABLE_PAYMENTS + "(" + COLUMN_PAYMENT_MODE + ")"+
            ")";


//creation of cart table
private static final String CREATE_TABLE_CART = "CREATE TABLE " + TABLE_CART + "(" +
        COLUMN_CART_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
        COLUMN_PRODUCT_TITLE + " TEXT, " +
        COLUMN_PRODUCT_IMAGE + " BLOB, " +
        COLUMN_PRODUCT_PRICE + " INTEGER, " +
        COLUMN_CUSTOMER_MOBILE + " TEXT, " +
        COLUMN_CUSTOMER_PASSWORD + " TEXT, " + // Add space before "TEXT"
        COLUMN_PRODUCT_QUANTITY + " TEXT, " +
        "FOREIGN KEY(" + COLUMN_PRODUCT_TITLE + ") REFERENCES " +
        TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_TITLE + ")," +
        "FOREIGN KEY(" + COLUMN_CUSTOMER_MOBILE + ") REFERENCES " +
        TABLE_CUSTOMERS + "(" + COLUMN_CUSTOMER_MOBILE + ")," +
        "FOREIGN KEY(" + COLUMN_CUSTOMER_PASSWORD + ") REFERENCES " +
        TABLE_CUSTOMERS + "(" + COLUMN_CUSTOMER_PASSWORD + "))";





    //creation of payments table
    private static final String CREATE_TABLE_PAYMENTS = "CREATE TABLE " + TABLE_PAYMENTS + "(" +
            COLUMN_PRODUCT_QUANTITY + " TEXT, " +
            COLUMN_PRODUCT_PRICE + " INTEGER, " +
            COLUMN_DELIVERY_CHARGES + " INTEGER, " +
            COLUMN_PAYMENT_MODE + " TEXT PRIMARY KEY, " +
            COLUMN_ORDER_ID + " INTEGER, " +
            COLUMN_PRODUCT_TITLE + " TEXT, " + // Add the new column "productTitle"
            "FOREIGN KEY(" + COLUMN_ORDER_ID + ") REFERENCES " +
            TABLE_CUSTOMERS + "(" + COLUMN_ORDER_ID + ")," +
            "FOREIGN KEY (" + COLUMN_PRODUCT_TITLE + ") REFERENCES " +
            TABLE_PRODUCTS + "("+ COLUMN_PRODUCT_TITLE + "))";


    // creation of analysis table
    private static final String CREATE_TABLE_ANALYSIS = "CREATE TABLE " + TABLE_ANALYSIS + "(" +
            COLUMN_MARKET_NAME + "TEXT PRIMARY KEY, " +
            COLUMN_PRODUCT_PRICE + "INTEGER, "+
            COLUMN_DEMAND + "REAL, " +
            COLUMN_SUPPLY +"REAL, " +
            COLUMN_DOCUMENT + "BLOB" +
    ")";

    //creation of news table
    private static final String CREATE_TABLE_NEWS = "CREATE TABLE " + TABLE_NEWS + "(" +
    COLUMN_NEWS_TITLE + "TEXT PRIMARY KEY," +
    COLUMN_NEWS_DATE +"DATE," +
    COLUMN_DOCUMENT + "BLOB" +
   ")";
    //creation of search table
     private static final String CREATE_TABLE_SEARCH = "CREATE TABLE " + TABLE_SEARCH + "(" +
            COLUMN_PRODUCT_TITLE + " TEXT, " +
            COLUMN_SELLER_MOBILE + " TEXT, " +
            COLUMN_PAYMENT_MODE + " TEXT, " +
            COLUMN_ORDER_ID + " INTEGER, " + // Add space before INTEGER
            COLUMN_MARKET_NAME + " TEXT, " +
            COLUMN_NEWS_TITLE + " TEXT, " + // Add space before TEXT
            "FOREIGN KEY(" + COLUMN_PRODUCT_TITLE + ") REFERENCES " +
            TABLE_PRODUCTS + "(" + COLUMN_PRODUCT_TITLE + ")," +
            "FOREIGN KEY(" + COLUMN_SELLER_MOBILE + ") REFERENCES " +
            TABLE_SELLERS + "(" + COLUMN_SELLER_MOBILE + ")," +
            "FOREIGN KEY(" + COLUMN_ORDER_ID + ") REFERENCES " +
            TABLE_ORDER + "(" + COLUMN_ORDER_ID + ")," +
            "FOREIGN KEY(" + COLUMN_MARKET_NAME + ") REFERENCES " +
            TABLE_ANALYSIS + "(" + COLUMN_MARKET_NAME + ")," +
            "FOREIGN KEY(" + COLUMN_NEWS_TITLE + ") REFERENCES " +
            TABLE_NEWS + "(" + COLUMN_NEWS_TITLE + ")" +
            ")";



    //creation of lesson table
    private static final String CREATE_TABLE_LESSON = "CREATE TABLE " + TABLE_LESSON + "(" +
    COLUMN_LESSON_ID + "INTEGER PRIMARY KEY, "+
    COLUMN_LESSON_NAME + "TEXT, "+
   COLUMN_LESSON_VIDEO + "BLOB "+
            ")";

    //creation of search table
    private static final String CREATE_TABLE_TRANSPORTS = "CREATE TABLE " + TABLE_TRANSPORTS + "(" +
     COLUMN_TRANSPORT_TYPE + "TEXT, " +
     COLUMN_TRANSPORT_ADDRESS + "TEXT, " +
    COLUMN_TRANSPORT_NAME + "TEXT, " +
     COLUMN_TRANSPORT_COMPANY + "TEXT, " +
    COLUMN_TRANSPORT_IMAGE + "BLOB " +
            ")";


   /**
    *  validation zone
    */
    // Validate seller
    public boolean isSellerValid(String mobile, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SELLERS + " WHERE " + COLUMN_SELLER_MOBILE + " = ? AND " + COLUMN_SELLER_PASSWORD + " = ?", new String[]{mobile, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
    // Validate customer
    public boolean isCustomerValid(String mobile, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CUSTOMERS + " WHERE " + COLUMN_CUSTOMER_MOBILE + " = ? AND " + COLUMN_CUSTOMER_PASSWORD + " = ?", new String[]{mobile, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }
    // Validate Admin
    public boolean isAdminValid(String mobile, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_ADMIN + " WHERE " + COLUMN_ADMIN_MOBILE + " = ? AND " + COLUMN_ADMIN_PASSWORD + " = ?", new String[]{mobile, password});

        boolean isValid = cursor.getCount() > 0;

        cursor.close();
        db.close();

        return isValid;
    }

/**
 * Data Insertion Zone
 */
 // inserting Seller information
 public long insertSeller(User user) {
 SQLiteDatabase db = null;
 long rowId = -1;

 try {
 db = this.getWritableDatabase();
 ContentValues values = new ContentValues();
 values.put(COLUMN_SELLER_FIRST_NAME, user.getFirstName());
 values.put(COLUMN_SELLER_LAST_NAME, user.getLastName());
 values.put(COLUMN_SELLER_GENDER, user.getGender());
 values.put(COLUMN_SELLER_AGE, user.getAge());
 values.put(COLUMN_SELLER_REGION, user.getRegion());
 values.put(COLUMN_SELLER_MOBILE, user.getPhoneNumber());
 values.put(COLUMN_SELLER_PASSWORD, user.getPassword());
 values.put(COLUMN_SELLER_IMAGE, user.getImage());

 rowId = db.insert(TABLE_SELLERS, null, values);
 return rowId;
 } catch (Exception e) {
 Log.e(TAG, "Error inserting SELLER: " + e.getMessage());
 return rowId;
 } finally {
 if (db != null) {
 db.close();
 }
 }
 }

    // inserting Customer information
    public long insertCustomer(User user) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_CUSTOMER_FIRST_NAME, user.getFirstName());
            values.put(COLUMN_CUSTOMER_LAST_NAME, user.getLastName());
            values.put(COLUMN_CUSTOMER_GENDER, user.getGender());
            values.put(COLUMN_CUSTOMER_AGE, user.getAge());
            values.put(COLUMN_CUSTOMER_REGION, user.getRegion());
            values.put(COLUMN_CUSTOMER_MOBILE, user.getPhoneNumber());
            values.put(COLUMN_CUSTOMER_PASSWORD, user.getPassword());

            rowId = db.insert(TABLE_CUSTOMERS, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting CUSTOMERS: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // inserting ADMIN information
    public long insertAdmin(User user) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ADMIN_NAME, user.getLastName());
            values.put(COLUMN_ADMIN_MOBILE, user.getPhoneNumber());
            values.put(COLUMN_ADMIN_EMAIL, user.getEmail());
            values.put(COLUMN_ADMIN_PASSWORD, user.getPassword());
            values.put(COLUMN_ADMIN_IMAGE, user.getImage());

            rowId = db.insert(TABLE_ADMIN, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting ADMIN DATA: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //inserting Products information
    public long insertProduct(Product product) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COLUMN_PRODUCT_TITLE, product.getProductName());
        values.put(COLUMN_SELLER_NAME, product.getSellerName());
        values.put(COLUMN_PRODUCT_LOCATION, product.getProductLocation());
        values.put(COLUMN_PRODUCT_CATEGORY, product.getProductCategory());
        values.put(COLUMN_PRODUCT_DESCRIPTION, product.getProductDescription());
        values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());
        values.put(COLUMN_PRODUCT_IMAGE, product.getImageBytes());

        long productId = db.insert(TABLE_PRODUCTS, null, values);
        db.close();
        return productId;
    }


    // Insert ordered products
  /*public long insertOrder(Product product, User user, Payments payments) {


 }*/

    public long insertOrder(String productName, String productPrice, byte[] imageBytes, int productQuantity) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_TITLE, productName);
            values.put(COLUMN_PRODUCT_PRICE, productPrice);
            values.put(COLUMN_PRODUCT_IMAGE, imageBytes);
            values.put(COLUMN_PRODUCT_QUANTITY,productQuantity);
           // values.put(COLUMN_SELLER_NAME, user.getLastName());
            //values.put(COLUMN_PAYMENT_MODE, payments.getPayment_gateway());

            rowId = db.insert(TABLE_ORDER, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting order product: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

 //Insert product to cart

    public long insertCartUser(String customerMobile, String customerPassword) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        // Insert data into ContentValues
        values.put(COLUMN_CUSTOMER_MOBILE, customerMobile);
        values.put(COLUMN_CUSTOMER_PASSWORD, customerPassword);

        // Insert the row
        long newRowId = db.insert(TABLE_CART, null, values);
        db.close();
        return newRowId;
    }



 // Insert payments Information
 public long insertPayments(Product product,User user, Payments payments) {
     SQLiteDatabase db = null;
     long rowId = -1;

     try {
         db = this.getWritableDatabase();
         ContentValues values = new ContentValues();
         values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());
         values.put(COLUMN_PRODUCT_IMAGE, product.getImageBytes());
         values.put(COLUMN_PRODUCT_QUANTITY, product.getQuantity());
         values.put(COLUMN_DELIVERY_CHARGES, payments.getDelivery_charges());
         values.put(COLUMN_PAYMENT_MODE, payments.getPayment_gateway());

         rowId = db.insert(TABLE_PAYMENTS, null, values);
         return rowId;
     } catch (Exception e) {
         Log.e(TAG, "Error inserting products to Cart: " + e.getMessage());
         return rowId;
     } finally {
         if (db != null) {
             db.close();
         }
     }

 }

    // Insert Analysis Information
    public long insertAnalysis(Analysis analysis, Product product) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_PRICE, product.getProductPrice());
           values.put(COLUMN_DEMAND, analysis.getDemand());
           values.put(COLUMN_SUPPLY, analysis.getSupply());
           values.put(COLUMN_DOCUMENT, analysis.getDocument());

            rowId = db.insert(TABLE_ANALYSIS, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting products to Cart: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // Insert news Information
   /* public long insertNews(News news) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_NEWS_TITLE, news.get);
            values.put(COLUMN_NEWS_DATE,news.getNewsDate());
            values.put(COLUMN_DOCUMENT, news.getDocument());
            rowId = db.insert(TABLE_NEWS, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting products to Cart: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }*/

    // Insert transport Information
    public long insertTransports(Transport transport) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
           values.put(COLUMN_TRANSPORT_TYPE, transport.getTransport_type());
           values.put(COLUMN_TRANSPORT_ADDRESS, transport.getTransport_address());
           values.put(COLUMN_TRANSPORT_NAME, transport.getTransport_name());
           values.put(COLUMN_TRANSPORT_COMPANY, transport.getTransport_company());
           values.put(COLUMN_TRANSPORT_IMAGE, transport.getTransport_image());
            rowId = db.insert(TABLE_TRANSPORTS, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting products to Cart: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    // Insert lesson Information
    public long insertLessons(Lesson lesson) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
           values.put(COLUMN_LESSON_ID,lesson.getLesson_id());
           values.put(COLUMN_LESSON_NAME, lesson.getLesson_name());
           values.put(COLUMN_LESSON_VIDEO, lesson.getLesson_video());

            rowId = db.insert(TABLE_LESSON, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting products to Cart: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }

        }



    /**
     *updating zone
     */
    //updating seller password
    public boolean updateSeller(String phoneNumber, String newPassword) {
        SQLiteDatabase db = null;
        int rowsAffected = 0;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_SELLER_MOBILE, newPassword);

            rowsAffected = db.update(TABLE_SELLERS, values, COLUMN_SELLER_MOBILE + " = ?", new String[]{phoneNumber});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error updating password: " + e.getMessage());
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }
    //updating customer password
    public boolean updateCustomers(String phoneNumber, String newPassword) {
        SQLiteDatabase db = null;
        int rowsAffected = 0;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_SELLER_MOBILE, newPassword);

            rowsAffected = db.update(TABLE_CUSTOMERS, values, COLUMN_CUSTOMER_MOBILE + " = ?", new String[]{phoneNumber});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error updating password: " + e.getMessage());
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    //updating admin password
    public boolean updateAdmin(String phoneNumber, String newPassword) {
        SQLiteDatabase db = null;
        int rowsAffected = 0;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_ADMIN_MOBILE, newPassword);

            rowsAffected = db.update(TABLE_SELLERS, values, COLUMN_ADMIN_MOBILE + " = ?", new String[]{phoneNumber});
            return rowsAffected > 0;
        } catch (Exception e) {
            Log.e(TAG, "Error updating password: " + e.getMessage());
            return false;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    /**
     * Retrieving/fetching all data from the database tables
     */

    // fetching all sellers
    public ArrayList<User> getAllSellers() {
        ArrayList<User> users = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_SELLERS, null);

        if (cursor.moveToFirst()) {
            int firstNameIndex = cursor.getColumnIndex(COLUMN_SELLER_FIRST_NAME);
            int lastNameIndex = cursor.getColumnIndex(COLUMN_SELLER_LAST_NAME);
            int genderIndex = cursor.getColumnIndex(COLUMN_SELLER_GENDER);
            int regionIndex = cursor.getColumnIndex(COLUMN_SELLER_REGION);
            int ageIndex = cursor.getColumnIndex(COLUMN_SELLER_AGE);
            int phoneNumberIndex = cursor.getColumnIndex(COLUMN_SELLER_MOBILE);
            int passwordIndex = cursor.getColumnIndex(COLUMN_SELLER_PASSWORD);
            int imageIndex = cursor.getColumnIndex(COLUMN_SELLER_IMAGE);
            do {
                String firstName = cursor.getString(firstNameIndex);
                String lastName = cursor.getString(lastNameIndex);
                String gender = cursor.getString(genderIndex);
                String region = cursor.getString(regionIndex);
                String age = cursor.getString(ageIndex);
                String phoneNumber = cursor.getString(phoneNumberIndex);
                String password = cursor.getString(passwordIndex);
                byte [] image = cursor.getBlob(imageIndex);



                User user = new User(firstName, lastName, gender, region, age,phoneNumber, password, image);
                users.add(user);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return users;
    }

    // fetching all products
    public ArrayList<Product> getAllProducts() {
        ArrayList<Product> arrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_PRODUCTS, null);

        int productIdIndex = cursor.getColumnIndex(COLUMN_PRODUCT_ID);
        int productTitleIndex = cursor.getColumnIndex(COLUMN_PRODUCT_TITLE);
        int priceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
        int sellerIndex = cursor.getColumnIndex(COLUMN_SELLER_NAME);
        int locationIndex = cursor.getColumnIndex(COLUMN_PRODUCT_LOCATION);
        int categoryIndex = cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY);
        int descriptionIndex = cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION);
        int imageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);

        while (cursor.moveToNext()) {
            int productId = cursor.getInt(productIdIndex);
            byte[] imageBytes = cursor.getBlob(imageIndex);
            String productTitle = cursor.getString(productTitleIndex);
            String price = cursor.getString(priceIndex);
            String description = cursor.getString(descriptionIndex);
            String location = cursor.getString(locationIndex);
            String category = cursor.getString(categoryIndex);
            String seller = cursor.getString(sellerIndex);



            Product product = new Product(0, imageBytes, productTitle, price, description,  location, category, seller);

            arrayList.add(product);
        }

        cursor.close();
        return arrayList;
    }

    // Get products by category

            public List<Product> getProductsByCategory(String category) {
                List<Product> productList = new ArrayList<>();
                SQLiteDatabase db = null;
                Cursor cursor = null;

                try {
                    db = this.getReadableDatabase();
                    String[] columns = {COLUMN_PRODUCT_TITLE, COLUMN_PRODUCT_PRICE, COLUMN_SELLER_NAME,
                            COLUMN_PRODUCT_LOCATION, COLUMN_PRODUCT_CATEGORY,
                            COLUMN_PRODUCT_DESCRIPTION, COLUMN_PRODUCT_IMAGE};

                    String selection = COLUMN_PRODUCT_CATEGORY + " = ?";
                    String[] selectionArgs = {category};

                    cursor = db.query(TABLE_PRODUCTS, columns, selection, selectionArgs, null, null, null);

                    int productTitleIndex = cursor.getColumnIndex(COLUMN_PRODUCT_TITLE);
                    int priceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
                    int sellerIndex = cursor.getColumnIndex(COLUMN_SELLER_NAME);
                    int locationIndex = cursor.getColumnIndex(COLUMN_PRODUCT_LOCATION);
                    int categoryIndex = cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY);
                    int descriptionIndex = cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION);
                    int imageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);

                    while (cursor.moveToNext()) {
                        byte[] imageBytes = cursor.getBlob(imageIndex);
                        String productTitle = cursor.getString(productTitleIndex);
                        String price = cursor.getString(priceIndex);
                        String description = cursor.getString(descriptionIndex);
                        String location = cursor.getString(locationIndex);
                        category = cursor.getString(categoryIndex);
                        String seller = cursor.getString(sellerIndex);

                        Product product = new Product(0, imageBytes, productTitle, price, description, location, category, seller);

                        productList.add(product);
                    }
                } catch (Exception e) {
                    Log.e(TAG, "Error fetching products by category: " + e.getMessage());
                } finally {
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (db != null) {
                        db.close();
                    }
                }

                return productList;
            }



    public List<Product> searchProductsByName(String productTitle) {
        List<Product> productList = new ArrayList<>();
        SQLiteDatabase db = null;
        Cursor cursor = null;

        try {
            db = this.getReadableDatabase();
            String[] columns = {COLUMN_PRODUCT_TITLE, COLUMN_PRODUCT_PRICE, COLUMN_SELLER_NAME,
                    COLUMN_PRODUCT_LOCATION, COLUMN_PRODUCT_CATEGORY,
                    COLUMN_PRODUCT_DESCRIPTION, COLUMN_PRODUCT_IMAGE};


            cursor = db.rawQuery("SELECT * FROM " + TABLE_PRODUCTS , null,null);

            int productTitleIndex = cursor.getColumnIndex(COLUMN_PRODUCT_TITLE);
            int priceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
            int sellerIndex = cursor.getColumnIndex(COLUMN_SELLER_NAME);
            int locationIndex = cursor.getColumnIndex(COLUMN_PRODUCT_LOCATION);
            int categoryIndex = cursor.getColumnIndex(COLUMN_PRODUCT_CATEGORY);
            int descriptionIndex = cursor.getColumnIndex(COLUMN_PRODUCT_DESCRIPTION);
            int imageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);

            while (cursor.moveToNext()) {
                byte[] imageBytes = cursor.getBlob(imageIndex);
                productTitle = cursor.getString(productTitleIndex);
                String price = cursor.getString(priceIndex);
                String description = cursor.getString(descriptionIndex);
                String location = cursor.getString(locationIndex);
                String category = cursor.getString(categoryIndex);
                String seller = cursor.getString(sellerIndex);

                Product product = new Product(0, imageBytes, productTitle, price, description, location, category, seller);

                productList.add(product);
            }
        } catch (Exception e) {
            Log.e(TAG, "Error fetching products by category: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            if (db != null) {
                db.close();
            }
        }

        return productList;
    }
    public byte[] drawableToByteArray(Drawable drawable) {
        Bitmap bitmap = null;

        if (drawable instanceof BitmapDrawable) {
            bitmap = ((BitmapDrawable) drawable).getBitmap();
        } else {
            bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }


    public long insertCart(String productName, String productPrice, byte[] imageBytes) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_CART_ID,0);
            values.put(COLUMN_PRODUCT_PRICE, productPrice);
            values.put(COLUMN_PRODUCT_IMAGE, imageBytes);
            values.put(COLUMN_PRODUCT_TITLE, productName );
           // values.put(COLUMN_SELLER_NAME, getLastName());
         //   values.put(COLUMN_PAYMENT_MODE, payments.getPayment_gateway());

            rowId = db.insert(TABLE_CART, null, values);
            return rowId;
        } catch (Exception e) {
            Log.e(TAG, "Error inserting products to Cart: " + e.getMessage());
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public List<Cart> getAllCartItems() {
        ArrayList<Cart> arrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_CART, null);

        int cartIdIndex = cursor.getColumnIndexOrThrow(COLUMN_CART_ID);
        int productTitleIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_TITLE);
        int sellerIndex = cursor.getColumnIndexOrThrow(COLUMN_SELLER_NAME);
        int locationIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_LOCATION);
        int categoryIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_CATEGORY);
        int descriptionIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_DESCRIPTION);
        int priceIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_PRICE);
        int imageIndex = cursor.getColumnIndexOrThrow(COLUMN_PRODUCT_IMAGE);

        while (cursor.moveToNext()) {
            int cartId = cursor.getInt(cartIdIndex);
            byte[] imageBytes = cursor.getBlob(imageIndex);
            String productTitle = cursor.getString(productTitleIndex);
            String description = cursor.getString(descriptionIndex);
            String price = cursor.getString(priceIndex);
            String location = cursor.getString(locationIndex);
            String category = cursor.getString(categoryIndex);
            String seller = cursor.getString(sellerIndex);

            Cart cart = new Cart(cartId, productTitle, price, description, imageBytes, category, location, seller);
            arrayList.add(cart);
        }

        cursor.close();
        return arrayList;
    }

    public long insertCartItem(String productTitle, byte[] productImage, int productPrice, String productQuantity) {

            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();

            // Insert data into ContentValues
            values.put(COLUMN_PRODUCT_TITLE, productTitle);
            values.put(COLUMN_PRODUCT_IMAGE, productImage);
            values.put(COLUMN_PRODUCT_PRICE, productPrice);
           values.put(COLUMN_PRODUCT_QUANTITY, productQuantity);

            // Insert the row
            long newRowId = db.insert(TABLE_CART, null, values);
            db.close();
            return newRowId;
    }

    public List<OrderProducts> getAllOrderProducts() {
        ArrayList<OrderProducts> arrayList = new ArrayList<>();
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_ORDER, null);

        int orderIdIndex = cursor.getColumnIndex(COLUMN_ORDER_ID);
        int productTitleIndex = cursor.getColumnIndex(COLUMN_PRODUCT_TITLE);
        int sellerIndex = cursor.getColumnIndex(COLUMN_SELLER_NAME);
        int quantityIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
        int priceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
        int imageIndex = cursor.getColumnIndex(COLUMN_PRODUCT_IMAGE);

        while (cursor.moveToNext()) {
            int orderId = cursor.getInt(orderIdIndex);
            byte[] imageBytes = cursor.getBlob(imageIndex);
            String productTitle = cursor.getString(productTitleIndex);
            int price = Integer.parseInt(cursor.getString(priceIndex));
            int quantity = cursor.getInt(quantityIndex);
            String seller = cursor.getString(sellerIndex);



            OrderProducts orderProducts = new OrderProducts(productTitle, price, seller, imageBytes, orderId, quantity);

            arrayList.add(orderProducts);
        }

        cursor.close();
        return arrayList;
    }

    public void deleteOrderProduct(int orderId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_ORDER, COLUMN_ORDER_ID + " = ?", new String[]{String.valueOf(orderId)});
        db.close();
    }
    public void deleteUser(int UserId) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SELLERS, COLUMN_SELLER_ID + " = ?", new String[]{String.valueOf(UserId)});
        db.close();
    }

    public long insertPaymentsData(String productName, int price, int productQuantity) {
        SQLiteDatabase db = null;
        long rowId = -1;

        try {
            db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_PRODUCT_TITLE, productName);
            values.put(COLUMN_PRODUCT_QUANTITY, productQuantity);
            values.put(COLUMN_PRODUCT_PRICE, price);

            rowId = db.insert(TABLE_PAYMENTS, null, values);

            // Add log statements to check the values being inserted
            Log.d(TAG, "Inserted data into payments table:");
            Log.d(TAG, "productName: " + productName);
            Log.d(TAG, "productQuantity: " + productQuantity);
            Log.d(TAG, "price: " + price);

            return rowId;
        } catch (SQLException e) {
            Log.e(TAG, "Error inserting products to payments table: " + e.getMessage());
            e.printStackTrace();
            return rowId;
        } finally {
            if (db != null) {
                db.close();
            }
        }
    }

    public Payments getPaymentDetailsByOrderId(int orderId) {
        SQLiteDatabase db = this.getReadableDatabase();
        Payments payments = null;

        Cursor cursor = null;
        try {
            String[] columns = {COLUMN_ORDER_ID, COLUMN_PRODUCT_QUANTITY, COLUMN_DELIVERY_CHARGES, COLUMN_PAYMENT_MODE, COLUMN_PRODUCT_TITLE};
            String selection = COLUMN_ORDER_ID + " = ?";
            String[] selectionArgs = {String.valueOf(orderId)};

            cursor = db.rawQuery("SELECT * FROM " + TABLE_PAYMENTS,null, null);

            if (cursor.moveToFirst()) {
                int orderIdIndex = cursor.getColumnIndex(COLUMN_ORDER_ID);
                int productQuantityIndex = cursor.getColumnIndex(COLUMN_PRODUCT_QUANTITY);
                int productPriceIndex = cursor.getColumnIndex(COLUMN_PRODUCT_PRICE);
                int deliveryChargesIndex = cursor.getColumnIndex(COLUMN_DELIVERY_CHARGES);
                int paymentModeIndex = cursor.getColumnIndex(COLUMN_PAYMENT_MODE);
                int productTitleIndex = cursor.getColumnIndex(COLUMN_PRODUCT_TITLE);

                orderId = cursor.getInt(orderIdIndex);
                int productQuantity = cursor.getInt(productQuantityIndex);
                int deliveryCharges = cursor.getInt(deliveryChargesIndex);
                int productPrice = cursor.getInt(productPriceIndex);
                String paymentMode = cursor.getString(paymentModeIndex);
                String productTitle = cursor.getString(productTitleIndex);

               payments = new Payments(productQuantity, deliveryCharges,productPrice, paymentMode, productTitle );

            }
        } catch (Exception e) {
            Log.e(TAG, "Error fetching payment details by order ID: " + e.getMessage());
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        return payments;
    }

    
        public long insertNewsData(String title, String date, String description) {
            long newRowId = -1; // Initialize to an invalid value

            try {
                // Get a writable database
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                // Create a ContentValues object to hold the news data
                ContentValues values = new ContentValues();
                values.put("title", title);
                values.put("date", date);
                values.put("description", description);

                // Insert the data into the "news" table and get the row ID of the newly inserted row
                newRowId = db.insert("news", null, values);

                // Close the database connection
                db.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }

            return newRowId;
        }


    public boolean insertNews(long newRowId, String pdfFileName, Uri selectedPdfUri) {
        try {
            // Get the input stream from the selected PDF file URI
            ContentResolver contentResolver = getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(selectedPdfUri);

            if (inputStream != null) {
                // Read the PDF file data into a byte array
                byte[] pdfData = getBytesFromInputStream(inputStream);

                // Get a writable database
                SQLiteDatabase db = databaseHelper.getWritableDatabase();

                // Create a ContentValues object to hold the PDF file data
                ContentValues values = new ContentValues();
                values.put("news_id", newRowId); // Assuming "news_id" is a foreign key to link the PDF with the news entry
                values.put("pdf_file_name", pdfFileName);
                values.put("pdf_data", pdfData);

                // Insert the PDF file data into the database
                db.insert("pdf_files", null, values);

                // Close the database connection
                db.close();

                return true; // Successfully inserted the PDF file data
            }
        } catch (SQLiteException | IOException e) {
            e.printStackTrace();
        }

        return false; // Failed to insert the PDF file data
    }

    private byte[] getBytesFromInputStream(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, bytesRead);
        }

        return byteBuffer.toByteArray();
    }

    public ContentResolver getContentResolver() {
        return contentResolver;
    }

    public void setContentResolver(ContentResolver contentResolver) {
        this.contentResolver = contentResolver;
    }

    public List<News> getAllNews() {
        List<News> newsList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();

        // Define the columns you want to retrieve from the "news" table
        String[] projection = {
                COLUMN_NEWS_TITLE,
                COLUMN_NEWS_DATE,
                COLUMN_DOCUMENT
        };

        // Query the "news" table to get all rows
        Cursor cursor = db.query(TABLE_NEWS, projection, null, null, null, null, null);

        // Iterate through the cursor to populate the newsList
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NEWS_TITLE));
                String date = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NEWS_DATE));
                byte[] document = cursor.getBlob(cursor.getColumnIndexOrThrow(COLUMN_DOCUMENT));

                // Convert the byte[] (BLOB) to a PDF file Uri (optional, based on your requirement)
                 Uri documentUri = convertBytesToUri(document);

                // Create a new News object and add it to the newsList
                News news = new News(title, date, document);
                newsList.add(news);
            }
            cursor.close();
        }

        return newsList;
    }

    private Uri convertBytesToUri(byte[] documentBytes) {
        // Create a directory to store the PDF file
        File pdfDir = new File(Environment.getExternalStorageDirectory(), "AgroMarket");
        if (!pdfDir.exists()) {
            if (!pdfDir.mkdirs()) {
                return null; // Failed to create the directory
            }
        }

        // Generate a unique file name for the PDF file
        String fileName = "PDF_" + System.currentTimeMillis() + ".pdf";
        File pdfFile = new File(pdfDir, fileName);

        try {
            // Write the byte array to the PDF file
            FileOutputStream outputStream = new FileOutputStream(pdfFile);
            outputStream.write(documentBytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
            return null; // Failed to write the byte array to the file
        }

        // Create a content URI for the PDF file
        Uri pdfUri = Uri.fromFile(pdfFile);

        return pdfUri;
    }


}



