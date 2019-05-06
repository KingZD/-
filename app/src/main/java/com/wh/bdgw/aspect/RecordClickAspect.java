package com.wh.bdgw.aspect;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.wh.bdgw.aspect.annotation.MultiClick;
import com.wh.bdgw.util.DeviceUtils;
import com.tencent.bugly.crashreport.CrashReport;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * 文件名:RecordClickAspect
 * 创建者:zed
 * 创建日期:2019/3/7 10:28
 * 描述:记录点击事件
 */
@Aspect
public class RecordClickAspect {
    final String TAG = RecordClickAspect.class.getSimpleName();
//全注解
//    @Pointcut("(execution(* android.view.View.OnClickListener.onClick(..))"
//            + "|| execution(* butterknife.internal.DebouncingOnClickListener.doClick(..)))"
//            + "&& target(Object) && this(Object)")
//    private void clickPoint() {
//    }

    /**
     * 单击事件和Butterknife的单击事件切入点
     */
    @Pointcut("(execution(* android.view.View.OnClickListener.onClick(..)))"
            + "&& target(Object) && this(Object)")
    private void clickPoint() {
    }


    /**
     * 事件操作
     *
     * @param joinPoint 切入点
     */
    @Around("clickPoint()")
    public void onClickLitener(ProceedingJoinPoint joinPoint) {
        Object[] objects = joinPoint.getArgs();
//        View view = objects.length == 0 ? null : (View) objects[0];
        try {
//            Context context = view.getContext();
//            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
//            MultiClick annotation = methodSignature.getMethod().getAnnotation(MultiClick.class);
//            boolean multiClick = DeviceUtils.isMultiClick();
//            Log.e(TAG, multiClick + "_" + annotation);
//            //单击允许执行后续逻辑
//            if (!multiClick || annotation != null)
            joinPoint.proceed(objects);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            CrashReport.postCatchedException(throwable);
        }
    }
}
