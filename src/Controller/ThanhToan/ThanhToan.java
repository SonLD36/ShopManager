package Controller.ThanhToan;

import Controller.ThaoTacVoiBang;
import Controller.ThaoTacVoiDoiTuong;
import Data.Connect;
import Model.HoaDon.GioHang;
import Model.HoaDon.HoaDon;
import Model.SanPham.DienThoai;
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.util.Callback;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;

public class ThanhToan implements Initializable, ThaoTacVoiDoiTuong, ThaoTacVoiBang {
    @FXML
    private TableColumn<SanPham, String> tuyChonCol;

    @FXML
    private TableColumn<SanPham, Integer> maCol;

    @FXML
    private TableColumn<SanPham, String> slCol;

    @FXML
    private TableColumn<SanPham, String> hangsxCol;


    @FXML
    private TableColumn<SanPham, Double> giaCol;

    @FXML
    private TableColumn<SanPham, String> tenCol;

    @FXML
    private Text tongThanhToan;

    @FXML
    private TableView<SanPham> thongTin;

    @FXML
    private TableColumn<SanPham,String> modelCol;
    Connection connection = Connect.setConnection();
    private ObservableList<SanPham> listSanPham;

    private int spTrongDH;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khoiTaoDanhSach(getData());
        setTongTien(getTongTien());
        maCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("maSanPham"));
        tenCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("tenSanPham"));
        hangsxCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("hangSanXuat"));
        modelCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("model"));
        giaCol.setCellValueFactory(new PropertyValueFactory<SanPham, Double>("gia"));

        Callback<TableColumn<SanPham, String>, TableCell<SanPham, String>> cellFoctoryTuyChon = (TableColumn<SanPham, String> param) -> {

            final TableCell<SanPham, String> cell = new TableCell<SanPham, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button xoaButton = new Button("Xóa");
                        xoaButton.setId("xoaSP");
                        xoaButton.setOnAction((ActionEvent event) -> {
                            SanPham sanPham = getTableView().getItems().get(getIndex());
                            xoa(sanPham);
                        });

                        HBox managebtn = new HBox(xoaButton);
                        managebtn.setStyle("-fx-alignment:center");
                        setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };

        Callback<TableColumn<SanPham, String>, TableCell<SanPham, String>> cellFoctorySoLuong = (TableColumn<SanPham, String> param) -> {

            final TableCell<SanPham, String> cell = new TableCell<SanPham, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                          Spinner<Integer> soLuongSP = new Spinner<>(1,100,0,1);
                          soLuongSP.valueProperty().addListener(new ChangeListener<Integer>() {
                              SanPham sanPham = getTableView().getItems().get(getIndex());
                              @Override
                              public void changed(ObservableValue<? extends Integer> observableValue, Integer integer, Integer t1) {
                                  double tongTienThanhToanHienTai = Double.parseDouble(tongThanhToan.getText());
                                  spTrongDH += (t1 - integer);
                                  tongTienThanhToanHienTai += sanPham.getGia() * (t1 - integer);
                                  tongThanhToan.setText(String.valueOf(tongTienThanhToanHienTai));
                                  sanPham.setSoLuongBan(t1);
                              }
                          });

                          HBox managebtn = new HBox(soLuongSP);
                          managebtn.setStyle("-fx-alignment:center");
                          setGraphic(managebtn);
                    }
                }
            };
            return cell;
        };
        slCol.setCellFactory(cellFoctorySoLuong);
        tuyChonCol.setCellFactory(cellFoctoryTuyChon);
        thongTin.setItems(listSanPham);
    }

    @Override
    public void refreshTable() {
        thongTin.getItems().clear();
        thongTin.setItems(khoiTaoDanhSach(getData()));
    }

    @Override
    public void sua(SanPham sanPham) {

    }

    @Override
    public void xoa(SanPham sanPham) {
        getData().remove(sanPham);
        khoiTaoDanhSach(getData());
        refreshTable();
        setTongTien(getTongTien());
    }

    @Override
    public void mua(SanPham sanPham) {

    }

    @Override
    public List<SanPham> getData() {
        return GioHang.gioHang;
    }

    public double getTongTien() {
        double tongTien = 0;
        List<SanPham> cacSanPham = getData();
        for (int i = 0; i < cacSanPham.size(); i++) {
            tongTien += cacSanPham.get(i).getGia();
        }
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        tongThanhToan.setText(String.valueOf(tongTien));
    }

    public ObservableList<SanPham> khoiTaoDanhSach(List<SanPham> laptops) {
        listSanPham = FXCollections.observableArrayList();
        List<SanPham> cacSanPham = getData();
        spTrongDH = cacSanPham.size();
        for (int i = 0; i < cacSanPham.size(); i++) {
            System.out.println(cacSanPham.get(i));
            listSanPham.add(cacSanPham.get(i));
        }
        return listSanPham;
    }

    public void buttonThanhToan(ActionEvent event) {
        List<SanPham> sanPhamList = GioHang.gioHang;
        SanPham sanPham = new SanPham();
        double loiNhuan = sanPham.getLoiNhuan(sanPhamList);

        HoaDon hoaDon = new HoaDon();
        hoaDon.setSanPham(sanPhamList);
        hoaDon.setLoiNhuan(loiNhuan);
        hoaDon.setSoLuong(spTrongDH);

        if (hoaDon.taoHoaDon() == 1 && sanPham.capNhatSLSP(sanPhamList) == 1) {
            GioHang.gioHang.clear();
            tongThanhToan.setText(String.valueOf(Double.valueOf(0)));
            refreshTable();
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setContentText("Thanh toán thành công");
            a.show();
        }else {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("Thanh toán thất bại");
            a.show();
        }
    }

}
