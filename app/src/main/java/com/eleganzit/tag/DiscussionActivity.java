package com.eleganzit.tag;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.eleganzit.tag.adapter.CommentAdapter;
import com.eleganzit.tag.adapter.DisCommentAdapter;
import com.eleganzit.tag.adapter.DiscussionAdapter;
import com.eleganzit.tag.adapter.HelpAdapter;
import com.eleganzit.tag.model.discussion.AnsList;
import com.eleganzit.tag.model.discussion.Datum;
import com.eleganzit.tag.model.discussion.ReplyList;

import java.util.ArrayList;

public class DiscussionActivity extends AppCompatActivity {
RecyclerView rc_discussion;
Datum datum;
ArrayList<AnsList> ansLists;
ArrayList<ReplyList> reply;
TextView hidetxt,emailtv,created_date,viewcomment;
    ProgressDialog progressDialog;
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
    datum=getIntent().getParcelableExtra("mydata");
    ansLists=getIntent().getParcelableArrayListExtra("answer");
    reply=getIntent().getParcelableArrayListExtra("reply");
        findViewById(R.id.back).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

    Log.d("dfsdfs","da"+ansLists.size());

    progressDialog = new ProgressDialog(DiscussionActivity.this);
    progressDialog.setMessage("Please Wait");
    progressDialog.setCancelable(false);
    progressDialog.setCanceledOnTouchOutside(false);
    hidetxt=findViewById(R.id.hidetxt);
    viewcomment=findViewById(R.id.viewcomment);
    created_date=findViewById(R.id.created_date);
    emailtv=findViewById(R.id.emailtv);
    emailtv.setText(datum.getFirstName());
    hidetxt.setText(datum.getQuestionText());
    created_date.setText(datum.getCreatedDate());
        rc_discussion=findViewById(R.id.rc_discussion);
    if (ansLists!=null) {
        if (ansLists.size() > 0) {
            viewcomment.setText("View Comment ("+ansLists.size()+")");
        }
    }
    viewcomment.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (ansLists!=null){
                if (ansLists.size()>0)
                {
                    rc_discussion.setVisibility((rc_discussion.getVisibility() == View.VISIBLE)
                            ? View.GONE : View.VISIBLE);
                    rc_discussion.setAdapter(new DisCommentAdapter(reply,ansLists,DiscussionActivity.this));

                }
                else
                {
                    viewcomment.setText("View Comment (0)");
                }

            }

        }
    });

    }@Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
