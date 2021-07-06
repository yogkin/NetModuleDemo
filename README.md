# NetModuleDemo
使用：
```kotlin
allprojects {
repositories {
    ...
    maven { url 'https://jitpack.io' }
	}
}
```
```kotlin
dependencies {
    implementation 'com.github.yogkin:NetModuleDemo:1.0.1'
}
```
该网络库优点：
1. 可以完全独立
2. 在任意自定义`view`使用，跟`presenter`完全分离，减少原来`presenter`大量拓展代码
3. 可拓展性良好。
* `loading`使用`showLoading`接口传入，可以自定义任意`loading`样式，如果需要，需要实现`showLoading`，`dismissLoading`方法。
* 自定义错误异常处理
只需要新建一个类，实现`IAPIErrorCode`接口，传进来就可以了，如下：
```kotlin
ApiFactory.handleCode = NetError()
```
* 自定义成功码
比如有两套逻辑，一套逻辑code=200 为成功返回，另外一套code=0 才算成功返回，这时调用接口只需要传入一个code，如下
```kotlin
myService.getRoomLiveList(mapOf()).execute {
    Log.d("MainActivity", it.toString())
}
myService.getRoomLiveList(mapOf()).execute(200) {
    Log.d("MainActivity", it.toString())
}
```
* 如果不需要loading可以使用 `executeNoLoading()`方法
## 使用步骤：
* 创建一个retrofix接口类，用于定义接口 如下
``` kotlin
  interface MyService {
    /**
     * 获取列表
     */

    @GET("/wxarticle/chapters/json")
    fun getRoomLiveList(@QueryMap map: Map<String, String>): Flowable<BaseHttpBean<List<ResultBean>>>

}
```

* 在需要使用网络请求的view中实现`INetView`接口，实现里面的方法。需要注意的是要实现rxjava compose的addDisposable（)
两个方法，我们需要在view中创建一个`CompositeDisposable`类，在view销毁的时候取消订阅，防止内存泄漏。实现如下：
```kotlin
private val mCompositeDisposable: CompositeDisposable = CompositeDisposable()
override fun addDisposable(subscription: Disposable) {
        mCompositeDisposable.add(subscription)
    }

    override fun onDestroy() {
        mCompositeDisposable.dispose()
        super.onDestroy()
    }
```
* 使用如下：
```kotlin
val myService = ApiFactory.create<MyService>()
myService.getRoomLiveList(mapOf()).execute {
                Log.d("MainActivity", it.toString())
            }
```

### 改库还有很多不足的地方，希望大家多多指教哈\(^o^)/~
