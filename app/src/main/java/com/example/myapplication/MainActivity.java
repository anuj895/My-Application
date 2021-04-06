package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
private RecyclerView recycle;
private UserRowAdapter userRowAdapter;
private ArrayList<Pojo> userList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycle=findViewById(R.id.recycle);
        userRowAdapter=new UserRowAdapter(userList);

        LinearLayoutManager llm= new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false);
        recycle.setLayoutManager(llm);
        recycle.setAdapter(userRowAdapter);
        listingdata();
    }

    private void listingdata() {
        ApiInterface apiInterface= Retrofit.getRetrofit().create(ApiInterface.class);
        Call<UserResponse> listingdata = apiInterface.getData();
        listingdata.enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()){

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            userList.addAll(response.body().getData());
                            userRowAdapter.notifyDataSetChanged();
                        }
                    });

                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {

            }


        });
    }

    class UserRowAdapter extends RecyclerView.Adapter<UserRowAdapter.MyViewHolder>{
        ArrayList<Pojo>list;
        public UserRowAdapter(ArrayList<Pojo> list){
            this.list= list;
        }

        @NonNull
        @Override
        public UserRowAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout,parent,false);
            UserRowAdapter.MyViewHolder viewHolder= new UserRowAdapter.MyViewHolder(view);

            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull UserRowAdapter.MyViewHolder holder, int position) {
            holder.Name.setText(list.get(position).getName());
            holder.Email.setText(list.get(position).getEmail());
            holder.Gender.setText(list.get(position).getGender());
            holder.Status.setText(list.get(position).getStatus());

        }

        @Override
        public int getItemCount() {
            return userList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView Name, Email, Gender, Status;

            public MyViewHolder(@NonNull View itemView) {
                super(itemView);
                Name = (TextView) itemView.findViewById(R.id.name);
                Email = (TextView) itemView.findViewById(R.id.email);
                Gender = (TextView) itemView.findViewById(R.id.Gender);
                Status = (TextView) itemView.findViewById(R.id.status);

            }
        }


    }
}