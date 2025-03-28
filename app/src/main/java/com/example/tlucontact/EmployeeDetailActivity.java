package com.example.tlucontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.card.MaterialCardView;

public class EmployeeDetailActivity extends AppCompatActivity {
    private ImageView ivEmployeeDetailAvatar;
    private TextView tvEmployeeDetailName, tvEmployeeDetailPosition, tvEmployeeDetailUnit,
            tvEmployeeDetailPhone, tvEmployeeDetailEmail;
    private TextView tvEmployeePosition, tvEmployeeUnit;
    private MaterialCardView cardCall, cardEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_detail);

        // Hiển thị nút Back trên ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Chi tiết cán bộ");
        }

        // Khởi tạo các view
        ivEmployeeDetailAvatar = findViewById(R.id.ivEmployeeDetailAvatar);
        tvEmployeeDetailName = findViewById(R.id.tvEmployeeDetailName);
        tvEmployeePosition = findViewById(R.id.tvEmployeePosition);
        tvEmployeeUnit = findViewById(R.id.tvEmployeeUnit);
        tvEmployeeDetailPosition = findViewById(R.id.tvEmployeeDetailPosition);
        tvEmployeeDetailUnit = findViewById(R.id.tvEmployeeDetailUnit);
        tvEmployeeDetailPhone = findViewById(R.id.tvEmployeeDetailPhone);
        tvEmployeeDetailEmail = findViewById(R.id.tvEmployeeDetailEmail);

        cardCall = findViewById(R.id.cardCall);
        cardEmail = findViewById(R.id.cardEmail);

        // Lấy dữ liệu từ Intent
        String employeeId = getIntent().getStringExtra("employee_id");
        String employeeName = getIntent().getStringExtra("employee_name");
        String employeePosition = getIntent().getStringExtra("employee_position");
        String employeePhone = getIntent().getStringExtra("employee_phone");
        String employeeEmail = getIntent().getStringExtra("employee_email");
        String employeeUnitId = getIntent().getStringExtra("employee_unit_id");
        String employeeUnitName = getIntent().getStringExtra("employee_unit_name");
        String employeeImageUrl = getIntent().getStringExtra("employee_image_url");

        // Hiển thị dữ liệu lên các view
        tvEmployeeDetailName.setText(employeeName);
        tvEmployeePosition.setText(employeePosition);
        tvEmployeeUnit.setText(employeeUnitName);
        tvEmployeeDetailPosition.setText(employeePosition);
        tvEmployeeDetailUnit.setText(employeeUnitName);
        tvEmployeeDetailPhone.setText(employeePhone);
        tvEmployeeDetailEmail.setText(employeeEmail);

        // Xử lý sự kiện nút gọi điện
        cardCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = employeePhone.replace(".", "");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });

        // Xử lý sự kiện nút gửi email
        cardEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + employeeEmail));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Liên hệ với " + employeeName);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Xử lý khi nút Back trên ActionBar được nhấn
        if (item.getItemId() == android.R.id.home) {
            finish(); // Quay lại Activity trước đó
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}