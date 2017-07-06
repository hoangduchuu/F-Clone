package net.hoangduchuu.foody;

/**
 * Created by hoang on 7/5/17.
 */

class User {

    String hoten;
    boolean gioitinh;
    Long tuoi;

    public User() {
    }

    public User(String hoten, boolean gioitinh, Long tuoi) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.tuoi = tuoi;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public boolean isGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Long getTuoi() {
        return tuoi;
    }

    public void setTuoi(Long tuoi) {
        this.tuoi = tuoi;
    }
}
