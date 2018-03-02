package com.hisuchina.tinkertest.mode;

import com.tencent.tinker.lib.service.DefaultTinkerResultService;
import com.tencent.tinker.lib.service.PatchResult;
import com.tencent.tinker.lib.util.TinkerLog;
import com.tencent.tinker.lib.util.TinkerServiceInternals;

import java.io.File;

/**
 *
 * @author xiaobaima
 * @date 18-3-2
 */

public class CustomResultService extends DefaultTinkerResultService{

	private static final String TAG = CustomResultService.class.getSimpleName();
	/**
	 * 返回patch文件的安装结果
	 * @param result
	 */
	@Override
	public void onPatchResult(PatchResult result) {
		if (result == null) {
			TinkerLog.e(TAG, "DefaultTinkerResultService received null result!!!!");
			return;
		}
		TinkerLog.i(TAG, "DefaultTinkerResultService received a result:%s ", result.toString());

		//first, we want to kill the recover process
		TinkerServiceInternals.killTinkerPatchServiceProcess(getApplicationContext());

		// if success and newPatch, it is nice to delete the raw file, and restart at once
		// only main process can load an upgrade patch!
		if (result.isSuccess) {
			deleteRawPatchFile(new File(result.rawPatchFilePath));
			if (checkIfNeedKill(result)) {
				TinkerLog.i(TAG, "patch file install success");
			} else {
				TinkerLog.i(TAG, "I have already install the newly patch version!");
			}
		}
	}
}
