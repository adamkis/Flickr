# Flickr Demo App
An Android demo app for a Flickr client

<p align="center">
  <img src="https://github.com/adamkis/Flickr/blob/prototyping/extraFiles/recents.jpg" width="250">
  <img src="https://github.com/adamkis/Flickr/blob/prototyping/extraFiles/photo_detail.jpg" width="250">
  <img src="https://github.com/adamkis/Flickr/blob/prototyping/extraFiles/photo_detail_collapsed.jpg" width="250">
</p>

## Libraries used:
- Retrofit2
- Dagger2
- RxJava
- RxAndroid
- Glide
- Espresso
- MockWebServer

### Setup networking with your Flickr API Key:
- [Get your API Key](https://www.flickr.com/services/apps/create/apply/)
- Create ```SecretKeys.kt```  in  ```/app/src/main/java/com/adamkis/flickr/helper/```
- Add the code below:
```
package com.adamkis.flickr.helper

object SecretKeys {
    val FLICKR_KEY: String = "YOUR SECRET KEY"
}
```

### APK:
- [DOWNLOAD](https://github.com/adamkis/Flickr/blob/prototyping/extraFiles/flickr-app-debug.apk)

### License
```
   Copyright (C) 2017 Adam Kis

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```

### Contributing to Flickr Demo App
Just make pull request.
