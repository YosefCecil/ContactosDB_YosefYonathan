package com.example.contactosdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
//import android.widget.SearchView;
import androidx.appcompat.widget.SearchView;


import com.example.contactosdb.adaptadores.AdapterListaContactos;
import com.example.contactosdb.db.ContactosCRUD;
import com.example.contactosdb.entidades.Contactos;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener
{

    private RecyclerView recyclerViewListContactos;
    private FloatingActionButton ftbAnadir;
    private ArrayList<Contactos> listArrayContactos;
    private SearchView svBuscar;

    AdapterListaContactos adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewListContactos = (RecyclerView)findViewById(R.id.rvContactos);

        recyclerViewListContactos.setLayoutManager(new LinearLayoutManager(this));

        ContactosCRUD contactosCRUD = new ContactosCRUD(MainActivity.this);

        listArrayContactos = new ArrayList<>();

         adapter = new AdapterListaContactos(contactosCRUD.showContactos());

        recyclerViewListContactos.setAdapter(adapter);

        if(this.recyclerViewListContactos.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)
        {
            recyclerViewListContactos.setLayoutManager(new GridLayoutManager(this, 2));
        }

        ItemOffsetDecoration itemDecoration = new ItemOffsetDecoration(MainActivity.this, R.dimen.spacingGridRecyclerView);
        recyclerViewListContactos.addItemDecoration(itemDecoration);

        ftbAnadir = (FloatingActionButton)findViewById(R.id.ftbAnadir);
        ftbAnadir.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                Intent intentNewContacto = new Intent(MainActivity.this, NewContactoActivity.class);
                startActivity(intentNewContacto);
            }
        });

        svBuscar = (SearchView)findViewById(R.id.svBuscar);
        svBuscar.setOnQueryTextListener(this);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.menuAnadirContacto:
                mAddContacto();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void mAddContacto()
    {
        Intent intent = new Intent(MainActivity.this, NewContactoActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onQueryTextSubmit(String s)
    {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s)
    {
        adapter.searchContacto(s);
        return false;
    }
}