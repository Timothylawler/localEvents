package com.example.lawkar.localeventsv2.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lawkar.localeventsv2.R;
import com.example.lawkar.localeventsv2.model.EventModel;

import java.util.List;

/**
 * Created by Timothy on 2016-06-22.
 */
public class EventRecyclerAdapter extends RecyclerView.Adapter<EventRecyclerAdapter.RowViewHolder>{

    public class RowViewHolder extends RecyclerView.ViewHolder {

        public CardView cv;
        public TextView titleView;

        public RowViewHolder(View itemView) {
            super(itemView);

            cv = (CardView)itemView.findViewById(R.id.event_row_cv);
            titleView = (TextView)itemView.findViewById(R.id.event_row_title);
        }
    }

    private List<EventModel> eventList;
    private Context context;

    public EventRecyclerAdapter(List<EventModel> eventList, Context context){
        this.eventList = eventList;
        this.context = context;
    }

    @Override
    public RowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.events_row
                , parent
                , false);
        final RowViewHolder rvh = new RowViewHolder(v);

        return rvh;
    }

    @Override
    public void onBindViewHolder(RowViewHolder holder, int position) {
        holder.titleView.setText(eventList.get(position).getTitle());

        holder.cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  Handle clicks on the item
            }
        });
    }

    @Override
    public int getItemCount() {
        return eventList.size();
    }


}
