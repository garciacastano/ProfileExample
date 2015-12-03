package com.example.jorge.profileexample;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.util.Log;

/**
 * This class manages the display of data in the GridView *
 * @author Jorge García, Javier Gonzalez *
 */

public class GridViewAdapter extends ArrayAdapter<Profile> {


    private static final String TAG = GridViewAdapter.class.getSimpleName();
    private Context mContext;
    private int layoutResourceId;
    private ArrayList<Profile> mGridData = new ArrayList<Profile>();

    /**
     * Constructor
     * @param mContext Context of the activity
     * @param layoutResourceId ID of the cell in the GridView we want to inflate
     * @param mGridData Data array to display in the GridView
     */
    public GridViewAdapter(Context mContext, int layoutResourceId, ArrayList<Profile> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }

    /**
     * Updates grid data and refreshes grid items.
     * @param mGridData Data array to display in the GridView
     */
    public void setGridData(ArrayList<Profile> mGridData) {
        Log.d(TAG, "SET GRID DATA");
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    /**
     * Updates grid data and refreshes grid items.
     * @param parent Parent layout of the GridView
     * @param convertView View object of an element in the GridView
     * @param position ID of the convertView
     *
     * @return Custom element to represent in the GridView
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d(TAG, "GET VIEW");
        View cell = convertView;
        ViewHolder holder;

        if (cell == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            cell = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) cell.findViewById(R.id.profile_cell_name);
            holder.photoImageView = (ImageView) cell.findViewById(R.id.profile_cell_image);
            cell.setTag(holder);
        } else {
            holder = (ViewHolder) cell.getTag();
        }

        Profile item = mGridData.get(position);
        holder.nameTextView.setText(item.getName());

        // TODO: 2/12/15
        /*
        * Muchos fallos al intentar obtener la imagen correspondiente de la foto del perfil
        * Las imagenes están de drawable
        * Pongo por defecto la foto jorge.jpg
        *
        */

        holder.photoImageView.setBackgroundResource(R.drawable.jorge);

        return cell;
    }

    /**
     * This class defines the attributes of each cell of the GridView *
     */

    static class ViewHolder {
        TextView nameTextView;
        ImageView photoImageView;
    }
}
