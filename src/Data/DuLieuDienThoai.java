package Data;

import Model.SanPham.DienThoai;
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuLieuDienThoai implements GetThongTinSanPham {
    private String querySQLDT = "select *  from dienthoai";
    private String querySQLDTMaSP = "select * from laptop where MaSP = ?";
    public List<SanPham> getThongTin() {
        List<SanPham> res = new ArrayList<SanPham>();
        ResultSet rs = null;
        try {
            Statement stm = conn.createStatement();
            rs = stm.executeQuery(querySQLDT);

            while(rs.next()){
                DienThoai dt = new DienThoai();
                dt.setMaSanPham(rs.getInt("MaSP"));
                dt.setTenSanPham(rs.getString("TenSP"));
                dt.setHangSanXuat(rs.getString("HangSX"));
                dt.setModel(rs.getString("Model"));
                dt.setGia(rs.getDouble("GiaBR"));
                dt.setThoiGianBaoHanh(rs.getInt("TGBH"));
                dt.setKichThuocManHinh(rs.getString("KTMH"));
                dt.setThoiLuongPin(rs.getDouble("ThoiLuongPin"));
                dt.setDoPhanGiaiCamera(rs.getString("DPGCamera"));
                dt.setChongNuoc(rs.getString("ChongNuoc"));
                dt.setSoLuongSP(rs.getInt("SLSP"));
                SanPham sp = (SanPham) dt;
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
            PreparedStatement preparedStatement = conn.prepareStatement(querySQLDTMaSP);
            preparedStatement.setInt(1,maSP);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                DienThoai dt = new DienThoai();
                dt.setMaSanPham(rs.getInt("MaSP"));
                dt.setTenSanPham(rs.getString("TenSP"));
                dt.setHangSanXuat(rs.getString("HangSX"));
                dt.setModel(rs.getString("Model"));
                dt.setGia(rs.getDouble("GiaBR"));
                dt.setThoiGianBaoHanh(rs.getInt("TGBH"));
                dt.setKichThuocManHinh(rs.getString("KTMH"));
                dt.setThoiLuongPin(rs.getDouble("ThoiLuongPin"));
                dt.setDoPhanGiaiCamera(rs.getString("DPGCamera"));
                dt.setChongNuoc(rs.getString("ChongNuoc"));
                dt.setSoLuongSP(rs.getInt("SLSP"));
                SanPham sp = (SanPham) dt;
                res.add(sp);
            }
            return res;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

}
