package be.vdab.myormliteexample;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdabcursist on 06/10/2017.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "bank";
    private static final int DATABASE_VERSION = 1;

    private Context context;
    private Dao<Person, Long> simpleDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    public Dao<Person, Long> getDao() throws SQLException, java.sql.SQLException {
        if(simpleDao == null){
            simpleDao = getDao(Person.class);
        }
        return simpleDao;
    }

    public List <Person> GetData (){
        DatabaseHelper databaseHelper = new DatabaseHelper(context);
        try {
            Dao<Person, Long> simpleDao = databaseHelper.getDao();
            return simpleDao.queryForAll();

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public int addData (Person person){
        try{
            Dao<Person, Long> dao = getDao();
            return dao.create(person);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public void deleteAll(){
        try{
            Dao<Person,Long> dao = getDao();
            List<Person> list = dao.queryForAll();
            dao.delete(list);

        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

        try{
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Person.class);
        } catch (java.sql.SQLException e) {
           Log.e(DatabaseHelper.class.getName(),"Cannot create database", e);
            throw new RuntimeException(e);
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try{
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Person.class,true);
            onCreate(database,connectionSource);
        } catch (java.sql.SQLException e) {
            Log.e(DatabaseHelper.class.getName(),"Cannot drop databases", e);
            throw new RuntimeException(e);
        }

    }
}
