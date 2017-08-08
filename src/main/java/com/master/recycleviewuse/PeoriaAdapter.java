package com.master.recycleviewuse;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

//列表适配器
class PeoriaAdapter extends RecyclerView.Adapter<PeoriaAdapter.Holder> {
    private Context mContext;
    private List<Object> mPics;
    private List<Object> mLogos;
    private List<Object> mTexts;
    private Boolean isLong = true;


    public PeoriaAdapter(Context context, List<Object> mPics, List<Object> mLogos, List<Object> mTexts) {
        mContext = context;
        this.mPics = mPics;
        this.mLogos = mLogos;
        this.mTexts = mTexts;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_recycler, parent, false);
        return new Holder(root);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.iv_bg.setImageResource((int) (mPics.get(position)));
        holder.iv.setImageResource((int) (mLogos.get(position)));
        holder.tv.setText((String) mTexts.get(position));
    }

    @Override
    public int getItemCount() {
        return mPics.size();
    }

    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        private ImageView iv_bg;
        private final TextView tv;
        private final ImageView iv;

        Holder(View itemView) {
            super(itemView);

            iv_bg = (ImageView) itemView.findViewById(R.id.iv_bg);
            View main = itemView.findViewById(R.id.main);
            main.setOnClickListener(this);
            main.setOnLongClickListener(this);

            View delete = itemView.findViewById(R.id.delete);
            tv = (TextView) itemView.findViewById(R.id.tvset);
            iv = (ImageView) itemView.findViewById(R.id.ivset);
            View bt = itemView.findViewById(R.id.btset);
            tv.setOnClickListener(this);
            iv.setOnClickListener(this);
            bt.setOnClickListener(this);
            delete.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.tvset:
                    Toast.makeText(v.getContext(), "点击了文字，位置为：" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.ivset:
                    Toast.makeText(v.getContext(), "点击了头像，位置为：" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.btset:
                    Toast.makeText(v.getContext(), "点击了关注，位置为：" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    break;
                case R.id.main:
                    if (!isLong) {
                        isLong = true;

                    } else {

                        Toast.makeText(v.getContext(), "点击了条目布局，位置为：" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    }
                    break;

                case R.id.delete:
                    int pos = getAdapterPosition();
                    mPics.remove(pos);
                    mLogos.remove(pos);
                    mTexts.remove(pos);
                    notifyItemRemoved(pos);
                    break;
            }
        }

        @Override
        public boolean onLongClick(View v) {
            switch (v.getId()) {
                case R.id.main:
                    Toast.makeText(v.getContext(), "长按了条目布局，位置为：" + getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    isLong = false;
                    break;
            }
            return false;
        }
    }
}
