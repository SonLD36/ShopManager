//Nguyễn Đình Hiếu
package Controller;


import Controller.TimKiemSanPham.TimKiemSP;
import Controller.TinhLoiNhuan.TinhLoiNhuan;
import Model.HoaDon.GioHang;
import Model.HoaDon.HoaDon;
import Model.SanPham.SanPham;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TableColumn<SanPham, String> tuyChonCol;

    @FXML
    private TableColumn<SanPham, Integer> maCol;

    @FXML
    private TableColumn<SanPham, Integer> slCol;

    @FXML
    private TableColumn<SanPham, String> hangsxCol;

    @FXML
    private TableColumn<SanPham, Double> giaCol;

    @FXML
    private TableColumn<SanPham, String>tenCol;

    @FXML
    private TableView<SanPham> thongTin;

    @FXML
    private TableColumn<SanPham, String> modelCol;

    @FXML
    private TableColumn<SanPham, Integer> bhCol;

    @FXML
    private TextField timKiemInput;

    @FXML
    private DatePicker tuNgay;

    @FXML
    private DatePicker denNgay;

    @FXML
    private Button laptopTT;

    @FXML
    private Button dienThoaiTT;

    @FXML
    private AnchorPane thongTinLoiNhuan;

    @FXML
    private Text loiNhuanText;

    @FXML
    private Text tuNgayText;

    @FXML
    private Text denNgayText;

    @FXML
    private Button buttonLamMoi;

    @FXML
    private Button gioHang;

    @FXML
    private Button xemLoiNhuan;

    //set thong tin bang tim kiem
    public void setBangThongTin() {
        TimKiemSP timKiemSP = new TimKiemSP();
        timKiemSP.TimKiemSanPham(thongTin,timKiemInput,maCol,tenCol,hangsxCol,modelCol,giaCol,slCol,bhCol,tuyChonCol);
    }

    public Scene setScence(Parent root) {
        Scene scene = new Scene(root,1200,580);
        return scene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setBangThongTin();
        buttonLamMoi.setOnAction((ActionEvent event) -> {
            setBangThongTin();
        });
        Stage thongTin = new Stage();
        //xu ly event cua button xem thong tin laptop
        laptopTT.setOnAction((ActionEvent event) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/ThemSuaXoaSanPham/thongTinLapTop2.fxml"));
                Scene scene = setScence(root);
                thongTin.setScene(scene);
                thongTin.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //xu ly event cua button xem thong tin dien thoai
        dienThoaiTT.setOnAction((ActionEvent event) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/ThemSuaXoaSanPham/thongTinDienThoai.fxml"));
                Scene scene = setScence(root);
                thongTin.setScene(scene);
                thongTin.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //xu ly event cua button xem thong tin gio hang
        gioHang.setOnAction((ActionEvent event) -> {
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/sample/GioHang/gioHang.fxml"));
                Scene scene = new Scene(root,880,566);
                thongTin.setScene(scene);
                thongTin.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        //xu ly event cua button xem loi nhuan
        xemLoiNhuan.setOnAction((ActionEvent event) -> {
            LocalDate tuNgayValue = tuNgay.getValue();
            LocalDate denNgayValue = denNgay.getValue();
            if (tuNgayValue == null || denNgayValue == null) {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setContentText("Vui lòng điền đầy đủ thông tin");
                alert.show();
            } else {
                TinhLoiNhuan tinhLoiNhuan = new TinhLoiNhuan();
                double loiNhuan = tinhLoiNhuan.loiNhuan(tuNgayValue,denNgayValue);
                tuNgayText.setText(tuNgayValue.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
                denNgayText.setText(denNgayValue.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")));
                loiNhuanText.setText(String.format("%.2f",loiNhuan));
                thongTinLoiNhuan.setVisible(true);
            }
        });
    }
}
