package aragon.com.assembleapkdemo;

import android.app.Activity;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import aragon.com.library.util.StringUtil;


public class MainActivity extends Activity {
    private static final String TAG = "MainActivity";
    private TextView mResultDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mResultDisplay = (TextView) findViewById(R.id.result_display);

        View.OnClickListener ocl = new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switch (view.getId()) {
                    case R.id.get_channel: {
                        //获取渠道号
                        mResultDisplay.setText(readMetaDataFromApplication());
                        break;
                    }

                    case R.id.get_library_method: {
                        //获取依赖的library方法
                        mResultDisplay.setText(StringUtil.getLibraryPkgName());
                        break;
                    }

                    case R.id.get_market: {
                        //个性化定制举例
                        mResultDisplay.setText(getString(R.string.app_market));
                        break;
                    }

                    case R.id.config: {
                        //BuildConfig
                        mResultDisplay.setText("" + BuildConfig.ISDEBUG);
                        break;
                    }
                    case R.id.get_file_content: {
                        //获取文件内容
                        mResultDisplay.setText("" + getString(R.string.app_key));
                        break;
                    }
                }
            }
        };

        findViewById(R.id.get_channel).setOnClickListener(ocl);
        findViewById(R.id.get_library_method).setOnClickListener(ocl);
        findViewById(R.id.get_market).setOnClickListener(ocl);
        findViewById(R.id.config).setOnClickListener(ocl);
        findViewById(R.id.get_file_content).setOnClickListener(ocl);

    }

    private String readMetaDataFromApplication() {
        String channel = "";

        try {
            ApplicationInfo appInfo = this.getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            channel = appInfo.metaData.getString("MY_CHANNEL");
            //Log.e(TAG, "channel = " + channel);

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            channel = "";
        }

        return channel;
    }
}
