<p align="right"> 
  <img height="280px" src="/images/logo.png">
</p>


# About
Over-scrolling effect library for android scrollable views


<p align="center">
  <img width="270" src="/images/gif_list.gif">
  <img width="270" src="/images/gif_rec.gif">
   <img width="270" src="/images/gif_grid.gif">
</p>

# How to Use
#### Elastic effect
Kotlin extensions are used to bind the elastic effect to the view 

```kotlin
val recyclerViewBinder = recycler_view.elastic().bind() //bind elastic effect to the view
recyclerViewBinder.unbind() //unbind elastic effect

val listViewBinder = list_view.elastic().bind()
listViewBinder.unbind()

val gridViewBinder = grid_view.elastic().bind()
gridViewBinder.unbind()

val scrollViewBinder = scroll_view.elastic().bind()
scrollViewBinder.unbind()

val nestedScrollViewBinder = nested_scroll_view.elastic().bind()
nestedScrollViewBinder.unbind()

val horizontalScrollViewBinder = horizontal_scroll_view.elastic().bind()
horizontalScrollViewBinder.unbind()

val viewPagerBinder = view_pager.elastic().bind()
viewPagerBinder.unbind()

...
```
#### State listener

```kotlin
val someViewBinder = some_view.elastic().bind()
someViewBinder!!.listener = object : IElasticViewBinder.StateListener {

            override fun onStateChanged(state: IElasticViewBinder.State) {
                when(state) {
                    IElasticViewBinder.State.Idle -> print("Idle")
                    IElasticViewBinder.State.Bounce -> print("Bounce")
                    IElasticViewBinder.State.DraggingStart -> print("DraggingStart")
                    IElasticViewBinder.State.DraggingEnd -> print("DraggingEnd")
                }
            }
        } 
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
