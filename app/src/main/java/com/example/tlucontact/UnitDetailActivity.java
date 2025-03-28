package com.example.tlucontact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UnitDetailActivity extends AppCompatActivity {
    private TextView tvUnitDetailName, tvUnitDetailPhone, tvUnitDetailAddress,
            tvUnitDetailEmail, tvUnitDetailDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unit_detail);

        // Hiển thị nút Back trên ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setTitle("Chi tiết đơn vị");
        }

        // Khởi tạo các view
        tvUnitDetailName = findViewById(R.id.tvUnitDetailName);
        tvUnitDetailPhone = findViewById(R.id.tvUnitDetailPhone);
        tvUnitDetailAddress = findViewById(R.id.tvUnitDetailAddress);
        tvUnitDetailEmail = findViewById(R.id.tvUnitDetailEmail);
        tvUnitDetailDescription = findViewById(R.id.tvUnitDetailDescription);
        Button btnCall = findViewById(R.id.btnCall);
        Button btnEmail = findViewById(R.id.btnEmail);

        // Lấy dữ liệu từ Intent
        String unitId = getIntent().getStringExtra("unit_id");
        String unitName = getIntent().getStringExtra("unit_name");
        String unitPhone = getIntent().getStringExtra("unit_phone");
        String unitAddress = getIntent().getStringExtra("unit_address");
        String unitEmail = getIntent().getStringExtra("unit_email");
        String unitDescription = getIntent().getStringExtra("unit_description");

        // Hiển thị dữ liệu lên các view
        tvUnitDetailName.setText(unitName);
        tvUnitDetailPhone.setText(unitPhone);
        tvUnitDetailAddress.setText(unitAddress);
        tvUnitDetailEmail.setText(unitEmail);
        tvUnitDetailDescription.setText(unitDescription);

        // Xử lý sự kiện nút gọi điện
        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phone = unitPhone.replace(".", "");
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
            }
        });

        // Xử lý sự kiện nút gửi email
        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:" + unitEmail));
                intent.putExtra(Intent.EXTRA_SUBJECT, "Liên hệ với " + unitName);
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