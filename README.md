# TicketView

An Android Library used to implement TicketView in android with normal, rounded and scallop corners.

### Specs
[![API](https://img.shields.io/badge/API-15%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=15) 
[![Maven Central](https://img.shields.io/maven-central/v/com.vipulasri/ticketview.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.vipulasri%22%20AND%20a:%22ticketview%22) 
[![License](https://img.shields.io/badge/license-Apache%202.0-blue.svg)](https://github.com/vipulasri/Timeline-View/blob/master/LICENSE)


### Badges/Featured In
[![TicketView](https://www.appbrain.com/stats/libraries/shield/ticketview.svg)](https://www.appbrain.com/stats/libraries/details/ticketview/ticketview)
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
    implementation 'com.vipulasri:ticketview:1.1.2'
}
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
        app:ticketOrientation="vertical"
        app:ticketScallopRadius="10dp"
        app:ticketShowBorder="false"
        app:ticketScallopPositionPercent="50"
        app:ticketShowDivider="true"
        app:ticketDividerType="dash"
        app:ticketDividerPadding="0dp"
        app:ticketElevation="14dp"/>
```

* Configure using xml attributes or setters in code:

    <table>
    <th>Attribute Name</th>
    <th>Default Value</th>
    <th>Description</th>
    <tr>
        <td>app:ticketOrientation="vertical"</td>
        <td>horizontal</td>
        <td>sets orientation of divider and scallop</td>
    </tr>
    <tr>
        <td>app:ticketBackgroundColor="@android:color/black"</td>
        <td>white</td>
        <td>sets background color</td>
    </tr>
    <tr>
        <td>app:ticketScallopRadius="10dp"</td>
        <td>20dp</td>
        <td>sets scallop radius</td>
    </tr>
    <tr>
        <td>app:ticketScallopPositionPercent="50"</td>
        <td>50</td>
        <td>sets position of scallop and divider</td>
    </tr>
    <tr>
        <td>app:ticketShowBorder="false"</td>
        <td>false</td>
        <td>shows border if `true`</td>
    </tr>
    <tr>
        <td>app:ticketBorderWidth="4dp"</td>
        <td>2dp</td>
        <td>sets border width</td>
    </tr>
    <tr>
        <td>app:ticketBorderColor="@color/grey"</td>
        <td>black</td>
        <td>sets border color</td>
    </tr>
    <tr>
        <td>app:ticketShowDivider="true"</td>
        <td>false</td>
        <td>shows divider if `true`</td>
    </tr>
    <tr>
        <td>app:ticketDividerType="dash"</td>
        <td>normal</td>
        <td>sets type of divider ie `normal` or `dash`</td>
    </tr>
    <tr>
        <td>app:ticketDividerColor="@color/colorAccent"</td>
        <td>dark gray</td>
        <td>sets divider color</td>
    </tr>
    <tr>
        <td>app:ticketDividerWidth="2dp"</td>
        <td>2dp</td>
        <td>sets divider width</td>
    </tr>
    <tr>
        <td>app:ticketDividerPadding="0dp"</td>
        <td>10dp</td>
        <td>sets divider padding</td>
    </tr>
    <tr>
        <td>app:ticketDividerDashGap="4dp"</td>
        <td>4dp</td>
        <td>sets divider dash gap</td>
    </tr>
    <tr>
        <td>app:ticketDividerDashLength="8dp"</td>
        <td>8dp</td>
        <td>sets divider dash length</td>
    </tr>
    <tr>
        <td>app:ticketCornerType="rounded"</td>
        <td>normal</td>
        <td>sets type of corner ie `normal` or `rounded` or `scallop`</td>
    </tr>
    <tr>
        <td>app:ticketCornerRadius="15dp"</td>
        <td>4dp</td>
        <td>sets corner radius if corner rounder or scallop</td>
    </tr>
    <tr>
        <td>app:ticketElevation="14dp"</td>
        <td>0dp</td>
        <td>sets elevation to ticket view on android jellybean and above</td>
    </tr>
    <tr>
       <td>app:ticketBackgroundBeforeDivider</td>
       <td>none</td>
       <td>sets background to ticket view before divider</td>
    </tr>
    <tr>
       <td>app:ticketBackgroundAfterDivider</td>
       <td>none</td>
       <td>sets background to ticket view after divider</td>
    </tr>
    <tr>
       <td>app:ticketShadowColor</td>
       <td>black</td>
       <td>sets shadow to ticket view</td>
    </tr>
    </table>
 
## Apps that use this library

* [Open. Yoga](https://play.google.com/store/apps/details?id=com.labfoodandfriends.nikitagudkovs.jlogOpen_yoga)
* [Open. Gym](https://play.google.com/store/apps/details?id=com.labfoodandfriends.nikitagudkovs.jlog_gym)

[Apps using Ticket View, via AppBrain Stats](https://www.appbrain.com/stats/libraries/details/ticketview/ticketview)

If you're using this library in your app and you'd like to list it here,
Please let me know via [email](mailto:me@vipulasri.com), [pull requests](https://github.com/vipulasri/TicketView/pulls) or [issues](https://github.com/vipulasri/TicketView/issues).

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
