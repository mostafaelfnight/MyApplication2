package com.mostafa1.myapplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity{
    private User user=new User();
    private UserSharedPrefManager prefManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        prefManager=new UserSharedPrefManager(this);
        user=prefManager.getUser();

        ImageButton btnBack=(ImageButton)findViewById(R.id.back_button);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button editAvatar=(Button)findViewById(R.id.edit_avatar);
        editAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ProfileActivity.this,"ویرایش عکس کلیک شد",Toast.LENGTH_LONG).show();
            }
        });



        EditText firstNameEditText=(EditText)findViewById(R.id.edittext_firstName);

        firstNameEditText.setText(user.getFirstName());

        EditText lastNameEditText=(EditText)findViewById(R.id.edittext_lastName);

        lastNameEditText.setText(user.getLastName());

        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setFirstName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                user.setLastName(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        CheckBox javaCheckBox=(CheckBox)findViewById(R.id.java_checkbox);
        CheckBox cssCheckBOx=(CheckBox)findViewById(R.id.css_checkbox);
        CheckBox htmlCheckBOx=(CheckBox)findViewById(R.id.html_checkbox);

        javaCheckBox.setChecked(user.isJavaExpert());
        cssCheckBOx.setChecked(user.isCssExpert());
        htmlCheckBOx.setChecked(user.isHtmlExpert());



        htmlCheckBOx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setHtmlExpert(isChecked);
            }
        });

        cssCheckBOx.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setCssExpert(isChecked);
            }
        });

        javaCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                user.setJavaExpert(isChecked);
            }
        });


        RadioButton maleRadio=(RadioButton)findViewById(R.id.male_radio);
        RadioButton femaleRadio=(RadioButton)findViewById(R.id.female_radio);



        byte gender=user.getGender();
        if (gender==User.MALE){
            maleRadio.setChecked(true);
        }else {
            femaleRadio.setChecked(true);
        }

        maleRadio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    user.setGender(User.MALE);
                }else {
                    user.setGender(User.FEMALE);
                }
            }
        });

        Button saveForm=(Button)findViewById(R.id.save_form);
        saveForm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                prefManager.saveUser(user);
                Toast.makeText(ProfileActivity.this,"Save Form is clicked",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
