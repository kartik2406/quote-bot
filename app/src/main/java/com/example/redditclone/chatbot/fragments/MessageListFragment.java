package com.example.redditclone.chatbot.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.redditclone.chatbot.R;
import com.example.redditclone.chatbot.adapters.MessageListAdapter;
import com.example.redditclone.chatbot.utils.MessageDateFormat;
import com.example.redditclone.chatbot.viewmodels.Message;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MessageListFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MessageListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MessageListFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public static  final  String BASE_URL = "https://quotes007.herokuapp.com/quotes/";

    MessageListAdapter adapter;
    Button sendButton;
    RecyclerView recyclerView;
    TextView chatBox;

    // Volley: RequestQueue.
    RequestQueue requestQueue;

    // Date Uitl func
    MessageDateFormat messageDateFormat;

    // min-max range
    private static final int MIN = 1;
    private static final int MAX = 100;
    private OnFragmentInteractionListener mListener;

    public MessageListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MessageListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MessageListFragment newInstance(String param1, String param2) {
        MessageListFragment fragment = new MessageListFragment();
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
        // Instantiate the RequestQueue.
        requestQueue = Volley.newRequestQueue(getActivity().getApplicationContext());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message_list, container, false);

        chatBox = (TextView) view.findViewById(R.id.edittext_chatbox);

        messageDateFormat = MessageDateFormat.getInstance();

        sendButton = (Button) view.findViewById(R.id.button_chatbox_send);
        Log.d("Button", sendButton.toString());
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("Button", chatBox.getText().toString());
                // retrive wha user entered in the textbox

                String userInput = chatBox.getText().toString();
                chatBox.setText("");

                adapter.addMessage(new Message(userInput, getString(R.string.user), messageDateFormat.getFormattedDate()));



                // split userInput to check if multiple values present
                String[] quoteIds = userInput.split(" ");

                for(String id: quoteIds){
                    int input = 0;
                    try {
                        input = Integer.parseInt(id);
                    } catch (NumberFormatException e){
                        Toast.makeText(getActivity().getApplicationContext(), "Input "+ id + " is invalid", Toast.LENGTH_SHORT).show();
                        continue;
                    }
                    if(input < MIN || input > MAX){
                        Toast.makeText(getContext(), "Enter number within range", Toast.LENGTH_LONG).show();
                    } else {
                        //make an api call
                        String url = BASE_URL + id;
                        callApi(url);
                    }

                }


            }
        });

        adapter = new MessageListAdapter( generateSimpleList()) ;

        recyclerView = view.findViewById(R.id.reyclerview_message_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);

        return view;
    }

    private void callApi(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try{
                            adapter.addMessage(new Message( response.getString("quote"), getString(R.string.bot), messageDateFormat.getFormattedDate()));
                        } catch (JSONException e){
                            Log.d("API failed", e.toString());
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        requestQueue.add(jsonObjectRequest);

    }
    private List<Message> generateSimpleList() {
        List<Message> simpleViewModelList = new ArrayList<>();

        for (int i = 0; i < 1; i++) {
         //   simpleViewModelList.add(new Message(new String("This is item " + i)));
        }
        Log.d("Dummy size ", " " + simpleViewModelList.size());
        return simpleViewModelList;
    }
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
