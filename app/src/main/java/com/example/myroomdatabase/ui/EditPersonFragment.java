package com.example.myroomdatabase.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

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
 * Use the {@link EditPersonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditPersonFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EditPersonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditPersonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EditPersonFragment newInstance(String param1, String param2) {
        EditPersonFragment fragment = new EditPersonFragment();
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
        return inflater.inflate(R.layout.fragment_edit_person, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        PersonDatabase db = Room.databaseBuilder(getActivity().getApplicationContext(),
                PersonDatabase.class,"person-database").allowMainThreadQueries().build();

        Button btnEdit = (Button) getView().findViewById(R.id.btnEdit);

        btnEdit.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        EditText idEditField = (EditText) getView().findViewById(R.id.idEdit);
                        EditText newName = (EditText) getView().findViewById(R.id.nameEdit);
                        EditText newAge = (EditText) getView().findViewById(R.id.idadeEdit);
                        List<Person> personList = db.personDao().getAllPerson();
                        int idEdit = Integer.parseInt(idEditField.getText().toString());
                        for(Person list: personList){
                            if(idEdit== list.uid){
                                list.name= newName.getText().toString();
                                list.idade=newAge.getText().toString();
                                db.personDao().editPerson(list);
                                Toast.makeText(getActivity().getApplicationContext(), "Atualização feita com sucesso!"
                                        , Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                }
        );

    }
}