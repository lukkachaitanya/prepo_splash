package com.experiences.projects.booktable;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.experiences.projects.booktable.utils.ApplicationConstants;
import com.experiences.projects.booktable.utils.Preference;

/**
 * Created by lc on 9/19/2015.
 */
public abstract class BaseActivity extends FragmentActivity
{
    public TextView tvTitle;
    public LinearLayout llContent,llHeader;
    public LayoutInflater layoutInflater;
    public ListView lvMenu;
    public ImageView ivMenu;
    public ImageView ivBack;
    public Button btnSave;
    private DrawerLayout drawerLayout;
    public Preference preferenceUtils;
    private String menuItems [] = {"REQUEST A TABLE","REQUESTS","RESERVATIONS","ACCOUNT","INVITE FRIENDS","ABOUT"};
    private int imageIcons [] = {R.drawable.findatable,R.drawable.detailviewrestaurant,R.drawable.reservations,R.drawable.account,R.drawable.share_icon_drawer,R.drawable.about};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        defineControls();
        createActivity();

        ivMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(Gravity.START)) {
                    drawerLayout.closeDrawer(Gravity.START);
                } else {
                    drawerLayout.openDrawer(Gravity.START);
                }
            }
        });

        ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                Intent intent = null;
                    switch (position)
                    {
                        case 3:
                            intent = new Intent(BaseActivity.this,AccountActivity.class);
                            break;
                    }

                    if(intent!=null)
                        startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        ApplicationConstants.TYPEFACE_NORMAL        = Typeface.createFromAsset(getApplicationContext().getAssets(), "GothamBook.otf");
    }

    public void setTypeFace(ViewGroup group)
    {
        int count = group.getChildCount();
        View v;
        for(int i = 0; i < count; i++) {
            v = group.getChildAt(i);
            if(v instanceof TextView || v instanceof Button || v instanceof EditText/*etc.*/)
                ((TextView)v).setTypeface(ApplicationConstants.TYPEFACE_NORMAL);
            else if(v instanceof ViewGroup)
                setTypeFace((ViewGroup)v);
        }
    }

    private void defineControls()
    {
        layoutInflater  =  getLayoutInflater();
        tvTitle         = (TextView)findViewById(R.id.tvTitle);
        llContent       = (LinearLayout)findViewById(R.id.llContent);
        btnSave         = (Button)findViewById(R.id.btnSave);
        ivMenu           = (ImageView)findViewById(R.id.ivMenu);
        ivBack           = (ImageView)findViewById(R.id.ivBack);
        llHeader        = (LinearLayout)findViewById(R.id.llHeader);
        lvMenu          = (ListView)findViewById(R.id.lvMenu);
        drawerLayout    = (DrawerLayout)findViewById(R.id.drawerLayout);
        preferenceUtils = new Preference(BaseActivity.this);
        tvTitle.setTypeface(ApplicationConstants.TYPEFACE_NORMAL);
        lvMenu.setAdapter(new MenuAdapter());
    }

    public abstract  void  createActivity();

    private class MenuAdapter extends BaseAdapter
    {
        @Override
        public int getCount() {

            return menuItems.length;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            convertView = (LinearLayout)layoutInflater.inflate(R.layout.menu_list_item, null);
            ImageView ivMenuItem = (ImageView)convertView.findViewById(R.id.ivMenuItem);
            TextView tvMenuTitle = (TextView)convertView.findViewById(R.id.tvMenuTitle);
            ivMenuItem.setImageResource(imageIcons[position]);
            tvMenuTitle.setText(menuItems[position]);
            tvMenuTitle.setTypeface(ApplicationConstants.TYPEFACE_NORMAL);

//            convertView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = null;
//                    switch (position)
//                    {
//                        case 3:
//                            intent = new Intent(BaseActivity.this,AccountActivity.class);
//                            break;
//                    }
//
//                    if(intent!=null)
//                        startActivity(intent);
//                }
//            });
            return convertView;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }
    }
}
