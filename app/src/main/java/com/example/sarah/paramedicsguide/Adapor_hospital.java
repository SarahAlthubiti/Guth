package com.example.sarah.paramedicsguide;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.List;

public class Adapor_hospital extends RecyclerView.Adapter<Adapor_hospital.ViewHolder> {
    @NonNull
    @Override
    public Adapor_hospital.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_recyclerview_search, viewGroup,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final Adapor_hospital.ViewHolder viewHolder, int i) {
        Hospital h = hospitals.get(i);
        viewHolder.text.setText(h.hospitalName);

        viewHolder.text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = viewHolder.text.getText().toString();
                Search.search_bar.setText(s);
                //Toast.makeText(context,"لم يتم العثور على المستشفى ", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() { return hospitals.size(); }

    private Context context;
    private List<Hospital>hospitals;
    public Adapor_hospital(Context context , List<Hospital> hospitals){
        this.context=context;
        this.hospitals=hospitals;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text=(TextView) itemView.findViewById(R.id.textView_name_search);
        }
    }
}