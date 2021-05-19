/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.HoaDon;


import Data.Connect;
import Model.SanPham.SanPham;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SUN_LIFE
 */
public class HoaDon {
    Connection connection = Connect.setConnection();
    private List<SanPham> cacSanPham;
    private double loiNhuan;
    private Date ngayXuatHoaDon;
    private int maHoaDon;

    private int soLuong;

    public HoaDon(List<SanPham> cacSanPham, double loiNhuan, Date ngayXuatHoaDon, int maHoaDon) {
        this.cacSanPham = cacSanPham;
        this.loiNhuan = loiNhuan;
        this.ngayXuatHoaDon = ngayXuatHoaDon;

        this.maHoaDon = maHoaDon;

    }

    public HoaDon() {

    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public List<SanPham>  getSanPham() {
        return cacSanPham;
    }

    public void setSanPham(List<SanPham> cacSanPham) {
        this.cacSanPham = cacSanPham;
    }

    public double getLoiNhuan() {
        return loiNhuan;
    }

    public void setLoiNhuan(double loiNhuan) {
        this.loiNhuan = loiNhuan;
    }

    public Date getNgayXuatHoaDon() {
        return ngayXuatHoaDon;
    }

    public void setNgayXuatHoaDon(Date ngayXuatHoaDon) {
        this.ngayXuatHoaDon = ngayXuatHoaDon;
    }


    public int getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(int maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public int taoHoaDon() {
        String themHopDongQuery = "INSERT INTO `hoadon`(`LoiNhuan`, `SoLuong`) VALUES (?,?)";
        int isSuccessful = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(themHopDongQuery);
            preparedStatement.setDouble(1, this.getLoiNhuan());
            preparedStatement.setInt(2, this.getSoLuong());

            isSuccessful = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return isSuccessful;
    }
    
    public double tinhLoiNhuan (LocalDate tuNgay, LocalDate denNgay) {
        String queryGetLoiNhuan = "select * from hoadon " +
                "where Date(NgayTaoHoaDon) BETWEEN ? and ?";
        double loiNhuan = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryGetLoiNhuan);
            preparedStatement.setDate(1, java.sql.Date.valueOf(tuNgay));
            preparedStatement.setDate(2, java.sql.Date.valueOf(denNgay));
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                double tmp = resultSet.getDouble("LoiNhuan");
                loiNhuan += tmp;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return loiNhuan;
    }
    
}
