package com.example.user.myschool;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class ImageAdapter extends BaseAdapter
{
    private Context mContext;
    private final int[] Image;
    private final String[] text;
    public ImageAdapter(Context c,String[] text,int[] Image )
    {
        mContext = c;
        this.Image = Image;
        this.text=text;
    }

    @Override
    public int getCount()
    {
        return Image.length;
    }
    @Override
    public Object getItem(int position)
    {
        return position;
    }
    @Override
    public long getItemId(int position)
    {
        return 0;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup
            parent)
    {
        LayoutInflater inflater = (LayoutInflater)
                mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null)
        {
            gridView = new View(mContext);
            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.grid_layout, null);
            // set value into textview
            TextView textView = (TextView)
                    gridView.findViewById(R.id.grid_item_label);
            textView.setText(text[position]);
            // set image based on selected text
            ImageView imageView = (ImageView)
                    gridView.findViewById(R.id.grid_item_image);
            imageView.setImageResource(Image[position]);
        }
        else
        {
            gridView = (View) convertView;
        }
        return gridView;
    }
}
