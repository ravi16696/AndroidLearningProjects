package com.example.expandablelistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    List<String> groupList;
    List<String> childList;
    Map<String, List<String>> mobileCollection;
    ExpandableListView expandableListView;
    ExpandableListAdapter expandableListAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createGroupList();
        createCollection();
        expandableListView = findViewById(R.id.mobileList);
        expandableListAdapter = new MyExpandableListAdaptor(this, groupList, mobileCollection);
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int lastExpandedGroupPosition = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(lastExpandedGroupPosition != -1 && lastExpandedGroupPosition != groupPosition) {
                    expandableListView.collapseGroup(lastExpandedGroupPosition);
                }
                lastExpandedGroupPosition = groupPosition;
            }
        });
        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                String childName = expandableListAdapter.getChild(groupPosition, childPosition).toString();
                Toast.makeText(getApplicationContext(), "selected  " + childName, Toast.LENGTH_SHORT);
                return true;
            }
        });
    }

    private void createCollection() {
        String[] samsungModels = {
                "samsung mobile1",
                "samsung mobile2",
                "samsung mobile2"
        };
        String[] realMeModels = {
                "realme 1",
                "realme 2",
                "realme 3",
                "realme 4"
        };
        String[] XiomiModels = {
                "xiomi 1",
                "xiomi 2",
                "xiomi 3",
                "xiomi 4",
        };

        String[] iphoneModels = {
                "iphone 1",
                "iphone 2",
                "iphone 3",
                "iphone 4",
        };

        mobileCollection = new HashMap<>();
        for (String group : groupList) {
            if( group.equals("samsung")) {
                loadChild(samsungModels);
            } else if(group.equals("realMe")) {
                loadChild(realMeModels);
            } else  if (group.equals("Xiomi")) {
                loadChild(XiomiModels);
            } else if (group.equals("iphone")) {
                loadChild(iphoneModels);
            }
            mobileCollection.put(group, childList);
        }
    }

    private void loadChild(String[] mobileModels) {
        childList = new ArrayList<>();
        for (String model : mobileModels) {
            childList.add(model);
        }
    }

    private void createGroupList() {
        groupList = new ArrayList<>();
        groupList.add("samsung");
        groupList.add("realMe");
        groupList.add("Xiomi");
        groupList.add("iphone");
    }
}