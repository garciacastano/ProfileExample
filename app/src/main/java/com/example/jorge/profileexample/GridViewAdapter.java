package com.example.jorge.profileexample;

/**
 * Created by jorge on 5/11/15.
 */
import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

public class GridViewAdapter extends ArrayAdapter<GridItem> {

    private Context mContext;
    private int layoutResourceId;
    private ArrayList<GridItem> mGridData = new ArrayList<GridItem>();

    public GridViewAdapter(Context mContext, int layoutResourceId, ArrayList<GridItem> mGridData) {
        super(mContext, layoutResourceId, mGridData);
        this.layoutResourceId = layoutResourceId;
        this.mContext = mContext;
        this.mGridData = mGridData;
    }


    /**
     * Updates grid data and refresh grid items.
     * @param mGridData
     */
    public void setGridData(ArrayList<GridItem> mGridData) {
        this.mGridData = mGridData;
        notifyDataSetChanged();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View cell = convertView;
        ViewHolder holder;

        if (cell == null) {
            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
            cell = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.nameTextView = (TextView) cell.findViewById(R.id.profile_cell_name);
            holder.imageView = (ImageView) cell.findViewById(R.id.profile_cell_image);
            cell.setTag(holder);
        } else {
            holder = (ViewHolder) cell.getTag();
        }

        GridItem item = mGridData.get(position);
        holder.nameTextView.setText(Html.fromHtml(item.getTitle()));

        Picasso.with(mContext).load(item.getImage()).into(holder.imageView);
        return cell;
    }

    static class ViewHolder {
        TextView nameTextView;
        ImageView imageView;
    }
}
