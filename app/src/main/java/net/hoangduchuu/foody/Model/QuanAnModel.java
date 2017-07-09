package net.hoangduchuu.foody.Model;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import net.hoangduchuu.foody.Interface.OdauInterface;
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
    List<String> tienich, hinhquanan;

    List<QuanAnModel> quanAnModelList;

    DatabaseReference nodeRoot;

    public QuanAnModel() {
        nodeRoot = FirebaseDatabase.getInstance().getReference();
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

    public List<String> getHinhquanan() {
        return hinhquanan;
    }

    public void setHinhquanan(List<String> hinhquanan) {
        this.hinhquanan = hinhquanan;
    }

    public void getDanhSachQuanAn(final OdauInterface odauInterface) {

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                DataSnapshot dataSnapshotQuanAn = dataSnapshot.child(Constants.NODE_QUAN_AN_S);
                for (DataSnapshot valueQuanAn : dataSnapshotQuanAn.getChildren()) {
                    QuanAnModel quanAnModel = valueQuanAn.getValue(QuanAnModel.class);

                    Log.d("kiemtra-quanan", valueQuanAn.getKey() + "");
                    quanAnModel.setMaquanan(valueQuanAn.getKey());
                    String maQuanAn = valueQuanAn.getKey();
                    DataSnapshot dataShotHinhQuanAn = dataSnapshot.child(Constants.NODE_HINH_ANH_QUAN_AN_S).child(maQuanAn);
                    List<String> hinhQuanAnList = new ArrayList<>();
                    for (DataSnapshot valueHinhQuanAn : dataShotHinhQuanAn.getChildren()) {
                        hinhQuanAnList.add(valueHinhQuanAn.getValue(String.class));

                    }

                    quanAnModel.setHinhquanan(hinhQuanAnList);
                    odauInterface.getDanhSachQuanAnModel(quanAnModel);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        nodeRoot.addListenerForSingleValueEvent(valueEventListener);
    }

    // by hand
}
