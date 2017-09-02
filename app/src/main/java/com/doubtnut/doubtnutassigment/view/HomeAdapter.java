package com.doubtnut.doubtnutassigment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.doubtnut.doubtnutassigment.R;
import com.doubtnut.doubtnutassigment.model.CountryData;

import java.util.List;

/**
 * Created by rahul on 20/6/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {
    private final OnItemClickListener mListener;
    private List<CountryData> mCountryDataList;
    private Context mContext;

    public HomeAdapter(Context context, List<CountryData> data, OnItemClickListener listener) {
        mCountryDataList = data;
        mListener = listener;
        mContext = context;
    }

    public void updateCountryDataList(List<CountryData> dataList){
        mCountryDataList = dataList;
        notifyDataSetChanged();
        //notifyItemRangeChanged(pos, totalCount);
    }

    @Override
    public HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home, parent, false);
        //view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
        return new HomeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(HomeViewHolder holder, int position) {
        String countryName = mCountryDataList.get(position).getCountryName();
        holder.tv_country.setText(countryName);
        holder.click(mCountryDataList.get(position), mListener);

    }


    @Override
    public int getItemCount() {
        return mCountryDataList.size();
    }


    public interface OnItemClickListener {
        void onClick(CountryData usersData);
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        TextView tv_country;

        public HomeViewHolder(View itemView) {
            super(itemView);
            tv_country = (TextView) itemView.findViewById(R.id.tv_country);
        }


        public void click(final CountryData usersData, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onClick(usersData);
                }
            });
        }
    }
}
