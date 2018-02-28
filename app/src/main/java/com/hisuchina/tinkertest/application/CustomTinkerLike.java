package com.hisuchina.tinkertest.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.support.multidex.MultiDex;
import com.hisuchina.tinkertest.manager.TinkerManager;
import com.tencent.tinker.anno.DefaultLifeCycle;
import com.tencent.tinker.loader.app.ApplicationLike;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * Created by xiaobaima on 18-2-28.
 */

@DefaultLifeCycle(application = ".MyTinkerApplication",
flags = ShareConstants.TINKER_ENABLE_ALL, loadVerifyFlag = false)
public class CustomTinkerLike extends ApplicationLike{
	public CustomTinkerLike(Application application, int tinkerFlags,
		boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
		long applicationStartMillisTime, Intent tinkerResultIntent) {
		super(application, tinkerFlags, tinkerLoadVerifyFlag,
			applicationStartElapsedTime, applicationStartMillisTime,
			tinkerResultIntent);
	}

	@Override
	public void onBaseContextAttached(Context base) {
		super.onBaseContextAttached(base);
		//使应用支持分包
		MultiDex.install(base);
		//安装tinker
		TinkerManager.installTinker(this);
	}
}
