package com.example.retrofitecommerceapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.example.retrofitecommerceapp.R;
import com.example.retrofitecommerceapp.activities.MainTwoActivity;
import com.example.retrofitecommerceapp.api.RetroFit_API_NetworkCall;
import com.example.retrofitecommerceapp.model.LoginDataModel;
import com.example.retrofitecommerceapp.session.SessionManagement;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OrdersFragment extends Fragment {
    TextView textContinue;
    EditText editTextemail, editTextpassword;
    ImageView searchImgView;
    View viewLogin;

    //    SessionManagement sessionManagement;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_orders, container, false);
        searchImgView = view.findViewById(R.id.searchImageOrderFragment);

        editTextemail = view.findViewById(R.id.editTextEmail);
        editTextpassword = view.findViewById(R.id.editTextPassword);
        textContinue = view.findViewById(R.id.letMeInButton);
        viewLogin = view.findViewById(R.id.viewLogin);

        searchImgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MainTwoActivity.class);
                intent.putExtra("search", "search");
                startActivity(intent);
            }
        });

        boolean b = new SessionManagement(getContext()).checkSession();

        if (b) {
            viewLogin.setVisibility(View.GONE);
            getActivity().findViewById(R.id.logTextView).setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.recyclerViewInCart).setVisibility(View.VISIBLE);
        } else {
            viewLogin.setVisibility(View.VISIBLE);
            getActivity().findViewById(R.id.logTextView).setVisibility(View.GONE);
            textContinue.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = editTextemail.getText().toString().trim();
                    String password = editTextpassword.getText().toString().trim();
                    userLoginUsingRetrofit(email, password);//
                }
            });
        }
        return view;
    }

    private void userLoginUsingRetrofit(String email, String password) {//
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RetroFit_API_NetworkCall.BASE_URL_LOGIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        LoginDataModel dataModel = new LoginDataModel(email, password);
        RetroFit_API_NetworkCall networkCall = retrofit.create(RetroFit_API_NetworkCall.class);
        Call<ResponseBody> call = networkCall.login(dataModel);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                Toast.makeText(getActivity(), String.valueOf(response.code()), Toast.LENGTH_SHORT).show();
                if (response.isSuccessful()) {
                    viewLogin.setVisibility(View.GONE);
                    new SessionManagement(getContext()).setUserInfo(email, password);

                    Toast.makeText(getContext(), String.valueOf(new SessionManagement(getContext()).checkSession()), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}
