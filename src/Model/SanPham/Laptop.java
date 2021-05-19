/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.SanPham;

/**
 *
 * @author SUN_LIFE
 */
public class Laptop extends SanPham{
    private String viXuLy;
    private double khoiLuong;
    private String oCung;
    private String ram;

    public Laptop() {
    }

    public Laptop(double khoiLuong, String tenSanPham, String hangSanXuat, String model, double gia, int maSanPham, int thoiGianBaoHanh,String ram,String viXuLy,String oCung, int soLuongSP) {
        super(tenSanPham, hangSanXuat, model, gia, maSanPham, thoiGianBaoHanh,soLuongSP);
        this.khoiLuong = khoiLuong;
        this.ram = ram;
        this.viXuLy = viXuLy;
        this.oCung = oCung;
    }

    public double getKhoiLuong() {
        return khoiLuong;
    }

    public void setKhoiLuong(double khoiLuong) {
        this.khoiLuong = khoiLuong;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getViXuLy() {
        return viXuLy;
    }

    public void setViXuLy(String viXuLy) {
        this.viXuLy = viXuLy;
    }

    public String getOCung() {
        return oCung;
    }

    public void setOCung(String oCung) {
        this.oCung = oCung;
    }

}
