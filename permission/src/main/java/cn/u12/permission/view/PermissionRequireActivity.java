package cn.u12.permission.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cn.u12.permission.R;
import cn.u12.permission.adapter.PermissionAdapter;
import cn.u12.permission.entity.PermissionItem;

public class PermissionRequireActivity extends AppCompatActivity {
    public static final int REQ_PERMISSION_CODE = 101 ;

    private List<PermissionItem> permissionList = new ArrayList<>();
    private String appName ;
    private int appIcon ;
    private Toolbar mToolbar ;
    private TextView mAppTitle ,mAppName ;
    private RecyclerView mListView ;
    private PermissionAdapter adapter ;
    private ImageView mAppIcon ;
    private Button mRequireBtn ;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_require);
        permissionList = getIntent().getParcelableArrayListExtra("PermissionList");
        appName = getIntent().getStringExtra("AppName");
        appIcon = getIntent().getIntExtra("AppIcon",0);
        initView();
        initAdapter();
        checkPermission();
    }
    private void initView(){
        mToolbar = findViewById(R.id.toolbar);
        mAppTitle = findViewById(R.id.permission_app_title);
        mAppName = findViewById(R.id.permission_app_name);
        mListView = findViewById(R.id.permission_list);
        mAppIcon = findViewById(R.id.permission_app_image);
        mRequireBtn = findViewById(R.id.permission_require);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        mAppName.setText(appName);
        mAppTitle.setText(appName+"想获取以下权限");
        mAppIcon.setImageResource(appIcon);
    }
    private void initAdapter(){
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mListView.setLayoutManager(manager);
        mListView.setHasFixedSize(true);
        mListView.setItemAnimator(new DefaultItemAnimator());

        adapter = new PermissionAdapter(this,new ArrayList<PermissionItem>());
        adapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        adapter.bindToRecyclerView(mListView);
        adapter.setEmptyView(R.layout.layout_permission_empty,mListView);
        mListView.setAdapter(adapter);
    }
    public void back(View view){
        onBackPressed();
    }
    private void checkPermission(){
        List<PermissionItem> savePermission = new ArrayList<>();
        for(PermissionItem per : permissionList){
            String [] pers = per.getPermission();
            if (!AndPermission.hasPermissions(this, pers)) {
                savePermission.add(per);
            }
        }
        if(!savePermission.isEmpty()){
            adapter.setNewData(savePermission);
            mRequireBtn.setEnabled(true);
            mRequireBtn.setBackgroundResource(R.drawable.shape_permission_btn_primary_radius);
        }else{
            adapter.setNewData(new ArrayList<PermissionItem>());
            mRequireBtn.setEnabled(false);
            mRequireBtn.setBackgroundResource(R.drawable.shape_permission_btn_disabled_radius);
        }
    }
    @SuppressLint("WrongConstant")
    public void permissionRequire(View view){
        List<PermissionItem> data = adapter.getData();
        if(data.isEmpty()){
            return;
        }
        List<String> dataStrList = new ArrayList<>();
        for(PermissionItem pers : data){
            dataStrList.addAll(Arrays.asList(pers.getPermission()));
        }
        String[] permissionArray = new String[dataStrList.size()];
        for (int i = 0; i < dataStrList.size(); i++) {
            permissionArray[i] = dataStrList.get(i);
        }
        AndPermission.with(this)
                .runtime()
                .permission(permissionArray)
                .onGranted(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Intent intentTemp = new Intent();
                        intentTemp.putExtra("PERMISSION_REQUIRE_STATE",1);
                        setResult(REQ_PERMISSION_CODE,intentTemp);
                        finish();
                    }
                })
                .onDenied(new Action<List<String>>() {
                    @Override
                    public void onAction(List<String> data) {
                        Intent intentTemp = new Intent();
                        if (AndPermission.hasAlwaysDeniedPermission(PermissionRequireActivity.this, data)) {
                            intentTemp.putExtra("PERMISSION_REQUIRE_STATE",3);
                        }else{
                            intentTemp.putExtra("PERMISSION_REQUIRE_STATE",2);
                        }
                        setResult(REQ_PERMISSION_CODE,intentTemp);
                        finish();
                    }
                }).start();
    }
}
