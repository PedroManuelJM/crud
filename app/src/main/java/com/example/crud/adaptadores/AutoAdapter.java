package com.example.crud.adaptadores;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.crud.R;
import com.example.crud.actividades.EditarActivity;
import com.example.crud.clases.Auto;
import com.loopj.android.http.Base64;


import java.util.List;

public class AutoAdapter extends RecyclerView.Adapter<AutoAdapter.ViewHolder> {

    private List<Auto> lista_autos;

    public AutoAdapter(List<Auto> lista_autos) {
        this.lista_autos =lista_autos;
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
        // en item auto se mostrara los autos
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_auto,parent,false);
        // devolver la vista
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull AutoAdapter.ViewHolder holder, int position) {
           // Posición de autos
        Auto auto = lista_autos.get(position);
        // recuperando los datos
        holder.Marca.setText(auto.getMarca());
        holder.Modelo.setText(auto.getModelo());
        holder.Placa.setText(auto.getPlaca());
        holder.Precio.setText(String.valueOf(auto.getPrecio())); // precio --> double
        String imagen = auto.getImagen();
        byte[] image_byte = Base64.decode(imagen, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(image_byte,0,image_byte.length);
        holder.Foto_auto.setImageBitmap(bitmap);
        holder.item_auto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i_editar = new Intent(v.getContext(), EditarActivity.class);
                i_editar.putExtra("id",lista_autos.get(position).getId());
                v.getContext().startActivity(i_editar);
                //Toast.makeText(v.getContext(),"ID: "+ lista_autos.get(position).getId(),Toast.LENGTH_SHORT).show();
                System.out.println("ID: "+ lista_autos.get(position).getId());
            }
        });



    }

    @Override
    public int getItemCount() {
        return lista_autos.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // instanciar
        CardView item_auto;
        TextView  Marca,Modelo,Placa,Precio;
        ImageView Foto_auto;


        public ViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            // enlazando la parte lógica y gráfica
            item_auto=itemView.findViewById(R.id.cv_item_auto);
            Marca=itemView.findViewById(R.id.lblItemMarca);
            Modelo=itemView.findViewById(R.id.lblItemModelo);
            Placa=itemView.findViewById(R.id.lblItemPlaca);
            Precio=itemView.findViewById(R.id.lblItemPrecio);
            Foto_auto=itemView.findViewById(R.id.ivItemAuto);


        }
    }
}
