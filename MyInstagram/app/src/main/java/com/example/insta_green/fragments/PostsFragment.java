package com.example.insta_green.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.insta_green.Post;
import com.example.insta_green.PostsAdapter;
import com.example.insta_green.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    public static final String TAG = "PostFragment";
    private RecyclerView rvPosts;
    protected PostsAdapter adapter;
    protected List<Post> mPosts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);

        //create data sourse
        mPosts = new ArrayList<>();
        //create adapter
        adapter = new PostsAdapter(getContext(), mPosts);
        //set adapter on RV
        rvPosts.setAdapter(adapter);
        //set layput manager on the Rv
        rvPosts.setLayoutManager(new LinearLayoutManager(getContext()));

        queryPost();
    }


    protected void queryPost() {
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        postQuery.setLimit(20);
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if(e != null){
                    Log.e(TAG, "There is an error returning data from parse");
                    e.printStackTrace();
                    return;
                }
                mPosts.addAll(posts);
                adapter.notifyDataSetChanged();
                for(int i =0; i < posts.size(); i++){
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: "+post.getDescription() + ", username: "+ post.getUser().getUsername());
                }
            }
        });
    }
}
