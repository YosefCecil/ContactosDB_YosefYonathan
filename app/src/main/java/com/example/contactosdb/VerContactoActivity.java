package com.example.contactosdb;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactosdb.db.ContactosCRUD;
import com.example.contactosdb.entidades.Contactos;

public class VerContactoActivity extends AppCompatActivity
{
    private EditText tfMatricula;
    private EditText tfNombre;
    private EditText tfApellidos;
    private EditText tfSexo;
    private EditText tfFechaNacimiento;
    private Button btnUpdateContacto;
    private Button btnDeleteContacto;

    private Contactos contacto;

    private boolean correcto = false;

    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_contacto);

        tfMatricula = (EditText)findViewById(R.id.tfMatricula);
        tfNombre = (EditText)findViewById(R.id.tfNombre);
        tfApellidos = (EditText)findViewById(R.id.tfApellidos);
        tfSexo = (EditText)findViewById(R.id.tfSexo);
        tfFechaNacimiento = (EditText)findViewById(R.id.tfFechaNacimiento);

        btnUpdateContacto = (Button)findViewById(R.id.btnUpdateContacto);

        if (savedInstanceState == null)
        {
            Bundle extras = getIntent().getExtras();

            if (extras == null)
            {
                id = Integer.parseInt(null);
            } else
            {
                id = extras.getInt("ID");
            }
        } else
        {
            if (id != 0)
            {
                id = (int) savedInstanceState.getSerializable("ID");
            }

        }

        final ContactosCRUD contactosCRUD = new ContactosCRUD(VerContactoActivity.this);
        contacto = contactosCRUD.verContactos(id);

        if (contacto != null)
        {
            tfMatricula.setText(contacto.getMatricula());
            tfNombre.setText(contacto.getNombre());
            tfApellidos.setText(contacto.getApellidos());
            tfSexo.setText(contacto.getSexo());
            tfFechaNacimiento.setText(contacto.getFechanacimiento());
        }

        btnUpdateContacto.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                if(!tfMatricula.getText().toString().equals("") && !tfNombre.getText().toString().equals("") && !tfApellidos.getText().toString().equals("") && !tfSexo.getText().toString().equals("") && !tfFechaNacimiento.getText().toString().equals(""))
                {
                    correcto = contactosCRUD.updateContacto(id, tfMatricula.getText().toString(), tfNombre.getText().toString(), tfApellidos.getText().toString(), tfSexo.getText().toString(), tfFechaNacimiento.getText().toString());

                    if (correcto)
                    {
                        Toast.makeText(VerContactoActivity.this, "Contacto actualizado", Toast.LENGTH_SHORT).show();
                    } else
                    {
                        Toast.makeText(VerContactoActivity.this, "Error al actualizar contacto", Toast.LENGTH_SHORT).show();
                    }
                } else
                {
                    Toast.makeText(VerContactoActivity.this, "Por favor, lleno todos los campos", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnDeleteContacto = (Button)findViewById(R.id.btnDeleteContacto);
        btnDeleteContacto.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerContactoActivity.this);

                builder.setMessage("¿Eliminar este contacto?").setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override public void onClick(DialogInterface dialogInterface, int i)
                    {
                        boolean accion = contactosCRUD.deleteContacto(id);

                        if (accion)
                        {
                            Toast.makeText(VerContactoActivity.this, "Contacto eliminado", Toast.LENGTH_LONG).show();
                            listaContactos();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener()
                {
                    @Override public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                }).show();
            }
        });
    }

    private void listaContactos()
    {
        Intent intent = new Intent(VerContactoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}