package com.giti.cricket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private Context context;
    private List<Model> modelList;
    Intent intent;

    public MyAdapter(Context context, List<Model> modelList){

        this.context=context;
        this.modelList=modelList;
        intent = new Intent(context,secondApi.class);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View view= LayoutInflater.from(context).inflate(R.layout.singlerow, parent, false);
      MyViewHolder viewHolder = new MyViewHolder(view);
      return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MainActivity ma=new MainActivity();


        Model model=modelList.get(position);
        holder.t1.setText( model.getTeam1());
        holder.t2.setText( model.getTeam2());
holder.ll.setOnClickListener(v -> {
    Log.e("HELLo","xxxxxxx");

    intent.putExtra("idid",model.getId());
    intent.putExtra("date",model.getDate());
    context.startActivity(intent);


});

    }




    @Override
    public int getItemCount() {
        return this.modelList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
LinearLayout ll;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            t1=itemView.findViewById(R.id.t1);
            t2=itemView.findViewById(R.id.t2);

ll=itemView.findViewById(R.id.llout);

        }
    }
}
