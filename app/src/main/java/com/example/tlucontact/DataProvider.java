package com.example.tlucontact;

import java.util.ArrayList;
import java.util.List;

public class DataProvider {
    // Danh sách lưu trữ dữ liệu
    private static List<Unit> unitsList;
    private static List<Employee> employeesList;

    // Khởi tạo dữ liệu mẫu
    static {
        initData();
    }

    private static void initData() {
        unitsList = new ArrayList<>();
        employeesList = new ArrayList<>();

        // Thêm các đơn vị mẫu
        unitsList.add(new Unit("U001", "Khoa Công nghệ thông tin", "024.38522028",
                "Tầng 1, Nhà C5, Đại học Thủy Lợi", "cntt@tlu.edu.vn",
                "Đào tạo ngành CNTT, Kỹ thuật phần mềm, Hệ thống thông tin"));

        unitsList.add(new Unit("U002", "Khoa Kinh tế và Quản lý", "024.35638213",
                "Tầng 3, Nhà A1, Đại học Thủy Lợi", "kinhte@tlu.edu.vn",
                "Đào tạo các ngành Kinh tế, Quản trị kinh doanh"));

        unitsList.add(new Unit("U003", "Phòng Đào tạo", "024.35639460",
                "Tầng 1, Nhà A1, Đại học Thủy Lợi", "daotao@tlu.edu.vn",
                "Quản lý công tác đào tạo, kế hoạch giảng dạy"));

        unitsList.add(new Unit("U004", "Phòng Tài chính - Kế toán", "024.35632211",
                "Tầng 2, Nhà A1, Đại học Thủy Lợi", "tckt@tlu.edu.vn",
                "Quản lý tài chính, kế toán của trường"));

        unitsList.add(new Unit("U005", "Thư viện", "024.35638825",
                "Tầng 1, Nhà T45, Đại học Thủy Lợi", "thuvien@tlu.edu.vn",
                "Cung cấp tài liệu học tập, nghiên cứu"));

        // Thêm các cán bộ nhân viên mẫu
        employeesList.add(new Employee("E001", "Nguyễn Văn An", "Trưởng khoa",
                "0912345678", "an.nv@tlu.edu.vn", "U001", "Khoa Công nghệ thông tin", ""));

        employeesList.add(new Employee("E002", "Trần Thị Bình", "Phó trưởng khoa",
                "0923456789", "binh.tt@tlu.edu.vn", "U001", "Khoa Công nghệ thông tin", ""));

        employeesList.add(new Employee("E003", "Lê Văn Cường", "Trưởng khoa",
                "0934567890", "cuong.lv@tlu.edu.vn", "U002", "Khoa Kinh tế và Quản lý", ""));

        employeesList.add(new Employee("E004", "Phạm Thị Dung", "Giảng viên",
                "0945678901", "dung.pt@tlu.edu.vn", "U001", "Khoa Công nghệ thông tin", ""));

        employeesList.add(new Employee("E005", "Hoàng Văn Em", "Trưởng phòng",
                "0956789012", "em.hv@tlu.edu.vn", "U003", "Phòng Đào tạo", ""));

        employeesList.add(new Employee("E006", "Vũ Thị Phương", "Kế toán viên",
                "0967890123", "phuong.vt@tlu.edu.vn", "U004", "Phòng Tài chính - Kế toán", ""));

        employeesList.add(new Employee("E007", "Đỗ Văn Giang", "Thủ thư",
                "0978901234", "giang.dv@tlu.edu.vn", "U005", "Thư viện", ""));

        employeesList.add(new Employee("E008", "Ngô Thị Hương", "Giảng viên",
                "0989012345", "huong.nt@tlu.edu.vn", "U002", "Khoa Kinh tế và Quản lý", ""));
    }

    // Lấy danh sách đơn vị
    public static List<Unit> getUnitsList() {
        return new ArrayList<>(unitsList);
    }

    // Lấy danh sách cán bộ nhân viên
    public static List<Employee> getEmployeesList() {
        return new ArrayList<>(employeesList);
    }

    // Tìm đơn vị theo ID
    public static Unit findUnitById(String id) {
        for (Unit unit : unitsList) {
            if (unit.getId().equals(id)) {
                return unit;
            }
        }
        return null;
    }

    // Tìm cán bộ nhân viên theo ID
    public static Employee findEmployeeById(String id) {
        for (Employee employee : employeesList) {
            if (employee.getId().equals(id)) {
                return employee;
           }
        }
        return null;
    }

    // Thêm đơn vị mới
    public static Unit addUnit(String name, String phoneNumber, String address, String email, String description) {
        String id = "U" + String.format("%03d", unitsList.size() + 1);
        Unit newUnit = new Unit(id, name, phoneNumber, address, email, description);
        unitsList.add(newUnit);
        return newUnit;
    }

    // Cập nhật thông tin đơn vị
    public static boolean updateUnit(String id, String name, String phoneNumber, String address, String email, String description) {
        Unit unit = findUnitById(id);
        if (unit != null) {
            unit.setName(name);
            unit.setPhoneNumber(phoneNumber);
            unit.setAddress(address);
            unit.setEmail(email);
            unit.setDescription(description);
            return true;
        }
        return false;
    }

    // Xóa đơn vị
    public static boolean deleteUnit(String id) {
        Unit unit = findUnitById(id);
        if (unit != null) {
            // Trước khi xóa đơn vị, cần kiểm tra xem có CBNV nào thuộc đơn vị này không
            boolean hasEmployees = false;
            for (Employee employee : employeesList) {
                if (employee.getUnitId().equals(id)) {
                    hasEmployees = true;
                    break;
                }
            }

            if (!hasEmployees) {
                unitsList.remove(unit);
                return true;
            }
        }
        return false;
    }

    // Thêm cán bộ nhân viên mới
    public static Employee addEmployee(String name, String position, String phoneNumber,
                                       String email, String unitId, String imageUrl) {
        String id = "E" + String.format("%03d", employeesList.size() + 1);
        Unit unit = findUnitById(unitId);
        String unitName = (unit != null) ? unit.getName() : "";

        Employee newEmployee = new Employee(id, name, position, phoneNumber, email, unitId, unitName, imageUrl);
        employeesList.add(newEmployee);
        return newEmployee;
    }

    // Cập nhật thông tin cán bộ nhân viên
    public static boolean updateEmployee(String id, String name, String position, String phoneNumber,
                                         String email, String unitId, String imageUrl) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            Unit unit = findUnitById(unitId);
            String unitName = (unit != null) ? unit.getName() : "";

            employee.setName(name);
            employee.setPosition(position);
            employee.setPhoneNumber(phoneNumber);
            employee.setEmail(email);
            employee.setUnitId(unitId);
            employee.setUnitName(unitName);
            employee.setImageUrl(imageUrl);
            return true;
        }
        return false;
    }

    // Xóa cán bộ nhân viên
    public static boolean deleteEmployee(String id) {
        Employee employee = findEmployeeById(id);
        if (employee != null) {
            employeesList.remove(employee);
            return true;
        }
        return false;
    }
}
