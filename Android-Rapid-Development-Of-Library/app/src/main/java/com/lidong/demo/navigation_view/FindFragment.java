package com.lidong.demo.navigation_view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.lidong.demo.R;
import com.lidong.demo.greendao.Note;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FindFragment extends Fragment {

    @Bind(R.id.btn_test)
    Button mBtnTest;
    private View view;

    public static FindFragment newInstance(String param1) {
        FindFragment fragment = new FindFragment();
        Bundle args = new Bundle();
        args.putString("agrs1", param1);
        fragment.setArguments(args);
        return fragment;
    }

    public FindFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_find, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @OnClick(R.id.btn_test)
    public void onClick() {
        TextView textView = (TextView) view.findViewById(R.id.btn_text111111);
        Note note =null;
//        try {
            textView.setText(note.getText());
//        }catch (Exception e){
//            ExceptionUtils.handleException(getActivity(),e);
//        }
    }
}
