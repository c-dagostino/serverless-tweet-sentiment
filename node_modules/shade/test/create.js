var fs = require('fs');
var path = require('path');
var test = require('./helpers/tempdirtest');
var shade = require('../index.js');

test('creates a directory', function (t, tempdir) {
    t.plan(2);
    var dbdir = path.join(tempdir, 'deep', 'shade');
    shade(dbdir, function (err, db) {
        t.error(err, 'shade created without an error');
        fs.exists(dbdir, function (exists) {
            t.ok(exists, 'shade directory exists');
        });
    });
});

test('returns a db with a path property', function (t, tempdir) {
    t.plan(2);
    shade(tempdir, function(err, db) {
        t.ok(db, 'truthy db passed to callback');
        t.equals(db.path, tempdir, 'db.path property matches argument');
    });
});
