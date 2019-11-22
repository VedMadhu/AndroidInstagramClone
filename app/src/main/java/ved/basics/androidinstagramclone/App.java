package ved.basics.androidinstagramclone;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("ttpta5osHLgJ77KKkVHJGjWXEoAHRNLEl45suF9D")
                // if defined
                .clientKey("N6a3GZfcbz2HkM2bdm9ZYWCZJzdSGt42gwVgIBax")
                .server("https://parseapi.back4app.com/")
                .build() );
    }
}
