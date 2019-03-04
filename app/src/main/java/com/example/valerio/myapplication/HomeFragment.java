package com.example.valerio.myapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.json.JSONException;

import java.io.IOException;

import java.util.ArrayList;

public class HomeFragment extends Fragment implements ItemClickListener{

    public static final String THEME_PREFERENCES = "com.avjindersekhon.themepref";
    public static final String RECREATE_ACTIVITY = "com.avjindersekhon.recreateactivity";
    public static final String THEME_SAVED = "com.avjindersekhon.savedtheme";
    public static final String DARKTHEME = "com.avjindersekon.darktheme";
    public static final String LIGHTTHEME = "com.avjindersekon.lighttheme";

    private RecyclerView recyclerView;
    private StoreRetrieveData storeRetrieveData;
    private MyAdapter adapter;
    private ArrayList<House> addedHouse = new ArrayList<>();
    private NavigationView navigationView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(
                R.layout.fragment_home, container, false);

        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerView);
        // layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        // adapter
        final String FILENAME = MainActivity.getAccountMail();

        navigationView = (NavigationView) getActivity().findViewById(R.id.navigation_view);


        storeRetrieveData = new StoreRetrieveData(getContext(), FILENAME);
        getLocallyStoredData(storeRetrieveData);
        adapter = new MyAdapter(addedHouse, getContext());
        recyclerView.setAdapter(adapter);
        adapter.setClickListener(this);

        enableSwipe();
        return v;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //generate a list of Link
    private void getLocallyStoredData(StoreRetrieveData storeRetrieveData) {

        try {
            addedHouse = storeRetrieveData.loadFromFile();
            if(addedHouse != null){
                for(int i = 0; i<addedHouse.size(); i++){
                    MainActivity.getAddedHouse1().add(addedHouse.get(i));
                    char[] ascii = addedHouse.get(i).getName().toCharArray();
                    int tot = 0;
                    for(char ch:ascii){
                        tot = tot+((int)ch);
                    }
                    navigationView.getMenu().add(0, tot, 0, addedHouse.get(i).getName());
                    //navigationView.getMenu().getItem(addedHouse.size()).getActionView().setTag(addedHouse.get(i).getName());
                }
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        if (addedHouse == null) {
            addedHouse = new ArrayList<>();
        }
    }

    public void addItemList(House newHouse){
        newHouse.setIcon(R.drawable.ic_link);
        addedHouse.add(newHouse);
        recyclerView.setAdapter(adapter);
    }

    private void saveDate() {
        try {
            storeRetrieveData.saveToFile(addedHouse);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onPause() {
        super.onPause();
        try {
            storeRetrieveData.saveToFile(addedHouse);
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        //recyclerView.removeOnScrollListener(customRecyclerScrollViewListener);
    }

    @Override
    public void onClick(View view, int position) {
        final House house = addedHouse.get(position);

        Intent i = new Intent(this.getContext(), ControlHouseActivity.class);
        i.putExtra("address", house.getAddress());
        i.putExtra("name", house.getName());
        i.putExtra("city", house.getCity());
        i.putExtra("sensorServo", house.getSensorServo());
        i.putExtra("sensorTemp", house.getSensorTemp());
        i.putExtra("sensorNoise", house.getSensorNoise());
        i.putExtra("sensorLight", house.getSensorLight());
        i.putExtra("sensorSisma", house.getSensorSisma());

        startActivity(i);
    }

    private void enableSwipe(){
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
//                    final Model deletedModel = addedHouse.get(position);
//                    final int deletedPosition = position;
                String name = adapter.getItem(position).getName();
                char[] ascii = name.toCharArray();
                int tot = 0;
                for(char ch:ascii){
                    tot = tot+((int)ch);
                }
                adapter.removeItem(position);

                MainActivity.getAddedHouse1().remove(position);
                navigationView.getMenu().removeItem(tot);

                // showing snack bar with Undo option
//                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), " removed from Recyclerview!", Snackbar.LENGTH_LONG);
//                    snackbar.setAction("UNDO", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//                            // undo is selected, restore the deleted item
//                            MyAdapter.restoreItem(deletedModel, deletedPosition);
//                        }
//                    });
//                    snackbar.setActionTextColor(Color.YELLOW);
//                    snackbar.show();
//                 else {
//                    final Model deletedModel = imageModelArrayList.get(position);
//                    final int deletedPosition = position;
//                    adapter.removeItem(position);
//                    // showing snack bar with Undo option
//                    Snackbar snackbar = Snackbar.make(getWindow().getDecorView().getRootView(), " removed from Recyclerview!", Snackbar.LENGTH_LONG);
//                    snackbar.setAction("UNDO", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View view) {
//
//                            // undo is selected, restore the deleted item
//                            adapter.restoreItem(deletedModel, deletedPosition);
//                        }
//                    });
//                    snackbar.setActionTextColor(Color.YELLOW);
//                    snackbar.show();
//                }
            }

//            @Override
//            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//
//                Bitmap icon;
//                if(actionState == ItemTouchHelper.ACTION_STATE_SWIPE){
//
//                    View itemView = viewHolder.itemView;
//                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
//                    float width = height / 3;
//
//                    if(dX > 0){
//                        p.setColor(Color.parseColor("#388E3C"));
//                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX,(float) itemView.getBottom());
//                        c.drawRect(background,p);
//                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
//                        RectF icon_dest = new RectF((float) itemView.getLeft() + width ,(float) itemView.getTop() + width,(float) itemView.getLeft()+ 2*width,(float)itemView.getBottom() - width);
//                        c.drawBitmap(icon,null,icon_dest,p);
//                    } else {
//                        p.setColor(Color.parseColor("#D32F2F"));
//                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(),(float) itemView.getRight(), (float) itemView.getBottom());
//                        c.drawRect(background,p);
//                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.delete);
//                        RectF icon_dest = new RectF((float) itemView.getRight() - 2*width ,(float) itemView.getTop() + width,(float) itemView.getRight() - width,(float)itemView.getBottom() - width);
//                        c.drawBitmap(icon,null,icon_dest,p);
//                    }
//                }
//                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
//            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}