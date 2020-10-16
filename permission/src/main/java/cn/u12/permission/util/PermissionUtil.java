package cn.u12.permission.util;

import android.content.Context;

import com.yanzhenjie.permission.AndPermission;

import java.util.List;

import cn.u12.permission.entity.PermissionItem;

public class PermissionUtil {
    /**
     * 是否缺少权限
     * @param context 上下文
     * @param permissionList 权限列表
     * @return
     */
    public static boolean NotPermission(Context context , List<PermissionItem> permissionList){
        for(PermissionItem per : permissionList){
            String [] pers = per.getPermission();
            if (!AndPermission.hasPermissions(context, pers)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 是否总是拒绝了该权限
     * @param context 上下文
     * @param permissionList 权限列表
     * @return
     */
    public static boolean isAlwaysDeniedPermission(Context context , List<PermissionItem> permissionList ){
        for(PermissionItem per : permissionList){
            String [] pers = per.getPermission();
            if (AndPermission.hasAlwaysDeniedPermission(context, pers)) {
                return true;
            }
        }
        return false;
    }
}
