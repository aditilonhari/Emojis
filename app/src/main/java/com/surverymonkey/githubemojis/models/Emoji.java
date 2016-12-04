package com.surverymonkey.githubemojis.models;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by aditi on 12/2/2016.
 */

public class Emoji {
    private String mName;
    private String mThumbnailImage;

    public Emoji(String name, String image){
        mName = name;
        mThumbnailImage = image;
    }

    public Emoji(JSONObject jsonObject, List<Emoji> emojisList){
        Iterator<String> iter = jsonObject.keys();
        while(iter.hasNext()){
            mName = iter.next();
            try {
                mThumbnailImage = String.valueOf(jsonObject.get(mName));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            Emoji newEmoji = new Emoji(mName, mThumbnailImage);
            emojisList.add(newEmoji);
        }
    }

    public static List<Emoji> getAllEmojis(JSONObject object){
        List<Emoji> allEmojis = new ArrayList<>();
        new Emoji(object, allEmojis);
        return allEmojis;
    }

    public String getmName() {
        return mName;
    }

    public String getmThumbnailImage() {
        return mThumbnailImage;
    }
}
