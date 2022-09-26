# CustomToast
Custom Toast

[![](https://jitpack.io/v/ugny/customtoast.svg)](https://jitpack.io/#ugny/customtoast)

Özelleştirilebilir <code>Toast</code> mesajları gösterir. Bu kütüphane <b>API 30</b> ve sonrasında 
kaldırılan özel <code>Toast</code> kullanımı için tasarlanmıştır. Kütüphaneden 
bir nesne oluşturulduktan sonra <b>iki farklı kullanım vardır:</b>
<ul>
  <li><code>show</code> anahtar kelimesi ile başlayan hazır fonksiyonlar kullanılabilir.
  Örnek: {@link #showToast(String, Duration, int, int)}, {@link #showSuccessToast(
  Resources, String)} vs.</li>
  <li>Tüm özelliklerin tek tek ayarlanabildiği <code>set</code> fonksiyonları kullanılabilir.
  Örnek: {@link #setMessage(String)}, {@link #setTextColor(int)} vs. <code>Set</code>
  fonksiyonları kullanıldıktan sonra {@link #show()} fonksiyonunun çağrılması gerekmektedir.</li>
</ul>

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


