/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author admin
 */
public class Khach_hang645 {
    private int idNguoiDung;
    private String loaiThe;

    public Khach_hang645(int idNguoiDung, String loaiThe) {
        this.idNguoiDung = idNguoiDung;
        this.loaiThe = loaiThe;
    }

    public int getIdNguoiDung() {
        return idNguoiDung;
    }

    public void setIdNguoiDung(int idNguoiDung) {
        this.idNguoiDung = idNguoiDung;
    }

    public String getLoaiThe() {
        return loaiThe;
    }

    public void setLoaiThe(String loaiThe) {
        this.loaiThe = loaiThe;
    }

    @Override
    public String toString() {
        return "Khach_hang645{" + "idNguoiDung=" + idNguoiDung + ", loaiThe=" + loaiThe + '}';
    }
    
}
