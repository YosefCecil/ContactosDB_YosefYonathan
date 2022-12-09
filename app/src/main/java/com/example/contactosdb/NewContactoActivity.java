package com.example.contactosdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.contactosdb.db.ContactosCRUD;

public class NewContactoActivity extends AppCompatActivity
{
    private EditText tfMatricula;
    private EditText tfNombre;
    private EditText tfApellidos;
    private EditText tfSexo;
    private EditText tfFechaNacimiento;
    private Button btnAnadirContacto;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcontacto);

        tfMatricula = (EditText)findViewById(R.id.tfMatricula);
        tfNombre = (EditText)findViewById(R.id.tfNombre);
        tfApellidos = (EditText)findViewById(R.id.tfApellidos);
        tfSexo = (EditText)findViewById(R.id.tfSexo);
        tfFechaNacimiento = (EditText)findViewById(R.id.tfFechaNacimiento);

        btnAnadirContacto = (Button)findViewById(R.id.btnAnadirContacto);
        btnAnadirContacto.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                ContactosCRUD contactosCRUD = new ContactosCRUD(NewContactoActivity.this);


                if(!tfMatricula.getText().toString().equals("") && !tfNombre.getText().toString().equals("") && !tfApellidos.getText().toString().equals("") && !tfSexo.getText().toString().equals("") && !tfFechaNacimiento.getText().toString().equals(""))
                {
                    long id = contactosCRUD.insertContacto(tfMatricula.getText().toString(), tfNombre.getText().toString(), tfApellidos.getText().toString(), tfSexo.getText().toString(), tfFechaNacimiento.getText().toString());

                    if(id > 0)
                    {
                        Toast.makeText(NewContactoActivity.this, "Contacto añadido", Toast.LENGTH_SHORT).show();
                        limpiar();
                    } else
                    {
                        Toast.makeText(NewContactoActivity.this, "Error al añadir contacto", Toast.LENGTH_SHORT).show();
                    }

                } else
                {
                    Toast.makeText(NewContactoActivity.this, "Por favor, lleno todos los campos", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void limpiar()
    {
        tfMatricula.setText("");
        tfNombre.setText("");
        tfApellidos.setText("");
        tfSexo.setText("");
        tfFechaNacimiento.setText("");
    }
}