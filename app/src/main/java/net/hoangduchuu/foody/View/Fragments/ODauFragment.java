package net.hoangduchuu.foody.View.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import net.hoangduchuu.foody.Controller.OdauController;
import net.hoangduchuu.foody.Model.QuanAnModel;
import net.hoangduchuu.foody.R;

import java.util.List;

/**
 * Created by hoang on 7/8/17.
 */

public class ODauFragment extends Fragment {
    OdauController odauController;
    RecyclerView recyclerViewOdau;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.layout_fragment_odau, container, false);
        recyclerViewOdau = (RecyclerView) view.findViewById(R.id.RecyclerOdau);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        odauController = new OdauController(getContext());
        odauController.getDanhSachQuanAnController(recyclerViewOdau);

    }
}
