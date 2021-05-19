package Controller.ThemSuaXoaSanPham;


import Data.Connect;
import Data.DuLieuDienThoai;
import Data.DuLieuLaptop;
import Model.SanPham.SanPham;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ThemSuaDienThoai extends ThemSuaSP {
    @FXML
    private TextField chongNuoc;
    @FXML
    private TextField camera;
    @FXML
    private TextField ktmh;
    @FXML
    private TextField pin;

    private String queryDienThoai = "INSERT INTO `dienthoai`(`TenSP`, `GiaBR`, `GiaNV`, `HangSX`, `Model`, `TGBH`, `ChongNuoc`, `DPGCamera`, `KTMH`, `ThoiLuongPin`,`SLSP`) VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?)";


    public int themSuaSP() {
        Connection connection = Connect.setConnection();
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryDienThoai);
            preparedStatement.setString(1,tenSP.getText());
            preparedStatement.setDouble(2, Double.parseDouble(giaBR.getText()));
            preparedStatement.setDouble(3, Double.parseDouble(giaNV.getText()));
            preparedStatement.setString(4, hangSX.getText());
            preparedStatement.setString(5, model.getText());
            preparedStatement.setInt(6, Integer.parseInt(tgBH.getText()));
            preparedStatement.setString(7, chongNuoc.getText());
            preparedStatement.setString(8, camera.getText());
            preparedStatement.setString(9, ktmh.getText());
            preparedStatement.setDouble(10, Double.parseDouble(pin.getText()));
            preparedStatement.setInt(11, Integer.parseInt(slSP.getText()));
            res = preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public void setTextField(String tenSanPham, String hangSanXuat, String model, double gia, int maSanPham, int thoiGianBaoHanh, String chongNuoc, String camera, String ktMH, double pin,int soLuongSP, double giaNV){
        super.setTextField(tenSanPham,hangSanXuat,model,gia,maSanPham,thoiGianBaoHanh,soLuongSP, giaNV);
        this.camera.setText(camera);
        this.chongNuoc.setText(chongNuoc);
        this.ktmh.setText(ktMH);
        this.pin.setText(String.valueOf(pin));
    }


    public void handleButton(ActionEvent event) {
        if (this.themSuaSP() == 1) {
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Lưu sản phẩm thành công");
            a.show();
            this.tenSP.setText("");
            this.giaBR.setText("");
            this.giaNV.setText("");
            this.hangSX.setText("");
            this.model.setText("");
            this.tgBH.setText("");
            this.slSP.setText("");
            this.chongNuoc.setText("");
            this.camera.setText("");
            this.ktmh.setText("");
            this.pin.setText("");
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Lưu thất bại, thử lại");
            a.show();
        }

    }

    public void getQuery(int maSp) {

        if (isUpdate == false) {
            queryDienThoai =  "INSERT INTO `dienthoai`(`TenSP`, `GiaBR`, `GiaNV`, `HangSX`, `Model`, `TGBH`, `ChongNuoc`, `DPGCamera`, `KTMH`, `ThoiLuongPin`,`SLSP`) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)";
        }else{
            queryDienThoai = "UPDATE `dienthoai` SET " +
                    "`TenSP`=?," +
                    "`GiaBR`=?," +
                    "`GiaNV`=?," +
                    "`HangSX`=?," +
                    "`Model`=?," +
                    "`TGBH`=?," +
                    "`ChongNuoc`=?," +
                    "`DPGCamera`=?," +
                    "`KTMH`=?," +
                    "`ThoiLuongPin`=?," +
                    "`SLSP`=? WHERE `MaSP`= " + maSp;
        }
    }

}
