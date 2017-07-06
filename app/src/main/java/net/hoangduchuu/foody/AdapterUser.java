package net.hoangduchuu.foody;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by hoang on 7/5/17.
 */

public class AdapterUser extends ArrayAdapter<User> {
    Context context;
    int resources;
    List<User> objects;

    public AdapterUser(@NonNull Context context, @LayoutRes int resource, @NonNull List<User> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resources = resource;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(resources, parent, false);

        TextView tvHoten = (TextView) view.findViewById(R.id.txtHoTen);
        TextView tvTuoi = (TextView) view.findViewById(R.id.txtTuoi);

        tvHoten.setText(objects.get(position).getHoten());
        tvTuoi.setText(String.valueOf(objects.get(position).getTuoi().toString()));


        return view;
    }
}
