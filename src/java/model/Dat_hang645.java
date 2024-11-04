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
public class Dat_hang645 {
    private int id;
    private int idKhachHang;
    private Date ngayDat;
    private float tongtien;

    public Dat_hang645(int id, int idKhachHang, Date ngayDat, float tongtien) {
        this.id = id;
        this.idKhachHang = idKhachHang;
        this.ngayDat = ngayDat;
        this.tongtien = tongtien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public Date getNgayDat() {
        return ngayDat;
    }

    public void setNgayDat(Date ngayDat) {
        this.ngayDat = ngayDat;
    }

    public float getTongtien() {
        return tongtien;
    }

    public void setTongtien(float tongtien) {
        this.tongtien = tongtien;
    }

    @Override
    public String toString() {
        return "Dat_hang645{" + "id=" + id + ", idKhachHang=" + idKhachHang + ", ngayDat=" + ngayDat + ", tongtien=" + tongtien + '}';
    }
    
}
