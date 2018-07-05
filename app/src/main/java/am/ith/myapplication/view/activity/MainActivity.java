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
//Rob?ahaesqan@ mihat ete karelia naes gnahates, esranc ogtagorcumy hima arden shat heshta bayc karevora haskanal vonca shxtum
    // inch skzbunqov u aveli xory qan es orinaknery orinak observable pattern shat shutvanic ardiakana u shat a ogtagorcvum nai te
    // vonca ashxatum inch terutiunner u aravelutiunner uni, irakanum himqy haskanalov piti helnes verev voch te mianqamic patrasti
    // inch verabervum orinakin hamaria sax chish es arel, bayc eli em asum sranc ogtagorcely mi miec ban chi iranc havasar
    // gorciq sarqelna hetaqrqrir, amen inch petq e himqic u xory usumnasirutiunov hasanas, gitem vor uzum es vraz vraz
    // bayc tenc lav tex ches hasni
    //ha hamadzayn em,bayc en orinak@ inch arelem bavakanin haskanalia,uxxaki nexvum em
    //vor baner ka mexanikorena,u haskanum em vor sa der amenaskizbna,doc-ov em nayum sax,
    //u docum etqanel parz chi grac,Rob jan,mihat xndri araj em kangnac,ete harmara mihat ases eti
    //mi hat tiv petqa pahem recyclerviewi item-in click anelu vaxt,esqan@ arelem,mnacac@ tanjvumem vor esi
    //ognes anenq aveli arag u hesh khaskanam
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

