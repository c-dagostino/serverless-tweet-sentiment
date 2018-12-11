var test = require('./helpers/tempdirtest');
var shade = require('../index.js');

test('can retrieve a saved value', function (t, tempdir) {
    t.plan(2);
    shade(tempdir, function(err, db) {
        var content = 'a string';
        db.save(content, function(err, key){
            db.load(key, function(err, data) {
                t.error(err);
                t.equal(content, data);
            });
        });
    });
});

test('returns an error when loading an unknown key', function (t, tempdir) {
    t.plan(1);
    shade(tempdir, function(err, db) {
        db.load('ajunkkey', function(err, data) {
            t.equal(err, 'unknown key');
        });
    });
});