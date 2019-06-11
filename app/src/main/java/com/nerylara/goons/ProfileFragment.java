package com.nerylara.goons;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileFragment extends Fragment {
    ViewGroup rootView;
    ImageView user_icon;

    int icon_indicator = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  (ViewGroup) inflater.inflate(R.layout.fragment_profile, container,false);
        user_icon = rootView.findViewById(R.id.user_icon);
        TextView win_loss = rootView.findViewById(R.id.reputation);
        TextView squad_repping = rootView.findViewById(R.id.placement);
        TextView rank = rootView.findViewById(R.id.members);

        // TODO: Get user icon function;
        // TODO: Get user rank function;
        // TODO: Get user squad function;
        // TODO: Get user win_loss function;

        win_loss.setText("Win-Loss");
        squad_repping.setText("Squad");
        rank.setText("Rank");


        if(icon_indicator == 0){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 1){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 2){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 3){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 4){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 5){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 6){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 7){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 8){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 9){
            user_icon.setImageResource(R.drawable.squad1);
        }


        return rootView;
    }

}
