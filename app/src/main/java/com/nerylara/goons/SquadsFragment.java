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

        final ImageView other_squad0 = rootView.findViewById(R.id.otherSquad0);
        final ImageView other_squad1 = rootView.findViewById(R.id.otherSquad1);
        final ImageView other_squad2 = rootView.findViewById(R.id.otherSquad2);
        final squad squad = new squad(getContext());
        squad.sendSquadDetails();
        TextView wins = rootView.findViewById(R.id.reputation);
        TextView placements = rootView.findViewById(R.id.placement);
        TextView members = rootView.findViewById(R.id.members);
        squad.sendSquadDetails();

        if (squad_indicator == 0) {
            mySquad.setImageResource(R.drawable.squad5);
            mySquad.setTag(10,12);
            other_squad0.setImageResource(R.drawable.squad2);
            other_squad0.setTag(20,13);
            other_squad1.setImageResource(R.drawable.squad7);
            other_squad1.setTag(30,14);
            other_squad2.setImageResource(R.drawable.squad1);
            other_squad2.setTag(40,15);
        } else if (squad_indicator == 1) {
            mySquad.setImageResource(R.drawable.squad2);
            mySquad.setTag("squad2");
            other_squad0.setImageResource(R.drawable.squad5);
            other_squad1.setImageResource(R.drawable.squad7);
            other_squad2.setImageResource(R.drawable.squad1);

        } else if (squad_indicator == 2) {
            mySquad.setImageResource(R.drawable.squad7);
            mySquad.setTag("squad3");
            other_squad0.setImageResource(R.drawable.squad5);
            other_squad1.setImageResource(R.drawable.squad2);
            other_squad2.setImageResource(R.drawable.squad1);
        } else if (squad_indicator == 3) {
            mySquad.setImageResource(R.drawable.squad1);
            mySquad.setTag("squad4");
            other_squad0.setImageResource(R.drawable.squad5);
            other_squad1.setImageResource(R.drawable.squad2);
            other_squad2.setImageResource(R.drawable.squad7);
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

        final squad squad = new squad(getContext());
        final ImageView mySquad = rootView.findViewById(R.id.mySquad);
        TextView wins = rootView.findViewById(R.id.reputation);
        TextView placements = rootView.findViewById(R.id.placement);
        TextView members = rootView.findViewById(R.id.members);

        if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad1)
                .getConstantState()){
            System.out.println("Squad 1 has been chosen");
            Log.d("squad north:",  Integer.toString(squad.getNorthMemberCount()));
            members.setText(Integer.toString(squad.getNorthMemberCount()));
            placements.setText(Integer.toString(squad.getNorthRep()));
            wins.setText(Integer.toString(squad.getNorthWins()));
        }else if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad2)
                .getConstantState()){
            System.out.println("Squad 2 has been chosen");
            Log.d("squad south:",  Integer.toString(squad.getSouthMemberCount()));
            members.setText(Integer.toString(squad.getSouthMemberCount()));
            placements.setText(Integer.toString(squad.getSouthRep()));
            wins.setText(Integer.toString(squad.getSouthWins()));
        }else if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad3)
                .getConstantState()){
            System.out.println("Squad 3 has been chosen");
            Log.d("squad East:",  Integer.toString(squad.getEastMemberCount()));
            members.setText(Integer.toString(squad.getEastMemberCount()));
            placements.setText(Integer.toString(squad.getEastRep()));
            wins.setText(Integer.toString(squad.getEastWins()));
        } else if (mySquad.getDrawable().getConstantState() == getResources().getDrawable(R.drawable.squad4)
                .getConstantState()) {
            System.out.println("Squad 4 has been chosen");
            Log.d("squad West:",  Integer.toString(squad.getWestMemberCount()));
            members.setText(Integer.toString(squad.getWestMemberCount()));
            placements.setText(Integer.toString(squad.getWestRep()));
            wins.setText(Integer.toString(squad.getWestWins()));
        }
    }

    @Override
    public void onClick(View v) {
    }
}
