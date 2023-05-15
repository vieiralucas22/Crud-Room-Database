package com.example.myroomdatabase.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myroomdatabase.Person;
import com.example.myroomdatabase.PersonDatabase;
import com.example.myroomdatabase.R;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RemovePersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RemovePersonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public RemovePersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RemovePersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RemovePersonFragment newInstance(String param1, String param2) {
        RemovePersonFragment fragment = new RemovePersonFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_remove_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PersonDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                PersonDatabase.class,"person-database").allowMainThreadQueries().build();
        Button btnDeletePerson = (Button) getView().findViewById(R.id.btnDelete);
        Button btnRemoveAll = (Button) getView().findViewById(R.id.btnRemoveAll);

        btnDeletePerson.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText idDelete = (EditText) getView().findViewById(R.id.idDeletePerson);

                        List<Person> personList = db.personDao().getAllPerson();
                        int idRemove = Integer.parseInt(idDelete.getText().toString());

                        for(Person list: personList){
                            if (list.uid==idRemove){
                                Toast.makeText(getActivity().getApplicationContext(), list.name+" foi removido"
                                        , Toast.LENGTH_SHORT).show();
                                Log.d("msg","======="+list.name + " foi removido do banco de dados!"+"=======");
                                db.personDao().deletePerson(list);
                            }
                        }

                    }
                }
        );

        btnRemoveAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db.personDao().removeAll();
                        Toast.makeText(getActivity().getApplicationContext(), "Todas as pessoas cadastradas foram removidas"
                                , Toast.LENGTH_SHORT).show();                    }
                }
        );
    }
}

