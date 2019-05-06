package com.wh.bdgw.util;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;

import com.wh.bdgw.MyApplication;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringUtils {
    private StringUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * 判断字符串是否为null或长度为0
     *
     * @param s 待校验字符串
     * @return {@code true}: 空<br> {@code false}: 不为空
     */
    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    /**
     * 判断字符串是否为null或全为空格
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空格<br> {@code false}: 不为null且不全空格
     */
    public static boolean isTrimEmpty(String s) {
        return (s == null || s.trim().length() == 0);
    }

    /**
     * 判断字符串是否为null或全为空白字符
     *
     * @param s 待校验字符串
     * @return {@code true}: null或全空白字符<br> {@code false}: 不为null且不全空白字符
     */
    public static boolean isSpace(String s) {
        if (s == null) return true;
        for (int i = 0, len = s.length(); i < len; ++i) {
            if (!Character.isWhitespace(s.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 判断两字符串是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equals(CharSequence a, CharSequence b) {
        if (a == b) return true;
        int length;
        if (a != null && b != null && (length = a.length()) == b.length()) {
            if (a instanceof String && b instanceof String) {
                return a.equals(b);
            } else {
                for (int i = 0; i < length; i++) {
                    if (a.charAt(i) != b.charAt(i)) return false;
                }
                return true;
            }
        }
        return false;
    }

    /**
     * 判断两字符串忽略大小写是否相等
     *
     * @param a 待校验字符串a
     * @param b 待校验字符串b
     * @return {@code true}: 相等<br>{@code false}: 不相等
     */
    public static boolean equalsIgnoreCase(String a, String b) {
        return a == null ? b == null : a.equalsIgnoreCase(b);
    }

    /**
     * null转为长度为0的字符串
     *
     * @param s 待转字符串
     * @return s为null转为长度为0字符串，否则不改变
     */
    public static String null2Length0(String s) {
        return s == null ? "" : s;
    }

    /**
     * 返回字符串长度
     *
     * @param s 字符串
     * @return null返回0，其他返回自身长度
     */
    public static int length(CharSequence s) {
        return s == null ? 0 : s.length();
    }

    /**
     * 首字母大写
     *
     * @param s 待转字符串
     * @return 首字母大写字符串
     */
    public static String upperFirstLetter(String s) {
        if (isEmpty(s) || !Character.isLowerCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) - 32)) + s.substring(1);
    }

    /**
     * 首字母小写
     *
     * @param s 待转字符串
     * @return 首字母小写字符串
     */
    public static String lowerFirstLetter(String s) {
        if (isEmpty(s) || !Character.isUpperCase(s.charAt(0))) return s;
        return String.valueOf((char) (s.charAt(0) + 32)) + s.substring(1);
    }

    /**
     * 反转字符串
     *
     * @param s 待反转字符串
     * @return 反转字符串
     */
    public static String reverse(String s) {
        int len = length(s);
        if (len <= 1) return s;
        int mid = len >> 1;
        char[] chars = s.toCharArray();
        char c;
        for (int i = 0; i < mid; ++i) {
            c = chars[i];
            chars[i] = chars[len - i - 1];
            chars[len - i - 1] = c;
        }
        return new String(chars);
    }

    /**
     * 转化为半角字符
     *
     * @param s 待转字符串
     * @return 半角字符串
     */
    public static String toDBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == 12288) {
                chars[i] = ' ';
            } else if (65281 <= chars[i] && chars[i] <= 65374) {
                chars[i] = (char) (chars[i] - 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 转化为全角字符
     *
     * @param s 待转字符串
     * @return 全角字符串
     */
    public static String toSBC(String s) {
        if (isEmpty(s)) return s;
        char[] chars = s.toCharArray();
        for (int i = 0, len = chars.length; i < len; i++) {
            if (chars[i] == ' ') {
                chars[i] = (char) 12288;
            } else if (33 <= chars[i] && chars[i] <= 126) {
                chars[i] = (char) (chars[i] + 65248);
            } else {
                chars[i] = chars[i];
            }
        }
        return new String(chars);
    }

    /**
     * 去除特殊字符
     *
     * @param world  字符串
     * @param perfix 字符
     * @return
     */
    public static String trimSuffixWorld(String world, String perfix) {
        return trimSuffixWorld(world, perfix, "");
    }

    /**
     * 去除特殊字符
     *
     * @param world        字符串
     * @param perfix       字符
     * @param replaceWorld 替换的字符
     * @return
     */
    public static String trimSuffixWorld(String world, String perfix, String replaceWorld) {
        if (isEmpty(world)) {
            return "";
        }
        return world.replaceAll(perfix, replaceWorld);
    }

    /**
     * 转换HTML标签
     *
     * @param html
     * @return
     */
    public static Spanned formatHtml(String html) {
        return Html.fromHtml(html);
    }

    /**
     * 转换HTML标签
     *
     * @param html
     * @return
     */
    public static Spanned formatHtml(String html, Object... param) {
        return Html.fromHtml(
                String.format(Locale.getDefault(), html,
                        param));
    }

    /**
     * 复制到剪切板
     *
     * @param mContext 上下文
     * @param word     文本
     */
    public static void copyToClipboard(Context mContext, String word) {
        //获取剪贴板管理器：
        ClipboardManager cm = (ClipboardManager) mContext.getSystemService(Context.CLIPBOARD_SERVICE);
        // 创建普通字符型ClipData
        ClipData mClipData = ClipData.newPlainText("Label", word);
        // 将ClipData内容放到系统剪贴板里。
        cm.setPrimaryClip(mClipData);
    }

    public static String replaceEvenWord(String word) {
        if (isEmpty(word)) {
            return word;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (i % 2 != 0) {
                sb.append("*");
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String replaceX(String word, int count) {
        if (isEmpty(word)) {
            return word;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < word.length(); i++) {
            if (i > word.length() - (count + 1)) {
                sb.append("*");
            } else {
                sb.append(word.charAt(i));
            }
        }
        return sb.toString();
    }

    //根据身份证号输出年龄
    public static String IdNOToAge(String IdNO) {
        if (TextUtils.isEmpty(IdNO)) {
            return "";
        }
        if (IdNO.length() == 18) {
            String year = IdNO.substring(6, 10);// 得到年份
            String yue = IdNO.substring(10, 12);// 得到月份
            Date date = new Date();// 得到当前的系统时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fyear = format.format(date).substring(0, 4);// 当前年份
            String fyue = format.format(date).substring(5, 7);// 月份
            // String fday=format.format(date).substring(8,10);
            int age = 0;
            if (Integer.parseInt(yue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
                age = Integer.parseInt(fyear) - Integer.parseInt(year) + 1;
            } else {// 当前用户还没过生
                age = Integer.parseInt(fyear) - Integer.parseInt(year);
            }
            return String.valueOf(age);
        } else {

            String uyear = "19" + IdNO.substring(6, 8);// 年份
            String uyue = IdNO.substring(8, 10);// 月份
            // String uday=card.substring(10, 12);//日
            String usex = IdNO.substring(14, 15);// 用户的性别
            Date date = new Date();// 得到当前的系统时间
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            String fyear = format.format(date).substring(0, 4);// 当前年份
            String fyue = format.format(date).substring(5, 7);// 月份
            // String fday=format.format(date).substring(8,10);
            int age = 0;
            if (Integer.parseInt(uyue) <= Integer.parseInt(fyue)) { // 当前月份大于用户出身的月份表示已过生
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear) + 1;
            } else {// 当前用户还没过生
                age = Integer.parseInt(fyear) - Integer.parseInt(uyear);
            }
            return String.valueOf(age);
        }
    }

    //获取元素
    public static Bundle getMetaData() {
        ApplicationInfo appInfo = null;
        try {
            appInfo = MyApplication
                    .getApplication()
                    .getPackageManager()
                    .getApplicationInfo(MyApplication
                            .getApplication().getPackageName(), PackageManager.GET_META_DATA);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return appInfo == null ? null : appInfo.metaData;
    }
}
