var fs = require('fs');
var path = require('path');
var crypto = require('crypto');
var mkdirp = require('mkdirp');

var Store = function(path) {
    this.path = path;
};

Store.prototype._createPaths = function(key) {
    var keyDir = path.join(
        this.path,
        key.substring(0,2),
        key.substring(2,4),
        key.substring(4,6),
        key.substring(6,8)
    );
    return {
        keyDir: keyDir,
        keyPath: path.join(keyDir, key)
    };
};

Store.prototype.save = function(thing, cb) {
    var sha1, key, paths;
    try {
        sha1 = crypto.createHash('sha1');
        sha1.update(thing);
        key = sha1.digest('hex').substring(0,8);
        paths = this._createPaths(key);
        mkdirp(paths.keyDir, function(err) {
            if (!err) {
                fs.writeFile(paths.keyPath, thing, function(err) {
                    if (cb) { cb(err, key); }
                });
            } else {
                if (cb) { cb(err, undefined); }
            }
        });
    } catch(err) {
        if (cb) { cb(err, undefined); }
    }
};

Store.prototype.load = function(key, cb) {
    var paths = this._createPaths(key);
    if (!cb) { return; }
    fs.exists(paths.keyPath, function (exists) {
        if (exists) {
            fs.readFile(paths.keyPath, function (err, data) {
                if (!err) {
                    cb(null, data.toString());
                } else {
                    cb(err, undefined);
                }
            });
        } else {
            cb('unknown key', undefined);
        }
    });
};

exports = module.exports = function(p, cb) {
    if (!cb) { return; }
    mkdirp(p, function(err) {
        cb(err, new Store(p));
    });
};