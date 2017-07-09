package net.hoangduchuu.foody.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.hoangduchuu.foody.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 7/9/17.
 */

public class QuanAnModel {
    boolean giaohang;
    String giodongcua, giomocua, tenquanan, videogioithieu, maquanan;
    Long luotthich;
    List<String> tienich;

    List<QuanAnModel> quanAnModelList;

    DatabaseReference dataQuanAn;

    public QuanAnModel() {
        dataQuanAn = FirebaseDatabase.getInstance().getReference().child(Constants.NODE_QUAN_AN_S);
    }

    public boolean isGiaohang() {
        return giaohang;
    }

    public void setGiaohang(boolean giaohang) {
        this.giaohang = giaohang;
    }

    public String getGiodongcua() {
        return giodongcua;
    }

    public void setGiodongcua(String giodongcua) {
        this.giodongcua = giodongcua;
    }

    public String getGiomocua() {
        return giomocua;
    }

    public void setGiomocua(String giomocua) {
        this.giomocua = giomocua;
    }

    public String getTenquanan() {
        return tenquanan;
    }

    public void setTenquanan(String tenquanan) {
        this.tenquanan = tenquanan;
    }

    public String getVideogioithieu() {
        return videogioithieu;
    }

    public void setVideogioithieu(String videogioithieu) {
        this.videogioithieu = videogioithieu;
    }

    public String getMaquanan() {
        return maquanan;
    }

    public void setMaquanan(String maquanan) {
        this.maquanan = maquanan;
    }

    public List<String> getTienich() {
        return tienich;
    }

    public void setTienich(List<String> tienich) {
        this.tienich = tienich;
    }

    public Long getLuotthich() {
        return luotthich;
    }

    public void setLuotthich(Long luotthich) {
        this.luotthich = luotthich;
    }

    // by hand
    public List<QuanAnModel> getDanhSachQuanAn() {
        quanAnModelList = new ArrayList<>();
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataValue : dataSnapshot.getChildren()) {
                    QuanAnModel quanAnModel = dataValue.getValue(QuanAnModel.class);
                    Log.d("kiemtra-a", quanAnModel.getTenquanan() + "--video---"
                            + quanAnModel.getVideogioithieu() + "--luotthich---" + quanAnModel.getLuotthich() + "---"+ quanAnModel.isGiaohang());
                    quanAnModelList.add(quanAnModel);
                    Log.d("kiemtra-QqModel-size", quanAnModelList.size() +"");

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        dataQuanAn.addListenerForSingleValueEvent(valueEventListener);

        return quanAnModelList;

    }
}
