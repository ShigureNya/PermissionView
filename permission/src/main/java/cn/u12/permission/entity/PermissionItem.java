package cn.u12.permission.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.yanzhenjie.permission.runtime.Permission;

import cn.u12.permission.R;

public enum  PermissionItem implements Parcelable {
    STORAGE(new String[]{Permission.WRITE_EXTERNAL_STORAGE,Permission.READ_EXTERNAL_STORAGE},"存储空间","在使用过程中，本应用需要读写手机存储权限，用于存储和备份设备信息功能。", R.drawable.ic_permission_storage_black),
    CAMERA(new String[]{Permission.CAMERA},"相机","进行直播时，本应用需要您拥有相机权限，以保证和老师的面对面沟通。", R.drawable.ic_permission_camera_black),
    RECORD(new String[]{Permission.RECORD_AUDIO},"音频录制","进行直播时，本应用需要您拥有录制音频的权限，以保证与老师的实时交流。",R.drawable.ic_permission_mic_black),
    LOCATION(new String[]{Permission.ACCESS_FINE_LOCATION},"位置信息","在使用过程中，本应用需要读取当前位置信息，用于导航功能的正常使用。",R.drawable.ic_permission_location_black),
    PHONE_STATE(new String[]{Permission.READ_PHONE_STATE},"设备状态","在使用过程中，本应用需要读取您设备的当前状态，用于对系统方面的使用。",R.drawable.ic_permission_phone_black),
    CALL(new String[]{Permission.CALL_PHONE},"拨打电话","在使用过程中，您有时需要通过App打开手机的拨号功能。",R.drawable.ic_permission_call_black),
    SMS(new String[]{Permission.READ_SMS,Permission.SEND_SMS},"短信功能","在使用过程中，您有时需要通过App打开手机的短信发送功能。",R.drawable.ic_permission_sms_black);

    private String[] permission ;
    private String detail ;
    private String title ;
    private int iconRes ;
    PermissionItem(String[] permission , String title , String detail , int iconRes){
        this.permission = permission ;
        this.detail = detail;
        this.iconRes = iconRes ;
        this.title = title ;
    }

    PermissionItem(Parcel in) {
        permission = in.createStringArray();
        detail = in.readString();
        title = in.readString();
        iconRes = in.readInt();
    }

    public static final Creator<PermissionItem> CREATOR = new Creator<PermissionItem>() {
        @Override
        public PermissionItem createFromParcel(final Parcel source) {
            return PermissionItem.values()[source.readInt()];
        }

        @Override
        public PermissionItem[] newArray(final int size) {
            return new PermissionItem[size];
        }
    };

    public String[] getPermission() {
        return permission;
    }

    public void setPermission(String[] permission) {
        this.permission = permission;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconRes() {
        return iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(ordinal());
    }
}
