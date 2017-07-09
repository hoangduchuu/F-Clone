package net.hoangduchuu.foody.Controller;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import net.hoangduchuu.foody.Adapter.AdapterRecyclerOdau;
import net.hoangduchuu.foody.Interface.OdauInterface;
import net.hoangduchuu.foody.Model.QuanAnModel;
import net.hoangduchuu.foody.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hoang on 7/9/17.
 */

public class OdauController {
    Context context;
    QuanAnModel quanAnModel;
    AdapterRecyclerOdau adapterRecyclerOdau;


    public OdauController(Context context) {
        this.context = context;
        quanAnModel = new QuanAnModel();
    }

    public void getDanhSachQuanAnController(RecyclerView recyclerViewOdau) {
        final List<QuanAnModel> quanAnModelList = new ArrayList<>();
        OdauInterface odauInterface = new OdauInterface() {
            @Override
            public void getDanhSachQuanAnModel(QuanAnModel quanAnModel) {
                quanAnModelList.add(quanAnModel);
                adapterRecyclerOdau.notifyDataSetChanged();
                Log.d("kiemtra-inter", quanAnModel.getTenquanan() + "");
            }
        };
        quanAnModel.getDanhSachQuanAn(odauInterface);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context);
        recyclerViewOdau.setLayoutManager(layoutManager);
         adapterRecyclerOdau = new AdapterRecyclerOdau(quanAnModelList, R.layout.custom_layout_recyclerview_odau);
        recyclerViewOdau.setAdapter(adapterRecyclerOdau);

    }
}
