package com.hisuchina.tinkertest.mode;

import android.content.Context;
import android.util.Log;
import com.hisuchina.tinkertest.util.Utils;
import com.tencent.tinker.lib.listener.DefaultPatchListener;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 *检验patch文件是否合法
 * @author xiaobaima
 * @date 18-3-2
 */

public class CustomPatchListener extends DefaultPatchListener{

	private static final String TAG = CustomPatchListener.class.getSimpleName();
	private String currMd5;

	public void setCurrMd5(String currMd5) {
		this.currMd5 = currMd5;
	}

	public CustomPatchListener(Context context) {
		super(context);
	}

	@Override
	protected int patchCheck(String path, String patchMd5) {
		if (!Utils.isFileMD5Matched(path, currMd5)){
			Log.i(TAG, "ShareConstants.ERROR_PATCH_DISABLE");
			return ShareConstants.ERROR_PATCH_DISABLE;
		}

		Log.i(TAG, "===patchCheck===");
		return super.patchCheck(path, patchMd5);
	}
}
