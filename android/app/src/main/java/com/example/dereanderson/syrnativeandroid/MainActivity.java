package syrnativeandroid;

import android.app.Activity;

import android.app.Dialog;

// XXX TBD ???:
// import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import syr.js.org.syrnative.SyrAlertDialogue;
import syr.js.org.syrnative.SyrAnimatedImage;
import syr.js.org.syrnative.SyrAnimatedText;
import syr.js.org.syrnative.SyrAnimatedView;
import syr.js.org.syrnative.SyrBaseModule;
import syr.js.org.syrnative.SyrBundle;
import syr.js.org.syrnative.SyrBundleManager;
import syr.js.org.syrnative.SyrButton;
import syr.js.org.syrnative.SyrImage;
import syr.js.org.syrnative.SyrInstance;
import syr.js.org.syrnative.SyrInstanceManager;
import syr.js.org.syrnative.SyrLinearGradient;
import syr.js.org.syrnative.SyrNetworking;
import syr.js.org.syrnative.SyrRootView;
import syr.js.org.syrnative.SyrScrollview;
import syr.js.org.syrnative.SyrStackview;
import syr.js.org.syrnative.SyrText;
import syr.js.org.syrnative.SyrTouchableOpacity;
import syr.js.org.syrnative.SyrView;


public class MainActivity /* extends AppCompatActivity */ {

    /* ** XXX GONE:
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    // */


    public static void start(Activity myActivity) {
        RelativeLayout layout = new RelativeLayout(myActivity);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layout.setLayoutParams(layoutParams);

        /* ** TBD evidently not needed in Cordova plugin:
        // hide action bar aka title bar
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        // */

        // register NativeModules
        List<SyrBaseModule> modules = new ArrayList<>();

        // register NativeModules
        modules.add(new SyrView());
        modules.add(new SyrText());
        modules.add(new SyrButton());
        modules.add(new SyrImage());
        modules.add(new SyrTouchableOpacity());
        modules.add(new SyrLinearGradient());
        modules.add(new SyrScrollview());
        modules.add(new SyrStackview());
        modules.add(new SyrAnimatedImage());
        modules.add(new SyrAnimatedView());
        modules.add(new SyrAnimatedText());
        modules.add(new SyrNetworking());
        modules.add(new SyrAlertDialogue());

        // get the javascript bundle
        SyrBundle bundle = new SyrBundleManager().setBundleAssetName("file:///android_asset/www/native-index.html").build();

        // create an instance of Syr
        SyrInstance instance = new SyrInstanceManager().setJSBundleFile(bundle).build();

        // expose the desired native modules to the instance
        instance.setNativeModules(modules);

        // create a new Rootview
        // SyrRootView rootview = new SyrRootView(this);
        SyrRootView rootview = new SyrRootView(myActivity);

        JSONObject appProps = new JSONObject();

        // start the Syr Application
        rootview.startSyrApplication(instance, bundle, appProps);

        rootview.setLayoutParams(layoutParams);
        layout.addView(rootview);

        // Show the content of the Rootview

        // setContentView(layout);

        // THANKS FOR GUIDANCE:
        // https://stackoverflow.com/questions/6329360/how-to-set-dialog-to-show-with-full-screen/7597173#7597173
        Dialog d = new Dialog(myActivity, android.R.style.Theme_Black_NoTitleBar_Fullscreen);

        d.setContentView(layout);

        d.show();
  }
}
