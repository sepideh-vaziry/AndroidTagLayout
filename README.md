# Android TagLayout

[![](https://jitpack.io/v/sepideh-vaziry/AndroidTagLayout.svg)](https://jitpack.io/#sepideh-vaziry/AndroidTagLayout)

The TagLayout is a layout with a set of tags. You can use it to tag anything you want.

# Screenshot

![alt text](https://github.com/sepideh-vaziry/AndroidTagLayout/blob/master/images/tag_demo.png?raw=true)


# Installation

## Step 1
Add it in your root build.gradle at the end of repositories:

```groovy
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

## Step 1
Add the dependency

```groovy
dependencies {
	implementation 'com.github.sepideh-vaziry:AndroidTagLayout:v1.0.1'
}
```

# Usage
Use it in your own code:
```xml
<com.seva.taglib.TagLayout
    android:id="@+id/tagLayout"
    android:layout_width="match_parent"
    android:layout_height="wrap_content"
    app:tl_background_color="#ffffff"
    app:tl_background_border_color="#4AA46F"
    app:tl_background_border_width="2dp"
    app:tl_background_corner_radius="40dp"
    app:tl_horizontal_padding="12dp"
    app:tl_vertical_padding="8dp"
    app:tl_tag_margin="8dp"/>
```

```kotlin
tagLayout.addTag("Tag")
tagLayout.addTags(tagsList)
```

# Attributes

|           attr        	  |                         mean                          	 |
|:-------------------------   |:------------------------------------------------------- |
| android:textSize      	  | The tag text size.                                      |
| android:textColor           | The tag text color.                                     |
| android:fontFamily          | The tag font color.                                     |
| tl_background_color     	  | The tag background color.                   	         |
| tl_background_border_color  | The tag border color.                                   |
| tl_background_border_width  | The tag border stroke width.                            |
| tl_background_corner_radius | The tag corner radius.                                  |
| tl_horizontal_padding       | The horizontal tag padding.                             |
| tl_vertical_padding         | The vertical tag padding.                               |
| tl_tag_margin               | The tag spacing.                                        |
