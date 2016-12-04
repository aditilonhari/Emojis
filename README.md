# Project - Emojis

Emojis App is an android app that allows a user to view all the emojis made available for Github Api. The app utilizes [Github Emoji API](https://api.github.com/emojis).

## User Stories

The following functionality is included:

* User can scroll along all the emojis available with the Github Api.
* User can click on a particular emoji to view its details like emoji title and image
* Android Support library - for Snackbar support
  
## Open-source libraries used

* RecyclerView with the GridLayoutManager  - To display improve the grid of image results
* Android Async HTTP - Simple asynchronous HTTP requests for parsing JSON data from the server
* Picasso - Easy Image loading and caching library for Android

## Video Walkthrough

Here's a walkthrough of implemented user stories:

<img src='https://github.com/aditilonhari/MySimpleTweets/blob/fe96e3c63f16cf78c7c86a2d419c6d482a85d8f3/simpleTweetsApp.gif' title='Video Walkthrough' width='' alt='Video Walkthrough' />

GIF created with [LiceCap](http://www.cockos.com/licecap/).

## Notes

Describe any challenges encountered while building the app.

- Tried stretching the emojis to full-screen on the details page, but it would need a high quality larger image for that. 
- Setting actionbar title color required more complicated code changes, instead using a custom toolbar would have been nicer.

## License

    Copyright 2016 Aditi Lonhari

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
