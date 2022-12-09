package com.example.contactosdb.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.contactosdb.entidades.Contactos;

import java.util.ArrayList;

public class ContactosCRUD extends DBHelper
{
    private Context context;
    public ContactosCRUD(@Nullable Context context)
    {
        super(context);
        this.context = context;
    }

    public long insertContacto(String matricula, String nombre, String apellidos, String sexo, String fechaNacimiento)
    {
        long id = 0;

        try {
            DBHelper dbHelper = new DBHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();

            values.put("Matricula", matricula);
            values.put("Nombre", nombre);
            values.put("Apellidos", apellidos);
            values.put("Sexo", sexo);
            values.put("FechaNacimiento", fechaNacimiento);

            id = db.insert("Contactos", null, values);
        } catch (Exception ex)
        {
            ex.toString();
        }
        return id;
    }

    public ArrayList<Contactos> showContactos()
    {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ArrayList<Contactos> listContactos = new ArrayList<>();
        Contactos contacto = null;
        Cursor cursorContactos = null;

        cursorContactos = db.rawQuery("SELECT * FROM Contactos", null);

        if (cursorContactos.moveToFirst())
        {
            do
            {
                contacto = new Contactos();
                contacto.setId(cursorContactos.getInt(0));
                contacto.setMatricula(cursorContactos.getString(1));
                contacto.setNombre(cursorContactos.getString(2));
                contacto.setApellidos(cursorContactos.getString(3));
                contacto.setSexo(cursorContactos.getString(4));
                contacto.setFechanacimiento(cursorContactos.getString(5));

                listContactos.add(contacto);
            } while(cursorContactos.moveToNext());
        }

        cursorContactos.close();

        return listContactos;
    }

    public Contactos verContactos(int id)
    {
        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Contactos contacto = null;
        Cursor cursorContactos;

        cursorContactos = db.rawQuery("SELECT * FROM Contactos WHERE id = " + id + " LIMIT 1", null);

        if (cursorContactos.moveToFirst())
        {
            contacto = new Contactos();
            contacto.setId(cursorContactos.getInt(0));
            contacto.setMatricula(cursorContactos.getString(1));
            contacto.setNombre(cursorContactos.getString(2));
            contacto.setApellidos(cursorContactos.getString(3));
            contacto.setSexo(cursorContactos.getString(4));
            contacto.setFechanacimiento(cursorContactos.getString(5));

        }

        cursorContactos.close();

        return contacto;
    }

    public boolean updateContacto(int id, String matricula, String nombre, String apellidos, String sexo, String fechaNacimiento)
    {
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {

           db.execSQL("UPDATE Contactos SET Matricula = '" + matricula + "', Nombre = '" + nombre + "', Apellidos = '" + apellidos + "', Sexo = '" + sexo + "', FechaNacimiento = '" + fechaNacimiento + "' WHERE id = '" + id + "'");

           correcto = true;
        } catch (Exception ex)
        {
            ex.toString();
            correcto = false;
        } finally
        {
            db.close();
        }
        return correcto;
    }

    public boolean deleteContacto(int id)
    {
        boolean correcto = false;

        DBHelper dbHelper = new DBHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        try {

            db.execSQL("DELETE FROM Contactos WHERE id = '" + id + "'");

            correcto = true;
        } catch (Exception ex)
        {
            ex.toString();
            correcto = false;
        } finally
        {
            db.close();
        }
        return correcto;
    }
}
