package com.nxp.asm_android.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.nxp.asm_android.R;
import com.nxp.asm_android.entity.Spending;

import java.util.ArrayList;
import java.util.List;

public class ListFragment extends Fragment implements View.OnClickListener {

    private List<Spending> spendingList;
    private LinearLayout linearLayout;
    private Context mContext;
    private OnCallBack onCallBack;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout = view.findViewById(R.id.lv_list_contact);
        linearLayout.removeAllViews();
        for (int i = 0; i < spendingList.size(); i++) {
            Spending contactEntity = spendingList.get(i);
            View item = LayoutInflater.from(mContext).inflate(R.layout.item_contact, linearLayout, false);
            TextView tvName = item.findViewById(R.id.tv_name);
            TextView tvDesc = item.findViewById(R.id.tv_description);
            tvName.setText(contactEntity.getName());
            tvDesc.setText(contactEntity.getDescription());
            linearLayout.addView(item);
            item.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.item_contact){
            Spending contactEntity = (Spending) view.getTag();
            onCallBack.sendData(contactEntity);
        }
    }

    public OnCallBack getOnCallBack() {
        return onCallBack;
    }

    public void setOnCallBack(OnCallBack onCallBack) {
        this.onCallBack = onCallBack;
    }
}