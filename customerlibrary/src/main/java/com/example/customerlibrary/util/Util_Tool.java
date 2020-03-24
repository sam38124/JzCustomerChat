package com.example.customerlibrary.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util_Tool {
    public static String replace(String a,String b,String c){
        return a.replace(b, c);
    }
public static long TimeToStmp(String time){
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 指定时间格式
    Date setTime = null; // 指定时间
    try {
        setTime = sdf.parse(time); // 将字符串转换为指定的时间格式
    } catch (ParseException e) {

        e.printStackTrace();
    }
    long reset = setTime.getTime(); // 获取指定时间的毫秒数
    return reset;
}
    public static String CalculateTime(String time) {

        long nowTime = System.currentTimeMillis(); // 获取当前时间的毫秒数
        String msg = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 指定时间格式
        Date setTime = null; // 指定时间
        try {
            setTime = sdf.parse(time); // 将字符串转换为指定的时间格式
        } catch (ParseException e) {

            e.printStackTrace();
        }
        long reset = setTime.getTime(); // 获取指定时间的毫秒数
        long dateDiff = nowTime - reset;
        if (dateDiff < 0) {
            msg = "剛剛";
        } else {
            long dateTemp1 = dateDiff / 1000; // 秒
            long dateTemp2 = dateTemp1 / 60; // 分钟
            long dateTemp3 = dateTemp2 / 60; // 小时
            long dateTemp4 = dateTemp3 / 24; // 天数
            long dateTemp5 = dateTemp4 / 30; // 月数
            long dateTemp6 = dateTemp5 / 12; // 年数
            if (dateTemp6 > 0) {
                msg = dateTemp6 + "年前";
            } else if (dateTemp5 > 0) {
                msg = dateTemp5 + "個月前";
            } else if (dateTemp4 > 0) {
                msg = dateTemp4 + "天前";
            } else if (dateTemp3 > 0) {
                msg = dateTemp3 + "小时前";
            } else if (dateTemp2 > 0) {
                msg = dateTemp2 + "分鐘前";
            } else if (dateTemp1 > 0) {
                msg = "剛剛";
            }
        }
        return msg;
    }
        public static ArrayList<String> url(String typ){
            ArrayList<String> link = new ArrayList<>();
            String string=typ;
            //把中文替换为#
            string= string.replace("http"," http");
            string = string.replaceAll("[\u4E00-\u9FA5]", "#").replaceAll(" ", "#").replaceAll("\n", "#").replace("\t","#");
            String url[]=string.split("#");
            //转换为小写
            if(url!=null&&url.length>0){
                for(String tempurl:url){
                    if(tempurl.isEmpty()){
                        continue;
                    }  String urL=tempurl;
                    tempurl = tempurl.toLowerCase();
                    String regex = "^((https|http|rtsp|mms)?://)"
                            + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                            + "|" // 允许IP和DOMAIN（域名）
                            + "([0-9a-z_!~*'()-]+\\.)*" // 域名- www.
                            + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                            + "[a-z]{2,6})" // first level domain- .com or .museum
                            + "(:[0-9]{1,4})?" // 端口- :80
                            + "((/?)|" // a slash isn't required if there is no file name
                            + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
                    Pattern p = Pattern.compile(regex);
                    Matcher matcher = p.matcher(tempurl);
                    if(matcher.find()){ link.add(urL); }
                }
            }
            return link;
        }
    public static void loadResPic(Context context, SimpleDraweeView simpleDraweeView, int id) {
        Uri uri = Uri.parse("res://" +
                context.getPackageName() +
                "/" + id);
        simpleDraweeView.setImageURI(uri);
    }

    /**
     * 載入本地圖片（assets圖片）
     * @param context
     * @param simpleDraweeView
     * @param nameWithSuffix 帶字尾的名稱
     */
    public static void loadAssetsPic(Context context, SimpleDraweeView simpleDraweeView, String nameWithSuffix) {
        Uri uri = Uri.parse("asset:///" +
                nameWithSuffix);
        simpleDraweeView.setImageURI(uri);
    }
    public static int[] GetScreen(Activity context){
        DisplayMetrics metric = new DisplayMetrics();
        context.getWindowManager().getDefaultDisplay().getMetrics(metric);
        int[] a=new int[2];
        int width = metric.widthPixels;     // 螢幕寬度（畫素）
        int height = metric.heightPixels;   // 螢幕高度（畫素）
        a[0]=width;
        a[1]=height;
        return a;
    }
    public static void checkPermissiom(Activity context){

    }

    public static Bitmap convertViewToBitmap(View view, int size) {
   view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),
                   View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
   int width = size;
   view.layout(0, 0, view.getMeasuredHeight(), view.getMeasuredHeight()); //根据字符串的长度显示view的宽度
   view.buildDrawingCache();
   Bitmap bitmap = view.getDrawingCache();
 return bitmap;
    }


}
