/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class Mon_an645 {
    private int id;
    private String ten;             // Tương ứng với cột 'ten' trong bảng
    private String loai;            // Tương ứng với cột 'loai' trong bảng
    private String mota;            // Tương ứng với cột 'mota' trong bảng
    private float gia;              // Tương ứng với cột 'gia' trong bảng
    private int trangthai;          // Tương ứng với cột 'trangthai' trong bảng
    private int idNhaHang;          // Tương ứng với cột 'idNhaHang' trong bảng

    // Constructor
    public Mon_an645(int id, String ten, String loai, String mota, float gia, int trangthai, int idNhaHang) {
        this.id = id;
        this.ten = ten;
        this.loai = loai;
        this.mota = mota;
        this.gia = gia;
        this.trangthai = trangthai;
        this.idNhaHang = idNhaHang;
    }

    // Getters và Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public int getIdNhaHang() {
        return idNhaHang;
    }

    public void setIdNhaHang(int idNhaHang) {
        this.idNhaHang = idNhaHang;
    }

    // Phương thức toString để in thông tin món ăn
    @Override
    public String toString() {
        return "mon_an [id=" + id + ", ten=" + ten + ", loai=" + loai + ", mota=" + mota + ", gia=" + gia
                + ", trangthai=" + trangthai + ", idNhaHang=" + idNhaHang + "]";
    }
}

