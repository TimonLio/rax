/*
 * Copyright (C) 2007 The Android Open Source Project
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

package com.rax.accmeter;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class AboutActivity extends Activity {

	static final private boolean DEBUG = true;
	static final private String TAG = "RaxLog";

	public AboutActivity() {
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (DEBUG) Log.v(TAG, "AboutActivity::onCreate");
		setContentView(R.layout.about_activity);
	}

	@Override
	protected void onStart() {
		if (DEBUG) Log.v(TAG, "AboutActivity::onStart");

		try {
			PackageInfo packageInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
			((TextView) findViewById(R.id.about_version_info)).setText(String
					.format(getResources().getString(
							R.string.about_version_info),
							packageInfo.versionName, packageInfo.versionCode));
		} catch (NameNotFoundException ex) {
			Log.e(TAG, "AboutActivity::onStart " + ex.getMessage());
		}

		super.onStart();
	}

}
