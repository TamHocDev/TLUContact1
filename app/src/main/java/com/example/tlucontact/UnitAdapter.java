package com.example.tlucontact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UnitAdapter extends RecyclerView.Adapter<UnitAdapter.UnitViewHolder> {
    private Context context;
    private List<Unit> unitList;

    public UnitAdapter(Context context, List<Unit> unitList) {
        this.context = context;
        this.unitList = unitList;
    }

    @NonNull
    @Override
    public UnitViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_unit, parent, false);
        return new UnitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UnitViewHolder holder, int position) {
        Unit unit = unitList.get(position);

        holder.tvUnitName.setText(unit.getName());
        holder.tvUnitPhone.setText(unit.getPhoneNumber());
        holder.tvUnitAddress.setText(unit.getAddress());

        // Xử lý sự kiện khi nhấn vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UnitDetailActivity.class);
                intent.putExtra("unit_id", unit.getId());
                intent.putExtra("unit_name", unit.getName());
                intent.putExtra("unit_phone", unit.getPhoneNumber());
                intent.putExtra("unit_address", unit.getAddress());
                intent.putExtra("unit_email", unit.getEmail());
                intent.putExtra("unit_description", unit.getDescription());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return unitList.size();
    }

    public static class UnitViewHolder extends RecyclerView.ViewHolder {
        TextView tvUnitName, tvUnitPhone, tvUnitAddress;

        public UnitViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUnitName = itemView.findViewById(R.id.tvUnitName);
            tvUnitPhone = itemView.findViewById(R.id.tvUnitPhone);
            tvUnitAddress = itemView.findViewById(R.id.tvUnitAddress);
        }
    }
}