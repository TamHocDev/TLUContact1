package com.example.tlucontact;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.EmployeeViewHolder> {
    private Context context;
    private List<Employee> employeeList;

    public EmployeeAdapter(Context context, List<Employee> employeeList) {
        this.context = context;
        this.employeeList = employeeList;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee employee = employeeList.get(position);

        holder.tvEmployeeName.setText(employee.getName());
        holder.tvEmployeePosition.setText(employee.getPosition());
        holder.tvEmployeeUnit.setText(employee.getUnitName());
        holder.tvEmployeePhone.setText(employee.getPhoneNumber());

        // Xử lý sự kiện khi nhấn vào item
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, EmployeeDetailActivity.class);
                intent.putExtra("employee_id", employee.getId());
                intent.putExtra("employee_name", employee.getName());
                intent.putExtra("employee_position", employee.getPosition());
                intent.putExtra("employee_phone", employee.getPhoneNumber());
                intent.putExtra("employee_email", employee.getEmail());
                intent.putExtra("employee_unit_id", employee.getUnitId());
                intent.putExtra("employee_unit_name", employee.getUnitName());
                intent.putExtra("employee_image_url", employee.getImageUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return employeeList.size();
    }

    public static class EmployeeViewHolder extends RecyclerView.ViewHolder {
        ImageView ivEmployeeAvatar;
        TextView tvEmployeeName, tvEmployeePosition, tvEmployeeUnit, tvEmployeePhone;

        public EmployeeViewHolder(@NonNull View itemView) {
            super(itemView);
            ivEmployeeAvatar = itemView.findViewById(R.id.ivEmployeeAvatar);
            tvEmployeeName = itemView.findViewById(R.id.tvEmployeeName);
            tvEmployeePosition = itemView.findViewById(R.id.tvEmployeePosition);
            tvEmployeeUnit = itemView.findViewById(R.id.tvEmployeeUnit);
            tvEmployeePhone = itemView.findViewById(R.id.tvEmployeePhone);
        }
    }
}