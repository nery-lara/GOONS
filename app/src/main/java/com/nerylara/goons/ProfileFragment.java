package com.nerylara.goons;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView =  (ViewGroup) inflater.inflate(R.layout.fragment_profile, container,false);
        user_icon = rootView.findViewById(R.id.user_icon);

        String username = getArguments().getString("Username_transfer");

        TextView win_loss = rootView.findViewById(R.id.reputation);
        TextView squad_repping = rootView.findViewById(R.id.placement);
        TextView rank = rootView.findViewById(R.id.members);


        System.out.println("Username in PROFILE FRAGMENT " + username);
        final profile profile = new profile(getContext());
        profile.setId(username);
        profile.sendProfileDetails(getContext());

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



        int icon_indicator = profile.getImageNum();
        System.out.println("icon " + icon_indicator);
        System.out.println(icon_indicator);
        // TODO: Get user icon function;
        // TODO: Get user rank function;
        // TODO: Get user squad function;
        // TODO: Get user win_loss function;
                TextView win_loss = rootView.findViewById(R.id.reputation);
                TextView squad_repping = rootView.findViewById(R.id.placement);
                TextView rank = rootView.findViewById(R.id.members);


        String winrate = Double.toString(profile.getWinrate());
        win_loss.setText(winrate);
        squad_repping.setText(profile.getSquad());
        rank.setText(profile.getRank());


        if(icon_indicator == 0){
            user_icon.setImageResource(R.drawable.squad1);
        }else if (icon_indicator == 1){
            user_icon.setImageResource(R.drawable.squad2);
        }else if (icon_indicator == 2){
            user_icon.setImageResource(R.drawable.squad3);
        }else if (icon_indicator == 3){
            user_icon.setImageResource(R.drawable.squad4);
        }else if (icon_indicator == 4){
            user_icon.setImageResource(R.drawable.squad5);
        }else if (icon_indicator == 5){
            user_icon.setImageResource(R.drawable.squad6);
        }else if (icon_indicator == 6){
            user_icon.setImageResource(R.drawable.squad7);
        }else if (icon_indicator == 7){
            user_icon.setImageResource(R.drawable.squad8);
        }else if (icon_indicator == 8){
            user_icon.setImageResource(R.drawable.squad9);
        }
            }}, 200);
        return rootView;
    }

}
