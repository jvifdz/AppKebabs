package com.example.donner;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AdaptadorRecycler extends RecyclerView.Adapter<AdaptadorRecycler.ViewHolder>{

    List<Kebab> misfilas;

    private OnRecyclerViewLongItemClickListener itemLongClickListener;

    public void setOnItemLongClickListener
            (OnRecyclerViewLongItemClickListener listener){
        itemLongClickListener = listener;
    }

    public AdaptadorRecycler(List<Kebab> datosEnviados){

        misfilas = datosEnviados;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.
                from(parent.getContext()).
                inflate(R.layout.fila,parent,false);
        final ViewHolder viewHolder = new ViewHolder(v);

        v.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (itemLongClickListener!=null){
                    itemLongClickListener.onItemLongClick(
                            v,viewHolder.getAdapterPosition());;
                }
                return true;
            }
        });

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final int pos = position;
        holder.tipo.setText(misfilas.get(position).getTipo());
        holder.miCheck.setChecked(misfilas.get(position).isCheckbox());
        holder.precio.setText(misfilas.get(position).getPrecio());
        holder.miCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Toast.makeText(v.getContext(),
                        misfilas.get(pos).getTipo(),
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return misfilas.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        TextView tipo;
        TextView precio;
        CheckBox miCheck;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tipo = itemView.findViewById(R.id.textViewTipo);
            precio = itemView.findViewById(R.id.textViewPrecio);
            miCheck = itemView.findViewById(R.id.checkBoxLinea);
        }
    }
}
