package Controller.ThemSuaXoaSanPham;

import Controller.ThaoTacVoiBang;
import Controller.ThaoTacVoiDoiTuong;
import Data.Connect;
import Model.SanPham.DienThoai;
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;

import java.sql.*;
import java.util.List;

public abstract class ThongTinSanPham implements ThaoTacVoiDoiTuong, ThaoTacVoiBang {

    @FXML
    protected TableColumn<SanPham, Integer> maCol;

    @FXML
    protected TableColumn<SanPham, Integer> slCol;

    @FXML
    protected TableColumn<SanPham, String> hangsxCol;

    @FXML
    protected TableColumn<SanPham, Double> giaCol;

    @FXML
    protected TableColumn<SanPham, String> tenCol;

    @FXML
    protected TableColumn<SanPham, Integer> bhCol;

    @FXML
    protected TableColumn<SanPham, String> modelCol;
    String query = null;
    PreparedStatement preparedStatement = null ;
    ResultSet resultSet = null ;
    Connection connection = Connect.setConnection();
    public abstract void lamMoiBang(ActionEvent event) ;
    public abstract void themSP(ActionEvent event);
}
