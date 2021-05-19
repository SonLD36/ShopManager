package Controller.ThemSuaXoaSanPham;

import Controller.ThaoTacVoiBang;
import Data.Connect;
import Data.DuLieuDienThoai;
import Data.DuLieuLaptop;
import Model.SanPham.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public abstract class ThemSuaSP {

    @FXML
    protected TextField tenSP;
    @FXML
    protected TextField giaBR;
    @FXML
    protected TextField giaNV;
    @FXML
    protected TextField hangSX;
    @FXML
    protected TextField model;
    @FXML
    protected TextField tgBH;
    @FXML
    protected TextField slSP;
    @FXML
    protected AnchorPane themSPPane;


    protected boolean isUpdate =  false;

    private String query = "insert into sanpham(TenSP,GiaBR,GiaNV,HangSX,Model,TGBH,SLSP) values" +
            " (?,?,?,?,?,?,?)";
    protected Connection con = Connect.setConnection();
    ResultSet resultSet = null;

    public void setTextField(String tenSanPham, String hangSanXuat, String model, double gia, int maSanPham, int thoiGianBaoHanh, int soLuongSP, double giaNV) {
        tenSP.setText(tenSanPham);
        giaBR.setText(String.valueOf(gia));
        this.giaNV.setText(String.valueOf(giaNV));
        hangSX.setText(hangSanXuat);
        this.model.setText(model);
        this.tgBH.setText(String.valueOf(thoiGianBaoHanh));
        slSP.setText(String.valueOf(soLuongSP));
    }

    public abstract int themSuaSP();
    public void setUpdate(boolean b) {
        this.isUpdate = b;
    }
    public abstract void handleButton(ActionEvent event);
    public abstract void getQuery(int maSp);
}
