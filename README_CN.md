# TicketView

Android UI库，用于在android中实现正常、圆角和扇贝角的TicketView（小票样式）。
[English README](https://github.com/vipulasri/TicketView/tree/master/README.md) 

### Specs
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15)
[![Download](https://api.bintray.com/packages/vipulasri/maven/TicketView/images/download.svg)](https://bintray.com/vipulasri/maven/TicketView/_latestVersion)
[![MethodsCount](https://img.shields.io/badge/Methods%20and%20size-125%20|%2012KB-e91e63.svg)](http://www.methodscount.com/?lib=com.vipulasri%3Aticketview%3A1.0.2)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/vipulasri/Timeline-View/blob/master/LICENSE)

### Badges/Featured In
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Ticket%20View-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6521)
[![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%23171-blue.svg)](https://www.androiddevdigest.com/digest-171/) 

![showcase](https://github.com/vipulasri/TicketView/blob/master/art/showcase.png)

## Sample Project

示例代码 : [Sample App Code](https://github.com/vipulasri/TicketView/tree/master/sample) 

### Download

[![TicketView on Google Play](https://github.com/vipulasri/TicketView/blob/master/art/google_play.png)](https://play.google.com/store/apps/details?id=com.vipulasri.ticketview.sample)
Google Play 你懂的，国内请自备梯子。<b>（建议下载源码）</b>

## Quick Setup

### 1. Include library

**Using Gradle**

``` gradle
dependencies {
    compile 'com.vipulasri:ticketview:1.0.5'
}
```

**Using Maven**

``` maven
<dependency>
    <groupId>com.vipulasri</groupId>
    <artifactId>ticketview</artifactId>
    <version>1.0.5</version>
    <type>pom</type>
</dependency>
```

### What's New

请查看版本更新日志。.

### [View Releases](https://github.com/vipulasri/TicketView/releases)

如果您 "Watch" 此库，每次发布更新时，GitHub都会向您发送一封电子邮件。

### 2. Usage
 * In XML Layout :

``` java
<com.vipulasri.ticketview.TicketView
        android:layout_width="match_parent"
        android:layout_height="160dp"
        android:layout_marginTop="60dp"
        android:layout_marginLeft="20dp"
        android:layout_marginRight="20dp"
        android:id="@+id/ticketView"
        app:orientation="vertical"
        app:scallopRadius="10dp"
        app:showBorder="false"
        app:scallopPositionPercent="50"
        app:showDivider="true"
        app:dividerType="dash"
        app:ticketElevation="14dp"/>
```

* XML Attributes 说明。可以在代码中使用相应setter方法配置:

    <table>
    <th>Attribute Name</th>
    <th>Default Value</th>
    <th>Description</th>
    <tr>
        <td>app:orientation="vertical"</td>
        <td>horizontal</td>
        <td>设置小票分割段方向</td>
    </tr>
    <tr>
        <td>app:backgroundColor="@android:color/black"</td>
        <td>white</td>
        <td>设置背景颜色</td>
    </tr>
    <tr>
        <td>app:scallopRadius="10dp"</td>
        <td>20dp</td>
        <td>设置小票分割段的内凹圆角</td>
    </tr>
    <tr>
        <td>app:scallopPositionPercent="50"</td>
        <td>50</td>
        <td>设置小票分割段位置（由上至下、由左至右，百分比值）</td>
    </tr>
    <tr>
        <td>app:showBorder="false"</td>
        <td>false</td>
        <td>是否显示边框</td>
    </tr>
    <tr>
        <td>app:borderWidth="4dp"</td>
        <td>2dp</td>
        <td>设置边框宽度</td>
    </tr>
    <tr>
        <td>app:borderColor="@color/grey"</td>
        <td>black</td>
        <td>设置边框颜色</td>
    </tr>
    <tr>
        <td>app:showDivider="true"</td>
        <td>false</td>
        <td>是否显示分隔线</td>
    </tr>
    <tr>
        <td>app:dividerType="dash"</td>
        <td>normal</td>
        <td>设置分隔线为实线或虚线（`normal` or `dash`）</td>
    </tr>
    <tr>
        <td>app:dividerColor="@color/colorAccent"</td>
        <td>dark gray</td>
        <td>设置分隔线颜色</td>
    </tr>
    <tr>
        <td>app:dividerWidth="2dp"</td>
        <td>2dp</td>
        <td>设置分隔线宽度</td>
    </tr>
    <tr>
        <td>app:dividerDashGap="4dp"</td>
        <td>4dp</td>
        <td>设置分隔线虚线间距</td>
    </tr>
    <tr>
        <td>app:dividerDashLength="8dp"</td>
        <td>8dp</td>
        <td>设置分隔线虚线段长度</td>
    </tr>
    <tr>
        <td>app:cornerType="rounded"</td>
        <td>normal</td>
        <td>设置边角类型为无或圆角或内凹圆角（`normal` or `rounded` or `scallop`）</td>
    </tr>
    <tr>
        <td>app:cornerRadius="15dp"</td>
        <td>4dp</td>
        <td>设置边角的圆角度数</td>
    </tr>
    <tr>
        <td>app:ticketElevation="14dp"</td>
        <td>0dp</td>
        <td>设置小票阴影</td>
    </tr>
    </table>
 
## Apps that use this library

* [Open. Yoga](https://play.google.com/store/apps/details?id=com.labfoodandfriends.nikitagudkovs.jlogOpen_yoga)
* [Open. Gym](https://play.google.com/store/apps/details?id=com.labfoodandfriends.nikitagudkovs.jlog_gym)

[Apps using Ticket View, via AppBrain Stats](https://www.appbrain.com/stats/libraries/details/ticketview/ticketview)

如果你在你的应用中使用了这个库，并且想在这里列出，请通过[email](mailto:vipulasri.2007@gmail.com)告知我，[pull requests](https://github.com/vipulasri/TicketView/pulls) 或 [issues](https://github.com/vipulasri/TicketView/issues)。

## Special Thanks

[**Nick Butcher**](https://github.com/nickbutcher) for helping me out with TicketView Shadow/Elevation.


## License


    Copyright 2017 Vipul Asri

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.