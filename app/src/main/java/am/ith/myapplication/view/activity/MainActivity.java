package am.ith.myapplication.view.activity;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import am.ith.myapplication.R;
import am.ith.myapplication.local.Engine;
import am.ith.myapplication.model.AppResponse;
import am.ith.myapplication.view.adapter.GeneralRecycleViewAdapter;
import am.ith.myapplication.viewmodel.VMNews;

public class MainActivity extends AppCompatActivity {

    private VMNews vmNews;
    private RecyclerView recyclerView;
    private GeneralRecycleViewAdapter adapter;
    private GridLayoutManager gridLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        vmNews = ViewModelProviders.of(this).get(VMNews.class);
        getData();


    }
    private void init(){
        recyclerView= (RecyclerView) findViewById(R.id.generalRecycleViewID);
    }

    private void getData() {

        vmNews.getMutableLiveData().observe(MainActivity.this, new Observer<AppResponse>() {
            @Override
            public void onChanged(@Nullable AppResponse appResponse) {
                Engine.getInstance().setAppResponse(appResponse.getMetadata());
                    serDataRecyclerViewAdapter(appResponse);


            }
        });

    }
    private void serDataRecyclerViewAdapter(AppResponse appResponse){
        gridLayoutManager=new GridLayoutManager(this,1);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter=new GeneralRecycleViewAdapter(appResponse,this);
        recyclerView.setAdapter(adapter);
    }

}

