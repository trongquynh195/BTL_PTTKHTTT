/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Date;

/**
 *
 * @author admin
 */
public class Ban_duoc_dat645 {
    private int id;
    private String ten;
    private String sdt;
    private Date ngaydat;
    private int soban;
    private int idBanAn;
    private int idKhachHang;

    public Ban_duoc_dat645(int id, String ten, String sdt, Date ngaydat, int idBanAn, int idKhachHang,int soban) {
        this.id = id;
        this.ten = ten;
        this.sdt = sdt;
        this.ngaydat = ngaydat;
        this.idBanAn = idBanAn;
        this.idKhachHang = idKhachHang;
        this.soban = soban;
    }

    public int getId() {
        return id;
    }

    public int getSoban() {
        return soban;
    }

    public void setSoban(int soban) {
        this.soban = soban;
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

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public Date getNgaydat() {
        return ngaydat;
    }

    public void setNgaydat(Date ngaydat) {
        this.ngaydat = ngaydat;
    }

    public int getIdBanAn() {
        return idBanAn;
    }

    public void setIdBanAn(int idBanAn) {
        this.idBanAn = idBanAn;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    @Override
    public String toString() {
        return "Ban_duoc_dat645{" + "id=" + id + ", ten=" + ten + ", sdt=" + sdt + ", ngaydat=" + ngaydat + ", idBanAn=" + idBanAn + ", idKhachHang=" + idKhachHang + '}';
    }
    
}
