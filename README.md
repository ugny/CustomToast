# CustomToast
Custom Toast

[![](https://jitpack.io/v/ugny/customtoast.svg)](https://jitpack.io/#ugny/customtoast)

Özelleştirilebilir <code>Toast</code> mesajları gösterir. Bu kütüphane <b>API 30</b> ve sonrasında 
kaldırılan özel <code>Toast</code> kullanımı için tasarlanmıştır. Kütüphaneden 
bir nesne oluşturulduktan sonra <b>iki farklı kullanım vardır:</b>
<ul>
  <li><code>show</code> anahtar kelimesi ile başlayan hazır fonksiyonlar kullanılabilir. Örnek kullanımlar:
  
  ```java
    CustomToast customToast = CustomToast.getInstance(getSupportFragmentManager());
    
    customToast.showToast("Toast message...");
    customToast.showToast("Toast message...", Duration.SHORT);
    customToast.showToast("Toast message...", Duration.LONG, R.drawable.ct_ic_mood);
    customToast.showToast("Toast message...", Duration.EX_LONG, R.drawable.ct_ic_mood, getResources().getColor(R.color.teal_700));
        
    customToast.showSuccessToast(getResources(), "Toast message with Success...");
    customToast.showErrorToast(getResources(), "Toast message with Error...");
    customToast.showInfoToast(getResources(), "Toast message with Info...");
    customToast.showWarningToast(getResources(), "Toast message with Warning...");
    customToast.showWarningToast(getResources(), "Toast message with Warning...", Duration.LONG);
    
    customToast.showWorksContinuesToast(getResources());
  ```
  </li>
  
  <li>Tüm özelliklerin tek tek ayarlanabildiği <code>set</code> fonksiyonları kullanılabilir. 
  <code>Set</code> fonksiyonları kullanıldıktan sonra <code>show()</code> fonksiyonunun çağrılması gerekmektedir. 
  Örnek kullanımlar:
  
  ```java
    CustomToast.getInstance(getSupportFragmentManager())
        .setMessage("Bazı özellikler set edildi...")
        .setTextColor(getResources().getColor(R.color.black))
        .setBackgroundColor(getResources().getColor(R.color.teal_700))
        .show();
        
    CustomToast.getInstance(getSupportFragmentManager())
        .setMessage("Tüm özellikler set edildi...")
        .setTextColor(Color.CYAN)
        .setTypeface(Typeface.MONOSPACE)
        .setDuration(Duration.EX_LONG)
        .setIconResId(R.drawable.ct_ic_mood)
        .setBackgroundColor(getResources().getColor(R.color.purple_200))
        .show();
  ```
  
  Bu yöntem kullanılırken bir nesneyle sadece bir toast gösterimi tavsiye edilmektedir.
  </li>
</ul>

<code>textColor</code> ve <code>backgroundColor</code> atamaları yapılırken ya <code>Color</code> sınıfı kullanılmalı
ya da <code>getResources().getColor(R.color.your_color)</code> yöntemi kullanılmalıdır.

******************************************************************************************

### Kütüphanenin *gradle* ile kullanımı

> Step 1. Add the JitPack repository to your build file

##### Add it in your root build.gradle at the end of repositories:

```gradle
allprojects {
    repositories {
      ...
      maven { url 'https://jitpack.io' }
    }
}
```
  
> Step 2. Add the dependency

```gradle
dependencies {
    implementation 'com.github.ugny:customtoast:Tag'
}
```

******************************************************************************************

### Kütüphanenin *maven* ile kullanımı

> Step 1. Add the JitPack repository to your build file

```gradle
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

> Step 2. Add the dependency

```gradle
<dependency>
    <groupId>com.github.ugny</groupId>
    <artifactId>customtoast</artifactId>
    <version>Tag</version>
</dependency>
```


