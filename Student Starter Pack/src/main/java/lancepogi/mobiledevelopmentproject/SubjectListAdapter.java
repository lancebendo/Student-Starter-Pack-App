package lancepogi.mobiledevelopmentproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Lance on 2/4/2017.
 */

public class SubjectListAdapter extends ArrayAdapter<Subject> implements Serializable {

    Context context;
    public List<Subject> subjectList;
    int layoutResourceId;
    SubjectListAdapter newAdapter = this;
    public SubjectListAdapter(Context context, int textViewResourceId, List<Subject> objects) {
        super(context, textViewResourceId, objects);
        this.subjectList = objects;
        this.context = context;
        this.layoutResourceId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        SubjectHolder holder = null;

            LayoutInflater inflater = ((Activity)context).getLayoutInflater();
            convertView = inflater.inflate(layoutResourceId, parent, false);

            holder = new SubjectHolder();
            holder.imgRemove = (ImageButton) convertView.findViewById(R.id.removeSubject);
            holder.txtSubj = (TextView) convertView.findViewById(R.id.subjectName);
            holder.txtUnits = (TextView) convertView.findViewById(R.id.subjectUnit);

        final Subject newSubject = subjectList.get(position);

        holder.txtSubj.setText(newSubject.getSubjName());
        holder.txtUnits.setText(newSubject.getDayArray());



        holder.imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Integer index = (Integer) v.getTag();
                                subjectList.remove(position);
                                notifyDataSetChanged();

                                break;

                            case DialogInterface.BUTTON_NEGATIVE:

                                break;
                        }
                    }
                };


                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Are you sure you want to remove this subject?")
                        .setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();

            }
        });


            convertView.setTag(holder);
        return convertView;

    }

    public static class SubjectHolder
    {
        ImageButton imgRemove;
        TextView txtSubj;
        TextView txtUnits;
    } }

