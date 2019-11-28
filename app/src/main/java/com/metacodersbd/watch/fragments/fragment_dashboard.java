package com.metacodersbd.watch.fragments;

import android.content.Context;
import android.content.Intent;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.metacodersbd.watch.MovieDetailsActivity;
import com.metacodersbd.watch.R;
import com.metacodersbd.watch.adapters.viewHolderTvSeries;
import com.metacodersbd.watch.adapters.viewholderForContent;
import com.metacodersbd.watch.models.modelForMovie;

public class fragment_dashboard extends Fragment  {

    View view ;
    LinearLayoutManager  linearLayoutManager , tvSereisManager   ;
    Context context  ;
    RecyclerView recyclerView  ,  tvRecyclerView;
    String link , name  , desc ;
    public fragment_dashboard() {
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_stream_home , container , false) ;

            context = view.getContext() ;

             recyclerView = view.findViewById(R.id.movieList);
             tvRecyclerView = view.findViewById(R.id.tvSeriesList);
            // getCOntext // getActivty

             linearLayoutManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL  , false);

             tvSereisManager = new LinearLayoutManager(getContext() , LinearLayoutManager.HORIZONTAL  , false);

                getMovieList();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getTvseriesList();
            }
        }  , 500) ;







        return  view ;
    }

    private void writeExampleData() {

        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("category").child("movie").child("content");

       //  name , id , category , decription , link  , query  , image ;
            String key = ref.push().getKey()  ;
             modelForMovie model = new modelForMovie("Test Movie"  , key , "movie" ,"this is a test " , "null" ,"test movie" ,"https://picsum.photos/200" ) ;
             ref.child(key).setValue(model) ;






    }
    private  void getMovieList() {




        FirebaseRecyclerAdapter<modelForMovie, viewholderForContent> firebaseRecyclerAdapter ;
        FirebaseRecyclerOptions<modelForMovie> options ;



        DatabaseReference   mref = FirebaseDatabase.getInstance().getReference("category").child("movie").child("content");

        options = new FirebaseRecyclerOptions.Builder<modelForMovie>().setQuery(mref, modelForMovie.class).build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForMovie, viewholderForContent>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final viewholderForContent viewholderForContent, final int i, @NonNull  modelForMovie model) {

                viewholderForContent.setDataToView(getContext(),model.getName() , model.getId() , model.getCategory() , model.getDecription()
                , model.getLink() , model.getQuery() , model.getImage());
                //getItem(holder.getAdapterPosition()).getPushid();

                //kmnj kjnkj

                viewholderForContent.setOnClickListener(new viewholderForContent.ClickListener() {
                    @Override
                    public void onItemClick(View view, final int position) {



                        // sending user to the video VIew  activity

                        Intent o = new Intent( context, MovieDetailsActivity.class);
                        o.putExtra("NAME" ,getItem(position).getName() ) ;
                        o.putExtra( "LINK" ,getItem(position).getLink()) ;
                        o.putExtra("DESC" , getItem(position).getDecription());
                        startActivity(o);





                    }
                });



            }

            @NonNull
            @Override
            public viewholderForContent onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_movie, parent, false);
                final  viewholderForContent viewholderForContent = new viewholderForContent(view);






                return viewholderForContent;
            }
        };

        recyclerView.setLayoutManager(linearLayoutManager) ;

        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter) ;









    }

    private  void getTvseriesList() {

        FirebaseRecyclerAdapter<modelForMovie, viewHolderTvSeries> firebaseRecyclerAdapter ;
        FirebaseRecyclerOptions<modelForMovie> options ;



        DatabaseReference   mref = FirebaseDatabase.getInstance().getReference("category").child("tvseries").child("content");

        options = new FirebaseRecyclerOptions.Builder<modelForMovie>().setQuery(mref, modelForMovie.class).build() ;

        firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<modelForMovie, viewHolderTvSeries>(options) {
            @Override
            protected void onBindViewHolder(@NonNull viewHolderTvSeries viewholderForContent, final int i, @NonNull modelForMovie model) {

                viewholderForContent.setDataToView(getContext(),model.getName() , model.getId() , model.getCategory() , model.getDecription()
                        , model.getLink() , model.getQuery() , model.getImage());


                viewHolderTvSeries.setOnClickListener(new viewHolderTvSeries.ClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        // sending user to the video VIew  activity






                    }
                });
            }

            @NonNull
            @Override
            public viewHolderTvSeries onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
            {

                View  view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tv_series, parent, false);
                final  viewHolderTvSeries viewholderForContent = new viewHolderTvSeries(view);
                return viewholderForContent;
            }
        };

        tvRecyclerView.setLayoutManager(tvSereisManager) ;

        firebaseRecyclerAdapter.startListening();
        tvRecyclerView.setAdapter(firebaseRecyclerAdapter) ;









    }

}
