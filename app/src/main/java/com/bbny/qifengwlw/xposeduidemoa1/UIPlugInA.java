package com.bbny.qifengwlw.xposeduidemoa1;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import de.robv.android.xposed.IXposedHookInitPackageResources;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_InitPackageResources;
import de.robv.android.xposed.callbacks.XC_LayoutInflated;

/**
 * Created by ZWX on 2019/2/14.
 */

public class UIPlugInA implements IXposedHookInitPackageResources {
    @Override
    public void handleInitPackageResources(XC_InitPackageResources.InitPackageResourcesParam initPackageResourcesParam) throws Throwable {
        XposedBridge.log("APP Info:" + initPackageResourcesParam.packageName);

        if (initPackageResourcesParam.packageName.equals("com.bbny.qifengwlw")){
            initPackageResourcesParam.res.hookLayout(initPackageResourcesParam.packageName, "layout", "activity_login", new XC_LayoutInflated() {
                @Override
                public void handleLayoutInflated(LayoutInflatedParam layoutInflatedParam) throws Throwable {
                    XposedBridge.log(layoutInflatedParam.view.toString());
                    XposedBridge.log(((ViewGroup) layoutInflatedParam.view).getChildAt(0).toString());
                    ((ViewGroup) layoutInflatedParam.view).getChildAt(0).setBackgroundResource(R.drawable.meizi);//更改登入界面的背景图
                    View iv = ((ViewGroup) ((ViewGroup) layoutInflatedParam.view).getChildAt(0)).getChildAt(0);
                    XposedBridge.log("view ID:"+iv.getId());
                    if (iv instanceof ImageView) {
                        iv.setBackgroundResource(R.drawable.meizi);
                        XposedBridge.log("设置iv 背景图片成功");
                    }
//                    layoutInflatedParam.view.findViewById(R.id.img_icon).setBackgroundResource(R.drawable.meizi);
                    XposedBridge.log("qifeng login 背景更换成功");
                }
            });
        }

    }
}
