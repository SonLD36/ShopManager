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
public class DienThoai extends SanPham{
    private String kichThuocManHinh;
    private double thoiLuongPin;
    private String doPhanGiaiCamera;
    private String chongNuoc;

    public DienThoai() {
    }

    public DienThoai(String kichThuocManHinh, double thoiLuongPin, String doPhanGiaiCamera, String chongNuoc, String tenSanPham, String hanSanXuat, String model, double gia, int maSanPham, int thoiGianBaoHanh, int soLuongSP) {
        super(tenSanPham, hanSanXuat, model, gia, maSanPham, thoiGianBaoHanh,soLuongSP);
        this.kichThuocManHinh = kichThuocManHinh;
        this.thoiLuongPin = thoiLuongPin;
        this.doPhanGiaiCamera = doPhanGiaiCamera;
        this.chongNuoc = chongNuoc;
    }

    public String getKichThuocManHinh() {
        return kichThuocManHinh;
    }

    public void setKichThuocManHinh(String kichThuocManHinh) {
        this.kichThuocManHinh = kichThuocManHinh;
    }

    public double getThoiLuongPin() {
        return thoiLuongPin;
    }

    public void setThoiLuongPin(double thoiLuongPin) {
        this.thoiLuongPin = thoiLuongPin;
    }

    public String getDoPhanGiaiCamera() {
        return doPhanGiaiCamera;
    }

    public void setDoPhanGiaiCamera(String doPhanGiaiCamera) {
        this.doPhanGiaiCamera = doPhanGiaiCamera;
    }

    public String getChongNuoc() {
        return chongNuoc;
    }
    public void setChongNuoc(String chongNuoc) {
        this.chongNuoc = chongNuoc;
    }

}
