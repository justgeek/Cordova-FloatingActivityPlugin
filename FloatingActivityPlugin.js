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