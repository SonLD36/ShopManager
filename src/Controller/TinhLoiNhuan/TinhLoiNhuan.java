//Nguyễn Đình Hiếu
package Controller.TinhLoiNhuan;

import Model.HoaDon.HoaDon;

import java.time.LocalDate;

public class TinhLoiNhuan {
    HoaDon hoaDon = new HoaDon();
    //tinh loi nhuan tu 2 khoang ngay
    public double loiNhuan(LocalDate tuNgayValue,LocalDate denNgayValue) {
        double loiNhuan = hoaDon.tinhLoiNhuan(tuNgayValue,denNgayValue);
        return loiNhuan;
    }
}
