package goldteam.meetup.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.AppCompatButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import goldteam.meetup.R;
import goldteam.meetup.statics.Constants;

/**
 * Created by c on 8/3/2016.
 */
public class SocratesFragment extends Fragment implements View.OnClickListener {

    private TextView tv_name,tv_email,tv_message;
    private SharedPreferences pref;
    private AppCompatButton btn_view_blog, btn_change_pw;
    private EditText et_old_password,et_new_password;
    private AlertDialog dialog;
    private ProgressBar progress;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.soc_fragment,container,false);
        initViews(view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        pref = getActivity().getPreferences(0);
        tv_name.setText("Welcome to Socrates' Profile");
        tv_email.setText("sophistry@philosophicalNulPointer.net");

    }

    private void initViews(View view) {

        tv_name = (TextView) view.findViewById(R.id.tv_name);
        tv_email = (TextView) view.findViewById(R.id.tv_email);
        btn_view_blog = (AppCompatButton) view.findViewById(R.id.btn_view_blog);
        btn_change_pw = (AppCompatButton) view.findViewById(R.id.btn_change_password);
        btn_view_blog.setOnClickListener(this);
        btn_change_pw.setOnClickListener(this);

    }

    private void showDialog(){

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_change_password, null);
        et_old_password = (EditText)view.findViewById(R.id.et_old_password);
        et_new_password = (EditText)view.findViewById(R.id.et_new_password);
        tv_message = (TextView)view.findViewById(R.id.tv_message);
        progress = (ProgressBar)view.findViewById(R.id.progress);
        builder.setView(view);
        builder.setTitle("Change Password");
        builder.setPositiveButton("Change Password", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog = builder.create();
        dialog.show();
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String old_password = et_old_password.getText().toString();
                String new_password = et_new_password.getText().toString();
                if(!old_password.isEmpty() && !new_password.isEmpty()){

                    progress.setVisibility(View.VISIBLE);
                    changePasswordProcess(pref.getString(Constants.EMAIL,""),old_password,new_password);

                }else {

                    tv_message.setVisibility(View.VISIBLE);
                    tv_message.setText("Field(s) are empty");
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_view_blog:
                Fragment fragment;
                fragment = new SocBlog();
                FragmentTransaction blog_ft = getFragmentManager().beginTransaction();
                blog_ft.replace(R.id.fragment_frame,fragment);
                blog_ft.commit();
                break;

            case R.id.btn_change_password:
                Toast.makeText(getActivity(), "Request sent!", Toast.LENGTH_SHORT).show();
                break;
        }
    }



    private void changePasswordProcess(String email,String old_password,String new_password){

        Log.d(old_password, "lol");
        if(old_password.equals("fn")) {

            progress.setVisibility(View.GONE);
            tv_message.setVisibility(View.GONE);
            dialog.dismiss();
            Snackbar.make(getView(), "SUCCESS", Snackbar.LENGTH_LONG).show();

        } else{
            progress.setVisibility(View.GONE);
            tv_message.setVisibility(View.VISIBLE);
            tv_message.setText("Failure");

        }

    }}
