package Controller.ThemSuaXoaSanPham;

import Data.DuLieuLaptop;
import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.sql.*;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThongTinLapTop extends ThongTinSanPham implements Initializable{

    @FXML
    private TableColumn<Laptop, String> ramCol;

    @FXML
    private TableColumn<Laptop, String> ocungCol;

    @FXML
    private TableColumn<Laptop, Double> klCol;

    @FXML
    private TableView<Laptop> thongTin;

    @FXML
    private TableColumn<Laptop, String> vxlCol;
    @FXML
    protected TableColumn<Laptop, String> tuyChonCol;

    private ObservableList<Laptop> listLaptop;


    @Override
    public List<SanPham> getData() {
        DuLieuLaptop duLieuLaptop = new DuLieuLaptop();
        return duLieuLaptop.getThongTin();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        khoiTaoDanhSach(getData());
        maCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("maSanPham"));
        tenCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("tenSanPham"));
        hangsxCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("hangSanXuat"));
        modelCol.setCellValueFactory(new PropertyValueFactory<SanPham, String>("model"));
        giaCol.setCellValueFactory(new PropertyValueFactory<SanPham, Double>("gia"));
        bhCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("thoiGianBaoHanh"));
        slCol.setCellValueFactory(new PropertyValueFactory<SanPham, Integer>("soLuongSP"));
        ramCol.setCellValueFactory(new PropertyValueFactory<Laptop, String>("ram"));
        ocungCol.setCellValueFactory(new PropertyValueFactory<Laptop, String>("oCung"));
        klCol.setCellValueFactory(new PropertyValueFactory<Laptop, Double>("khoiLuong"));
        vxlCol.setCellValueFactory(new PropertyValueFactory<Laptop, String>("viXuLy"));

        Callback<TableColumn<Laptop, String>, TableCell<Laptop, String>> cellFoctory = (TableColumn<Laptop, String> param) -> {

            final TableCell<Laptop, String> cell = new TableCell<Laptop, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);

                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {
                        Button suaButton = new Button("Sửa");
                        Button xoaButton = new Button("Xóa");
                        suaButton.setId("suaSP");
                        xoaButton.setId("xoaSP");

                        suaButton.setOnAction((ActionEvent event) -> {
                            sua(getTableView().getItems().get(getIndex()));
                        });

                        xoaButton.setOnAction((ActionEvent event) -> {
                            Laptop laptop = getTableView().getItems().get(getIndex());
                            xoa(laptop);

                        });

                        HBox managebtn = new HBox(suaButton, xoaButton);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(xoaButton, new Insets(2, 2, 0, 3));
                        HBox.setMargin(suaButton, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                    }
                }
            };
            return cell;
        };
        tuyChonCol.setCellFactory(cellFoctory);
        thongTin.setItems(listLaptop);
    }

    @Override
    public void refreshTable() {
        thongTin.getItems().clear();
        thongTin.setItems(khoiTaoDanhSach(getData()));
    }

    @Override
    public void sua(SanPham sanPham) {
        Laptop laptop = (Laptop) sanPham;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/ThemSuaXoaSanPham/themLaptop.fxml"));
            Parent root = fxmlLoader.load();
            ThemSuaLaptop suaLaptop =fxmlLoader.getController();
            suaLaptop.setTextField(laptop.getKhoiLuong(),laptop.getTenSanPham(),laptop.getHangSanXuat(),laptop.getModel(),laptop.getGia(),laptop.getMaSanPham(),laptop.getThoiGianBaoHanh(),laptop.getRam(),laptop.getViXuLy(),laptop.getOCung(),laptop.getSoLuongSP(), laptop.getGiaNhapVao());
            suaLaptop.setUpdate(true);
            suaLaptop.getQuery(laptop.getMaSanPham());
            Stage sua = new Stage();
            sua.setScene(new Scene(root));
            sua.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void xoa(SanPham sanPham) {
        Laptop laptop = (Laptop) sanPham;
        ButtonType dongY = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        ButtonType huy = new ButtonType("Hủy", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Bạn có chắc chắn muốn xóa không ?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(dongY,huy);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == dongY) {
            laptop.xoaSanPham();
            refreshTable();
        } else {
            alert.close();
        }

    }

    @Override
    public void mua(SanPham sanPham) {

    }


    public ObservableList<Laptop> khoiTaoDanhSach(List<SanPham> laptops) {
        listLaptop = FXCollections.observableArrayList();
        List<SanPham> cacLaptop = getData();
        for (int i = 0; i < cacLaptop.size(); i++) {
            Laptop laptop = (Laptop) cacLaptop.get(i);
            listLaptop.add(laptop);
        }
        return listLaptop;
    }

    @Override
    public void lamMoiBang(ActionEvent event) {
        refreshTable();
    }

    public void themSP(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/ThemSuaXoaSanPham/themLaptop.fxml"));
            Stage them = new Stage();
            them.setScene(new Scene(root));
            them.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
