package Data;
//Nguyễn Danh Tú
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuLieuLaptop implements GetThongTinSanPham {
    private String querySQLLT = "select * from laptop";
    private String querySQLLTMaSP = "select * from laptop where MaSP = ?";

    public List<SanPham> getThongTin()  {
        List<SanPham> res = new ArrayList<SanPham>();
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(querySQLLT);
            while (rs.next()) {
                Laptop lt = new Laptop();
                lt.setMaSanPham(Integer.parseInt(rs.getString("MaSP")));
                lt.setTenSanPham(rs.getString("TenSP"));
                lt.setHangSanXuat(rs.getString("HangSX"));
                lt.setModel(rs.getString("Model"));
                lt.setGia(rs.getDouble("GiaBR"));
                lt.setThoiGianBaoHanh(rs.getInt("TGBH"));
                lt.setKhoiLuong(rs.getDouble("KL"));
                lt.setRam(rs.getString("Ram"));
                lt.setOCung(rs.getString("OCung"));
                lt.setViXuLy(rs.getString("ViXuLy"));
                lt.setSoLuongSP(rs.getInt("SLSP"));
                SanPham sp = lt;
                res.add(sp);
            }
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public List<SanPham> getThongTin(int maSP) {
        List<SanPham> res = new ArrayList<SanPham>();
        ResultSet rs = null;
        try {
            PreparedStatement preparedStatement = conn.prepareStatement(querySQLLTMaSP);
            preparedStatement.setInt(1,maSP);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Laptop lt = new Laptop();
                lt.setMaSanPham(Integer.parseInt(rs.getString("MaSP")));
                lt.setTenSanPham(rs.getString("TenSP"));
                lt.setHangSanXuat(rs.getString("HangSX"));
                lt.setModel(rs.getString("Model"));
                lt.setGia(rs.getDouble("GiaBR"));
                lt.setThoiGianBaoHanh(rs.getInt("TGBH"));
                lt.setKhoiLuong(rs.getDouble("KL"));
                lt.setRam(rs.getString("Ram"));
                lt.setOCung(rs.getString("OCung"));
                lt.setViXuLy(rs.getString("ViXuLy"));
                lt.setSoLuongSP(rs.getInt("SLSP"));
                SanPham sp = lt;
                res.add(sp);
            }
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
