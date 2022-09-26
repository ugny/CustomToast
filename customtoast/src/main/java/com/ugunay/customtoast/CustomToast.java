package com.ugunay.customtoast;

import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;

import com.ugunay.customtoast.databinding.CustomToastBinding;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Özelleştirilebilir <code>Toast</code> mesajları gösterir. Bu kütüphane <b>API 30</b> ve
 * sonrasında kaldırılan özel <code>Toast</code> kullanımı için tasarlanmıştır. Kütüphaneden
 * bir nesne oluşturulduktan sonra <b>iki farklı kullanım vardır:</b>
 * <ul>
 *     <li><code>show</code> anahtar kelimesi ile başlayan hazır fonksiyonlar kullanılabilir.
 *     Örnek: {@link #showToast(String, Duration, int, int)}, {@link #showSuccessToast(
 *     Resources, String)} vs.</li>
 *     <li>Tüm özelliklerin tek tek ayarlanabildiği <code>set</code> fonksiyonları kullanılabilir.
 *     Örnek: {@link #setMessage(String)}, {@link #setTextColor(int)} vs. <code>Set</code>
 *     fonksiyonları kullanıldıktan sonra {@link #show()} fonksiyonunun çağrılması gerekmektedir.</li>
 * </ul>
 */
public class CustomToast extends DialogFragment {

    private static final String TAG = CustomToast.class.getSimpleName();

    //--------------------------Background Color----------------------------------------------------
    // Varsayılan arka plan rengi.
    private static int staticBackgroundColor = 0;

    /**
     * Varsayılan arka plan rengini set eder.
     *
     * @param backgroundColor toast arka plan rengi.
     */
    public static void setStaticBackgroundColor(int backgroundColor) {
        CustomToast.staticBackgroundColor = backgroundColor;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------Text Color----------------------------------------------------------
    // Varsayılan mesaj metin rengi.
    private static int staticTextColor = 0;

    /**
     * Varsayılan mesaj metin rengini set eder.
     *
     * @param textColor mesaj metin rengi.
     */
    public static void setStaticTextColor(int textColor) {
        CustomToast.staticTextColor = textColor;
    }
//--------------------------------------------------------------------------------------------------

    //--------------------------Typeface------------------------------------------------------------
    // Mesajın varsayılan typeface bilgisi.
    private static Typeface staticTypeface = null;

    /**
     * Mesajın varsayılan <code>typeface</code> özelliğini set eder.
     *
     * @param typeface typeface.
     */
    public static void setStaticTypeface(Typeface typeface) {
        CustomToast.staticTypeface = typeface;
    }
//--------------------------------------------------------------------------------------------------

    /**
     * Factory method.
     *
     * @param fragmentManager parent fragment manager.
     * @return a new instance of CustomToast
     */
    @NonNull
    public static CustomToast getInstance(FragmentManager fragmentManager) {
        return new CustomToast(fragmentManager);
    }

    /**
     * Constructor method.
     *
     * @param fragmentManager parent fragment manager.
     */
    public CustomToast(FragmentManager fragmentManager) {
        this.fragmentManager = fragmentManager;
    }

    private final FragmentManager fragmentManager;

    private CustomToastBinding binding;
    @NonNull
    private String message = "";
    @NonNull
    private Duration duration = Duration.SHORT;
    @DrawableRes
    private int iconResId = 0;
    private int textColor = 0;
    private int backgroundColor = 0;
    private Typeface typeface = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = CustomToastBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setWindow();
        setWidgets();
        setTimer();
    }

    /**
     * Toast penceresinin ayarlarını set eder.
     */
    private void setWindow() {
        Window window = requireDialog().getWindow();
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.getAttributes().windowAnimations = R.style.ct_animation;
        }
    }

    /**
     * Toast widgetlarını set eder.
     */
    private void setWidgets() {
        if (backgroundColor != 0) {
            binding.crdToast.setCardBackgroundColor(backgroundColor);
        }

        if (iconResId == 0) {
            binding.imgIcon.setVisibility(View.GONE);
        } else {
            binding.imgIcon.setVisibility(View.VISIBLE);
            binding.imgIcon.setImageResource(iconResId);
        }

        if (!message.isEmpty()) {
            binding.txtMessage.setText(message);

            if (textColor != 0) {
                binding.txtMessage.setTextColor(textColor);
            }

            if (typeface != null) {
                binding.txtMessage.setTypeface(typeface);
            }
        }
    }

    /**
     * <code>Duration</code> süresi sonunda dialog penceresini kapatır.
     */
    private void setTimer() {
        final Handler handler = new Handler();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                // Handler nesnesi burada tanımlanırsa handler nesnesi oluşturulamıyor.
                // Bu sebeple yukarda tanımlanmıştır.
                handler.post(() -> dismiss());
            }
        };
        new Timer().schedule(timerTask, duration.value);
    }

    /**
     * <code>Toast</code> değişkenlerini set eder ve pencereyi açma işlemini başlatır.
     * Mesajın <code>textColor</code> ve <code>typeface</code> özelliklerine sınıfın
     * static değişkenlerini set eder.
     *
     * @param message         toast mesajı.
     * @param duration        pencccerenin kapatılacağı süre.
     * @param iconResId       toast ikonu. Sıfır değeri geldiği zaman ikon <code>GONE</code> olacaktır.
     * @param backgroundColor pencere arka plan rengi.
     * @see #show()
     */
    public void showToast(@NonNull String message, @NonNull Duration duration,
                          @DrawableRes int iconResId, int backgroundColor) {
        this.message = message;
        this.duration = duration;
        this.iconResId = iconResId;
        this.backgroundColor = backgroundColor;
        textColor = staticTextColor;
        typeface = staticTypeface;
        show();
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showToast(@NonNull String message, @NonNull Duration duration, @DrawableRes int iconResId) {
        showToast(message, duration, iconResId, staticBackgroundColor);
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showToast(@NonNull String message, @NonNull Duration duration) {
        showToast(message, duration, 0, staticBackgroundColor);
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showToast(@NonNull String message) {
        showToast(message, Duration.SHORT, 0, staticBackgroundColor);
    }


    /**
     * @see #showInfoToast(Resources, String, Duration)
     */
    public void showInfoToast(@NonNull Resources res, @NonNull String message) {
        showInfoToast(res, message, Duration.SHORT);
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showInfoToast(@NonNull Resources res, @NonNull String message, @NonNull Duration duration) {
        showToast(message, duration, R.drawable.ct_ic_info, res.getColor(R.color.ct_info_color));
    }

    /**
     * @see #showSuccessToast(Resources, String, Duration)
     */
    public void showSuccessToast(@NonNull Resources res, @NonNull String message) {
        showSuccessToast(res, message, Duration.SHORT);
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showSuccessToast(@NonNull Resources res, @NonNull String message, @NonNull Duration duration) {
        showToast(message, duration, R.drawable.ct_ic_check_circle, res.getColor(R.color.ct_success_color));
    }

    /**
     * @see #showErrorToast(Resources, String, Duration)
     */
    public void showErrorToast(@NonNull Resources res, @NonNull String message) {
        showErrorToast(res, message, Duration.SHORT);
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showErrorToast(@NonNull Resources res, @NonNull String message, @NonNull Duration duration) {
        showToast(message, duration, R.drawable.ct_ic_error, res.getColor(R.color.ct_error_color));
    }

    /**
     * @see #showWarningToast(Resources, String, Duration)
     */
    public void showWarningToast(@NonNull Resources res, @NonNull String message) {
        showWarningToast(res, message, Duration.SHORT);
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showWarningToast(@NonNull Resources res, @NonNull String message, @NonNull Duration duration) {
        showToast(message, duration, R.drawable.ct_ic_warning, res.getColor(R.color.ct_warning_color));
    }

    /**
     * @see #showToast(String, Duration, int, int)
     */
    public void showWorksContinuesToast(@NonNull Resources res) {
        showToast(res.getString(R.string.works_continues), Duration.SHORT, R.drawable.ct_ic_mood,
                res.getColor(R.color.ct_success_color));
    }


    //--------------------------Set Fonksiyonları---------------------------------------------------
    public CustomToast setMessage(@NonNull String message) {
        this.message = message;
        return this;
    }

    public CustomToast setTextColor(int textColor) {
        this.textColor = textColor;
        return this;
    }

    public CustomToast setTypeface(Typeface typeface) {
        this.typeface = typeface;
        return this;
    }

    public CustomToast setDuration(@NonNull Duration duration) {
        this.duration = duration;
        return this;
    }

    public CustomToast setIconResId(@DrawableRes int iconResId) {
        this.iconResId = iconResId;
        return this;
    }

    public CustomToast setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
        return this;
    }
//--------------------------------------------------------------------------------------------------


    /**
     * <code>Toast</code> penceresini açar.
     */
    public void show() {
        show(fragmentManager, TAG);
    }

}
