# Permission View
> 一个简单的权限封装库，基于AndPermission



# 特性

> - 接入简单，注入性少
- 封装了权限相关的实用方法



# 安装

**Gradle**
```java
maven { url 'https://jitpack.io' }  

implementation 'com.github.ShigureNya:PermissionView:1.0.3'
```
**Maven**

```xml
<repositories>
      <repository>
          <id>jitpack.io</id>
          <url>https://jitpack.io</url>
      </repository>
</repositories>

<dependency>
       <groupId>com.github.ShigureNya</groupId>
       <artifactId>PermissionView</artifactId>
       <version>1.0.3</version>
</dependency>
```



# 使用方法

### 初始化需要授权的信息

```java 
ArrayList<PermissionItem> pers = new ArrayList<>();
pers.add(PermissionItem.STORAGE);
pers.add(PermissionItem.CAMERA);
pers.add(PermissionItem.RECORD);
```



### 启动授权页

```java 
//通过Intent为授权页赋值
Intent intent = new Intent(this, PermissionRequireActivity.class);
intent.putParcelableArrayListExtra("PermissionList",pers);  //授权列表
intent.putExtra("AppName",getString(R.string.app_name));	//App名称
intent.putExtra("AppIcon",R.mipmap.ic_launcher);			//App图标
startActivityForResult(intent,PermissionRequireActivity.REQ_PERMISSION_CODE);
```



### 在ActivityResult中监听返回的信息

```java 
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if(requestCode == PermissionRequireActivity.REQ_PERMISSION_CODE){
         int code = data.getIntExtra("PERMISSION_REQUIRE_STATE",0);
         switch (code){
             case 1:
                 //授权完成
                 break;
             case 2:
                 //用户拒绝了授权
                 break;
             case 3:
                 //用户永久拒绝了授权，提示打开设置手动勾选权限
                 break;
        }
   }
}
```



# 权限工具类（PermissionUtil）

| 方法                     |         描述         |                 参数                  | 返回值  |
| :----------------------- | :------------------: | :-----------------------------------: | :-----: |
| NotPermission            |     是否缺少权限     | context上下文，permissionList权限列表 | boolean |
| isAlwaysDeniedPermission | 是否总是拒绝了该权限 | context上下文，permissionList权限列表 | boolean |

# TODO

> 底部弹出的权限确认框



# License

> MIT License
> Copyright (c) [2017] [EDT]
> Permission is hereby granted, free of charge, to any person obtaining a copy
> of this software and associated documentation files (the "Software"), to deal
> in the Software without restriction, including without limitation the rights
> to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
> copies of the Software, and to permit persons to whom the Software is
> furnished to do so, subject to the following conditions:<br/>
> The above copyright notice and this permission notice shall be included in all
> copies or substantial portions of the Software.<br/>
> THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
> IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
> FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
> AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
> LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
> OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
> SOFTWARE.
