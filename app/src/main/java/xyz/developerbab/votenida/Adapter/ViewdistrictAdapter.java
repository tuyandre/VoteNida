package xyz.developerbab.votenida.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import xyz.developerbab.votenida.Model.Province;
import xyz.developerbab.votenida.R;


public class ViewdistrictAdapter extends RecyclerView.Adapter<ViewdistrictAdapter.ViewdistrictAdadpterViewHolder> {

    private Context mcontext;
    private List<Province> muploads;

    private OnItemClickListener mListener;

    public ViewdistrictAdapter(Context context, List<Province> uploads) {

        this.mcontext = context;
        this.muploads = uploads;
    }

    @NonNull
    @Override
    public ViewdistrictAdadpterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lay_province, parent, false);

        return new ViewdistrictAdadpterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewdistrictAdadpterViewHolder holder, int position) {

        Province uploadCurrent = muploads.get(position);

        if (uploadCurrent != null) {

            holder.tvprovinceid.setText(uploadCurrent.getId());
            holder.tvprovincename.setText(uploadCurrent.getProvince());
        }


    }


    @Override
    public int getItemCount() {
        return muploads.size();
    }

    public class ViewdistrictAdadpterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView tvprovincename,tvprovinceid;

        public ViewdistrictAdadpterViewHolder(@NonNull View itemView) {

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
