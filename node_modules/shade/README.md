# shade

A file system data store using SHA1 keys

[![Build Status](https://travis-ci.org/azavea/shade.png?branch=master)](https://travis-ci.org/azavea/shade)

## example

### save

```javascript
var shade = require('shade');

shade('/tmp/mydata', function (err, db) {
    db.save('ChBZvReWjm5YcTXTMK1GZausXZaCn', function (err, key) {
        console.log(key) // ecef0937
    })
});

```

### load

```javascript
var shade = require('shade');

shade('/tmp/mydata', function (err, db) {
    db.load('ecef0937', function (err, data) {
        if (!err) {
            console.log(data); // ChBZvReWjm5YcTXTMK1GZausXZaCn
        }
    })
});

```

## methods

### shade(dir, cb)

Creates a new data store rooted in the specified directory. ``cb(err, db)`` is called with either an error or a handle to the data store.

### db.save(s, cb)

Saves a string ``s`` into the data store. ``cb(err, key)`` is called with either an error or the 8-character key that can be used to retrieve the data from the ``load`` method.

### db.load(key, cb)

Loads a string from the data store using the specified ``key``. ``cb(err, data)`` is called with either an error or the previously saved string.

## install

Using [npm](https://npmjs.org):

```
npm install shade
```

## license

MIT