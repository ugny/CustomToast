package com.ugunay.customtoast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.ugunay.customtoast.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Resources res = getResources();
        CustomToast.setStaticBackgroundColor(Color.BLUE);
        CustomToast.setStaticTextColor(Color.LTGRAY);
        CustomToast.setStaticTypeface(Typeface.DEFAULT_BOLD);

        CustomToast customToast = CustomToast.getInstance(getSupportFragmentManager());

        binding.btnShowToast1.setOnClickListener(v ->
                customToast.showToast("Sadece mesaj uygulandı...")
        );

        binding.btnShowToast2.setOnClickListener(v ->
                customToast.showToast("Duration.EX_SHORT...", Duration.EX_SHORT)
        );

        binding.btnShowToast3.setOnClickListener(v ->
                customToast.showToast("Duration.SHORT ve ikon uygulandı...",
                        Duration.SHORT, R.drawable.ct_ic_mood)
        );

        binding.btnShowToast4.setOnClickListener(v ->
                customToast.showToast("Duration.LONG, ikon ve arka plan uygulandı...",
                        Duration.LONG, R.drawable.ct_ic_mood, getResources().getColor(R.color.teal_700))
        );

        binding.btnShowSuccessToastLong.setOnClickListener(v ->
                customToast.showSuccessToast(res, "Hello world! Duration.LONG...", Duration.LONG)
        );

        binding.btnShowSuccessToast.setOnClickListener(v ->
                customToast.showSuccessToast(res, "Hello world with Success...")
        );

        binding.btnShowErrorToast.setOnClickListener(v ->
                customToast.showErrorToast(res, "Hello world with Error...")
        );

        binding.btnShowInfoToast.setOnClickListener(v ->
                customToast.showInfoToast(res, "Hello world with Info...")
        );

        binding.btnShowWarningToast.setOnClickListener(v ->
                customToast.showWarningToast(res, "Hello world with Warning...")
        );

        binding.btnShowWorksContinuesToast.setOnClickListener(v ->
                customToast.showWorksContinuesToast(res)
        );

        binding.btnSetFreeToast1.setOnClickListener(v ->
                CustomToast.getInstance(getSupportFragmentManager())
                        .setMessage("Bazı özellikler set edildi...")
                        .setTextColor(res.getColor(R.color.black))
                        .setBackgroundColor(res.getColor(R.color.teal_700))
                        .show()
        );

        binding.btnSetFreeToast2.setOnClickListener(v ->
                CustomToast.getInstance(getSupportFragmentManager())
                        .setMessage("Tüm özellikler set edildi...")
                        .setTextColor(Color.CYAN)
                        .setTypeface(Typeface.MONOSPACE)
                        .setDuration(Duration.EX_LONG)
                        .setIconResId(R.drawable.ct_ic_mood)
                        .setBackgroundColor(res.getColor(R.color.purple_200))
                        .show()
        );
    }

}