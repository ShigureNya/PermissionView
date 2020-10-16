package cn.u12.permission.adapter;

import android.content.Context;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.u12.permission.R;
import cn.u12.permission.entity.PermissionItem;

public class PermissionAdapter extends BaseQuickAdapter<PermissionItem, BaseViewHolder> {
    private Context context ;
    public PermissionAdapter(Context context , List<PermissionItem> data) {
        super(R.layout.layout_permission_item, data);
        this.context = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, PermissionItem permission) {
        helper.setText(R.id.item_permission_title,permission.getTitle());
        helper.setText(R.id.item_permission_desc,permission.getDetail());
        helper.setImageResource(R.id.item_permission_icon,permission.getIconRes());
    }
}
