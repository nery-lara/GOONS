package com.nerylara.goons;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.nfc.Tag;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import static android.support.constraint.Constraints.TAG;

public class SquadsFragment extends Fragment implements View.OnClickListener {

    ViewGroup rootView;
    final int squad_indicator = 3;
    private Object Tag;
    private Object Squad1 = "squad1";
    private Object Squad2 = "squad2";
    private Object Squad3 = "squad3";
    private Object Squad4 = "squad4";



    @SuppressLint("ResourceType")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // TODO: Get the squad indicator

        rootView = (ViewGroup) inflater.inflate(R.layout.fragment_squads, container, false);
        final ImageView mySquad = rootView.findViewById(R.id.mySquad);

        String username = getArguments().getString("Username_transfer");
        System.out.println("Username in SQUADS_FRAGMENT" + username);

        final ImageView other_squad0 = rootView.findViewById(R.id.otherSquad0);
        final ImageView other_squad1 = rootView.findViewById(R.id.otherSquad1);
        final ImageView other_squad2 = rootView.findViewById(R.id.otherSquad2);

        TextView reputation = rootView.findViewById(R.id.reputation);
        TextView placements = rootView.findViewById(R.id.placement);
        TextView members = rootView.findViewById(R.id.members);

        if (squad_indicator == 0) {
            mySquad.setImageResource(R.drawable.squad1);
            mySquad.setTag(10,12);
            other_squad0.setImageResource(R.drawable.squad2);
            other_squad0.setTag(20,13);
            other_squad1.setImageResource(R.drawable.squad3);
            other_squad1.setTag(30,14);
            other_squad2.setImageResource(R.drawable.squad4);
            other_squad2.setTag(40,15);
        } else if (squad_indicator == 1) {
            mySquad.setImageResource(R.drawable.squad2);
            mySquad.setTag("squad2");
            other_squad0.setImageResource(R.drawable.squad1);
            other_squad1.setImageResource(R.drawable.squad3);
            other_squad2.setImageResource(R.drawable.squad4);

        } else if (squad_indicator == 2) {
            mySquad.setImageResource(R.drawable.squad3);
            mySquad.setTag("squad3");
            other_squad0.setImageResource(R.drawable.squad1);
            other_squad1.setImageResource(R.drawable.squad3);
            other_squad2.setImageResource(R.drawable.squad4);
        } else if (squad_indicator == 3) {
            mySquad.setImageResource(R.drawable.squad4);
            mySquad.setTag("squad4");
            other_squad0.setImageResource(R.drawable.squad1);
            other_squad1.setImageResource(R.drawable.squad2);
            other_squad2.setImageResource(R.drawable.squad3);
        }

        other_squad0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(TAG, "Entered other squad onClick Method");
                Drawable other_squad_Id = other_squad0.getDrawable();
                Drawable current_squad_displayed = mySquad.getDrawable();
                mySquad.setImageDrawable(other_squad_Id);
                other_squad0.setImageDrawable(current_squad_displayed);
                check_squad_displayed();
            }
        });
        other_squad1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable other_squad_Id = other_squad1.getDrawable();
                Drawable current_squad_displayed = mySquad.getDrawable();
                mySquad.setImageDrawable(other_squad_Id);
                other_squad1.setImageDrawable(current_squad_displayed);
                check_squad_displayed();
            }
        });
        other_squad2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable other_squad_Id = other_squad2.getDrawable();
                Drawable current_squad_displayed = mySquad.getDrawable();
                mySquad.setImageDrawable(other_squad_Id);
                other_squad2.setImageDrawable(current_squad_displayed);
                check_squad_displayed();
            }
        });

        check_squad_displayed();

        return rootView;
    }

    public void check_squad_displayed(){
        final ImageView mySquad = rootView.findViewById(R.id.mySquad);

        TextView reputation = rootView.findViewById(R.id.reputation);
        TextView placements = rootView.findViewById(R.id.placement);
        TextView members = rootView.findViewById(R.id.members);

        if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad1)
                .getConstantState()){
            System.out.println("Squad 1 has been chosen");
            members.setText("0");
            placements.setText("1");
            reputation.setText("100");
        }else if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad2)
                .getConstantState()){
            System.out.println("Squad 2 has been chosen");
            members.setText("1");
            placements.setText("12");
            reputation.setText("11230");
        }else if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad3)
                .getConstantState()){
            System.out.println("Squad 3 has been chosen");
            members.setText("0");
            placements.setText("1");
            reputation.setText("10qwe0");
        } else if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad4)
                .getConstantState()) {
            System.out.println("Squad 4 has been chosen");
            members.setText("0qwer");
            placements.setText("1qwf");
            reputation.setText("1weve2323200");
        }
    }

    @Override
    public void onClick(View v) {
    }
}
