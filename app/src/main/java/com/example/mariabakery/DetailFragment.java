package com.example.mariabakery;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import org.w3c.dom.Text;


public class DetailFragment extends Fragment {


    public static DetailFragment newInstance() {
        DetailFragment fragment = new DetailFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        String Name = bundle.getString("Title");
        String detail = bundle.getString("Detail");
        int Image = bundle.getInt("Image");

        TextView txvTitle = (TextView) view.findViewById(R.id.textViewName);
        TextView txvDetail = (TextView) view.findViewById(R.id.textViewDetail);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView2);
        Button button = (Button) view.findViewById(R.id.button);
        EditText editText = (EditText) view.findViewById(R.id.editText);

        txvTitle.setText(Name);
        txvDetail.setText("價格：" + detail +"\n商品內容:蜂蜜、老麵麵糰、紅酒、桂圓、葡萄乾、核桃");
        imageView.setImageResource(Image);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Data", Context.MODE_PRIVATE);
                if(editText.getText().toString().isEmpty()){
                    Toast.makeText(getActivity(), "請輸入數量", Toast.LENGTH_SHORT).show();
                }else{
                    String addString =  Name + " *" + editText.getText().toString() ;
                    int total = Integer.parseInt(editText.getText().toString()) * Integer.parseInt(detail) ;
                    String txvTotal = Integer.toString(total);
                    String create;
                    if(sharedPreferences.getString("Saved", null) == null){
                        create = addString + "   " + txvTotal + " $NTD\n";
                    }else{
                        create = sharedPreferences.getString("Saved", null) + addString + "   " + txvTotal + " $NTD\n";
                    }

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("Saved", create);
                    editor.apply();

                    Toast.makeText(getActivity(), "已加入購物車", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

}

