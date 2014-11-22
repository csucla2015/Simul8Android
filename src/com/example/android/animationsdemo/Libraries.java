/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.animationsdemo;

import android.content.Context;
import android.content.SharedPreferences;

public class Libraries {
	Context applicationContext = ListViewDraggingAnimation.getContextOfApplication();

	SharedPreferences settings1 = applicationContext.getSharedPreferences("com.example.android.animationsdemo", 0);
	String a = settings1.getString(String.valueOf(1), "yolo");

    public static final String[] sCheeseStrings = {
            "Powell Library", "Young Research Library",
            "Science and Engineering Library", "Music Library", "Management Library", "Arts Library", "Law Library",
            "Biomedical Library"
    };

}
