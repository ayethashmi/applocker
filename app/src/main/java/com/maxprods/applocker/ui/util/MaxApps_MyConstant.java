package com.maxprods.applocker.ui.util;

import android.os.Environment;

import com.maxprods.applocker.ui.model.MaxApps_AppList;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MaxApps_MyConstant {

    public static String DOWNLOAD_PATH = Environment.getExternalStorageDirectory().getAbsolutePath()+ "/Download/MyIntruderSelfies/";

    public static boolean firsttime=false;
    public static final String SCREEN_TAG = "screentag";
    public static final String FROM_MYAPP = "myApplication";
    public static final String FINGER_Print_PREF = "fingerpref";
    public static final String FINGER_PRINT = "fingerprint";
    public static final String ROOT_FOLDER = "AppLocker";
    public static final String SUBDIRECTORY_FOLDER = ".Locked";
    public static final String IMAGES_PATH = ROOT_FOLDER + File.separator + SUBDIRECTORY_FOLDER;
    public static final String VIDEOS_PATH = ROOT_FOLDER + File.separator + SUBDIRECTORY_FOLDER;
    public static final String AUDIO_PATH = ROOT_FOLDER + File.separator + SUBDIRECTORY_FOLDER;
    public static final String FILE_PATH = ROOT_FOLDER + File.separator + SUBDIRECTORY_FOLDER;
    public static final String SUBSCRIBE = "SubscriptionKey";
     public static String PREF="pref";
    public static String ONE_RATING="MYRATE";
    public static String RATING="MYRATE";
    public static String finger_once="onece";
    public static final String NAVIGATION_ROOT = "NavigationRoot";
    public static final String UNLOCK_SETTING = "UnlockSetting";
    public static final String TINY_DB = "MyDb";

    public static final String LOCKED = "Locked";

    public static final String YOUTUBE = "com.google.android.youtube";
    public static final String CHROME = "com.android.chrome";
    public static final String PLAY_MOVIE = "com.google.android.videos";
    public static final String PLAY_MUSIC = "com.google.android.music";

    public static final String BG_THEME = "BgTheme";


    public static final String PASSWORD_MODE = "Password Mode";
    public static final String PIN = "Pin";
    public static final String PATTERN = "Pattern";

    public static final String MyPREF = "MyPreference";
    public static final String MYPLAN="myplan";


    public static final String PATTERN_PASSWORD = "Pattern Password";
    public static final String PIN_PASSWORD = "Pin Password";

    public static final String UNLOCK_APP = "Unlocked App";

    public static final String STATUS = "Status";


    public static final String SECURITY_QUESTION = "Security Question";
    public static final String SECURITY_ANSWER = "Security Answer";

    public static final String SECURITY_EMAIL = "Security Email";
    public static final String UNLOCK_FEATURES = "Unlock Feature";

    public static final String PASSWORD_VISIBLE = "Password Visible";
    public static final String PRIVACY = "PasswordVisible";
    public static final String PATTERN_VIBRATE = "PatterVibrate";

    public static final String PATTERN_HIDE_KEYBOARD = "Patter Vibrate";


    public static final String PIN_HIDE_KEYBOARD = "Pint hide keyboard";
    public static final String PIN_VIBRATE = "Pin Vibrate";
    public static final String PIN_HINT = "Pin hint";


    public static final String ONE_MONTH = "one_month";
    public static final String QUARTERLY = "one_week";
    public static final String YEARLY = "one_year";
    public static final String THEME = "Theme";



    public static final int VERSION_CODE = 5;
    public static final String SHUFFLE = "Shuffle";
    public static final String INTRUDER = "Intruder";
    public static final String INTRUDER_PATTERN = "IntruderPattern";


    public static final String VAULT = "vault";
    public static final String PHOTO = "photo";
    public static final String VIDEO = "video";
    public static final String AUDIO = "audio";
    public static final String FILE = "file";

    public static final String SECURED = "secured";
    public static final String UNSECURED = "unsecured";


    public static final String APP_SECURITY = "AppSecurity";


    public static final String REQUEST_MODE = "RequestMode";
    public static final String DEFAULT_REQUEST = "DefaultRequestMode";
    public static final String PIN_REQUEST = "PinRequestMode";
    public static final String PATTERN_REQUEST = "PatternRequestMode";

    public static final String SELFIE_PATH_WITH="/Pictures/AppLockSelfies/";
    public static final String SELFIE_PATH="/Pictures/AppLockSelfies";
    public static ArrayList<MaxApps_AppList> appsList =new ArrayList<>();
    public static List<MaxApps_AppList> mymainapps =new ArrayList<>();
    public static boolean enable=false;

}

