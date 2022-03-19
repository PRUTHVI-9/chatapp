package com.example.chatapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.truecaller.android.sdk.ITrueCallback;
import com.truecaller.android.sdk.TrueError;
import com.truecaller.android.sdk.TrueProfile;
import com.truecaller.android.sdk.TruecallerSDK;
import com.truecaller.android.sdk.TruecallerSdkScope;

public class TestActivity extends AppCompatActivity {
    Button truecaller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
         truecaller  = findViewById(R.id.truecaller);
         truecaller.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (TruecallerSDK.getInstance().isUsable()) {
                     TruecallerSDK.getInstance().getUserProfile(TestActivity.this);
                 }
             }
         });
        TruecallerSdkScope truecallerSdkScope = new TruecallerSdkScope.Builder(this,callback)
                 .consentMode(TruecallerSdkScope.CONSENT_MODE_BOTTOMSHEET)
                .buttonColor(Color.MAGENTA)
                .buttonTextColor(Color.WHITE)
                .loginTextPrefix(TruecallerSdkScope.LOGIN_TEXT_PREFIX_TO_GET_STARTED)
                .loginTextSuffix(TruecallerSdkScope.LOGIN_TEXT_SUFFIX_PLEASE_VERIFY_MOBILE_NO)
                .ctaTextPrefix(TruecallerSdkScope.CTA_TEXT_PREFIX_USE)
                .buttonShapeOptions(TruecallerSdkScope.BUTTON_SHAPE_ROUNDED)
                .privacyPolicyUrl("https://www.truecaller.com")
                .termsOfServiceUrl("https://www.truecaller.com")
                .footerType(TruecallerSdkScope.FOOTER_TYPE_NONE)
                .consentTitleOption(TruecallerSdkScope.SDK_CONSENT_TITLE_LOG_IN)
                .sdkOptions(TruecallerSdkScope.SDK_OPTION_WITH_OTP)
                .build();
        TruecallerSDK.init(truecallerSdkScope);

    }
    ITrueCallback callback = new ITrueCallback() {
        @Override
        public void onSuccessProfileShared(@NonNull TrueProfile trueProfile) {

        }

        @Override
        public void onFailureProfileShared(@NonNull TrueError trueError) {
            Toast.makeText(TestActivity.this, ""+trueError.getErrorType(), Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onVerificationRequired(TrueError trueError) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        TruecallerSDK.getInstance().onActivityResultObtained(TestActivity.this,requestCode,resultCode,data);
    }
}

