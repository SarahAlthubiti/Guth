package com.example.sarah.paramedicsguide;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.security.PublicKey;
import java.util.List;


public class Adapor_report extends RecyclerView.Adapter<Adapor_the_cases.ViewHolder>{
    TextView text_name,text_case, text_details ;

    private Context context;
    private List<Report> reports;

    public Adapor_report(Context context, List<Report> reports) {
        this.context = context;
        this.reports = reports;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView text_name,text_case, text_details ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            text_name=(TextView) itemView.findViewById(R.id.textView_name_get);
            text_case=(TextView) itemView.findViewById(R.id.textView_case_get);
            text_details=(TextView) itemView.findViewById(R.id.textView_details);
        }

    }
    @NonNull
    @Override
    public Adapor_the_cases.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_new_case, viewGroup, false);
        return new Adapor_the_cases.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapor_the_cases.ViewHolder viewHolder, int i) {
        final Report r = reports.get(i);
        viewHolder.text_name.setText(r.patient_name);
        viewHolder.text_case.setText(r.patient_medical_case);
        viewHolder.text_details.setText("التفاصيل..");
        viewHolder.text_details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Report_list_pg.selected_report=r;
                Intent intent = new Intent(context,Report_pg.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }
}
