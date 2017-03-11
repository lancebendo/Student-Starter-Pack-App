package lancepogi.mobiledevelopmentproject;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;


/**
 * Created by Lance on 12/23/2016.
 */

public class FragmentAdd extends Fragment {

    Spinner spTodo;
    Spinner spTowhat;

    private static int selectedPosition = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.add_fragment,container,false);


        // test


        //test end

        this.spTodo = (Spinner) rootView.findViewById(R.id.spTodo);
        this.spTowhat = (Spinner) rootView.findViewById(R.id.spTowhat);
        spTowhat.setVisibility(View.INVISIBLE);

        spTodo.setSelection(selectedPosition);
        Button btn = (Button) rootView.findViewById(R.id.btnGo);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //index of toDo: 1 for add, 2 for delete
                //index of toWhat: 1 for subject, 2 for assignment, 3 for activity, 4 for no class

                Bundle args = new Bundle();

                int[] spinnerIndex = {0, 0};

                if(spTodo.getSelectedItemPosition() != 0 && spTowhat.getSelectedItemPosition() != 0) {
                    spinnerIndex[0] = spTodo.getSelectedItemPosition();
                    spinnerIndex[1] = spTowhat.getSelectedItemPosition();
                    DialogAdapter da = new DialogAdapter();
                    args.putIntArray("index", spinnerIndex);
                    da.setArguments(args);
                    da.show(getActivity().getFragmentManager(), "asd");
                }

            }
        });

        spTodo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //index: 1 for add, 2 for delete
                if (position != 0) {
                    selectedPosition = position;
                    spTowhat.setVisibility(View.VISIBLE);
                }
                if (position == 0) {

                    spTowhat.setVisibility(View.INVISIBLE);
                }

            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        return rootView;
    }



}
