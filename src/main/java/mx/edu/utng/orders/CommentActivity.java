package mx.edu.utng.orders;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


import mx.edu.utng.orders.adapters.CommentAdapter;
import mx.edu.utng.orders.model.Comment;

import mx.edu.utng.orders.sqlite.DBOperations;

/**
 * Created by Jesús Eduardo Rico Sandoval on 23/02/2017.
 */

public class CommentActivity extends AppCompatActivity {
    private EditText etComment;
    private EditText etMsg;
    private EditText etCreated;
    private Button btSaveComment;
    private DBOperations operations;
    private Comment comment = new Comment();
    private RecyclerView rvComments;
    private List<Comment> comments=new ArrayList<Comment>();
    private CommentAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        //iniciacion de la intancia
        operations=DBOperations.getDBOperations(getApplicationContext());
        comment.setId("");
        initComponents();
    }

    private void initComponents() {
        rvComments=(RecyclerView)findViewById(R.id.rv_comment_list);
        rvComments.setHasFixedSize(true);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        rvComments.setLayoutManager(layoutManager);
        //
        getComments();

        adapter=new CommentAdapter(comments);

         adapter.setListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 switch (v.getId()) {
                     case R.id.ib_delete_comment:
                         operations.deleteComment(comments.get(rvComments.getChildPosition((View) v.getParent().getParent())).getId());
                         getComments();
                         adapter.notifyDataSetChanged();
                         break;
                     case R.id.ib_edit_comment:
                         Toast.makeText(getApplicationContext(), "Edit", Toast.LENGTH_SHORT).show();
                         Cursor c = operations.getCommentById(comments.get(
                                 rvComments.getChildPosition(
                                         (View) v.getParent().getParent())).getId());
                         if (c != null) {
                             if (c.moveToFirst()) {
                                 comment = new Comment(c.getString(1), c.getString(2), c.getString(3), c.getString(4));
                                 etComment.setText(comment.getComment());
                                 etMsg.setText(comment.getMsg());
                                 etCreated.setText(comment.getCreated());

                             } else {
                                 Toast.makeText(getApplicationContext(), "Comment not found", Toast.LENGTH_SHORT).show();
                             }


                         }
                         break;
                     default:
                         break;
                 }
             }
         });
        rvComments.setAdapter(adapter);

        etComment=(EditText)findViewById(R.id.et_comment);
        etMsg=(EditText)findViewById(R.id.et_msg);
        etCreated=(EditText)findViewById(R.id.et_created);

        btSaveComment=(Button)findViewById(R.id.bt_save_comment);

        btSaveComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!comment.getId().equals("")){
                    comment.setComment(etComment.getText().toString());
                    comment.setMsg(etMsg.getText().toString());
                    comment.setCreated(etCreated.getText().toString());
                    operations.updateComment(comment);

                }else {
                    comment = new Comment("", etComment.getText().toString(),etMsg.getText().toString() , etCreated.getText().toString());
                    operations.insertComment(comment);
                }
                getComments();
                clearData();
                adapter.notifyDataSetChanged();
                DatabaseUtils.dumpCursor(operations.getComments());
            }
        });
}


    public void  getComments() {
        Cursor c =operations.getComments();
        comments.clear();
        if(c!=null){
            while (c.moveToNext()){
                comments.add( new Comment(c.getString(1), c.getString(2), c.getString(3), c.getString(4)));
            }
        }
    }


    private void clearData(){
        etComment.setText("");
        etMsg.setText("");
        etCreated.setText("");
        comment=null;
        comment= new Comment();
        comment.setId("");
    }
}
