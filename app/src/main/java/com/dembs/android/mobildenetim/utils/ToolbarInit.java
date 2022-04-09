package com.dembs.android.mobildenetim.utils;

import android.annotation.SuppressLint;
import android.view.Menu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class ToolbarInit extends AppCompatActivity {

    Toolbar toolbar;
    List<String> listItem=new ArrayList<>();//for options menu (3dots)


    public ToolbarInit(Toolbar toolbar2, @Nullable List<String> listItem) {

        this.toolbar = toolbar2;
        this.listItem=listItem;

    }

    @Nullable
    public Toolbar getToolbar( String title, @Nullable String subTitle,int titleColor, int subTitleColor)
    {

        toolbar.setTitle(title);
        toolbar.setSubtitle(subTitle);
        toolbar.setTitleTextColor(titleColor);
        toolbar.setSubtitleTextColor(subTitleColor);
        return toolbar;
    }
    @Nullable
    public Toolbar getToolbarWithoutSubtitle( String title,int titleColor, int subTitleColor)
    {

        toolbar.setTitle(title);
        toolbar.setTitleTextColor(titleColor);
        toolbar.setSubtitleTextColor(subTitleColor);
        return toolbar;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

            menu.clear();
            for (int i=0;i<listItem.size();i++)
            {
                menu.add(0,i, Menu.NONE,listItem.get(i));
            }
            return true;
    }


}
