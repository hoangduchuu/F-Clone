package net.hoangduchuu.foody.Adapter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import net.hoangduchuu.foody.Model.QuanAnModel;
import net.hoangduchuu.foody.R;
import net.hoangduchuu.foody.utils.Constants;

import java.util.List;

/**
 * Created by hoang on 7/9/17.
 */

public class AdapterRecyclerOdau extends RecyclerView.Adapter<AdapterRecyclerOdau.ViewHolder> {
    List<QuanAnModel> quanAnModelList;
    int resourceFile;

    public AdapterRecyclerOdau(List<QuanAnModel> quanAnModel, int resourceFile) {
        this.quanAnModelList = quanAnModel;
        this.resourceFile = resourceFile;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenQuanQuanOdau;
        Button btnDatMonOdau;
        ImageView imageHinhQuanAnOdau;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenQuanQuanOdau = itemView.findViewById(R.id.txtTenQuanQuanOdau);
            btnDatMonOdau = itemView.findViewById(R.id.btnDatMonOdau);
            imageHinhQuanAnOdau = itemView.findViewById(R.id.imageHinhQuanAnOdau);
        }
    }

    @Override
    public AdapterRecyclerOdau.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resourceFile, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(final AdapterRecyclerOdau.ViewHolder holder, int position) {
        QuanAnModel quanAnModel = quanAnModelList.get(position);
        holder.txtTenQuanQuanOdau.setText(quanAnModel.getTenquanan());
        if (quanAnModel.isGiaohang()) {
            holder.btnDatMonOdau.setVisibility(View.VISIBLE);
        }
        if (quanAnModel.getHinhquanan().size() > 0) {
            StorageReference storageHinhQuanAn = FirebaseStorage.getInstance().getReference()
                    .child(Constants.STORAGE_HINH_ANH_QUAN_AN)
                    .child(quanAnModel.getHinhquanan().get(0));
            storageHinhQuanAn.getBytes(Constants.ONE_MEGA_BYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    holder.imageHinhQuanAnOdau.setImageBitmap(bitmap);
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return quanAnModelList.size();
    }


}
