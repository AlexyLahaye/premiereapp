package com.btsSIO.firstapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

public class ListeAdapteur extends ArrayAdapter<Membres> {
    private Context context;
    private TextView tvNom;
    private TextView tvPrenom;
    private TextView tvEMail;
    private ImageView tvimgAvatar;
    private RequestOptions requestOptions;

    public ListeAdapteur (@NonNull Context context, List<Membres> listeMembres) {
        super(context, -1, listeMembres);
        this.context = context;
        requestOptions = new RequestOptions()
                .centerCrop()
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        Membres unMembre;
        view = null;
        if (convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) this.context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.ligne,parent,false);
        }
        else{
            view = convertView;
        }
        unMembre = getItem(position);
        tvNom = (TextView) view.findViewById(R.id.tvNom);
        tvPrenom = (TextView) view.findViewById(R.id.tvPrenom);
        tvEMail = (TextView) view.findViewById(R.id.tvEmail);
        tvimgAvatar = (ImageView) view.findViewById(R.id.imgAvatar);

        tvNom.setText(unMembre.getNom());
        tvPrenom.setText(unMembre.getPrenom());
        tvEMail.setText(unMembre.getMail());

        Glide.with(context).load(unMembre.getImg()).apply(requestOptions).into(tvimgAvatar);

        return view;

    }
}
