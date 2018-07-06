package am.ith.myapplication.view.adapter;

import android.annotation.SuppressLint;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.Date;

import am.ith.myapplication.R;
import am.ith.myapplication.local.entity.SaveColorModel;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.viewmodel.VMColorSave;

public class GeneralRecycleViewAdapter extends RecyclerView.Adapter<GeneralRecycleViewAdapter.MyViewHolder> {

    private AppResponse list;
    private Context context;
    private VMColorSave vmColorSave;


    public GeneralRecycleViewAdapter(AppResponse list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.general_item, parent, false);
        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        //convert date
        Date date = new Date(list.getMetadata().get(position).date);
        holder.category.setText(list.getMetadata().get(position).category);
        holder.title.setText(list.getMetadata().get(position).title);
        holder.date.setText(date.toString());

        Glide
                .with(context)
                .load(list.getMetadata().get(position).coverPhotoUrl)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .into(holder.coverPhotoUrl);
        int id = position;

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                long itemPosition=position;
                vmColorSave= ViewModelProviders.of((FragmentActivity) view.getContext()).get(VMColorSave.class);
                SaveColorModel saveColorModel=new SaveColorModel(itemPosition);
                vmColorSave.insert(saveColorModel);
            }
        });

    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return list.getMetadata().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title;
        TextView category;
        TextView date;
        ImageView coverPhotoUrl;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {
            super(itemView);
            cardView = itemView.findViewById(R.id.cardViewID);
            title = itemView.findViewById(R.id.titleID);
            category = itemView.findViewById(R.id.categoryID);
            date = itemView.findViewById(R.id.dateID);
            coverPhotoUrl = itemView.findViewById(R.id.coverPhotoUrlID);
            progressBar = itemView.findViewById(R.id.progressBarID);

        }
    }
}


