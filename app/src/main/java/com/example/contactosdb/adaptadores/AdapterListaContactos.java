package com.example.contactosdb.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.contactosdb.R;
import com.example.contactosdb.VerContactoActivity;
import com.example.contactosdb.entidades.Contactos;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.zip.Inflater;

public class AdapterListaContactos extends RecyclerView.Adapter<AdapterListaContactos.ContactosViewHolder>
{
    ArrayList<Contactos> listContactos;
    ArrayList<Contactos> listOrigin;

    public AdapterListaContactos(ArrayList<Contactos> listContactos)
    {
        this.listContactos = listContactos;
        listOrigin = new ArrayList<>();
        listOrigin.addAll(listContactos);
    }

    @NonNull
    @Override
    public ContactosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_contactos, null, false);

        return new ContactosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactosViewHolder holder, int position)
    {
        holder.lblMatricula.setText(listContactos.get(position).getMatricula());
        holder.lblNombre.setText(listContactos.get(position).getNombre());
        holder.lblApellidos.setText(listContactos.get(position).getApellidos());
    }

    public void searchContacto(String svBuscar)
    {
        int longitud = svBuscar.length();
        if (longitud == 0)
        {
            listContactos.clear();
            listContactos.addAll(listOrigin);
        } else {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N)
            {
                List<Contactos> coleccion1 = listContactos.stream().filter(i -> i.getMatricula().toLowerCase().contains(svBuscar.toLowerCase())).collect(Collectors.toList());

                List<Contactos> coleccion2 = listContactos.stream().filter(i -> i.getNombre().toLowerCase().contains(svBuscar.toLowerCase())).collect(Collectors.toList());

                List<Contactos> coleccion3 = listContactos.stream().filter(i -> i.getApellidos().toLowerCase().contains(svBuscar.toLowerCase())).collect(Collectors.toList());

                listContactos.clear();
                listContactos.addAll(coleccion1);
                listContactos.addAll(coleccion2);
                listContactos.addAll(coleccion3);

            } else {
                for (Contactos c: listOrigin)
                {
                    if (c.getMatricula().toLowerCase().contains(svBuscar.toLowerCase()) || c.getNombre().toLowerCase().contains(svBuscar.toLowerCase()) ||  c.getApellidos().toLowerCase().contains(svBuscar.toLowerCase()))
                    {
                        listContactos.add(c);
                    }
                }
            }
        }
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount()
    {
        return listContactos.size();
    }

    public class ContactosViewHolder extends RecyclerView.ViewHolder
    {
        TextView lblMatricula;
        TextView lblNombre;
        TextView lblApellidos;

        public ContactosViewHolder(@NonNull View itemView)
        {
            super(itemView);

            lblMatricula = (TextView)itemView.findViewById(R.id.lblMatricula);
            lblNombre = (TextView)itemView.findViewById(R.id.lblNombre);
            lblApellidos = (TextView)itemView.findViewById(R.id.lblApellidos);

            itemView.setOnClickListener(new View.OnClickListener()
            {
                @Override public void onClick(View view)
                {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, VerContactoActivity.class);
                    intent.putExtra("ID", listContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
