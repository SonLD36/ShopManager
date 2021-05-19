package Controller;

import Model.SanPham.Laptop;
import Model.SanPham.SanPham;
import javafx.scene.control.TableView;

import java.util.List;

public interface ThaoTacVoiDoiTuong {
    public void sua(SanPham sanPham);
    public void xoa(SanPham sanPham);
    public void mua(SanPham sanPham);
    public List<SanPham> getData();
}
