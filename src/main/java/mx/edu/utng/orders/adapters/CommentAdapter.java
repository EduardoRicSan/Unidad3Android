package mx.edu.utng.orders.adapters;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import mx.edu.utng.orders.R;
import mx.edu.utng.orders.model.Comment;


/**
 * Created by Jesús Eduardo Rico Sandoval on 23/02/2017.
 */

public class CommentAdapter extends RecyclerView.Adapter <CommentAdapter.CommentViewHolder>
        implements View.OnClickListener {

    List<Comment> comments;
    View.OnClickListener listener;

    //Constructor
    public CommentAdapter(List<Comment>
                                  comments) {
        this.comments = comments;
    }

    //getter and setter de listener
    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public CommentAdapter.CommentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //se inflael cardview al reciclerview
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.comment_layout,parent,false);
        CommentViewHolder holder=new CommentViewHolder(view);
        view.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(CommentViewHolder holder, int position) {
        holder.tvComment.setText(comments.get(position).getComment());
        holder.tvMsg.setText(comments.get(position).getMsg());
        holder.tvCreated.setText(comments.get(position).getCreated());
        holder.iv_Comment.setImageResource(R.mipmap.ic_launcher);
        holder.setListener(this);
    }


    @Override
    public int getItemCount() {
      return comments.size();
    }

    @Override
    public void onClick(View v) {

        if(listener!=null){
            listener.onClick(v);
        }

    }

    public static class CommentViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CardView cvComment;
        TextView tvComment;
        TextView tvMsg;
        TextView tvCreated;
        ImageView iv_Comment;
        ImageButton ibEditComment;
        ImageButton ibDeleteComment;
        View.OnClickListener listener;

        public CommentViewHolder(View itemView) {
            super(itemView);
            cvComment = (CardView) itemView.findViewById(R.id.cv_comment);
            iv_Comment = (ImageView) itemView.findViewById(R.id.iv_comment);
            tvComment = (TextView) itemView.findViewById(R.id.tv_comment);
            tvMsg = (TextView) itemView.findViewById(R.id.tv_msg);
            tvCreated = (TextView) itemView.findViewById(R.id.tv_created);
            ibEditComment = (ImageButton) itemView.findViewById(R.id.ib_edit_comment);
            ibDeleteComment = (ImageButton) itemView.findViewById(R.id.ib_delete_comment);
            ibEditComment.setOnClickListener(this);
            ibDeleteComment.setOnClickListener(this);


        }

        @Override
        public void onClick(View v) {
            if (listener != null) {
                listener.onClick(v);
            }
        }

        public void setListener(View.OnClickListener listener) {
            this.listener = listener;

        }
    }
}


