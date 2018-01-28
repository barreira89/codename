'use strict';

var rest = require('rest');
var defaultRequest = require('rest/interceptor/defaultRequest');
var mime = require('rest/interceptor/mime');
//var uriTemplateInterceptor = require('./api/uriTemplateInterceptor');
var errorCode = require('rest/interceptor/errorCode');
var baseRegistry = require('rest/mime/registry');
var interceptor = require('rest/interceptor');
var UrlPattern = require('url-pattern')
var gameMapper = require('./gameMapper.js')

var registry = baseRegistry.child();

//registry.register('text/uri-list', require('./api/uriListConverter'));
//registry.register('application/hal+json', require('rest/mime/type/application/hal'));
registry.register('application/json', require('rest/mime/type/application/json'));

module.exports = rest
		.wrap(mime, { registry: registry })
		//.wrap(uriTemplateInterceptor)
        .wrap(gameMapper)
		.wrap(errorCode)