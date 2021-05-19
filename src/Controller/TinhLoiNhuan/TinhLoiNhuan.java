package Controller.TinhLoiNhuan;

import Model.HoaDon.HoaDon;

import java.time.LocalDate;

public class TinhLoiNhuan {
    HoaDon hoaDon = new HoaDon();
    public double loiNhuan(LocalDate tuNgayValue,LocalDate denNgayValue) {
        double loiNhuan = hoaDon.tinhLoiNhuan(tuNgayValue,denNgayValue);
        return loiNhuan;
    }
}
