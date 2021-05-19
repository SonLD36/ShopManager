package Controller.TimKiemSanPham;

import Controller.ThaoTacVoiBang;
import Controller.ThaoTacVoiDoiTuong;
import Controller.ThemSuaXoaSanPham.ThemSuaLaptop;
import Controller.ThemSuaXoaSanPham.ThongTinDienThoai;
import Controller.ThemSuaXoaSanPham.ThongTinLapTop;
import Controller.ThemSuaXoaSanPham.ThongTinSanPham;
import Data.DuLieuDienThoai;
import Data.DuLieuLaptop;
import Model.HoaDon.GioHang;
import Model.SanPham.DienThoai;
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Filter;

public class TimKiemSP implements ThaoTacVoiDoiTuong {
    private ObservableList<SanPham> listSanPham;

    public List<SanPham> getData() {
        DuLieuDienThoai duLieuDienThoai = new DuLieuDienThoai();
        DuLieuLaptop duLieuLaptop = new DuLieuLaptop();
        List<SanPham> dt = duLieuDienThoai.getThongTin();
        List<SanPham> lt = duLieuLaptop.getThongTin();
        dt.addAll(lt);
        return dt;
    };

    public void TimKiemSanPham(TableView<SanPham> thongTin,TextField timKiemInput,TableColumn<SanPham, Integer> maCol,TableColumn<SanPham, String>tenCol,
                               TableColumn<SanPham, String> hangsxCol,TableColumn<SanPham, String> modelCol,TableColumn<SanPham, Double> giaCol,
                               TableColumn<SanPham, Integer> slCol,TableColumn<SanPham, Integer> bhCol,TableColumn<SanPham, String> tuyChonCol) {
        khoiTaoDanhSach(listSanPham);
        maCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("maSanPham"));
        tenCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("tenSanPham"));
        hangsxCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("hangSanXuat"));
        modelCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("model"));
        giaCol.setCellValueFactory(new PropertyValueFactory<SanPham, Double>("gia"));
        bhCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("thoiGianBaoHanh"));
        slCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("soLuongSP"));
        Callback<TableColumn<SanPham, String>, TableCell<SanPham, String>> cellFoctory = (TableColumn<SanPham, String> param) -> {

            final TableCell<SanPham, String> cell = new TableCell<SanPham, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button suaButton = new Button("Sửa");
                        Button xoaButton = new Button("Xóa");
                        Button muaButton = new Button("Mua");
                        suaButton.setId("suaSP");
                        xoaButton.setId("xoaSP");
                        muaButton.setId("muaSP");
                        suaButton.setOnAction((ActionEvent event) -> {
                            SanPham sanPham = getTableView().getItems().get(getIndex());
                            sua(sanPham);
                        });
                        xoaButton.setOnAction((ActionEvent event) -> {
                            SanPham sanPham = getTableView().getItems().get(getIndex());
                            xoa(sanPham);

                        });
                        muaButton.setOnAction((ActionEvent event) -> {
                            SanPham sanPham = getTableView().getItems().get(getIndex());
                            mua(sanPham);
                        });

                        HBox managebtn = new HBox(suaButton, xoaButton,muaButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(xoaButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(suaButton, new Insets(2, 3, 0, 2));
                        HBox.setMargin(muaButton, new Insets(2, 3, 0, 2));
                        setGraphic(managebtn);

                    }
                }
            };
            return cell;
        };

        FilteredList<SanPham> filteredList = new FilteredList<>(listSanPham, b -> true);

        timKiemInput.textProperty().addListener((observable, oldvalue, newValue) -> {
            filteredList.setPredicate(sanPham -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (sanPham.getTenSanPham().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (sanPham.getHangSanXuat().toLowerCase().indexOf(lowerCaseFilter) != -1){
                    return true;
                }else return false;
            });
        });
        SortedList<SanPham> sortedList = new SortedList<>(filteredList);
        sortedList.comparatorProperty().bind(thongTin.comparatorProperty());
        tuyChonCol.setCellFactory(cellFoctory);
        thongTin.setItems(sortedList);

    }

    public ObservableList<SanPham> khoiTaoDanhSach(List<SanPham> sanPhams) {
        listSanPham = FXCollections.observableArrayList();
        List<SanPham> cacSamPham = getData();
        for (int i = 0; i < cacSamPham.size(); i++) {
            listSanPham.add(cacSamPham.get(i));
        }
        return listSanPham;
    }


    @Override
    public void sua(SanPham sanPham) {
        if (sanPham instanceof Laptop) {
            ThongTinLapTop thongTinLapTop = new ThongTinLapTop();
            thongTinLapTop.sua(sanPham);
        } else if(sanPham instanceof DienThoai) {
            ThongTinDienThoai thongTinDienThoai = new ThongTinDienThoai();
            thongTinDienThoai.sua(sanPham);
        }
    }


    @Override
    public void xoa(SanPham sanPham) {
        ButtonType dongY = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        ButtonType huy = new ButtonType("Hủy", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Bạn có chắc chắn muốn xóa không ?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(dongY,huy);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == dongY) {
            sanPham.xoaSanPham();
        } else {
            alert.close();
        }
    }

    @Override
    public void mua(SanPham sanPham) {
        GioHang.gioHang.add(sanPham);
    }

}
