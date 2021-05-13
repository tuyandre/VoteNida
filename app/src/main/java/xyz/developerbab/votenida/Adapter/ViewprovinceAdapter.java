package xyz.developerbab.votenida.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.developerbab.votenida.Model.District;
import xyz.developerbab.votenida.Model.Province;
import xyz.developerbab.votenida.R;


public class ViewprovinceAdapter extends RecyclerView.Adapter<ViewprovinceAdapter.ViewprovinceAdadpterViewHolder> {

    private Context mcontext;
    private List<District> muploads;

    private OnItemClickListener mListener;

    public ViewprovinceAdapter(Context context, List<District> uploads) {

        this.mcontext = context;
        this.muploads = uploads;
    }

    @NonNull
    @Override
    public ViewprovinceAdadpterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_province, parent, false);

        return new ViewprovinceAdadpterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewprovinceAdadpterViewHolder holder, int position) {

        District uploadCurrent = muploads.get(position);

        if (uploadCurrent != null) {

            holder.tvprovinceid.setText(uploadCurrent.getDistrictid());
            holder.tvprovincename.setText(uploadCurrent.getDistrictname());
        }


    }


    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ViewprovinceAdadpterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvprovincename,tvprovinceid;

        public ViewprovinceAdadpterViewHolder(@NonNull View itemView) {

            super(itemView);

            tvprovincename=itemView.findViewById(R.id.tvprovincename);
            tvprovinceid=itemView.findViewById(R.id.tvprovinceid);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            if (mListener != null) {
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    mListener.onItemClick(position);
                }
            }

        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;

    }


}
