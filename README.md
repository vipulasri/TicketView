# TicketView

An Android Library used to implement TicketView in android with normal, rounded and scallop corners.

### Specs
[![Download](https://api.bintray.com/packages/vipulasri/maven/TicketView/images/download.svg)](https://bintray.com/vipulasri/maven/TicketView/_latestVersion)
[![MethodsCount](https://img.shields.io/badge/Methods%20and%20size-98%20|%2010KB-e91e63.svg)](http://www.methodscount.com/?lib=com.vipulasri%3Aticketview%3A1.0.2)
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/vipulasri/Timeline-View/blob/master/LICENSE)

### Badges/Featured In
[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-Ticket%20View-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/6521)
[![AndroidDev Digest](https://img.shields.io/badge/AndroidDev%20Digest-%23171-blue.svg)](https://www.androiddevdigest.com/digest-171/) 

![showcase](https://github.com/vipulasri/TicketView/blob/master/art/showcase.png)

## Sample Project

For information : checkout [Sample App Code](https://github.com/vipulasri/TicketView/tree/master/sample) in repository.

### Download

[![TicketView on Google Play](https://github.com/vipulasri/TicketView/blob/master/art/google_play.png)](https://play.google.com/store/apps/details?id=com.vipulasri.ticketview.sample)

## Quick Setup

### 1. Include library

**Using Gradle**

``` gradle
dependencies {
    compile 'com.vipulasri:ticketview:1.0.2'
}
```

**Using Maven**

``` maven
<dependency>
    <groupId>com.vipulasri</groupId>
    <artifactId>ticketview</artifactId>
    <version>1.0.2</version>
    <type>pom</type>
</dependency>
```

### What's New

See the project's Releases page for a list of versions with their change logs.

### [View Releases](https://github.com/vipulasri/TicketView/releases)

If you Watch this repository, GitHub will send you an email every time I publish an update.

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
        app:dividerType="dash"/>
```

* Configure using xml attributes or setters in code:

    <table>
    <th>Attribute Name</th>
    <th>Default Value</th>
    <th>Description</th>
    <tr>
        <td>app:orientation="vertical"</td>
        <td>horizontal</td>
        <td>sets orientation of divider and scallop</td>
    </tr>
    <tr>
        <td>app:backgroundColor="@android:color/black"</td>
        <td>white</td>
        <td>sets background color</td>
    </tr>
    <tr>
        <td>app:scallopRadius="10dp"</td>
        <td>20dp</td>
        <td>sets scallop radius</td>
    </tr>
    <tr>
        <td>app:scallopPositionPercent="50"</td>
        <td>50</td>
        <td>sets position of scallop and divider</td>
    </tr>
    <tr>
        <td>app:showBorder="false"</td>
        <td>false</td>
        <td>shows border if `true`</td>
    </tr>
    <tr>
        <td>app:borderWidth="4dp"</td>
        <td>2dp</td>
        <td>sets border width</td>
    </tr>
    <tr>
        <td>app:borderColor="@color/grey"</td>
        <td>black</td>
        <td>sets border color</td>
    </tr>
    <tr>
        <td>app:showDivider="true"</td>
        <td>false</td>
        <td>shows divider if `true`</td>
    </tr>
    <tr>
        <td>app:dividerType="dash"</td>
        <td>normal</td>
        <td>sets type of divider ie `normal` or `dash`</td>
    </tr>
    <tr>
        <td>app:dividerColor="@color/colorAccent"</td>
        <td>dark gray</td>
        <td>sets divider color</td>
    </tr>
    <tr>
        <td>app:dividerWidth="2dp"</td>
        <td>2dp</td>
        <td>sets divider width</td>
    </tr>
    <tr>
        <td>app:dividerDashGap="4dp"</td>
        <td>4dp</td>
        <td>sets divider dash gap</td>
    </tr>
    <tr>
        <td>app:dividerDashLength="8dp"</td>
        <td>8dp</td>
        <td>sets divider dash length</td>
    </tr>
    <tr>
        <td>app:cornerType="rounded"</td>
        <td>normal</td>
        <td>sets type of corner ie `normal` or `rounded` or `scallop`</td>
    </tr>
    <tr>
        <td>app:cornerRadius="15dp"</td>
        <td>4dp</td>
        <td>sets corner radius if corner rounder or scallop</td>
    </tr>
    </table>
 
## Apps that use this library

If you're using this library in your app and you'd like to list it here,
Please let me know via [email](mailto:vipulasri.2007@gmail.com), [pull requests](https://github.com/vipulasri/TicketView/pulls) or [issues](https://github.com/vipulasri/TicketView/issues).


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
