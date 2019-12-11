package com.example.sarah.paramedicsguide;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.paramedicViewHolder>{
    private Context mCtx;
    private List<Paramedic> paramedicsList;

    public Adapter(Context mCtx, List<Paramedic> paramedicsList) {
        this.mCtx = mCtx;
        this.paramedicsList = paramedicsList;
    }


    @NonNull
    @Override
    public paramedicViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.list_layout,null);
        paramedicViewHolder holder = new paramedicViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull paramedicViewHolder holder, int position) {
        Paramedic paramedic = paramedicsList.get(position);
        holder.name.setText(paramedic.getParamedicName());
        holder.id.setText(paramedic.getParamedicID());

    }

    @Override
    public int getItemCount() {
        return paramedicsList.size();
    }

    class paramedicViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView id;
        TextView email;

        public paramedicViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textViewName);
            id = itemView.findViewById(R.id.textViewID);
            email = itemView.findViewById(R.id.textViewEmail);

        }
    }

}
