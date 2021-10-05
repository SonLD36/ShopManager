package Data;

import Model.SanPham.SanPham;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface GetThongTinSanPham {
    Connection conn = Connect.setConnection();
    //lay thong tin tat ca san pham
    public List<SanPham> getThongTin() ;
}
