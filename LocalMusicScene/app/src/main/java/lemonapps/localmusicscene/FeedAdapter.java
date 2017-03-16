package lemonapps.localmusicscene;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


/**
 * Created by Rob on 2017-03-14.
 */

public class FeedAdapter extends RecyclerView.Adapter<FeedAdapter.ViewHolder>{
    private List<FeedItem> feedItemList;
    private Context context;
    public FeedAdapter(Context cont, List<FeedItem> feedlist){
        this.feedItemList = feedlist;
        this.context = cont;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i){
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.main_cardview,null);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }
    @Override
    public void onBindViewHolder (ViewHolder viewHolder, int i){
        FeedItem feedItem = feedItemList.get(i);
        viewHolder.artist.setText(feedItem.getArtist());
        viewHolder.title.setText(feedItem.getTitle());
        viewHolder.date.setText(feedItem.getDate());
        viewHolder.time.setText(feedItem.getTime());
        viewHolder.location.setText(feedItem.getLocation());
        viewHolder.cost.setText(feedItem.getCost());
        viewHolder.desc.setText(feedItem.getDesc());
    }
    @Override
    public int getItemCount(){
        return (null != feedItemList ? feedItemList.size() : 0);
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView artist;
        public TextView date;
        public TextView time;
        public TextView location;
        public TextView cost;
        public TextView desc;
        public ViewHolder (View view){
            super(view);
            title =  (TextView) view.findViewById(R.id.cardTitle);
            artist =  (TextView) view.findViewById(R.id.cardBandName);
            date =  (TextView) view.findViewById(R.id.cardDateText);
            time =  (TextView) view.findViewById(R.id.cardTimeText);
            location =  (TextView) view.findViewById(R.id.cardVenueText);
            cost =  (TextView) view.findViewById(R.id.cardCost);
            desc  =  (TextView) view.findViewById(R.id.cardDesc);

        }

    }
}
