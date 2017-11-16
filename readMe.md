DBFlow—目前最好用的安卓数据库，DBFlow使用详解


先上一张效果图

![](http://upload-images.jianshu.io/upload_images/4614633-3b4f145f12797570.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)


#### dbflow定义

 dbflow是一款android高性的ORM数据库.可以使用在进行项目中有关数据库的操作。

dbflow是Android SQLite ORM 的一个工具库。综合了 Active Android, Schematic, Ollie,Sprinkles 等库的优点；通过注解实现，性能好；能生成 ContentProvider


#### 为什么要是用DBFlow

根据DBFlow的源码来看，是分多个module的，有一个module叫做processor，这是编译时注解处理器Module，看到AbstractProcessor了吧，编译时注解处理器都需要继承这个类，可见DBFlow是编译时注解处理，在编译时就将表相关信息生成了，在编译的时候会把生成的java代码和你自己项目的代码打包到一起，在运行时，就可以调用这些代码了，不需要再写运行时注解处理器，避免反射机制带来的性能负担。

#### DBFlow有何优势

DBFlow的设计吸取了其他很多ORM框架中好的特征，并将之做得更好。它很灵活，让你能更专注于App中真正需要关注的地方。不要让一个ORM库限制了你的思维，而是让代码在你的App中工作得更好。

>****扩展性****： ORM所需的数据类只需要实现Model接口即可，而不需要必须继承一个类，同时为了方便，我们还是推荐继承BaseModel，这是Model接口的一个标准实现。这样你既可以通过继承一个来自其他包的非Model类来生成你的数据库表，也可以通过继承一个Model类并通过添加@Column注解的属性向表中自由添加列。这一切都是为了方便你的使用。
>
> ****速度****：DBFlow基于AnnotationProcessing(注解处理器），通过编译期代码生成，运行时对性能是零损耗的。通过模板来为你维护生成的代码。通过缓存和尽可能地重用对象，我们得到了比原生SQLite更快的速度。同时我们还支持懒加载（lazy-loading），比如对于@ForeignKey和@OneToMany，这使得我们有着更高效得查询效率。
>
> ****SQLite查询流(SQLite Query Flow)****： DBFlow的查询语法尽可能地和SQL语句相似，使您能更快上手。select(name, screenSize).from(Android.class).where(name.is("Nexus 5x")).and(version.is(6.0)).querySingle()
>
> ****开源****： 整个DBFlow库都是开源的，而且也非常欢迎大家来为这个库贡献自己的力量。
> Robust： 我们支持Trigger,ModelView,Index,Migration,所有的数据库操作都在同一个线程（线程安全），还有其他特性。
>
> ****多数据库、多表单****： 我们无缝支持多数据库文件，database modules using DBFlow in other dependencies, simultaneously.
>
> ****基于SQLite****： SQLite是世界上使用最广泛的数据库引擎，基于SQLite的DBFlow使你不需要被限制在某些平台上。
>




### 使用步骤（以最新的为例）：

**第一步** :  在Module级别的gradle中添加
```
apply plugin: 'com.neenbedankt.android-apt'
def dbflow_version = "4.1.2"


dependencies {
    apt "com.github.Raizlabs.DBFlow:dbflow-processor:${dbflow_version}"
    compile "com.github.Raizlabs.DBFlow:dbflow-core:${dbflow_version}"
    compile "com.github.Raizlabs.DBFlow:dbflow:${dbflow_version}"
    // sql-cipher database encyrption (optional)
    compile "com.github.Raizlabs.DBFlow:dbflow-sqlcipher:${dbflow_version}"
}


```
**第二步:** 在项目级的gradle中添加
```


buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:2.3.2'
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
    }
}

allprojects {
    repositories {
        jcenter()
        maven { url "https://www.jitpack.io" }
    }
}

```
**第三步:**  初始化
```
@Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
//      FlowManager.init(this);//这句也可以初始化
    }
```
**第四步**：配置数据库相关参数
```
 @Database(name = AppDatabase.NAME, version = AppDatabase.VERSION)
public final class AppDatabase {
    //数据库名称
    public static final String NAME = "AppDatabase";
    //数据库版本号
    public static final int VERSION = 1;

}

```
**第五步**：新建数据库表

```
@Table(database = AppDatabase.class)
public class Good extends BaseModel implements Serializable {
    @Column
    public String gName;
    @Column
    public String gDes;
    @Column
    public int num;

    @PrimaryKey(autoincrement = true)//ID自增
    public long id;


    public String getgName() {
        return gName;
    }

    public void setgName(String gName) {
        this.gName = gName;
    }

    public String getgDes() {
        return gDes;
    }

    public void setgDes(String gDes) {
        this.gDes = gDes;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}

```

androidStudio 报错
```
Plugin with id 'com.neenbedankt.android-apt' not found.

```

```
buildscript {
    repositories {
        jcenter()
    }
    dependencies {
        classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'

    }
}
```





**总结**

总结：这篇文章只是简单介绍了DBFlow的基本功能使用，DBFlow还有很多很厉害的功能，比如多数据库支持、Powerful Model Caching等，而且还支持Kotlin语言（运行在Java虚拟机的新语言）。我只使用过greenDAO、activeAndroid、afinal、DBFlow数据库，所以在我看来，DBFlow是我用过的数据库当中最好用的数据库，性能也很好，使用非常简单，高度推荐。

![](http://upload-images.jianshu.io/upload_images/4614633-964a9f344afc3f00.gif?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

> 项目地址：
>
> https://github.com/androidstarjack
>
#### 推荐文档

> 项目地址：https://github.com/Raizlabs/DBFlow
>
> 文档介绍：https://github.com/Raizlabs/DBFlow#usage-docs


关于更多：

[热修复实战-史上讲解最详细的文章，强烈推荐](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001255&idx=1&sn=6b1674c7578039b61b4c34825619c363&chksm=6b4769795c30e06fa1d02f89e7a3e230c2d9c5761b0256fd1ed33eee899803a95f574a144450#rd)

[NDK项目实战—高仿360手机助手之卸载监听](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247484610&idx=1&sn=d1b0805b95718cdd1dcb4b73d619d269&chksm=eb47685cdc30e14a6edb8a560f2b72bd66b566f2d23b120fa8b0d49607981687776c00cf3dd9&scene=21#wechat_redirect)

[一个强大的AutoLifecycle—让普通 Java 类自动感知 Activity Lifecycle](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=100001227&idx=1&sn=c3908e6cf25ab3e03f50e2cc7e73dd52&chksm=6b4769555c30e043d4da6697701a5a0f3c513f6a90a8f9dbb99f52a0ade5981f224b206f9c2b#rd)


[Android 图片选择到裁剪之步步深坑](http://mp.weixin.qq.com/s?__biz=MzI3OTU0MzI4MQ==&mid=2247484873&idx=1&sn=ff61bb74db725970d939a7b40ab0e06e&chksm=eb476957dc30e0417f04e9463949482d52ec30e181d38029f0dd18388b58448d067404678839&scene=21#wechat_redirect)

#### 相信自己，没有做不到的，只有想不到的

如果你觉得此文对您有所帮助， 欢迎加入微信公众号：终端研发部

![技术+职场](https://user-gold-cdn.xitu.io/2017/8/1/d354d51a5c58fb8a5ba576f2d9ea7a8e)
