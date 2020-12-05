package com.project.earthquake.ui;

import android.content.Context;
import android.graphics.Color;
import android.location.Address;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.project.earthquake.R;
import com.project.earthquake.data.network.EarthQuakeItem;
import com.project.earthquake.util.Constants;
import com.project.earthquake.util.Utils;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * Adapter for showing earthquake list.
 */
public class EarthQuakesAdapter extends RecyclerView.Adapter<EarthQuakesAdapter.ViewHolder> {

    private List<EarthQuakeItem> list;
    private OnItemClickListener clickListener;
    private Context context;
    public interface OnItemClickListener {
        void onClick(int pos);
    }

    public EarthQuakesAdapter(List<EarthQuakeItem> items) {
        list = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.earthquake_list_item, parent, false);
        ViewHolder viewHolder =  new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EarthQuakeItem item = list.get(position);
        List<Address> addresses = Utils.getAddress(context, item.getLat(), item.getLng());

        StringBuilder sb = new StringBuilder();
        //Geocoder sometime return empty locality or address so showing it as unknown
        if(addresses != null && addresses.size() > 0) {
            Address address = addresses.get(0);
            if(!TextUtils.isEmpty(address.getLocality())) {
                sb.append(address.getLocality());
            } else {
                sb.append(Constants.UNKNOWN);
            }
            if(!TextUtils.isEmpty(address.getCountryName())) {
                sb.append(", ");
                sb.append(address.getCountryName());
            }
        } else {
            sb.append(Constants.UNKNOWN);
        }

        holder.getTitle().setText(sb.toString());
        holder.getDepth().setText(item.getDepth()+context.getString(R.string.km_depth));
        holder.getDate().setText(item.getTimestamp());
        holder.getMagnitude().setText(String.valueOf(item.getMagnitude()));
        if(item.getMagnitude()>=8.0f) {
            holder.getMagnitude().setBackgroundColor(Color.RED);
        }
    }

    @Override
    public int getItemCount() {
        return list != null ? list.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView magnitude;
        private final TextView depth;
        private final TextView date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            magnitude = itemView.findViewById(R.id.magnitude);
            date = itemView.findViewById(R.id.date_tv);
            depth = itemView.findViewById(R.id.depth_tv);

            itemView.setOnClickListener(v -> {
                if(clickListener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION) {
                        clickListener.onClick(position);
                    }
                }
            });
        }

        public TextView getMagnitude() {
            return magnitude;
        }

        public TextView getTitle() {
            return title;
        }

        public TextView getDate() {
            return date;
        }

        public TextView getDepth() {
            return depth;
        }
    }

    public void setOnClickListener(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
}
