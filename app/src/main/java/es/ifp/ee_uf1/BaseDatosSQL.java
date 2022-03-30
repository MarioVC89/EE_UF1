package es.ifp.ee_uf1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.ContactsContract;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class BaseDatosSQL extends SQLiteOpenHelper {

    protected SQLiteDatabase db;

    public BaseDatosSQL(Context context) {
        super(context, "notas", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE table notas(id integer primary key autoincrement not null, nota text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
       // db.execSQL("DROP table IF EXISTS notas");

    }

    public void insertNote (String nota)
    {
        db= this.getReadableDatabase();
        db.execSQL("INSERT INTO notas (nota) VALUES ('"+nota+"')");
        db.close();
    }

    public void deleteAllNotes()
    {
        db = this.getWritableDatabase();
        db.execSQL("DELETE FROM notas");
    }

    public void deleteNote(int id)
    {
        db= this.getWritableDatabase();
        db.execSQL("DELETE FROM notas WHERE id=" + id);
    }

    @SuppressLint("Range")
    public ArrayList<String> getAllNotes()
    {
        ArrayList<String> notasGuardadas= new ArrayList<String>();
        Cursor res= null;
        String cont="";
        db= this.getReadableDatabase();
        res= db.rawQuery("SELECT * FROM notas", null);
        res.moveToFirst();
        while(!res.isAfterLast())
        {
            cont= res.getString(res.getColumnIndex("nota"));
            notasGuardadas.add(cont);
            res.moveToNext();
        }

        return notasGuardadas;
    }

    @SuppressLint("Range")
    public int numberOfNotes() {
        int num = 0;
        db = this.getReadableDatabase();
        num = (int) DatabaseUtils.queryNumEntries(db, "notas");
        db.close();
        return num;
    }

    @SuppressLint("Range")
    public Note getNote(String nota)
    {
        Note n= null;
        Cursor res= null;
        String contenido = "";
        if(numberOfNotes()>0)
        {
            db= this.getReadableDatabase();
            res= db.rawQuery("SELECT * FROM notas WHERE nota= '"+nota+"'", null);
            res.moveToFirst();
            while(res.isAfterLast()== false)
            {
                n= new Note(res.getInt(res.getColumnIndex("id")), res.getString(res.getColumnIndex("nota")));
                res.moveToNext();
            }
        }
        return n;

    }

    public void close() {
        db.close();
    }
}
