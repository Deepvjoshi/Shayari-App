package com.deepjoshi.shayar;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

/**
 * Created by harsh on 14/2/17.
 */
public class CustomlistAdapter  extends BaseAdapter{
    Context listcontext;
    ArrayList<Qlist> qlist;

    public CustomlistAdapter(Context listcontext, ArrayList<Qlist> qlist){
        this.listcontext = listcontext;
        this.qlist = qlist;

    }

    @Override
    public int getCount() {
        return qlist.size();
    }

    @Override
    public Object getItem(int position) {
        return qlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewListHolder{
        TextView Listquotes;
        MaterialLetterIcon icon;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewListHolder listholder = new ViewListHolder();
        if (convertView == null){

            convertView = LayoutInflater.from(listcontext).inflate(R.layout.single_row,parent,false);
            listholder.Listquotes = (TextView)convertView.findViewById(R.id.single_text);
            listholder.icon = (MaterialLetterIcon) convertView.findViewById(R.id.rge);

            convertView.setTag(listholder);
        }else

        {
            listholder = (ViewListHolder)convertView.getTag();
        }

        String caseName = qlist.get(position).getQuotes();
        String firstChar = String.valueOf(caseName.charAt(0));
        listholder.icon.setLetter(firstChar);

        listholder.Listquotes.setText(qlist.get(position).getQuotes());

        listholder.Listquotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(listcontext,Quotes.class);
                myintent.putExtra("quotePos",qlist.get(position).getQuotes());
                listcontext.startActivity(myintent);
            }
        });

        return convertView;
    }
}
