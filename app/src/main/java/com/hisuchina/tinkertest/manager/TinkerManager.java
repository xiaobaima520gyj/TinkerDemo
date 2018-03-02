package com.hisuchina.tinkertest.manager;

import android.content.Context;
import com.hisuchina.tinkertest.mode.CustomPatchListener;
import com.hisuchina.tinkertest.mode.CustomResultService;
import com.tencent.tinker.lib.patch.AbstractPatch;
import com.tencent.tinker.lib.patch.UpgradePatch;
import com.tencent.tinker.lib.reporter.DefaultLoadReporter;
import com.tencent.tinker.lib.reporter.DefaultPatchReporter;
import com.tencent.tinker.lib.reporter.LoadReporter;
import com.tencent.tinker.lib.reporter.PatchReporter;
import com.tencent.tinker.lib.tinker.Tinker;
import com.tencent.tinker.lib.tinker.TinkerInstaller;
import com.tencent.tinker.loader.app.ApplicationLike;

/**
 * Created by xiaobaima on 18-2-28.
 */

public class TinkerManager {

	private static boolean isIntalled = false;
	private static ApplicationLike mAppLike;
	private static CustomPatchListener mCustomPatchListener;

	public static void installTinker(ApplicationLike applicationLike){
		mAppLike = applicationLike;
		if (isIntalled){
			return;
		}

		mCustomPatchListener = new CustomPatchListener(getApplicationContext());

		LoadReporter loadReporter = new DefaultLoadReporter(getApplicationContext());
		PatchReporter patchReporter = new DefaultPatchReporter(getApplicationContext());

		AbstractPatch upgradePatchProcessor = new UpgradePatch();
		//完成Tinker初始化
		TinkerInstaller.install(applicationLike, loadReporter, patchReporter,
			mCustomPatchListener, CustomResultService.class, upgradePatchProcessor);
		isIntalled = true;
	}

	public static void loadPatch(String path,String md5){
		if (Tinker.isTinkerInstalled()){
			mCustomPatchListener.setCurrMd5(md5);
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
