package accesscode.c4q.nyc.memeifyme;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by s3a on 7/17/15.
 */
public class MemeTemplateAdapter extends BaseAdapter {

    private List<MemeTemplate> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;

    @Bind(R.id.meme)ImageView mImageView;
    @Bind(R.id.memeTitle)TextView mTextView;

    public MemeTemplateAdapter (Context mContext,List<MemeTemplate> memeTemplateList){
        this.mContext =mContext;
        this.mList = mList;
        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount(){
        return mList.size();
    }

    @Override
    public MemeTemplate getItem(int i) {
        return mList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View newView, ViewGroup viewGroup) {

        if (newView ==null){
            //newView =mLayoutInflater.inflate(R.layout.memes_list_template,viewGroup,false);

        }
        ButterKnife.bind(this,newView);
        Picasso.with(mContext).load(getItem(i).getRes_ID()).into(mImageView);
        mTextView.setText(getItem(i).getTitle());
        return newView;
    }

}
