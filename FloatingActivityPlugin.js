/**
 * Cordava Plugin to Launch Services, Activity
 *
 * @author Abraham.K <abrahamrkj@gmail.com>
 * @website abrahamk.in
 * @license MIT/X11
 */
var FloatingActivityPlugin = function() { };

FloatingActivityPlugin.prototype.launchplugin = function(options, success, error) {
 return cordova.exec(
    success,
    error,
    "FloatingActivityPlugin",
    "FloatingActivityPlugin",
    [options.activity]
  );
};

window.FloatingActivityPlugin = new FloatingActivityPlugin();