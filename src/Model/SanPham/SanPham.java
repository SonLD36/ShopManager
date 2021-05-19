/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.SanPham;

import Data.Connect;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

import java.sql.*;
import java.util.List;

/**
 *
 * @author SUN_LIFE
 */
public class SanPham {
    protected int maSanPham;
    protected String tenSanPham;
    protected String hangSanXuat;
    protected String model;
    protected double gia;
    protected int thoiGianBaoHanh;
    protected int soLuongSP;
    protected int soLuongBan = 1;
    Connection connection = Connect.setConnection();
    String query = null;
    ResultSet resultSet = null ;
    PreparedStatement preparedStatement = null ;

    public SanPham() {
    }

    public SanPham(String tenSanPham, String hangSanXuat, String model, double gia, int maSanPham, int thoiGianBaoHanh, int soLuongSP) {
        this.tenSanPham = tenSanPham;
        this.hangSanXuat = hangSanXuat;
        this.model = model;
        this.gia = gia;
        this.maSanPham = maSanPham;
        this.thoiGianBaoHanh = thoiGianBaoHanh;
        this.soLuongSP = soLuongSP;

    }

    public int getSoLuongBan() { return soLuongBan; }

    public void setSoLuongBan(int soLuongBan) { this.soLuongBan = soLuongBan; }

    public int getSoLuongSP() {
        return soLuongSP;
    }

    public void setSoLuongSP(int soLuongSP) {
        this.soLuongSP = soLuongSP;
    }

    public String getTenSanPham() {
        return tenSanPham;
    }

    public void setTenSanPham(String tenSanPham) {
        this.tenSanPham = tenSanPham;
    }

    public String getHangSanXuat() {
        return hangSanXuat;
    }

    public void setHangSanXuat(String hangSanXuat) {
        this.hangSanXuat = hangSanXuat;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public int getMaSanPham() {
        return maSanPham;
    }

    public void setMaSanPham(int maSanPham) {
        this.maSanPham = maSanPham;
    }

    public int getThoiGianBaoHanh() {
        return thoiGianBaoHanh;
    }

    public void setThoiGianBaoHanh(int thoiGianBaoHanh) {
        this.thoiGianBaoHanh = thoiGianBaoHanh;
    }

    public int capNhatSLSP(List<SanPham> sanPhamList) {
        String updateQuery = null;
        int isSuccessful = 0;

        for (SanPham sanPham: sanPhamList) {
            if (sanPham instanceof Laptop) {
                updateQuery = "UPDATE `laptop` SET " +
                        "`SLSP`= ? WHERE MaSP = ?";
            } else if (sanPham instanceof DienThoai) {
                updateQuery = "UPDATE `dienthoai` SET " +
                        "`SLSP`= ? WHERE MaSP = ?";
            }

            try {
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                int soLuongConLai = sanPham.getSoLuongSP() - sanPham.getSoLuongBan();
                if (soLuongConLai <= 0) {
                    isSuccessful = 0;
                    break;
                }
                preparedStatement.setDouble(1, soLuongConLai);
                preparedStatement.setInt(2, sanPham.getMaSanPham());

                isSuccessful = preparedStatement.executeUpdate();

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return isSuccessful;
    }

    public double getLoiNhuan(List<SanPham> sanPhamList) {
        double ketQua = 0;
        ResultSet resultSet = null;
        for (SanPham sanPham : sanPhamList){
            if (sanPham instanceof DienThoai){
                query = "select * from dienthoai where MaSP = '" + sanPham.getMaSanPham() + "'";
            } else if (sanPham instanceof Laptop) {
                query = "select * from laptop where MaSP = '" + sanPham.getMaSanPham() + "'";
            }
            try {
                Statement statement = connection.createStatement();
                resultSet = statement.executeQuery(query);
                System.out.println(query);
                while (resultSet.next()){
                    Double giaNV = resultSet.getDouble("GiaNV");
                    Double giaBR = resultSet.getDouble("GiaBR");
                    ketQua += (giaBR - giaNV)*sanPham.getSoLuongBan();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return ketQua;
    }

    public double getGiaNhapVao() {

        double ketQua = 0;
        if (this instanceof DienThoai) {
            query = "select GiaNV from dienthoai where MaSP = '" +this.getMaSanPham()+ "'";
        } else if (this instanceof Laptop) {
            query = "select GiaNV from laptop where MaSP = '" + this.getMaSanPham()+ "'";
        }
        try {
            Statement statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            while (resultSet.next()){
                ketQua = resultSet.getDouble("GiaNV");
                break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return ketQua;
    }

    public void xoaSanPham() {
        if (this instanceof DienThoai) {
            query = "DELETE FROM `dienthoai` WHERE `dienthoai`.`MaSP` = '"+ this.getMaSanPham() + "'";
        }else if (this instanceof Laptop) {
            query = "DELETE FROM `laptop` WHERE `laptop`.`MaSP` = '"+ this.getMaSanPham() + "'";
        }
        try {
            preparedStatement= connection.prepareStatement(query);
            preparedStatement.execute(query);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
//    public abstract String inTTin();
}
