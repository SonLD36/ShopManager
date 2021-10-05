//Lê Đình Sơn
package Controller.ThemSuaXoaSanPham;

import Data.Connect;
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ThemSuaLaptop extends ThemSuaSP {
    @FXML
    private TextField ram;
    @FXML
    private TextField viXuLy;
    @FXML
    private TextField oCung;
    @FXML
    private TextField khoiLuong;


    private String queryLaptop = "INSERT INTO `laptop`(`TenSP`, `GiaBR`, `GiaNV`, `HangSX`, `Model`, `TGBH`, `Ram`, `ViXuLy`, `OCung`, `KL`,`SLSP`) VALUES " +
            "(?,?,?,?,?,?,?,?,?,?,?)";

    public int themSuaSP() {
        Connection connection = Connect.setConnection();
        int res = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(queryLaptop);
            preparedStatement.setString(1,tenSP.getText());
            preparedStatement.setDouble(2, Double.parseDouble(giaBR.getText()));
            preparedStatement.setDouble(3, Double.parseDouble(giaNV.getText()));
            preparedStatement.setString(4, hangSX.getText());
            preparedStatement.setString(5, model.getText());
            preparedStatement.setInt(6, Integer.parseInt(tgBH.getText()));
            preparedStatement.setString(7, ram.getText());
            preparedStatement.setString(8, viXuLy.getText());
            preparedStatement.setString(9, oCung.getText());
            preparedStatement.setDouble(10, Double.parseDouble(khoiLuong.getText()));
            preparedStatement.setInt(11, Integer.parseInt(slSP.getText()));
            res = preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return res;
    }

    public void setTextField(double khoiLuong,String tenSanPham, String hangSanXuat, String model, double gia, int maSanPham, int thoiGianBaoHanh, String ram,String viXuLy,String oCung, int soLuongSP, double giaNV){
        super.setTextField(tenSanPham,hangSanXuat,model,gia,maSanPham,thoiGianBaoHanh,soLuongSP, giaNV);
        this.ram.setText(ram);
        this.khoiLuong.setText(String.valueOf(khoiLuong));
        this.oCung.setText(oCung);
        this.viXuLy.setText(viXuLy);
    }

    @Override
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
            this.ram.setText("");
            this.viXuLy.setText("");
            this.oCung.setText("");
            this.khoiLuong.setText("");
        } else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Lưu thất bại, thử lại");
            a.show();
        }
    }


    public void getQuery(int maSp) {

        if (isUpdate == false) {

            queryLaptop = "INSERT INTO `laptop`(`TenSP`, `GiaBR`, `GiaNV`, `HangSX`, `Model`, `TGBH`, `Ram`, `ViXuLy`, `OCung`, `KL`,`SLSP`) VALUES " +
                    "(?,?,?,?,?,?,?,?,?,?,?)";

        }else{

            queryLaptop = "UPDATE `laptop` SET " +
                    "`TenSP`=?," +
                    "`GiaBR`=?," +
                    "`GiaNV`=?," +
                    "`HangSX`=?," +
                    "`Model`=?," +
                    "`TGBH`=?," +
                    "`Ram`=?," +
                    "`ViXuLy`=?," +
                    "`OCung`=?," +
                    "`KL`=?," +
                    "`SLSP`=? WHERE `MaSP`= " + maSp;
        }
    }


}

