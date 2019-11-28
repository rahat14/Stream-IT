package com.metacodersbd.watch.adapters;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.metacodersbd.watch.R;
import com.squareup.picasso.Picasso;

public class viewHolderTvSeries extends RecyclerView.ViewHolder {

    View mview  ;
    TextView title , desc , dateView ;
    CardView card ;


    public viewHolderTvSeries(@NonNull View itemView) {
        super(itemView);

        mview = itemView ;

        /// item click

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                mClickListener.onItemClick(view , getAdapterPosition());
            }
        });

    }


    public  void setDataToView(Context context  , String name, String id, String category,
                               String decription, String link, String query,
                               String image)
    {
        TextView titleTv = mview.findViewById(R.id.movie_title) ;
        ImageView imageTv = mview.findViewById(R.id.featured_image) ;

        titleTv.setText(name);
        Picasso.get().load(image).into(imageTv) ;




    }



    private  static  viewHolderTvSeries.ClickListener  mClickListener;


    public  interface  ClickListener
    {
        void onItemClick( View view , int position ) ;

    }


    public  static String setOnClickListener(viewHolderTvSeries.ClickListener clickListener)
    {

        mClickListener = clickListener ;
        return null;
    }







}
