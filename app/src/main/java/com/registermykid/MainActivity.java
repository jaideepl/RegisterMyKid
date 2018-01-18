package com.registermykid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.registermykid.adapters.SchoolsListAdapter;
import com.registermykid.customviews.AutoFitGridLayoutManager;
import com.registermykid.customviews.ItemOffsetDecoration;
import com.registermykid.data.tos.SchoolData;
import com.registermykid.utils.Util;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity implements SchoolsListAdapter.OnSchoolItemClickListener {


    RecyclerView schoolListView;
    SchoolsListAdapter schoolsListAdapter;
    Toolbar toolbar;
    ArrayList<SchoolData> schoolDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Util.setToolbarProperties(toolbar);

        schoolDataArrayList = new ArrayList<>();

        schoolListView = findViewById(R.id.rv_schoollist);
        AutoFitGridLayoutManager gridLayoutManager = new AutoFitGridLayoutManager(RegisterMyKidApp.getContext(), 500);
        schoolListView.setLayoutManager(gridLayoutManager);
        schoolListView.addItemDecoration(new ItemOffsetDecoration(10));
        schoolListView.setHasFixedSize(true);

        //temp
        for (int i = 0; i < 10; i++) {
            SchoolData schoolData = new SchoolData();
            schoolData.setName("SCHOOL" + i);
            schoolData.setAddress("Massachusetts Hall\n" +
                    "Cambridge, MA 02138,\nUSA");
            schoolDataArrayList.add(schoolData);
        }
        //temp

        schoolsListAdapter = new SchoolsListAdapter(this, schoolDataArrayList, this);
        schoolListView.setAdapter(schoolsListAdapter);
    }

    @Override
    public void onSchoolClicked(SchoolData schoolData) {

    }
}
