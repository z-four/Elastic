<p align="center"> 
  <img align="right" height="280px" src="/images/logo.png">
</p>


# About
Over-scrolling effect library for android scrollable views 

<p align="center">
  <img width="280px" src="/images/gif_list.gif">
  <img width="280px" src="/images/gif_rec.gif">
</p>

# How to Use
Kotlin extensions are used to bind the elastic effect to the view 

```kotlin
val recyclerViewBinder = recycler_view.elastic().bind() //bind elastic effect to the view
recyclerViewBinder.unbind() //unbind elastic effect

val listViewBinder = list_view.elastic().bind()
listViewBinder.unbind()

val viewPagerBinder = view_pager.elastic().bind()
viewPagerBinder.unbind()

//so on for other scrollable views
...
```

Download
--------

```groovy
dependencies {
  compile 'com.github.z-four:elastic:1.0'
}
```

```groovy
allprojects {
    repositories {
        ...
        maven {
            url "https://jitpack.io"
        }
    }
}
```
License
-------

    Copyright 2018 Dmitriy Zhyzhko

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.