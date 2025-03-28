package com.example.tlucontact;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.text.Normalizer;
import java.util.regex.Pattern;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private SearchView searchView;
    private Spinner spinnerSort;

    private List<Unit> unitsList;
    private List<Unit> filteredUnitsList;
    private List<Employee> employeesList;
    private List<Employee> filteredEmployeesList;

    private UnitAdapter unitAdapter;
    private EmployeeAdapter employeeAdapter;

    private String listType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        // Lấy loại danh sách từ Intent
        listType = getIntent().getStringExtra("list_type");

        // Thiết lập tiêu đề và nút Back cho ActionBar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

            if ("units".equals(listType)) {
                getSupportActionBar().setTitle(R.string.units_list);
            } else if ("employees".equals(listType)) {
                getSupportActionBar().setTitle(R.string.employees_list);
            }
        }

        // Khởi tạo các view
        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.searchView);
        spinnerSort = findViewById(R.id.spinnerSort);

        // Thiết lập RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        // Lấy dữ liệu
        if ("units".equals(listType)) {
            setupUnitsList();
        } else if ("employees".equals(listType)) {
            setupEmployeesList();
        }

        // Thiết lập tìm kiếm
        setupSearch();
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

    private void setupUnitsList() {
        // Lấy dữ liệu đơn vị
        unitsList = DataProvider.getUnitsList();
        filteredUnitsList = new ArrayList<>(unitsList);

        // Khởi tạo adapter
        unitAdapter = new UnitAdapter(this, filteredUnitsList);
        recyclerView.setAdapter(unitAdapter);

        // Thiết lập spinner sắp xếp
        String[] sortOptions = {"Sắp xếp theo", "Tên (A-Z)", "Tên (Z-A)"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sortOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(spinnerAdapter);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Luôn bắt đầu với danh sách đầy đủ
                filteredUnitsList.clear();
                filteredUnitsList.addAll(unitsList);

                if (position == 1) {
                    // Sắp xếp theo tên (A-Z) - Sử dụng Collator cho tiếng Việt
                    java.text.Collator collator = java.text.Collator.getInstance(new java.util.Locale("vi", "VN"));
                    Collections.sort(filteredUnitsList, new Comparator<Unit>() {
                        @Override
                        public int compare(Unit u1, Unit u2) {
                            String name1 = getLastName(u1.getName());
                            String name2 = getLastName(u2.getName());
                            return collator.compare(name1, name2);
                        }
                    });
                } else if (position == 2) {
                    // Sắp xếp theo tên (Z-A) - Sử dụng Collator cho tiếng Việt
                    java.text.Collator collator = java.text.Collator.getInstance(new java.util.Locale("vi", "VN"));
                    Collections.sort(filteredUnitsList, new Comparator<Unit>() {
                        @Override
                        public int compare(Unit u1, Unit u2) {
                            String name1 = getLastName(u1.getName());
                            String name2 = getLastName(u2.getName());
                            return collator.compare(name2, name1);
                        }
                    });
                }
                // Thông báo cho adapter biết dữ liệu đã thay đổi
                unitAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Không làm gì
            }
        });
    }

    private void setupEmployeesList() {
        // Lấy dữ liệu CBNV
        employeesList = DataProvider.getEmployeesList();
        filteredEmployeesList = new ArrayList<>(employeesList);

        // Khởi tạo adapter
        employeeAdapter = new EmployeeAdapter(this, filteredEmployeesList);
        recyclerView.setAdapter(employeeAdapter);

        // Thiết lập spinner sắp xếp
        String[] sortOptions = {"Sắp xếp theo", "Tên (A-Z)", "Tên (Z-A)"};
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, sortOptions);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSort.setAdapter(spinnerAdapter);

        spinnerSort.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Luôn bắt đầu với danh sách đầy đủ
                filteredEmployeesList.clear();
                filteredEmployeesList.addAll(employeesList);

                if (position == 1) {
                    // Sắp xếp theo tên (A-Z) - Sử dụng Collator cho tiếng Việt
                    java.text.Collator collator = java.text.Collator.getInstance(new java.util.Locale("vi", "VN"));
                    Collections.sort(filteredEmployeesList, new Comparator<Employee>() {
                        @Override
                        public int compare(Employee e1, Employee e2) {
                            // Lấy tên từ họ tên đầy đủ
                            String name1 = getLastName(e1.getName());
                            String name2 = getLastName(e2.getName());
                            return collator.compare(name1, name2);
                        }
                    });
                } else if (position == 2) {
                    // Sắp xếp theo tên (Z-A) - Sử dụng Collator cho tiếng Việt
                    java.text.Collator collator = java.text.Collator.getInstance(new java.util.Locale("vi", "VN"));
                    Collections.sort(filteredEmployeesList, new Comparator<Employee>() {
                        @Override
                        public int compare(Employee e1, Employee e2) {
                            // Lấy tên từ họ tên đầy đủ
                            String name1 = getLastName(e1.getName());
                            String name2 = getLastName(e2.getName());
                            return collator.compare(name2, name1);
                        }
                    });
                }
                employeeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    // Phương thức để lấy tên từ họ tên đầy đủ
    private String getLastName(String fullName) {
        if (fullName == null || fullName.isEmpty()) {
            return "";
        }
        String[] parts = fullName.split("\\s+");
        return parts.length > 0 ? parts[parts.length - 1] : "";
    }

    // Thanh tìm kiếm
    private void setupSearch() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                String searchText = removeAccent(newText.toLowerCase().trim());

                if ("units".equals(listType)) {
                    // Tìm kiếm trong danh sách đơn vị - chỉ tìm theo tên đơn vị
                    filteredUnitsList.clear();

                    if (searchText.isEmpty()) {
                        filteredUnitsList.addAll(unitsList);
                    } else {
                        for (Unit unit : unitsList) {
                            // Tìm kiếm theo tên đơn vị với hỗ trợ tiếng Việt
                            String unitName = removeAccent(unit.getName().toLowerCase());
                            if (unitName.contains(searchText)) {
                                filteredUnitsList.add(unit);
                            }
                        }
                    }
                    unitAdapter.notifyDataSetChanged();
                } else if ("employees".equals(listType)) {
                    // Tìm kiếm trong danh sách CBNV - chỉ tìm theo tên cán bộ
                    filteredEmployeesList.clear();

                    if (searchText.isEmpty()) {
                        filteredEmployeesList.addAll(employeesList);
                    } else {
                        for (Employee employee : employeesList) {
                            // Tìm kiếm theo tên cán bộ với hỗ trợ tiếng Việt
                            String employeeName = removeAccent(employee.getName().toLowerCase());
                            if (employeeName.contains(searchText)) {
                                filteredEmployeesList.add(employee);
                            }
                        }
                    }
                    employeeAdapter.notifyDataSetChanged();
                }
                return true;
            }
        });
    }

    /**
     * Phương thức loại bỏ dấu tiếng Việt để hỗ trợ tìm kiếm không phân biệt dấu
     * Ví dụ: "Nguyễn Văn An" -> "nguyen van an"
     */
    private String removeAccent(String s) {
        if (s == null) {
            return "";
        }
        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replace('đ', 'd').replace('Đ', 'D');
    }
}