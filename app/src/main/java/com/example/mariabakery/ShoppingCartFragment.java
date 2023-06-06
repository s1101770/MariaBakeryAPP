package com.example.mariabakery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ShoppingCartFragment#newInstance} factory method to
 * create an instance of this fragment.
 *
 */
public class ShoppingCartFragment extends Fragment {


    public static ShoppingCartFragment newInstance() {
        ShoppingCartFragment fragment = new ShoppingCartFragment();
        return fragment;
    }


    @Override
    public void onViewCreated(View view,Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        String show = sharedPreferences.getString("Saved", null);

        TextView textView = (TextView) view.findViewById(R.id.textView2);
        textView.setText(show);

        Button button = (Button) view.findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();

                Toast.makeText(getActivity(), "已送出交易資料", Toast.LENGTH_SHORT).show();

                ShoppingCartFragment shoppingCartFragment = ShoppingCartFragment.newInstance();
                FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.frameLayout, shoppingCartFragment, null).commit();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shopping_cart, container, false);
    }

    private String read(){
        /**創建SharedPreferences，索引為"Data"*/
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
        /**回傳在"Saved"索引之下的資料；若無儲存則回傳"未存任何資料"*/
        return sharedPreferences.getString("Saved","未存任何資料");
    }
    private void clear(){
        /**創建SharedPreferences*/
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Data",Context.MODE_PRIVATE);
        /**取得SharedPreferences.Editor*/
        SharedPreferences.Editor editor = sharedPreferences.edit();
        /**利用clear清除掉所有資料*/
        editor.clear();
        /**不返回結果的提交*/
        /**若需要提交結果，則可使用.commit()*/
        editor.apply();

    }
}
