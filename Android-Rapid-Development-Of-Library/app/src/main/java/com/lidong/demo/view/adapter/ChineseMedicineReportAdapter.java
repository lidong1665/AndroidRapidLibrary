package com.lidong.demo.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.lidong.demo.R;
import com.lidong.demo.view.model.Question;

import butterknife.Bind;
import butterknife.ButterKnife;

public class ChineseMedicineReportAdapter extends BaseAdapter {

    Context context;
    Question quesions;

    public ChineseMedicineReportAdapter(Context context, Question quesions) {
        this.context = context;
        this.quesions = quesions;
    }

    @Override
    public int getCount() {
        return quesions.getAnswer().size();
    }

    @Override
    public Object getItem(int position) {
        return quesions.getAnswer().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_answer_list, null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Question.Answer answer = quesions.getAnswer().get(position);
        holder.tvText.setText(answer.getAnswerMessage());
        return convertView;
    }

    static class ViewHolder {

        @Bind(R.id.tv_answer)
        TextView tvText;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
