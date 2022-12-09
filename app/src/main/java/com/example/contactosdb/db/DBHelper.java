package com.example.contactosdb.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ContactosDB.db";

    public DBHelper(@Nullable Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String query = "CREATE TABLE " + "Contactos" +
                " (" + "id" + "       INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "Matricula" + "       TEXT UNIQUE NOT NULL, " +
                "Nombre" + "          TEXT NOT NULL, " +
                "Apellidos" + "       TEXT NOT NULL, " +
                "Sexo" + "            TEXT NOT NULL, " +
                "FechaNacimiento" + " TEXT NOT NULL);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS " + "ContactosDB.db");
        onCreate(db);
    }
}
