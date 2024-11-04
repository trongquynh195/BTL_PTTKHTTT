/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Mon_duoc_dat645 {
    private int idDathang;
    private int idMonan;
    private int soLuong ;
    private float tonggia;
    private String ten;

    public Mon_duoc_dat645(int idDathang, int idMonan, int soLuong, float tonggia, String ten) {
        this.idDathang = idDathang;
        this.idMonan = idMonan;
        this.soLuong = soLuong;
        this.tonggia = tonggia;
        this.ten = ten;
    }

    public int getIdDathang() {
        return idDathang;
    }

    public void setIdDathang(int idDathang) {
        this.idDathang = idDathang;
    }

    public int getIdMonan() {
        return idMonan;
    }

    public void setIdMonan(int idMonan) {
        this.idMonan = idMonan;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getTonggia() {
        return tonggia;
    }

    public void setTonggia(float tonggia) {
        this.tonggia = tonggia;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Override
    public String toString() {
        return "Mon_duoc_dat645{" + "idDathang=" + idDathang + ", idMonan=" + idMonan + ", soLuong=" + soLuong + ", tonggia=" + tonggia + ", ten=" + ten + '}';
    }
    
}
