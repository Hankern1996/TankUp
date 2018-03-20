package com.example.hannahkern.tankup;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hannahkern.tankup.database.CalculatorDbSchema;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareHashtag;
import com.facebook.share.model.ShareLinkContent;
import com.facebook.share.model.ShareOpenGraphAction;
import com.facebook.share.model.ShareOpenGraphContent;
import com.facebook.share.model.ShareOpenGraphObject;
import com.facebook.share.widget.ShareButton;
import com.facebook.share.widget.ShareDialog;


import java.io.File;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * Created by hannahkern on 28.02.18.
 */

public class CalculatorFragment extends Fragment {
    private static final String ARG_CALCULATOR_ID = "calculator_id";
    private static final String DIALOG_DATE = "DialogDate";

    CallbackManager callbackManager;

    private static final int REQUEST_DATE = 0;

    private Calculator mCalculator;

    private int mSafed =1;

    private SQLiteDatabase mDatabase;

    private TextView mMapskm;
    private EditText mTitle;
    private EditText mConsumption;
    private EditText mGas;
    private EditText mKm;
    private EditText mPassenger;
    private TextView mResultText;
    private Button mDateButton;
    private Button mCalculateButton;
    private Button mDeleteButton;
    private Button mSendButton;
    private Button mSafe;
    private ImageButton mPhotoButton;
    private ImageView mPhotoView;
    private File mPhotoFile;

    private static final int REQUEST_PHOTO= 2;



    private String item;


    public static CalculatorFragment newInstance(UUID calculatorId) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CALCULATOR_ID, calculatorId);

        CalculatorFragment fragment = new CalculatorFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID calculatorID = (UUID) getArguments().getSerializable(ARG_CALCULATOR_ID);
        mCalculator = CalculatorLab.get(getActivity()).getCalculator(calculatorID);

        mPhotoFile = CalculatorLab.get(getActivity()).getPhotoFile(mCalculator);

    }

    @Override
    public void onPause() {
        super.onPause();
            CalculatorLab.get(getActivity())
                    .updateCalculator(mCalculator);

    }

    private void updatePhotoView() {
        if (mPhotoFile == null || !mPhotoFile.exists()) {
            mPhotoView.setImageDrawable(null);
        } else {
            Bitmap bitmap = PictureUtils.getScaledBitmap(
                    mPhotoFile.getPath(), getActivity());
            int width=200;
            int height=300;
            bitmap=Bitmap.createScaledBitmap(bitmap, width,height, true);

            mPhotoView.setImageBitmap(bitmap);
        }
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_calculator, container, false);


        mPhotoButton = (ImageButton) v.findViewById(R.id.image_button);

        final Intent captureImage = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        PackageManager packageManager = getContext().getPackageManager();
        boolean canTakePhoto = mPhotoFile != null &&
                captureImage.resolveActivity(packageManager) != null;
        mPhotoButton.setEnabled(canTakePhoto);

        mPhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = FileProvider.getUriForFile(getActivity(),
                        "com.example.hannahkern.tankup.fileprovider",
                        mPhotoFile);
                captureImage.putExtra(MediaStore.EXTRA_OUTPUT, uri);

                List<ResolveInfo> cameraActivities = getActivity()
                        .getPackageManager().queryIntentActivities(captureImage,
                                PackageManager.MATCH_DEFAULT_ONLY);

                for (ResolveInfo activity : cameraActivities) {
                    getActivity().grantUriPermission(activity.activityInfo.packageName,
                            uri, Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                }

                startActivityForResult(captureImage, REQUEST_PHOTO);
            }
        });

        mPhotoView = (ImageView) v.findViewById(R.id.foto_trip);
        updatePhotoView();

        callbackManager= CallbackManager.Factory.create();

        LoginButton loginButton = (LoginButton) v.findViewById(R.id.login_button);
        loginButton.setReadPermissions("email");
        // If using in a fragment
        loginButton.setFragment(this);

        // Callback registration
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });

        /*mSafe = (Button) v.findViewById(R.id.safeButton);
        //mSafe.setOnClickListener(listener);
        mSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mSafed = 2;
                //getActivity().finish();
                CalculatorLab.get(getActivity()).addCalculator(mCalculator);
            }
        });*/


        mMapskm = (TextView) v.findViewById(R.id.mapskm);

        try {
            item = getActivity().getIntent().getExtras().getString("data");

        } catch (NullPointerException e ) {
            item = "";
        }

        mMapskm.setText(item);

        mTitle = (EditText) v.findViewById(R.id.enter_title);
        mTitle.setText(mCalculator.getTitle());
        mTitle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCalculator.setTitle(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mGas = (EditText) v.findViewById(R.id.enter_price);
        mGas.setText(mCalculator.getGas());
        mGas.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCalculator.setGas(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mConsumption = (EditText) v.findViewById(R.id.enter_fuelconsumption);
        mConsumption.setText(mCalculator.getConsumption());
        mConsumption.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCalculator.setConsumption(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mKm = (EditText) v.findViewById(R.id.enter_distance);
        mKm.setText(mCalculator.getKm());
        mKm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCalculator.setKm(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        mPassenger = (EditText) v.findViewById(R.id.enter_passenger);
        mPassenger.setText(mCalculator.getPassenger());
        mPassenger.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This space intentionally left blank
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                mCalculator.setPassenger(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This one too
            }
        });

        mResultText = (TextView) v.findViewById(R.id.result);
        mResultText.setText((CharSequence) mCalculator.getErgebnis());

        mCalculateButton = (Button) v.findViewById(R.id.calculateButton);
        mCalculateButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                calculate();
            }
        });

        mDeleteButton = (Button) v.findViewById(R.id.deleteButton);
        mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CalculatorLab.get(getActivity()).deleteCalculator(mCalculator);
                getActivity().finish();
            }
        });

        mSendButton = (Button) v.findViewById(R.id.sendButton);
        mSendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_TEXT, getMessage());
                i.putExtra(Intent.EXTRA_SUBJECT,
                        getString(R.string.message_subject));
                i = Intent.createChooser(i, getString(R.string.send_message));
                startActivity(i);
            }
        });

        mDateButton = (Button) v.findViewById(R.id.date_button);
        updateDate();
        mDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager manager = getFragmentManager();
                DatePickerFragment dialog = DatePickerFragment
                        .newInstance(mCalculator.getDate());
                dialog.setTargetFragment(CalculatorFragment.this, REQUEST_DATE);
                dialog.show(manager, DIALOG_DATE);
            }
        });

        ShareLinkContent content = new ShareLinkContent.Builder()
                        .setContentUrl(Uri.parse("https://www.benzinpreis-aktuell.de"))
                        .setQuote("Hey, I just planned my last trip with my friends on the New TankUp-App. Please check your Email, I sent you a payment notification!")
                        .build();

        ShareButton shareButton = (ShareButton)v.findViewById(R.id.share_button);
        shareButton.setShareContent(content);

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        
        
        if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_DATE) {
            Date date = (Date) data
                    .getSerializableExtra(DatePickerFragment.EXTRA_DATE);
            mCalculator.setDate(date);
            updateDate();
        }

         else if (requestCode == REQUEST_PHOTO) {
        Uri uri = FileProvider.getUriForFile(getActivity(),
                "com.example.hannahkern.tankup.fileprovider",
                mPhotoFile);

        getActivity().revokeUriPermission(uri,
                Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        updatePhotoView();
    }
    }

    private void updateDate() {
        Date date = mCalculator.getDate();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        mDateButton.setText("Select a date for your trip:\n" + simpleDateFormat.format(date));
    }

    private void calculate()throws NumberFormatException{
        // Gets the two EditText controls' Editable values
        Editable editableGas = mGas.getText(),
                editableCosumption = mConsumption.getText(),
                editableKm = mKm.getText(),
                editablePassenger = mPassenger.getText();

        // Initializes the double values and result
        double value1 = 0.0,
                value2 = 0.0,
                value3= 0.0,
                value4 = 0.0,
                result;

        // If the Editable values are not null, obtains their double values by parsing
        if (editableGas != null)

            try {
                value1 = Double.parseDouble(editableGas.toString());
            }
            catch (NumberFormatException e) {
                // Handle error here, perhaps notify the user to input some data
                Toast.makeText(getActivity(),
                        "Please enter a valid number", Toast.LENGTH_LONG).show();
                mGas.requestFocus();
                e.printStackTrace();
            }

        if (editableCosumption != null)

            try {
                value2 = Double.parseDouble(editableCosumption.toString());
            }
            catch (NumberFormatException e){
                Toast.makeText(getActivity(),
                        "Please enter a valid number", Toast.LENGTH_LONG).show();
                mConsumption.requestFocus();
                e.printStackTrace();
            }

        if (editableKm != null)

            try {
                value3 = Double.parseDouble(editableKm.toString());
            }
            catch (NumberFormatException e) {
                // Handle error here, perhaps notify the user to input some data
                Toast.makeText(getActivity(),
                        "Please enter a valid number", Toast.LENGTH_LONG).show();
                mKm.requestFocus();
                e.printStackTrace();
            }

        if (editablePassenger != null)

            try {
                value4 = Double.parseDouble(editablePassenger.toString());
            }
            catch (NumberFormatException e){
                // Handle error here, perhaps notify the user to input some data
                Toast.makeText(getActivity(),
                        "Please enter a valid number", Toast.LENGTH_LONG).show();
                mPassenger.requestFocus();
                e.printStackTrace();}

        // Calculates the result
        double result1 = ((value2 * value3 * value1) / (100 * value4))*100;
        result1 = Math.round(result1);
        result = result1/100;



        // Displays the calculated result
        mResultText.setText(String.valueOf(result));
        mCalculator.setErgebnis(String.valueOf(result));
    }

    private String getMessage() {

        String price = mResultText.getText().toString();


        @SuppressLint("StringFormatMatches") String message = getString(R.string.message, price);

        return message;
    }

}