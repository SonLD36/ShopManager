package Controller;

import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.scene.control.TableView;

import java.util.List;

public interface ThaoTacVoiDoiTuong {
    //sua san pham
    public void sua(SanPham sanPham);
    //xoa san pham
    public void xoa(SanPham sanPham);
    //mua san pham
    public void mua(SanPham sanPham);
    public List<SanPham> getData();
}
