//Lê Đình Sơn
package Controller.ThemSuaXoaSanPham;

import Data.DuLieuDienThoai;
import Model.SanPham.DienThoai;
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
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class ThongTinDienThoai extends ThongTinSanPham implements Initializable {
    @FXML
    private TableColumn<DienThoai, String> camCol;

    @FXML
    private TableColumn<DienThoai, Boolean> chongNuocCol;

    @FXML
    private TableColumn<DienThoai, Double> pinCol;

    @FXML
    private TableColumn<DienThoai, String> mhCol;

    @FXML
    private TableView<DienThoai> thongTin;
    @FXML
    protected TableColumn<DienThoai, String> tuyChonCol;

    private ObservableList<DienThoai> listDienThoai;

    @Override
    public List<SanPham> getData() {
        DuLieuDienThoai duLieuDienThoai = new DuLieuDienThoai();
        return duLieuDienThoai.getThongTin();
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
        camCol.setCellValueFactory(new PropertyValueFactory<DienThoai, String>("doPhanGiaiCamera"));
        chongNuocCol.setCellValueFactory(new PropertyValueFactory<DienThoai, Boolean>("chongNuoc"));
        pinCol.setCellValueFactory(new PropertyValueFactory<DienThoai, Double>("thoiLuongPin"));
        mhCol.setCellValueFactory(new PropertyValueFactory<DienThoai, String>("kichThuocManHinh"));

        Callback<TableColumn<DienThoai, String>, TableCell<DienThoai, String>> cellFoctory = (TableColumn<DienThoai, String> param) -> {

            final TableCell<DienThoai, String> cell = new TableCell<DienThoai, String>() {
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
                            DienThoai dienThoai = getTableView().getItems().get(getIndex());
                            sua(dienThoai);
                        });

                        xoaButton.setOnAction((ActionEvent event) -> {
                            DienThoai dienThoai = getTableView().getItems().get(getIndex());
                            xoa(dienThoai);
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
        thongTin.setItems(listDienThoai);
    }


    @Override
    public void lamMoiBang(ActionEvent event) {
        refreshTable();
    }

    @Override
    public void refreshTable() {
        thongTin.getItems().clear();
        thongTin.setItems(khoiTaoDanhSach(getData()));
    }

    @Override
    public void sua(SanPham sanPham) {
        DienThoai dienThoai = (DienThoai) sanPham;
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("/sample/ThemSuaXoaSanPham/themDT.fxml"));
            Parent root = fxmlLoader.load();
            ThemSuaDienThoai themDT =fxmlLoader.getController();
            themDT.setTextField(dienThoai.getTenSanPham(), dienThoai.getHangSanXuat(),dienThoai.getModel(), dienThoai.getGia(),dienThoai.getMaSanPham(),dienThoai.getThoiGianBaoHanh(),dienThoai.getChongNuoc(),dienThoai.getDoPhanGiaiCamera(),dienThoai.getKichThuocManHinh(), dienThoai.getThoiLuongPin(), dienThoai.getSoLuongSP(), dienThoai.getGiaNhapVao());
            themDT.setUpdate(true);
            themDT.getQuery(dienThoai.getMaSanPham());
            Stage sua = new Stage();
            sua.setScene(new Scene(root));
            sua.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void xoa(SanPham sanPham) {
        DienThoai dienThoai = (DienThoai) sanPham;
        ButtonType dongY = new ButtonType("Đồng ý", ButtonBar.ButtonData.YES);
        ButtonType huy = new ButtonType("Hủy", ButtonBar.ButtonData.NO);
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setContentText("Bạn có chắc chắn muốn xóa không ?");
        alert.getButtonTypes().clear();
        alert.getButtonTypes().addAll(dongY,huy);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == dongY) {
           dienThoai.xoaSanPham();
           refreshTable();
        } else {
            alert.close();
        }

    }

    @Override
    public void mua(SanPham sanPham) {

    }

    public ObservableList<DienThoai> khoiTaoDanhSach(List<SanPham> laptops) {
        listDienThoai = FXCollections.observableArrayList();
        List<SanPham> cacDienThoai = getData();
        for (int i = 0; i < cacDienThoai.size(); i++) {
            DienThoai dienThoai = (DienThoai) cacDienThoai.get(i);
            listDienThoai.add(dienThoai);
        }
        return listDienThoai;
    }

    public void themSP(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/sample/ThemSuaXoaSanPham/themDT.fxml"));
            Stage them = new Stage();
            them.setScene(new Scene(root));
            them.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
