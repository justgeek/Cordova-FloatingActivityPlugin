Cordova-FloatingActivityPlugin
==============================

Android - Launch Floating Activity from Cordova JS [ Supported Phonegap &lt;3.0.0 ]

## License

MIT/X11

## Usage

### Android

1. Create `src/com/phonegap/plugins/floatingactivityplugin` directory.
2. Copy `FloatingActivityPlugin.java` to `src/com/phonegap/plugins/floatingactivityplugin` directory.
3. Add `FloatingActivityPlugin.js` to your web application.
4. Add

        <plugin name="FloatingActivityPlugin" value="com.phonegap.plugins.floatingactivityplugin.FloatingActivityPlugin"/>

   to your `res/xml/config.xml` inside `<plugins></plugins>`.
5. Use

        window.FloatingActivityPlugin.launchplugin(
			{"activity":     "ChatHeadService"},
			success, failed
		);

   whenever required.
