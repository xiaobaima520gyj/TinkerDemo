package com.hisuchina.tinkertest.manager;

import android.content.Context;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Created by xiaobaima on 18-2-28.
 */

public class TinkerManager {

	private static boolean isIntalled = false;
	private static ApplicationLike mAppLike;

	public static void installTinker(ApplicationLike applicationLike){
		mAppLike = applicationLike;
		if (isIntalled){
			return;
		}

		TinkerInstaller.install(mAppLike);
		isIntalled = true;
	}

	public static void loadPatch(String path){
		if (Tinker.isTinkerInstalled()){
			TinkerInstaller.onReceiveUpgradePatch(getApplicationContext(), path);
		}
	}

	private static Context getApplicationContext(){
		if (mAppLike != null){
			return mAppLike.getApplication().getApplicationContext();
		}

		return null;
	}
}
