package com.example.mariabakery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShopFragment extends Fragment implements RecyclerViewInterface {

    ArrayList<ShopViewModel> viewModels = new ArrayList<>();
    Adapter adapter;
    RecyclerView recyclerView;
    public static ShopFragment newInstance() {
        ShopFragment fragment = new ShopFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_shop, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view,@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerViewBread);
        adapter = new Adapter(getActivity(), viewModels, this);
        adapter.notifyDataSetChanged();
        setUpNoteModel();
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
    }

    private void setUpNoteModel() {
        String[] title = getResources().getStringArray(R.array.note_title_text);
        String[] detail = getResources().getStringArray(R.array.note_detail_text);
        int[] images = {R.drawable.bread1, R.drawable.bread2, R.drawable.bread3, R.drawable.bread4,R.drawable.bread5,R.drawable.bread6};

        for (int i = 0; i < title.length; i++ ) {
            viewModels.add(new ShopViewModel(title[i], detail[i],images[i]));
        }
    }

    @Override
    public void onItemClick(int position) {
        DetailFragment nextFragment = DetailFragment.newInstance();
        Bundle bundle = new Bundle();
        bundle.putString("Title", viewModels.get(position).getName());
        bundle.putString("Detail", viewModels.get(position).getDetail());
        bundle.putInt("Image", viewModels.get(position).getImage());
        nextFragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, nextFragment, null).addToBackStack("2").commit();
    }
}