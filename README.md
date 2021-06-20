# BookStore
书店管理系统APK
### 基本功能
1. 书籍的简介与评论
2. 依据书籍的类别和作者的查询
3. 用户登录与注册(基于leancloud)
4. 书籍记录的增加，修改，删除，查询
5. 书籍种类的添加
6. 播放简单的视频
### 相关技术
+ Room
+ leancloud
+ powerspinner
+ toasty
+ GSYVideoPlayer
### 注意事项
如果同样需要使用leancloud托管用户数据
请在`app/src/main/java/com/example/bookstore`新建文件`LeanCloudApp.java`
```
package com.example.bookstore;

import android.app.Application;

import cn.leancloud.AVLogger;
import cn.leancloud.AVOSCloud;

public class LeanCloudApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AVOSCloud.initialize(this, "yourAppId", "yourAppKey", "yourServerURL");
        AVOSCloud.setLogLevel(AVLogger.Level.DEBUG);
    }
}
```
